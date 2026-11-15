import { defineConfig } from "@playwright/test";

export default defineConfig({
	testDir: "browserTest",
	forbidOnly: !!process.env.CI,
	use: {
		baseURL: "http://127.0.0.1:8081",
		channel: "chrome",
	},
	webServer: {
		command:
			"npx --no http-server composeApp/build/dist/wasmJs/productionExecutable -a 127.0.0.1 -p 8081 -c-1 -s",
		url: "http://127.0.0.1:8081",
	},
});
