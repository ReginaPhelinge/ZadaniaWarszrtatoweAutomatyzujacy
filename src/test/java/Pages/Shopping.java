package Pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Shopping {
    static WebDriver driver;

    @Given("I m logged in to CodersLab shop page")
    public void iMLoggedInToCodersLabShop() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("kwyxviqsrjqyrdrpdl@bvhrk.com", "123QWE");
    }

    @When("I chose product from CodersLab shop")
    public void iChoseProductFromCodersLabShop() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/article[1]/div/a")).click();
    }

    @And("I  choose the size M and quantity 5")
    public void iChooseTheSizeLAndQuantity() {
        Select select = new Select(driver.findElement(By.name("group[1]")));
        select.selectByVisibleText("M");
        driver.findElement(By.id("quantity_wanted")).click();
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("5");


    }

    @And("I add product to Cart")
    public void iAddProductToCart() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")).click();
    }

    @Then("I'm going to the checkout option")
    public void iMGoingToTheCheckoutOption() {
        driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")).click();
    }

    @And("I verify created address  {string} , {string} , {string} , {string} , {string}")
    public void iVerifyCreatedAddress(String arg0, String arg1, String arg2, String arg3, String arg4) {
        String name = "Natalia Geller";
        String valMsg2 = driver.findElement(By.cssSelector("div[class = address")).getText();
        assertEquals(String.join("", name + "\n", arg0 + "\n", arg1 + "\n", arg2 + "\n", arg3 + "\n", arg4), valMsg2);

    }

    @And("I select the collection method - PrestaShop pick up in store")
    public void iSelectTheCollectionMethodPrestaShopPickUpInStore() {
        driver.findElement(By.xpath("//*[@id=\"checkout-delivery-step\"]/h1")).click();
        driver.findElement(By.id("delivery_option_1")).submit();

    }

    @And("I select the payment option - Pay by Check")
    public void iSelectThePaymentOptionPayByCheck() {
        driver.findElement(By.xpath("//*[@id=\"checkout-payment-step\"]/h1")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-option-1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]")).click();
    }

    @And("I click on : order with an obligation to pay")
    public void iClickOnOrderWithAnObligationToPay() {
        driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();

    }

    @And("I will do a screenshot")
    public static void iWillDoAScreenshotWithTheOrderConfirmationAndTheAmount() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,195)", "");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Users\\Admin\\Desktop\\AUTOMATY\\report.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}
