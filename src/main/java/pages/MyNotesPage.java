package pages;

import dev.failsafe.internal.util.Assert;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HoldOn;
import utils.Log;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.XMLFormatter;

public class MyNotesPage {

    private final WebDriver driver;

    public MyNotesPage(WebDriver driver) {
        if (driver == null) throw new IllegalArgumentException("driver is null");

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//li[@id='menu-item-5286']/a")
    private WebElement myNotesLink;


    // dynamic list -> By (re-find fresh each time)
    private final By topicLinksBy = By.xpath("//ul[contains(@class,'nav-sidebar')]/li/div/a\n");


    @FindBy(xpath = "//h1[text()='Selenium']")
    private WebElement seleniumTitle;

    @FindBy(xpath = "//div[@class='shortcode_title']/h1")
    private WebElement topicTitle;

    private final By preloader = By.xpath("//div[@id='preloader']");

    public List<WebElement> getTopicLinks() {
        return driver.findElements(topicLinksBy);
    }


    public void clickMyNotesLink() {
        HoldOn.safeClick(driver, myNotesLink);
        Log.info("Verified that the My Notes Tab is visible.");


        // Wait for preloader to disappear after navigation
        HoldOn.waitForElementToDisappear(driver, preloader);

        // Wait for topic links to be visible
        HoldOn.waitForElementsToBeVisible(driver, getTopicLinks());

    }

    public void validateTopicLinks() {
        // Wait for preloader first
        HoldOn.waitForElementToDisappear(driver, preloader);

        HoldOn.waitForElementsToBeVisible(driver, getTopicLinks());
        Log.info("Topic links are present");

    }

    public void clickOnSeleniumTopicLink() {
        HoldOn.clickOnElementInList(driver, topicLinksBy, "Selenium");

        Log.info("Clicked on Selenium link");

    }

    public void validateSeleniumTitle() {
        HoldOn.waitForElementToBeVisible(driver, seleniumTitle);
        Log.info("Verified that on selenium page");

    }

    public void clickOnSidebarTopicLink(String link) {
        HoldOn.waitForElementToDisappear(driver, preloader);

        HoldOn.clickOnElementInList(driver, topicLinksBy, link);

        HoldOn.waitForElementToDisappear(driver, preloader);

    }

    public void waitForTopicTitle(String expected) {
        // Wait for preloader first
        HoldOn.waitForElementToDisappear(driver, preloader);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(topicTitle, expected));
    }

    public String getTopicTitle() {
        // Ensure preloader is gone before getting text
        HoldOn.waitForElementToDisappear(driver, preloader);

        HoldOn.waitForElementToBeVisible(driver, topicTitle);
        return topicTitle.getText().trim();
    }


}