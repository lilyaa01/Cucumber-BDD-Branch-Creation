package org.tss.cucumberdemo.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tss.cucumberdemo.drivers.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class GithubSteps {

    private WebDriver driver = ChromeDriver.getChromeDriver();
    private final By USERNAME_FIELD = By.id("login_field");
    private final By PASSWORD_FIELD = By.id("password");
    private final By SIGN_IN_BUTTON = By.name("commit");
    private final By REPOSITORY_NAME_FIELD = By.id("repository-name-input");
    private final By VISIBILILITY_BUTTON = By.id("visibility-anchor-button");
    private final String VISIBILILITY_SPAN_XPATH = "//ul//div//span[normalize-space()='%s']";
    private final By CREATE_REPO_BUTTON = By.xpath("//button[normalize-space()='Create repository']");
    private final By PAGE_TITLE = By.xpath("//strong[@itemprop='name']");

    @Given("^I am logged in to Github$")
    public void loginToGitHub() {
        driver.get("https://github.com/login");

        String username = System.getenv("GITHUB_TEST_USER");
        String password = System.getenv("GITHUB_TEST_PASS");

        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(SIGN_IN_BUTTON).click();

        assertEquals("Failed to login to GitHub", "https://github.com/", driver.getCurrentUrl());
    }

    @When("^I navigate to the \"Create a new repository\" page$")
    public void navigateToCreateNewRepoPage() {
        driver.get("https://github.com/new");
    }

    @When("^I fill in the repository name with \"([^\"]*)\"$")
    public void fillInRepoName(String repositoryName) {
        driver.findElement(REPOSITORY_NAME_FIELD).sendKeys(repositoryName);
    }

    @When("^I select \"([^\"]*)\" as the repository visibility$")
    public void selectRepoVisibility(String visibility) {
        driver.findElement(VISIBILILITY_BUTTON).click();

        driver.findElement(By.xpath(String.format(VISIBILILITY_SPAN_XPATH, visibility))).click();
    }

    @When("^I click the \"Create repository\" button$")
    public void clickCreateRepoButton() {
        driver.findElement(CREATE_REPO_BUTTON).click();
    }

    @Then("^I should be redirected to the page of the repository with name \"([^\"]*)\"$")
    public void checkCurrentPageIsNewReposPage(String repositoryName) {
        assertEquals("https://github.com/t7291864/" + repositoryName, driver.getCurrentUrl());
    }

    @And("^the title of the page should be \"([^\"]*)\"$")
    public void checkTitleIsRepoName(String repositoryName) {
        String title = driver.findElement(PAGE_TITLE).getText();
        assertEquals(repositoryName, title);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}