import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Ejercicio1 {
public WebDriver driver;

    public WebDriver getDriver(String newUrl){

        String path= "driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path );
        driver=new ChromeDriver();
        driver.get(newUrl);

        return  driver;

    }

    @Test
    public void netflixPageTest(){
        String url= "https://www.netflix.com/uy/.";

        driver=getDriver(url);
        driver.manage().window().maximize();

        List<WebElement> listaH1= driver.findElements(By.tagName("h1"));
        List<WebElement> listaH2= driver.findElements(By.tagName("h2"));



        System.out.println("\n");
        System.out.println("<---------Mostrando elementos h1 de la pagina-------->");

        for (WebElement elementoH1: listaH1) {

            if (elementoH1.getText().isEmpty()) {
                System.out.println("Elemento h2: Vacio");
            } else {

                System.out.println("Elemento h1: " + elementoH1.getText());
            }
        }

        System.out.println("\n");
        System.out.println("<---------Mostrando elementos h2 de la pagina-------->");

        for (WebElement elementoH2: listaH2) {

            if (elementoH2.getText().isEmpty()){
                System.out.println("Elemento h2: Vacio");
            }
            else{

                System.out.println("Elemento h2: " + elementoH2.getText());
            }
        }
        System.out.println("\n");
        System.out.println("<---------Refrescando pagina.....-------->");

        driver.navigate().refresh();

        List<WebElement> listaBotones= driver.findElements(By.xpath(".//button"));
        List<WebElement> listaDiv= driver.findElements(By.tagName("div"));
        List<WebElement> listaLink= driver.findElements(By.tagName("link"));

        System.out.println("\n");
        System.out.println("<---------Mostrando texto botones de la pagina: -------->");

        for (WebElement btn:listaBotones) {

            System.out.println("Texto boton: "+ btn.getText());
        }

        System.out.println("\n");
        System.out.println("<---------Mostrando cantidad de elemtos div de la pagina: -------->");
        System.out.println("Cantidad: " +listaDiv.size());

        System.out.println("\n");
        System.out.println("<---------Mostrando el titulo de la pagina-------->");
        System.out.println("TITULO: " +driver.getTitle());

        System.out.println("\n");
        System.out.println("<---------Mostrando cantidad de elemtos link de la pagina: -------->");
        System.out.println("Cantidad: " +listaLink.size());

        driver.quit();


    }




}
