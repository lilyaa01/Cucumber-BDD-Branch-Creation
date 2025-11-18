package steps;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateBranchSteps {

    WebDriver driver;

    @Given("the user is logged into GitHub")
    public void loginToGitHub() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://github.com/login");

        // Using environment variables for security
        driver.findElement(By.id("login_field"))
              .sendKeys(System.getenv("GITHUB_TEST_USER"));
        driver.findElement(By.id("password"))
              .sendKeys(System.getenv("GITHUB_TEST_PASS"));
        driver.findElement(By.name("commit")).click();
    }

    @Given("the user opens the repository {string}")
    public void openRepository(String repoName) {
        String user = System.getenv("GITHUB_TEST_USER");
        driver.get("https://github.com/" + user + "/" + repoName);

        Assert.assertTrue(driver.getPageSource().contains(repoName));
    }

    @When("the user opens the branch selector")
    public void openBranchSelector() {
        driver.findElement(
            By.cssSelector("summary[aria-label='Switch branches or tags']")
        ).click();
    }

    @When("the user creates a branch named {string}")
    public void createBranch(String branchName) {
        WebElement input = driver.findElement(
            By.xpath("//input[contains(@placeholder, 'Find or create a branch')]")
        );
        input.sendKeys(branchName);

        driver.findElement(
            By.xpath("//button[contains(., 'Create branch')]")
        ).click();
    }

    @Then("the branch {string} should appear in the branch list")
    public void verifyBranch(String branchName) {
        String url = driver.getCurrentUrl().replace("/tree/", "/branches");
        driver.get(url);

        Assert.assertTrue(driver.getPageSource().contains(branchName));

        driver.quit();
    }
}
