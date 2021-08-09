package ExamenFinal.Test;

import ExamenFinal.PageObject.NetflixLoginPage;
import ExamenFinal.PageObject.NetflixRegistrationPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class PruebaNetflixTest extends PruebaBaseNetflix {



    private static final String TITLE_LANDING_PAGE="Netflix Uruguay: Ve series online, ve películas online";
    private static final String TITLE_LOGIN_PAGE="Netflix";
    public String tituloActualPagina;

    @Test
    public void iniciarSesionPageTest(){

        tituloActualPagina= netflixLandingPage.getTituloPage();
        Assert.assertEquals(tituloActualPagina,TITLE_LANDING_PAGE,"Se esperaba otro tìtulo");

        NetflixLoginPage netflixLoginPage=netflixLandingPage.goToLoginPage();
        tituloActualPagina= netflixLoginPage.getTituloPage();

        Assert.assertEquals(tituloActualPagina,TITLE_LOGIN_PAGE,"Se esperaba otro tìtulo");

        boolean textoEncontrado=netflixLoginPage.findTextoInPageH1Element("Inicia sesión");
        Assert.assertTrue(textoEncontrado,"Se esperaba encontrar el texto: Inicia sesión en la página.");

        boolean textoIniciaConFacebookElement=netflixLoginPage.findTextoIniciaSesion("Iniciar sesión con Facebook");
        Assert.assertTrue(textoEncontrado, "Se esperaba encontrar el texto: Iniciar sesión con Facebook, en la página.");


    }

    @Test
    public void fakeEmailTest() throws Exception{
        FakerTest fakerTest=new FakerTest();

        netflixLandingPage.fillEmailFieldLandingPage(fakerTest.getEmail());
        NetflixRegistrationPage netflixRegistrationPage=netflixLandingPage.goToRegistrationPage();

        Thread.sleep(5000);
        String urlActualPagina= netflixRegistrationPage.getTituloPage();
        Assert.assertTrue(urlActualPagina.contains("signup"),"Se esperaba que la url de la página actual contenga la palabra: signup");
    }

    @Test(dataProvider = "email", dataProviderClass = DataProviderTest.class)
    public void dataProviderEmailTest(String email){

        netflixLandingPage.fillEmailFieldLandingPage(email);
        NetflixRegistrationPage netflixRegistrationPage=netflixLandingPage.goToRegistrationPage();

    }

    @Test
    @Parameters({"tagName"})
    public void printTagsTest(@Optional("h3") String tagName){

        ArrayList<String> elementos= netflixLandingPage.getListaDeElementosByTagName(tagName);
        for (String elemento: elementos){
            System.out.println("====> "+elemento);
        }

        }

}
