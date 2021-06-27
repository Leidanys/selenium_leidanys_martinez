package ExamenFinal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InterviewQuestionSiteTest {
    public WebDriver driverChrome;
    public WebDriverWait explicitWait;
    private static final String ROOT_DIR= System.getProperty("user.dir")+ File.separator;
    private static final String DRIVER_DIR= ROOT_DIR+"driver"+File.separator;
    private static final String CHROME_PATH= DRIVER_DIR+"chromedriver.exe";
    private static final String URL="https://allstq.com/most-popular-testng-interview-questions/";
    private static final String TITLE_LANDING_PAGE="Most popular TestNG interview questions - Software Testing Questions";

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
    public void validarTituloPageTest(){
        String tituloPagina= driverChrome.getTitle();
        Assert.assertEquals(tituloPagina,TITLE_LANDING_PAGE,"Se esperaba otro tìtulo");

    }

    @Test
    public void validarUrlPageTest(){
        String urlPagina= driverChrome.getCurrentUrl();
        Assert.assertTrue(urlPagina.contains(URL),"Se epsaraba otra URL para la pàgina");

    }


    @Test
    public void landingPageTest(){


        explicitWait=new WebDriverWait(driverChrome,20);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("entry-title")));
        WebElement h1Element= driverChrome.findElement(By.className("entry-title"));
        String textoImagen= h1Element.getText();
        System.out.println("====> "+textoImagen);
        Assert.assertEquals(textoImagen,"MOST POPULAR TESTNG INTERVIEW QUESTIONS", "Se esperaba encontrar el texto");


    }


    @Test
    public void validarOpcionesDeMenuTest(){
       explicitWait=new WebDriverWait(driverChrome,20);
       explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("primary-menu")));

       boolean opcionSeleniumEncontrada=false;
       boolean opcionJavaEncontrada=false;

       List<WebElement> opcionesMenu=driverChrome.findElements(By.className("menu-link"));
        if (opcionesMenu.size()>0) {

            for (WebElement opcion : opcionesMenu) {
                if (opcion.getText().equals("Selenium")) {
                    opcionSeleniumEncontrada = true;
                }
                if (opcion.getText().equals("Java")) {
                    opcionJavaEncontrada = true;
                }

            }
        }else {
            System.out.println("No se encontraron opciones en el menú");
        }
        Assert.assertTrue(opcionSeleniumEncontrada,"Se esperaba encontrar la opción Selenium en el menú.");
        Assert.assertTrue(opcionJavaEncontrada,"Se esperaba encontrar la opción Java en el menú.");



    }

    @AfterMethod
    public void closeDriver(){
        driverChrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverChrome.close();
    }


}
