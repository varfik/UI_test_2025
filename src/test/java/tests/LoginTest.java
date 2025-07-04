package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import services.AuthService;

public class LoginTest extends BaseTest {
    /**
     * Тест выводит в консоль данные для входа (номер телефона и пароль)
     * - получение тестового телефона из конфига (TestConfig.getPhone())
     * - получение тестового пароля из конфига (TestConfig.getPassword())
     * - вывод значения в стандартный вывод (System.out)
     **/
    @Test
    public void showConfig() {
        System.out.println("Phone: " + TestConfig.getPhone());
        System.out.println("Password: " + TestConfig.getPassword());
    }

    /**
     * Тест проверяет успешность авторизации через отображение иконки пользователя
     * - авторизация пользователя
     * - проверка видимости иконки канала на главной странице после входа (isChannelIconVisible() = true)
     **/
    @Test
    public void userIconIsDisplayedAfterSuccessfulLogin() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        Assertions.assertTrue(mainPageAfterLogin.isChannelIconVisible(),
                              "Иконка канала не отображается — вход неуспешен!");
    }
}
