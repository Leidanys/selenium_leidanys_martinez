package ExamenFinal.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NetflixLandingPage extends BasePageObject {

   public NetflixLandingPage(WebDriver remoteDriver){chromeDriver=remoteDriver;}

   public NetflixLoginPage goToLoginPage(){

      explicitWait=new WebDriverWait(chromeDriver,20);
      explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"Iniciar sesión\")]")));

      WebElement iniciarSesionElemnt= chromeDriver.findElement(By.xpath("//a[contains(text(),\"Iniciar sesión\")]"));
      iniciarSesionElemnt.click();
      chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      NetflixLoginPage netflixLoginPage= new NetflixLoginPage(chromeDriver);

      return  netflixLoginPage;

   }

   public void fillEmailFieldLandingPage(String unEmail){

      explicitWait=new WebDriverWait(chromeDriver,20);
      explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

      WebElement emailElement= chromeDriver.findElement(By.name("email"));
      emailElement.sendKeys(unEmail);
   }

   public NetflixRegistrationPage goToRegistrationPage() {

      chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      WebElement comenzarButtonElement=chromeDriver.findElement(By.xpath("//button[@type=\"submit\"]"));
      comenzarButtonElement.click();

      // driverChrome.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
      // explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("netflix-logo")));


      NetflixRegistrationPage netflixRegistrationPage=new NetflixRegistrationPage(chromeDriver);
      return netflixRegistrationPage;



   }

   public ArrayList<String> getListaDeElementosByTagName (String tagName)
   {

      List<WebElement> listaElementos = chromeDriver.findElements(By.tagName(tagName));
      ArrayList<String> nombresElementosList= new ArrayList<>();

      for (WebElement elemento : listaElementos) {

         if (!elemento.getText().isEmpty()) {
            nombresElementosList.add(elemento.getText());
         }
      }
      return nombresElementosList;
   }
}
