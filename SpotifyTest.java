package clase7.Practico.Test;

import clase7.Practico.PageObject.SpotifyLandingPage;
import clase7.Practico.PageObject.SpotifyRegistroPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SpotifyTest {
    public WebDriver driverChrome;
    private static final String URL="https://www.spotify.com/uy/";
    private static final String ROOT_DIR = System.getProperty("user.dir") + File.separator;
    private static final String DRIVERS_DIR = ROOT_DIR + "driver" + File.separator;
    private static final String CHROME_PATH = DRIVERS_DIR + "chromedriver.exe";



    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver",CHROME_PATH);
        driverChrome=new ChromeDriver();
        driverChrome.get(URL);
        driverChrome.manage().window().maximize();
        driverChrome.navigate().refresh();
    }

    @Test
    public void testSpotify(){

        SpotifyLandingPage spotifyLandingPage= new SpotifyLandingPage(driverChrome);
        Assert.assertEquals(spotifyLandingPage.getTitleSpotifyLandingPage(),"Escuchar es todo - Spotify","Se esperaba otro titulo para la página");
        Assert.assertEquals(spotifyLandingPage.getCurrentUrlSpotifyLandingPage(),"https://www.spotify.com/uy/","Se esperaba otra url");

        SpotifyRegistroPage spotifyRegistroPage = spotifyLandingPage.clickToRegistrarseLink();
        Assert.assertEquals(spotifyRegistroPage.getTitleSpotifyRegistrartePage(),"Registrarte - Spotify","Se esperaba otro titulo para la página");
        Assert.assertEquals(spotifyRegistroPage.getCurrentUrlSpotifyRegistrartePage(),"https://www.spotify.com/uy/signup/","Se esperaba otra url");

        spotifyRegistroPage.fillEmailField("@@@.com.");
        Assert.assertEquals(spotifyRegistroPage.getErrorInvalidMail(),"Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com","Se esperaba otro mensaje de error");


    }

   @AfterMethod
   public void closeDrive(){

        driverChrome.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driverChrome.close();
    }
}
