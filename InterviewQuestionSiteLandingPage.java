package ExamenFinal.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class InterviewQuestionSiteLandingPage extends BasePageObject{

    public WebDriver chromeDriver;
    public WebDriverWait explicitWait;

    public InterviewQuestionSiteLandingPage(WebDriver remoteDriver) {

        chromeDriver = remoteDriver;
    }

    public String getTextLandingPage() {
        explicitWait = new WebDriverWait(chromeDriver, 20);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("entry-title")));
        WebElement h1Element = chromeDriver.findElement(By.className("entry-title"));
        String textoImagen = h1Element.getText();

        return textoImagen;

    }

    public boolean findOtionMenu(String textMenu) {

        explicitWait = new WebDriverWait(chromeDriver, 20);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("primary-menu")));

        boolean encontrado = false;

        List<WebElement> opcionesMenu = chromeDriver.findElements(By.className("menu-link"));
        if (opcionesMenu.size() > 0) {

            for (WebElement opcion : opcionesMenu) {
                if (opcion.getText().equals(textMenu)) {
                    encontrado = true;
                    System.out.println("Se encontro la opcion: "+textMenu);
                    break;
                }

            }
        }
        return encontrado;
    }

    public String getPreguntaAndRespuesta(String preguntaParameter){

        List<WebElement> listaH3= chromeDriver.findElements(By.tagName("h3"));
        Assert.assertTrue(listaH3.size()>0, "Se esperaba que la lista contenga elementos");

        String pregunta="";
        String respuesta="";

        for (WebElement elemento:listaH3) {
            if (!elemento.getText().isEmpty()) {

                if (elemento.getText().contains(preguntaParameter)){

                    pregunta= elemento.getText();
                    respuesta= chromeDriver.findElement(By.cssSelector("h3+p")).getText();
                    break;
                }
            }

        }
        return pregunta+"\n"+respuesta;


    }

}