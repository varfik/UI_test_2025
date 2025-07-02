package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class LoginTest extends BaseTest {

    private final static String BASE_URL = "https://rutube.ru/";


    @Test
    void showConfig() {
        System.out.println("Phone: " + TestConfig.getPhone());
        System.out.println("Password: " + TestConfig.getPassword());
    }

    @Test
    public void userIconIsDisplayedAfterSuccessfulLogin() {
        MainPage mainPage = new MainPage();
        mainPage.open(BASE_URL);
        mainPage.login();

        Assertions.assertTrue(mainPage.isChannelIconVisible(), "Иконка канала не отображается — вход неуспешен!");
    }
}
