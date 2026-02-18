package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommonUtils {

    /**
     * Verifies that the current URL matches the expected URL.
     *
     * @param driver      The WebDriver instance.
     * @param expectedUrl The expected URL to verify against.
     * @throws RuntimeException if the URL does not match
     */
    public static void verifyUrl(WebDriver driver, String expectedUrl) {
        // Get the current URL from the browser
        String actualUrl = driver.getCurrentUrl();
        // Normalize the expected URL: trim spaces and convert to lowercase
        String normalizedExpectedUrl = expectedUrl.trim().toLowerCase();

        // Log the action and the URLs
        Log.info("Verifying the page URL.");
        Log.info("Expected URL (normalized): " + normalizedExpectedUrl);
        Log.info("Actual URL (normalized): " + actualUrl);

        // Validate the URL
        if (!actualUrl.equals(normalizedExpectedUrl)) {
            Log.error("The page URL is incorrect!");
            throw new RuntimeException("Expected URL: " + normalizedExpectedUrl + ", but got: " + actualUrl);
        }

        // Log success if URLs match
        Log.info("Page URL verified successfully: " + actualUrl);
    }

    /**
     * Verifies that the current page title matches the expected title (case-insensitive and trimmed).
     *
     * @param driver        The WebDriver instance.
     * @param expectedTitle The expected title to verify against.
     * @throws RuntimeException if the title does not match
     */
    public static void verifyTitle(WebDriver driver, String expectedTitle) {
        // Get the current page title from the browser
        String actualTitle = driver.getTitle().trim();

        // Normalize the expected title: trim spaces and convert to lowercase
        String normalizedExpectedTitle = expectedTitle.trim();

        // Log the action and the titles
        Log.info("Verifying the page title.");
        Log.info("Expected Title (normalized): " + normalizedExpectedTitle);
        Log.info("Actual Title (normalized): " + actualTitle);

        // Validate the title
        if (!actualTitle.equals(normalizedExpectedTitle)) {
            Log.error("The page title is incorrect!");
            throw new RuntimeException("Expected Title: \"" + normalizedExpectedTitle + "\", but got: \"" + actualTitle + "\"");
        }

        // Log success if titles match
        Log.info("Page title verified successfully: " + actualTitle);
    }

    /**
     * Gets the value of the specified attribute for a given WebElement.
     *
     * @param element   The WebElement from which to retrieve the attribute.
     * @param attribute The attribute name (e.g., "class", "id", "placeholder").
     * @return The value of the attribute, or null if the attribute is not found.
     */
    public static String getAttribute(WebElement element, String attribute) {
        try {
            return element.getAttribute(attribute);
        } catch (Exception e) {
            System.err.println("Failed to get attribute '" + attribute + "' from element: " + e.getMessage());
            return null;
        }
    }

    /**
     * Enhanced clickOnElementInList using List<WebElement> with preloader handling
     * This method re-finds elements to avoid stale references
     */
//    public static void clickOnElementInList(WebDriver driver, List<WebElement> elements, String target) {
//        // Wait for preloader to disappear first
//        waitForElementToDisappear(driver, );
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        wait.ignoring(StaleElementReferenceException.class)
//                .ignoring(ElementClickInterceptedException.class)
//                .until(driver1 -> {
//                    // Check if preloader is still present
//                    try {
//                        WebElement preloader = driver.findElement(By.id("preloader"));
//                        if (preloader.isDisplayed()) {
//                            return false; // Preloader visible, retry
//                        }
//                    } catch (NoSuchElementException e) {
//                        // Preloader not found, that's good
//                    }
//
//                    // Iterate through the list
//                    for (WebElement e : elements) {
//                        try {
//                            String elementText = e.getText().trim();
//                            if (elementText.equalsIgnoreCase(target)) {
//                                // Scroll element into view
//                                ((JavascriptExecutor) driver).executeScript(
//                                        "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", e
//                                );
//
//                                // Small pause after scroll
//                                wait.until(ExpectedConditions.elementToBeClickable(e));
//
//                                // Try normal click first
//                                try {
//                                    e.click();
//                                    return true; // Success
//                                } catch (ElementClickInterceptedException ex) {
//                                    // Fallback to JavaScript click
//                                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
//                                    return true; // Success
//                                }
//                            }
//                        } catch (StaleElementReferenceException ex) {
//                            // Element went stale, will retry in next iteration
//                            return false;
//                        }
//                    }
//                    return false; // Element not found, retry
//                });
//    }


}
