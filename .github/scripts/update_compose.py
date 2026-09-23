import datetime
import email.utils
import json
import os
import re
import sys
import urllib.request
from pathlib import Path

MAVEN = "https://repo1.maven.org/maven2"
PLUGIN = "org/jetbrains/compose/compose-gradle-plugin"
MATERIAL3 = "org/jetbrains/compose/material3/material3"
CHANGELOG = "https://raw.githubusercontent.com/JetBrains/compose-multiplatform/master/CHANGELOG.md"
CATALOG = Path("gradle/libs.versions.toml")
COMPOSE_KEY = "composeMultiplatform"
MATERIAL3_KEY = "material3"
ADAPTIVE_KEY = "compose-multiplatform-adaptive"
LOCKSTEP_GROUPS = {
    "org.jetbrains.compose.animation",
    "org.jetbrains.compose.components",
    "org.jetbrains.compose.foundation",
    "org.jetbrains.compose.material",
    "org.jetbrains.compose.runtime",
    "org.jetbrains.compose.ui",
}
CHANGELOG_GRACE = datetime.timedelta(days=3)
VERSION = re.compile(r"(\d+)\.(\d+)\.(\d+)(?:-(alpha|beta|rc)(\d+))?")
STAGES = {"alpha": 0, "beta": 1, "rc": 2, None: 3}


def order(version):
    match = VERSION.fullmatch(version)
    if match is None:
        return None
    major, minor, patch, stage, number = match.groups()
    return int(major), int(minor), int(patch), STAGES[stage], int(number or 0)


def series(version):
    return order(version)[:2]


def fetch(url):
    with urllib.request.urlopen(url, timeout=60) as response:
        return response.read().decode()


def published_versions(artifact):
    metadata = fetch(f"{MAVEN}/{artifact}/maven-metadata.xml")
    return [
        version
        for version in re.findall(r"<version>([^<]+)</version>", metadata)
        if order(version) is not None
    ]


def published_at(compose):
    url = f"{MAVEN}/{PLUGIN}/{compose}/compose-gradle-plugin-{compose}.pom"
    request = urllib.request.Request(url, method="HEAD")
    with urllib.request.urlopen(request, timeout=60) as response:
        return email.utils.parsedate_to_datetime(response.headers["Last-Modified"])


def latest_stable_compose():
    stable = [version for version in published_versions(PLUGIN) if order(version)[3] == STAGES[None]]
    return max(stable, key=order)


def compose_requirement(material3):
    module = json.loads(fetch(f"{MAVEN}/{MATERIAL3}/{material3}/material3-{material3}.module"))
    required = [
        dependency["version"].get("requires") or dependency["version"].get("strictly")
        for variant in module["variants"]
        for dependency in variant.get("dependencies", [])
        if dependency["group"] in LOCKSTEP_GROUPS and "version" in dependency
    ]
    comparable = [version for version in required if version and order(version) is not None]
    return max(comparable, key=order, default=None)


def select_material3(compose):
    candidates = sorted(
        (version for version in published_versions(MATERIAL3) if series(version) == series(compose)),
        key=order,
        reverse=True,
    )
    for candidate in candidates:
        requirement = compose_requirement(candidate)
        if requirement is not None and order(requirement) <= order(compose):
            return candidate
    sys.exit(f"No material3 release in the {'.'.join(map(str, series(compose)))} series fits Compose Multiplatform {compose}")


def changelog_section(changelog, compose):
    heading = re.search(rf"^# {re.escape(compose)} \(.*$", changelog, re.MULTILINE)
    if heading is None:
        return None
    rest = changelog[heading.end():]
    following = re.search(r"^# \d", rest, re.MULTILINE)
    return rest if following is None else rest[: following.start()]


def select_adaptive(compose, current, changelog, now):
    section = changelog_section(changelog, compose)
    if section is None:
        waited = now - published_at(compose)
        if waited > CHANGELOG_GRACE:
            return current, f"The Compose Multiplatform CHANGELOG has had no section for {compose} for {waited.days} days"
        return current, ""
    row = re.search(r"org\.jetbrains\.compose\.material3\.adaptive:adaptive\*:([0-9A-Za-z.-]+)", section)
    if row is None:
        return current, f"Cannot read the Material3 Adaptive version in the CHANGELOG section for Compose Multiplatform {compose}"
    return row.group(1), ""


def read_version(catalog, key):
    return re.search(rf'^{re.escape(key)} = "([^"]+)"$', catalog, re.MULTILINE).group(1)


def write_version(catalog, key, version):
    return re.sub(rf'^{re.escape(key)} = "[^"]+"$', f'{key} = "{version}"', catalog, count=1, flags=re.MULTILINE)


def pull_request_body(current, selected):
    rows = "\n".join(f"| `{key}` | `{current[key]}` | `{selected[key]}` |" for key in selected)
    return (
        "Updates the Compose Multiplatform dependencies that this workflow owns.\n\n"
        "| Version | From | To |\n"
        "| --- | --- | --- |\n"
        f"{rows}\n\n"
        "Compose Multiplatform follows the latest stable release. "
        "material3 is the newest release in the same series whose Compose requirements do not exceed it. "
        "Material3 Adaptive follows the Compose Multiplatform CHANGELOG.\n"
    )


def main():
    catalog = CATALOG.read_text()
    current = {key: read_version(catalog, key) for key in (COMPOSE_KEY, MATERIAL3_KEY, ADAPTIVE_KEY)}
    stable = latest_stable_compose()
    compose = stable if order(stable) > order(current[COMPOSE_KEY]) else current[COMPOSE_KEY]
    adaptive, alert = select_adaptive(
        compose,
        current[ADAPTIVE_KEY],
        fetch(CHANGELOG),
        datetime.datetime.now(datetime.timezone.utc),
    )
    selected = {COMPOSE_KEY: compose, MATERIAL3_KEY: select_material3(compose), ADAPTIVE_KEY: adaptive}
    for key, version in selected.items():
        catalog = write_version(catalog, key, version)
    CATALOG.write_text(catalog)
    changed = selected != current
    print(json.dumps({"current": current, "selected": selected, "changed": changed, "alert": alert}, indent=2))
    body_path = os.environ.get("PULL_REQUEST_BODY")
    if body_path:
        Path(body_path).parent.mkdir(parents=True, exist_ok=True)
        Path(body_path).write_text(pull_request_body(current, selected))
    output_path = os.environ.get("GITHUB_OUTPUT")
    if output_path:
        with open(output_path, "a") as output:
            output.write(f"changed={str(changed).lower()}\n")
            output.write(f"alert={alert}\n")


if __name__ == "__main__":
    main()
