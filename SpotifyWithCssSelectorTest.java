package clase3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;


public class SpotifyWithCssSelectorTest {

    public static WebDriver driveChrome;
    private static  String APP_URL_SPOTIFY= "https://www.spotify.com/uy/signup/";
    private static String ROOT_DIR = System.getProperty("user.dir") + File.separator;
    private static String DRIVERS_DIR = ROOT_DIR + "driver" + File.separator;
    private static String CHROME_PATH = DRIVERS_DIR + "chromedriver.exe";

    //localizadores cssSelector Placeholder
    private static String emailPlaceholder ="[placeholder=\"Introduce tu correo electrónico.\"]";
    private static String confirmaMailPlaceholder= "[placeholder=\"Vuelve a introducir tu correo electrónico.\"]";
    private static String passPlaceholder= "[placeholder=\"Crea una contraseña.\"]";
    private static String nombrePerfilPlaceholder= "[placeholder=\"Introduce un nombre de perfil.\"]";
    private static String dayPlaceholder= "[placeholder=\"DD\"]";
    private static String yearPlaceholder= "[placeholder=\"AAAA\"]";

    //otros localizadores
    private static String month= "#month"; // cssSelector id
    private static String terminosCondiciones= "[href=\"/uy/legal/end-user-agreement/\"]"; // cssSelector atributo, valor
    private static String gender= "//*[contains(text(),'Mujer')]" ;//xpath
    private static String checkNoPublicidad= "//*[contains(text(),'Prefiero no recibir publicidad de Spotify')]"; //xpath
    private static String checkCompartirDatos= "//*[contains(text(),'Compartir mis datos de registro con los proveedores de contenido de Spotify para fines de marketing.')]"; //xpath

    // values

    private String emailValue= "test1234@gmail.com";
    private String confirmaMailValue= "test1234@gmail.com";
    private String passValue= "Test123";
    private String nombrePerfilValue= "Test";
    private String mesValue= "10";
    private String diaValue= "23";
    private String añoValue= "1989";




    @BeforeMethod
    public WebDriver getSpotifyDriver(){
        System.setProperty("webdriver.chrome.driver",CHROME_PATH);
        driveChrome=new ChromeDriver();
        driveChrome.get(APP_URL_SPOTIFY);

        return driveChrome;
    }

    @Test
    private void spotifyByPlaceHolderTest() throws Exception{

        try {

            driveChrome.manage().window().maximize();
            driveChrome.navigate().refresh();
            Thread.sleep(1000);

            llenarFormularioPlaceholder();

        }catch (Exception e){

            System.out.println("ERROR: "+e.getMessage());
        }
    }
    private void llenarFormularioPlaceholder(){

        try {
            WebElement email =driveChrome.findElement(By.cssSelector(emailPlaceholder));
            if(email.isDisplayed()){
                email.sendKeys(emailValue);
            }
            Thread.sleep(1000);
            Assert.assertEquals(email.getAttribute("value"),emailValue, "Se esperaba el valor: "+emailValue+ " en el campo "+email.getText());

            WebElement confirmaMail =driveChrome.findElement(By.cssSelector(confirmaMailPlaceholder));
            if(confirmaMail.isDisplayed()){
                confirmaMail.sendKeys(confirmaMailValue);
            }
            Thread.sleep(1000);
            Assert.assertEquals(confirmaMail.getAttribute("value"),confirmaMailValue, "Se esperaba el valor: "+confirmaMailValue+ " en el campo "+confirmaMail.getText());


            WebElement pass =driveChrome.findElement(By.cssSelector(passPlaceholder));
            if(pass.isEnabled()){
                pass.sendKeys(passValue);
            }
            Thread.sleep(1000);
            Assert.assertEquals(pass.getAttribute("value"),passValue, "Se esperaba el valor : "+passValue+ " en el campo "+pass.getText());

            WebElement nombrePerfil =driveChrome.findElement(By.cssSelector(nombrePerfilPlaceholder));
            if(nombrePerfil.isDisplayed()){
                nombrePerfil.sendKeys(nombrePerfilValue);
            }
            Thread.sleep(1000);
            Assert.assertEquals(nombrePerfil.getAttribute("value"),nombrePerfilValue, "Se esperaba el valor : "+nombrePerfilValue+ " en el campo "+nombrePerfil.getText());

            WebElement dia=driveChrome.findElement(By.cssSelector(dayPlaceholder));

            if(dia.isEnabled()){
                dia.sendKeys(diaValue);
            }
            Thread.sleep(1000);
            Assert.assertEquals(dia.getAttribute("value"),diaValue, "Se esperaba el valor : "+diaValue+ " en el campo "+dia.getText());

            WebElement mes=driveChrome.findElement(By.cssSelector(month));
            Select mesOptions=new Select(mes);
            mesOptions.selectByValue(mesValue);

            Thread.sleep(1000);
            Assert.assertEquals(mes.getAttribute("value"),mesValue, "Se esperaba el valor : "+mesValue+ " en el campo "+mes.getText());

            WebElement ano=driveChrome.findElement(By.cssSelector(yearPlaceholder));
            if(ano.isEnabled()){
                ano.sendKeys(añoValue);
            }
            Thread.sleep(1000);
            Assert.assertEquals(ano.getAttribute("value"),añoValue, "Se esperaba el valor : "+añoValue+ " en el campo "+ano.getText());

            WebElement sexoF=driveChrome.findElement(By.xpath(gender));
            if (!sexoF.isSelected()) {
                    sexoF.click();
            }
            Thread.sleep(1000);
            System.out.println(sexoF.isSelected());
            //Assert.assertTrue(sexoF.isSelected(),"Se esperaba que el sexo: "+sexoF.getText()+" este seleccionado");

            WebElement check1 =driveChrome.findElement(By.xpath(checkNoPublicidad));
            if (!check1.isSelected()) {
                check1.click();
                }
            Thread.sleep(1000);
            System.out.println(check1.isSelected());
        //    Assert.assertTrue(check1.isSelected(),"Se esperaba elemento " +check1.getText()+" selecccionado");

            WebElement check2 =driveChrome.findElement(By.xpath(checkCompartirDatos));
            if (!check2.isSelected()) {
                check2.click();
            }
            Thread.sleep(1000);
            System.out.println(check2.isSelected());
           // Assert.assertTrue(check2.isSelected(),"Se esperaba elemento " +check2.getText()+" selecccionado");

            WebElement linkTerminos = driveChrome.findElement(By.cssSelector(terminosCondiciones));
            if (linkTerminos.isEnabled()){
                linkTerminos.click();
            }
            Thread.sleep(1000);
            Assert.assertEquals(driveChrome.getTitle(),"Registrarte - Spotify");

        }
        catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @AfterMethod
    private void quitDriver() throws Exception {

        try {
            driveChrome.quit();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
