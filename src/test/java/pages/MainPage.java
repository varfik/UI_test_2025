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
    /**
     * Кнопка для перехода к окну входу
     */
    private final Button loginButton = Button.byText("Вход и регистрация");

    /**
     * Поле ввода номера телефона
     */
    private final Input phoneInput = Input.byId("phone-or-email-login");

    /**
     * Кнопка "Продолжить"
     */
    private final Button continueButton = Button.byId("submit-login-continue");

    /**
     * Поле ввода пароля
     */
    private final Input passwordInput = Input.byId("login-password");

    /**
     * Кнопка "Войти"
     */
    private final Button submitButton = Button.byId("submit-login");

    /**
     * Кнопка "Закрыть"
     */
    private final Button closeButton = Button.byAriaLabel("Закрыть");

    /**
     * Кнопка "Ок"
     */
    private final Button okButton = Button.byText("Ок");
    /**
     * Кнопка "Не надо"
     */
    private final Button noNeedButton = Button.byText("Не надо");

    /**
     * Конструктор класса
     */
    public MainPage() {
        super(MainPage.class, "main");
    }

    /**
     * Открытие главной страницы
     */
    public static MainPage openMainPage() {
        return new MainPage();
    }

    /**
     * Нажатие на кнопку входа: "Вход и регистрация"
     */
    public void clickLoginButton() {
        loginButton.press();
    }

    /**
     * Переключение на iframe для ввода данных
     */
    public void switchToLoginFrame() {
        switchTo().frame(0);
    }

    /**
     * Ввод номера телефона
     */
    public void fillPhone() {
        phoneInput.fill(TestConfig.getPhone());
    }

    /**
     * Нажатие на кнопку "Продолжить"
     */
    public void clickContinue() {
        continueButton.press();
    }

    /**
     * Ввод пароля
     */
    public void fillPassword() {
        passwordInput.fill(TestConfig.getPassword());
    }

    /**
     * Нажатие на кнопку "Войти"
     */
    public MainAfterLoginPage clickLogin() {
        submitButton.press();
        return new MainAfterLoginPage();
    }

    /**
     * Закрытие всплывающих окон
     */
    public void closePopups() {
        List<Button> closeButtons = List.of(closeButton, okButton, noNeedButton).stream().filter(Button::isDisplayed)
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
