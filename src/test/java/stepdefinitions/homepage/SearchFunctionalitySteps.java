package stepdefinitions.homepage;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ExtentReportManager;
import utils.HoldOn;
import utils.Log;
import utils.ScreenshotUtils;

public class SearchFunctionalitySteps {

    private static final Logger log = LogManager.getLogger(SearchFunctionalitySteps.class);
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    public SearchFunctionalitySteps() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
    }

    private void logAndCapture(String message){
        Log.info(message); // logs message
        String base64 = ScreenshotUtils.takeScreenshotAsBase64(driver);
        ExtentReportManager.attachScreenshot(base64, message);

    }


    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        homePage.isOnHomePage();
        logAndCapture("Verified user is on home page");
    }

    @When("user enters {string} in the search box")
    public void user_enters_in_the_search_box(String searchTerm) {
        homePage.sendTermToSearchBox(searchTerm);
        logAndCapture("Search box text input VERIFIED");
    }

    @When("user clicks enter")
    public void user_clicks_search_button() {
        homePage.clickEnterInSearchBox();
        logAndCapture("Enter key in serach box functionality COMPLETED");
    }

    @Then("search results for {string} should be displayed")
    public void search_results_for_should_be_displayed(String searchTerm) {
        Assert.assertTrue("Not all search results contain the term: " + searchTerm,searchResultsPage.verifyAllSearchResultsContainSearchTerm(searchTerm));
        logAndCapture("Verified that search results contain given term");
    }

}
