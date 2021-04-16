package clase2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class registrationFacebookTest {

    private WebDriver driver;
    public static  String APP_URL= "https://www.facebook.com/";
    private static String CHROME_PATH = "driver/chromedriver.exe";

    private WebDriver getFacebookDriver(){

        System.setProperty("webdriver.chrome.driver",CHROME_PATH );

        driver= new ChromeDriver();
        driver.get(APP_URL);

        return  driver;

    }
    private void quitDriver() throws Exception {

        try {

            driver.quit();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Test
    public void fullRegistrationTest() throws Exception {

        driver=getFacebookDriver();
        driver.manage().window().maximize();
        driver.navigate().refresh();

        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(2000);

        WebElement nombre=driver.findElement(By.xpath(".//input[@name=\"firstname\"]"));
        nombre.sendKeys("John");

        WebElement apellidos=driver.findElement(By.xpath(".//input[@name=\"lastname\"]"));
        apellidos.sendKeys("Smith");

        WebElement numero_mail=driver.findElement(By.xpath(".//input[@name=\"reg_email__\"]"));
        numero_mail.sendKeys("5555555");

        WebElement pass=driver.findElement(By.xpath(".//input[@name=\"reg_passwd__\"]"));
        pass.sendKeys("123456789");

        WebElement birthday_day= driver.findElement(By.xpath(".//select[@name=\"birthday_day\"]"));
        WebElement birthday_month= driver.findElement(By.xpath(".//select[@name=\"birthday_month\"]"));
        WebElement birthday_year= driver.findElement(By.xpath(".//select[@name=\"birthday_year\"]"));

        Select daySelect= new Select(birthday_day);
        Select monthSelect= new Select(birthday_month);
        Select yearSelect= new Select(birthday_year);

        daySelect.selectByValue("26");
        monthSelect.selectByVisibleText("jun");
        yearSelect.selectByValue("1980");

        List<WebElement> listaSex= driver.findElements(By.xpath(".//input[@type=\"radio\" and @name=\"sex\"]"));
//        Assert.assertEquals(listaSex.size(),3);

        WebElement sexoMasc=listaSex.get(1);
        sexoMasc.click();

       quitDriver();

    }

}
