package clase4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class TestngSalesforce {

    public WebDriver driverChrome;
    private static String ROOT_DIR = System.getProperty("user.dir") + File.separator;
    private static String DRIVERS_DIR = ROOT_DIR + "driver" + File.separator;
    private static String CHROME_PATH = DRIVERS_DIR + "chromedriver.exe";

    private static  String SALEFORCE_URL= "https://login.salesforce.com/?locale=eu";

    @BeforeMethod
    private void setup(){

        System.setProperty("webdriver.chrome.driver",CHROME_PATH);
        driverChrome=new ChromeDriver();
    }

    @Test(priority = 1,groups = {"successTests"},enabled = true)
    public void validateSalesforceLogoTest(){
         driverChrome.get(SALEFORCE_URL);
         driverChrome.manage().window().maximize();
         driverChrome.navigate().refresh();

        String tagname=driverChrome.findElement(By.id("logo")).getTagName();
        WebElement icono =driverChrome.findElement(By.id("logo"));
        System.out.println("LOGO: " + icono.getText());
        System.out.println("ALT: " + icono.getAttribute("alt"));


    }

    @Test(priority = 4, groups = {"successTests"})
    public void rememberMeIsSelected()throws Exception{
       driverChrome.get("https://login.salesforce.com/?locale=eu");
       driverChrome.manage().window().maximize();
       driverChrome.navigate().refresh();

       Thread.sleep(2000);
       WebElement check= driverChrome.findElement(By.id("rememberUn"));
       if (!check.isSelected()){

           check.click();
       }
        Assert.assertTrue(check.isSelected(), "Se esperaba que el elemento: "+check.getText()+" estuviera seleccionado");
        Thread.sleep(2000);
    }

    @Test(priority = 2,enabled = false ,groups = {"fail test"})
    public void FooterIsValid() throws Exception {
        driverChrome.get(SALEFORCE_URL);
        driverChrome.manage().window().maximize();
        driverChrome.navigate().refresh();
        Thread.sleep(2000);

        String textoEsperado="All rights reserved";
        WebElement footer= driverChrome.findElement(By.id("footer"));

        Assert.assertTrue(footer.getText().contains(textoEsperado), "Se esperaba encontrar en el footer el texto: "+ textoEsperado);

    }

    //Ejercicio GIT
    @Test(priority = 3)
    public void loginFailureTest(@Optional("test@test.com") String emailParameter, @Optional("123466") String passwordParameter) throws Exception{
        driverChrome.get(SALEFORCE_URL);
        driverChrome.manage().window().maximize();
        driverChrome.navigate().refresh();
        Assert.assertEquals(driverChrome.getCurrentUrl(),SALEFORCE_URL, "No coincide la url");

        Thread.sleep(2000);
        WebElement logo= driverChrome.findElement(By.id("logo"));
        Assert.assertTrue(logo.isDisplayed(),"Se esperaba encontrar el logo en el sitio");

        WebElement email= driverChrome.findElement(By.id("username"));
        email.sendKeys(emailParameter);
        WebElement password= driverChrome.findElement(By.id("password"));
        password.sendKeys(passwordParameter);

        WebElement login= driverChrome.findElement(By.id("Login"));
        login.click();

        Thread.sleep(2000);
        WebElement error= driverChrome.findElement(By.id("error"));
        System.out.println("Mensaje: "+ error.getText());
        Assert.assertTrue(error.isDisplayed());


    }


    @AfterMethod
    private void  cleanCookies() throws Exception  {


        try {
            System.out.println("---->Limpiando cookies <-----------");
            driverChrome.manage().deleteAllCookies();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @AfterMethod
    private void closeDriver() throws Exception  {


        try {
            driverChrome.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
