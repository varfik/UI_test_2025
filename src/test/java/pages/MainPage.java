package pages;

import java.util.List;
import java.util.stream.Collectors;

import pages.elements.*;
import tests.TestConfig;

import static com.codeborne.selenide.Selenide.*;

/**
 * Главная страница сайта rutube.ru до авторизации
 **/
public class MainPage extends BasePage {
    /* Кнопка для перехода к окну входу */
    private final Button loginButton = Button.byText("Вход и регистрация");

    /* Конструктор класса */
    public MainPage() {
        super(MainPage.class, "main");
    }

    /* Нажатие на кнопку входа: "Вход и регистрация" */
    public void clickLoginButton() {
        loginButton.press();
    }

    /* Переключение на iframe для ввода данных */
    public void switchToLoginFrame() {
        switchTo().frame(0);
    }

    /* Ввод номера телефона */
    public void fillPhone() {
        Input.byId("phone-or-email-login").fill(TestConfig.getPhone());
    }

    /* Нажатие на кнопку "Продолжить" */
    public void clickContinue() {
        Button.byId("submit-login-continue").press();
    }

    /* Ввод пароля */
    public void fillPassword() {
        Input.byId("login-password").fill(TestConfig.getPassword());
    }

    /* Нажатие на кнопку "Войти" */
    public MainAfterLoginPage clickLogin() {
        Button.byId("submit-login").press();
        return new MainAfterLoginPage();
    }

    /* Открытие главной страницы */
    public static MainPage openMainPage() {
        return new MainPage();
    }

    /* Закрытие всплывающих окон */
    public void closePopups() {
        List<Button> closeButtons = List.of(
                Button.byAriaLabel("Закрыть"),
                Button.byText("Ок"),
                Button.byText("Не надо"))
                                        .stream()
                                        .filter(Button::isDisplayed)
                                        .collect(Collectors.toList());

        for (Button button : closeButtons) {
            try {
                button.press();
            } catch (Exception e) {
                System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
            }
        }
    }

}
