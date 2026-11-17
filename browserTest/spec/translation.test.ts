import { expect, test } from "@playwright/test";

test("should ask browsers not to offer to translate the page", async ({
	page,
}) => {
	// Act
	await page.goto("/");

	// Assert
	await expect(page.locator("html")).toHaveAttribute("translate", "no");
	await expect(page.locator('meta[name="google"]')).toHaveAttribute(
		"content",
		"notranslate",
	);
});
