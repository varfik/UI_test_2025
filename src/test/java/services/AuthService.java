package services;

import pages.MainAfterLoginPage;
import pages.MainPage;

/** Сервис авторизации пользователя
 *  Выполняет следующие действия для входа в систему:
 *      - открытие главной страницы
 *      - закрытие всплывших окон
 *      - нажатие на кнопку для входа на сайт
 *      - ввод номера телефона
 *      - нажатие кнопки для продолжения
 *      - ввод пароля
 *      - нажатие кнопки "Войти" (переход на авторизованную страницу)
 *      - закрытие всплывших окон **/
public class AuthService {
    public static MainAfterLoginPage auth() {
        MainPage mainPage = MainPage.openMainPage();
        mainPage.closePopups();
        mainPage.clickLoginButton();
        mainPage.switchToLoginFrame();
        mainPage.fillPhone();
        mainPage.clickContinue();
        mainPage.fillPassword();
        MainAfterLoginPage mainAfterLoginPage = mainPage.clickLogin();
        mainAfterLoginPage.closePopups();
        return mainAfterLoginPage;
    }

}
