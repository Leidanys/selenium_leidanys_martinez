package clase7.Practico.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SpotifyPremiunPage {
    public WebDriver driverChrome;

    public SpotifyPremiunPage(WebDriver remoteDriver){
        driverChrome=remoteDriver;
    }

    public String getTitlePremiunPage(){
       return driverChrome.getTitle();
    }

    public String getUrlPremiunPage(){
        return driverChrome.getCurrentUrl();
    }

    public boolean validateTitleH1(String unTitulo){

        List<WebElement> listaH1Elements=driverChrome.findElements(By.tagName("H1"));
        boolean encontrado=false;

        for (WebElement h1Element:listaH1Elements) {
             if (h1Element.getText().equals(unTitulo)){
                encontrado=true;
                System.out.println("===> Se encontró el título: "+h1Element.getText());
                 break;
            }
        }
        return encontrado;

    }
}
