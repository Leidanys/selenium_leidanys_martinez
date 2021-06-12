package clase7.Practico.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpotifyLandingPage {
    public WebDriver driverChrome;
    public WebDriverWait explictwait;

    public SpotifyLandingPage(WebDriver remoteDriver){
        driverChrome=remoteDriver;
    }

    public String getTitleSpotifyLandingPage(){
        return driverChrome.getTitle();
    }

    public String getCurrentUrlSpotifyLandingPage(){
        return driverChrome.getCurrentUrl();
    }

    public SpotifyRegistroPage clickToRegistrarseLink(){
        explictwait=new WebDriverWait(driverChrome,20);
        explictwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"Registrarse\")]")));

        WebElement registrarseElement=driverChrome.findElement(By.xpath("//a[contains(text(),\"Registrarse\")]"));
        registrarseElement.click();

        SpotifyRegistroPage spotifyRegistroPage =new SpotifyRegistroPage(driverChrome);
        return spotifyRegistroPage;

    }

    public SpotifyPremiunPage clickPremiunLink(){

        explictwait=new WebDriverWait(driverChrome,20);
        explictwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"Premium\")]")));
        WebElement premiumElement=  driverChrome.findElement(By.xpath("//a[contains(text(),\"Premium\")]"));
        premiumElement.click();

        SpotifyPremiunPage spotifyPremiunPage=new SpotifyPremiunPage(driverChrome);
        return spotifyPremiunPage;

    }
}
