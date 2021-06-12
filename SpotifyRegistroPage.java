package clase7.Practico.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpotifyRegistroPage {
    public WebDriver driverChrome;
    public  WebDriverWait explictwait;

    public SpotifyRegistroPage(WebDriver remoteDriver){
        driverChrome=remoteDriver;
    }

    public String getTitleSpotifyRegistrartePage(){
        return driverChrome.getTitle();
    }

    public String getCurrentUrlSpotifyRegistrartePage(){
        return driverChrome.getCurrentUrl();
    }

    public void fillEmailField(String newMail){

        explictwait=new WebDriverWait(driverChrome,20);
        explictwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        WebElement emailElement=driverChrome.findElement(By.id("email"));
        emailElement.sendKeys(newMail);
        emailElement.sendKeys(Keys.TAB);

    }

    public  String getErrorInvalidMail(){
        explictwait=new WebDriverWait(driverChrome,50);
        explictwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=\"Indicador de error\"]")));
        WebElement mensaInvalidEmail= driverChrome.findElement(By.xpath("//span[contains(text(), \"Este correo electrónico no es válido.\")]"));
        System.out.println("====> "+ mensaInvalidEmail.getText());
        return mensaInvalidEmail.getText();

    }


}
