package Pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class NewAddress {
    WebDriver driver;

    @Given("I m logged in to CodersLab shop")
    public void iMLoggedInToCodersLabShop() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("kwyxviqsrjqyrdrpdl@bvhrk.com", "123QWE");
    }

    @When("I go to Addresses page")
    public void iGoToAddressesPage() {
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span")).click();

    }

    @And("I create new address")
    public void iCreateNewAddress() {
        driver.findElement(By.xpath("//*[@id=\"addresses-link\"]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a/span")).click();
    }

    @And("I complete with data {string} , {string} ,{string},{string},{string},{string}")
    public void iCompleteWithData(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/section/div[1]/div[1]/input")).sendKeys(arg0);
        driver.findElement(By.name("address1")).sendKeys(arg1);
        driver.findElement(By.name("city")).sendKeys(arg2);
        driver.findElement(By.name("postcode")).sendKeys(arg3);
        driver.findElement(By.name("phone")).sendKeys(arg5);
    }
    // ALBO TAK : @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/section/div[1]/div[1]/input")
    //  WebElement alias; @FindBy(name = "address1") WebElement address; @FindBy(name = "city")
    // WebElement city; @FindBy(name = "postcode") WebElement postcode; @FindBy(name = "phone") WebElement phone;


    @And("I check if address is correct")
    public boolean iCheckIfAddressIsCorrect() {
        return !driver.findElements(By.cssSelector(".alert.alert-danger")).isEmpty();
    }
    // ALBO TAK : void iCheckIfAddressIsCorrect(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
    //        String addressAsText = HotelMyAddressesPage.getAddressFromPage();
    //        String expectedAddress = String.join(arg0,arg1,arg2,arg3,arg4,arg5);
    //        assertEquals(expectedAddress, addressAsText);}

    @And("I click save")
    public void iClickSave() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button")).click();
    }

    @Then("I can see success message with text {string}")
    public void iCanSeeSuccessMessageWithText(String arg0) throws InterruptedException {
        String valMsg = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li")).getText();
        assertEquals(arg0, valMsg);
        Thread.sleep(20);
    }
}
//        Thread.sleep(20);