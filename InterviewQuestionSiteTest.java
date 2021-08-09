package ExamenFinal.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InterviewQuestionSiteTest extends PruebaBaseInterview {
    private static final String TITLE_LANDING_PAGE="Most popular TestNG interview questions - Software Testing Questions";
    private static final String URL="https://allstq.com/most-popular-testng-interview-questions/";

    @Test
    public void validarTituloPageTest(){
        String tituloPagina= interviewQuestionSiteLandingPage.getTituloPage();
        Assert.assertEquals(tituloPagina,TITLE_LANDING_PAGE,"Se esperaba otro tìtulo");

    }

    @Test
    public void validarUrlPageTest(){
        String urlPagina= interviewQuestionSiteLandingPage.getURLPage();
        Assert.assertTrue(urlPagina.contains(URL),"Se epsaraba otra URL para la pàgina");

    }

    @Test
    public void landingPageTest(){
        String textoImagen=interviewQuestionSiteLandingPage.getTextLandingPage();
        Assert.assertEquals(textoImagen,"MOST POPULAR TESTNG INTERVIEW QUESTIONS", "Se esperaba encontrar el texto");
    }

    @Test
    public void validarOpcionesDeMenuTest(){
       boolean opcionSeleniumEncontrada=interviewQuestionSiteLandingPage.findOtionMenu("Java");
       boolean opcionJavaEncontrada=interviewQuestionSiteLandingPage.findOtionMenu("Selenium");

        Assert.assertTrue(opcionSeleniumEncontrada,"Se esperaba encontrar la opción Selenium en el menú.");
        Assert.assertTrue(opcionJavaEncontrada,"Se esperaba encontrar la opción Java en el menú.");
    }

    @Test
    public void printTitulosTest(){

        String ques1=interviewQuestionSiteLandingPage.getPreguntaAndRespuesta("Ques.1");
        System.out.println(ques1);

        String ques2=interviewQuestionSiteLandingPage.getPreguntaAndRespuesta("Ques.2");
        System.out.println(ques2);

        String ques3=interviewQuestionSiteLandingPage.getPreguntaAndRespuesta("Ques.3");
        System.out.println(ques3);

        String ques4=interviewQuestionSiteLandingPage.getPreguntaAndRespuesta("Ques.4");
        System.out.println(ques4);

        String ques5=interviewQuestionSiteLandingPage.getPreguntaAndRespuesta("Ques.5");
        System.out.println(ques5);
    }
}






