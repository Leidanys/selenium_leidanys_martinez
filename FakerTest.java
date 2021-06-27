package ExamenFinal;

import com.github.javafaker.Faker;

public class FakerTest {
    public static Faker faker=new Faker();

    public String getEmail(){
        return faker.internet().emailAddress();
    }
}
