import { defineConfig } from "@playwright/test";

export default defineConfig({
	testDir: "browserTest",
	forbidOnly: !!process.env.CI,
	use: {
		baseURL: `http://127.0.0.1:${process.env.APP_PORT}`,
		channel: "chrome",
	},
	webServer: {
		command:
			"npx --no http-server composeApp/build/dist/wasmJs/productionExecutable -a 127.0.0.1 -p 0 -c-1",
		wait: {
			stdout: /Available on:\s+http:\/\/127\.0\.0\.1:(?<app_port>\d+)/,
		},
	},
});
