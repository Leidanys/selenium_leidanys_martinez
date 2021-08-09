package ExamenFinal.Test;

import ExamenFinal.PageObject.InterviewQuestionSiteLandingPage;
import ExamenFinal.PageObject.NetflixLandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class PruebaBaseInterview {
    protected WebDriver driverChrome;
    protected WebDriverWait explicitWait;
    private static final String ROOT_DIR= System.getProperty("user.dir")+ File.separator;
    private static final String DRIVER_DIR= ROOT_DIR+"driver"+File.separator;
    private static final String CHROME_PATH= DRIVER_DIR+"chromedriver.exe";
    private static final String URL_ALLSTQ="https://allstq.com/most-popular-testng-interview-questions/";

    protected InterviewQuestionSiteLandingPage interviewQuestionSiteLandingPage;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver",CHROME_PATH);
        driverChrome=new ChromeDriver();
        driverChrome.get(URL_ALLSTQ);
        driverChrome.manage().window().maximize();
        driverChrome.navigate().refresh();
        driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        interviewQuestionSiteLandingPage=  new InterviewQuestionSiteLandingPage(driverChrome);
    }

    @AfterMethod
    public void closeDriver(){
        driverChrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverChrome.close();
    }


}
