package ExamenFinal;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PruebaNetflixTest {

    public WebDriver driverChrome;
    public WebDriverWait explicitWait;
    private static final String ROOT_DIR= System.getProperty("user.dir")+ File.separator;
    private static final String DRIVER_DIR= ROOT_DIR+"driver"+File.separator;
    private static final String CHROME_PATH= DRIVER_DIR+"chromedriver.exe";
    private static final String URL="https://www.netflix.com/uy/";

    private static final String TITLE_LANDING_PAGE="Netflix Uruguay: Ve series online, ve películas online";
    private static final String TITLE_LOGIN_PAGE="Netflix";

    public String tituloActualPagina;


    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver",CHROME_PATH);
        driverChrome=new ChromeDriver();
        driverChrome.get(URL);
        driverChrome.manage().window().maximize();
        driverChrome.navigate().refresh();
        driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void iniciarSesionPageTest(){

        tituloActualPagina= driverChrome.getTitle();
        Assert.assertEquals(tituloActualPagina,TITLE_LANDING_PAGE,"Se esperaba otro tìtulo");

        explicitWait=new WebDriverWait(driverChrome,20);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"Iniciar sesión\")]")));

        WebElement iniciarSesionElemnt= driverChrome.findElement(By.xpath("//a[contains(text(),\"Iniciar sesión\")]"));
        iniciarSesionElemnt.click();
        driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        tituloActualPagina= driverChrome.getTitle();
        Assert.assertEquals(tituloActualPagina,TITLE_LOGIN_PAGE,"Se esperaba otro tìtulo");

        boolean textoEncontrado=false;
        List<WebElement> listaH1=driverChrome.findElements(By.tagName("h1"));

        if (listaH1.size()>0) {
            for (WebElement h1 : listaH1) {
                if (h1.getText().equals("Inicia sesión")){
                    textoEncontrado=true;
                }

            }
        }
        Assert.assertTrue(textoEncontrado,"Se esperaba encontrar el texto: Inicia sesión en la página.");

        WebElement iniciaConFacebookElement=driverChrome.findElement(By.className("fbBtnText"));
        String textoIniciaConFacebookElement=iniciaConFacebookElement.getText();
        Assert.assertEquals(textoIniciaConFacebookElement,"Iniciar sesión con Facebook", "Se esperaba encontrar el texto: Iniciar sesión con Facebook, en la página.");


    }

    @Test
    public void fakeEmailTest(){
        FakerTest fakerTest=new FakerTest();
        explicitWait=new WebDriverWait(driverChrome,20);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        WebElement emailElement= driverChrome.findElement(By.name("email"));
        emailElement.sendKeys(fakerTest.getEmail());

        driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement comenzarButtonElement=driverChrome.findElement(By.xpath("//button[@type=\"submit\"]"));
        comenzarButtonElement.click();

        driverChrome.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        String urlActualPagina=driverChrome.getCurrentUrl();

        Assert.assertTrue(urlActualPagina.contains("signup"),"Se esperaba que la url contenga la palabra: signup");
    }

    @Test(dataProvider = "email", dataProviderClass = ExamenFinal.DataProviderTest.class)

    public void dataProviderEmailTest(){

        explicitWait=new WebDriverWait(driverChrome,20);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        WebElement emailElement= driverChrome.findElement(By.name("email"));
        emailElement.sendKeys();

        WebElement comenzarElement=driverChrome.findElement(By.xpath("//button[@type=\"submit\"]"));
        comenzarElement.click();
    }

    @Test
    @Parameters({"tagName"})
    public void printTagsTest(@Optional("h3") String tagName){
        List<WebElement> listaElementos= driverChrome.findElements(By.tagName(tagName));
        for (WebElement elemento:listaElementos) {

            if (!elemento.getText().isEmpty()){
                System.out.println("===>"+elemento.getText());
            }

        }
    }

    @AfterMethod
    public void closeDriver(){
        driverChrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverChrome.close();
    }
}
