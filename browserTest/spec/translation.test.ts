import { expect, test } from "@playwright/test";

test("should mark the page as not to be translated", async ({ page }) => {
	// Act
	await page.goto("/");

	// Assert
	await expect(page.locator("html")).toHaveAttribute("translate", "no");
});

test("should ask Chrome not to offer to translate the page", async ({
	page,
}) => {
	// Act
	await page.goto("/");

	// Assert
	await expect(page.locator('meta[name="google"]')).toHaveAttribute(
		"content",
		"notranslate",
	);
});
