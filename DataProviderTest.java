package ExamenFinal.Test;

import org.testng.annotations.DataProvider;

public class DataProviderTest {

    public static FakerTest fakerTest=new FakerTest();

    @DataProvider(name = "email")
    public Object[][] dataEmail(){
        return new Object[][]{
                {fakerTest.getEmail()},
                {fakerTest.getEmail()},
                {fakerTest.getEmail()},
    };
    }


}
