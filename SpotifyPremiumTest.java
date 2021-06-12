package clase7.Practico.Test;

import clase7.Practico.PageObject.SpotifyLandingPage;
import clase7.Practico.PageObject.SpotifyPremiunPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SpotifyPremiumTest {
    public WebDriver driverChrome;

    private static final String ROOT_DIR=System.getProperty("user.dir")+ File.separator;
    private static final String DRIVER_PATH = ROOT_DIR + "driver" + File.separator;
    private static final String CHROME_PATH=DRIVER_PATH + "chromedriver.exe";
    private static final String URL="https://www.spotify.com/";

    @BeforeMethod
    private void setup(){
        System.setProperty("webdriver.chrome.driver",CHROME_PATH);
        driverChrome =new ChromeDriver();
        driverChrome.get(URL);
        driverChrome.manage().window().maximize();
        driverChrome.navigate().refresh();

    }

    @Test
    public void PremiumTest(){
        SpotifyLandingPage spotifyLandingPage=new SpotifyLandingPage(driverChrome);
        Assert.assertEquals(spotifyLandingPage.getTitleSpotifyLandingPage(),"Escuchar es todo - Spotify","Se esperaba otro título para la página");
        Assert.assertEquals(spotifyLandingPage.getCurrentUrlSpotifyLandingPage(),"https://www.spotify.com/uy/","Se esperaba otra url");

        SpotifyPremiunPage spotifyPremiunPage=spotifyLandingPage.clickPremiunLink();
        Assert.assertEquals(spotifyPremiunPage.getTitlePremiunPage(),"Spotify Premium - Spotify (UY)","Se esperaba otro título para la página");
        Assert.assertEquals(spotifyPremiunPage.getUrlPremiunPage(),"https://www.spotify.com/uy/premium/","Se esperaba otra url");

        Assert.assertTrue(spotifyPremiunPage.validateTitleH1("Disfruta 3 meses de Premium gratis"), "Se espera encontrar el elemento H1");
        Assert.assertTrue(spotifyPremiunPage.validateTitleH1("Beneficios de Premium"),"Se espera encontrar el elemento H1");




    }

    @AfterMethod
    public void closeDriver(){

        driverChrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverChrome.close();
    }

}
