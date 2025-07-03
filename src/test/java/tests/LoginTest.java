package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.MainPage;
import services.AuthService;

public class LoginTest extends BaseTest {

    /* Вывод данных для вход */
    @Test
    void showConfig() {
        System.out.println("Phone: " + TestConfig.getPhone());
        System.out.println("Password: " + TestConfig.getPassword());
    }

    /* Успешное отображение иконки пользователя после входа */
    @Test
    public void userIconIsDisplayedAfterSuccessfulLogin() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        Assertions.assertTrue(mainPageAfterLogin.isChannelIconVisible(), "Иконка канала не отображается — вход неуспешен!");
    }
}
