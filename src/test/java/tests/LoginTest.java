package tests;

import org.junit.jupiter.api.Test;
import pages.MainPage;

public class LoginTest extends BaseTest {

    private final static String BASE_URL = "https://rutube.ru/";

    @Test
    public void checkHref() {
        MainPage mainPage = new MainPage();
        mainPage.open(BASE_URL);
        mainPage.login();
    }
}
