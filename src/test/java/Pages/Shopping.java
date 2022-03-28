package Pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import name.finsterwalder.fileutils.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.*;
import static org.openqa.selenium.OutputType.FILE;

public class Shopping {
    WebDriver driver;

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
        driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/article[2]/div/a/img")).click();
    }


    @And("I  choose the size M and quantity {string}")

    public void iChooseTheSizeAndQuantity(String quantity) {
        Select select = new Select(driver.findElement(By.name("group[1]")));
        select.selectByVisibleText("M");
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys(quantity);

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

    @And("I verify created address  {string},{string},{string}")
    public void iVerifyCreatedAddress(String arg0, String arg1, String arg2) {
        String expectedAddress = String.join("\n", arg0, arg1, arg2);
        String currentAddress = getAddressFromPage();
        assertEquals(expectedAddress, currentAddress);
    }

    public String getAddressFromPage() {
        driver.findElement(By.xpath("//*[@id=\"id-address-delivery-address-22027\"]/footer/a[1]")).click();
        String street = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/section/div[5]/div[1]/input")).getText();
        String city = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/section/div[7]/div[1]/input")).getText();
        String postcode = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/section/div[8]/div[1]/input")).getText();
        return String.join("\n", street, city, postcode);
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
    public void iClickOnOrderWithAnObligationToPay() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();

    }

    @And("I will do a screenshot with the order confirmation and the amount")
    public void iWillDoAScreenshotWithTheOrderConfirmationAndTheAmount() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(FILE);
    }
}
