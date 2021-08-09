package ExamenFinal.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NetflixLoginPage extends BasePageObject {


    public NetflixLoginPage(WebDriver remoteDriver){
        chromeDriver=remoteDriver;
    }

    public boolean findTextoInPageH1Element(String text){
        boolean textoEncontrado=false;
        List<WebElement> listaH1=chromeDriver.findElements(By.tagName("h1"));

        if (listaH1.size()>0) {
            for (WebElement h1 : listaH1) {
                if (h1.getText().equals(text)){
                    textoEncontrado=true;
                    break;
                }

            }
        }
        return textoEncontrado;
    }

    public boolean findTextoIniciaSesion(String texto){

        boolean textEncontrado=false;
        WebElement iniciaConFacebookElement=chromeDriver.findElement(By.className("fbBtnText"));
        String textoIniciaConFacebookElement=iniciaConFacebookElement.getText();
        if (textoIniciaConFacebookElement.equals(texto)){
            textEncontrado=true;
        }
        return textEncontrado;

    }


}
