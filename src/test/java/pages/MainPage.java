package pages;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.Selenide;
import tests.TestConfig;
import pages.elements.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/** Главная страница сайта rutube.ru **/

public class MainPage extends BasePage {
    /* Список кнопок, позволяющих перейти к окну для авторизации */
    private List<Button> loginButtons;

    /* Первая видимая кнопка для выполнения входа */
    private Button firstLoginButton;

    /* Конструктор класса */
    public MainPage() {
        super(MainPage.class, "main");
    }

    /* Открытие главной страницы */
    public void open(String url) {
        Selenide.open(url);
        $("body").shouldBe(visible);
        closePopups();
    }

    /* Атворизация на главной странице */
    public void login() {
        clickOnLogin();
        /* Переключение на iframe для ввода данных */
        switchTo().frame(0);
        /* Ввод номера телефона */
        Input phoneInput = Input.byId("phone-or-email-login");
        phoneInput.fill(TestConfig.getPhone());
        Button continueButton = Button.byId("submit-login-continue");
        continueButton.press(); /* (!) Возможна captcha -> необходима точка остановки */
        /* Ввод пароля */
        Input passwordInput = Input.byId("login-password");
        passwordInput.fill(TestConfig.getPassword());
        Button loginButton = Button.byId("submit-login");
        loginButton.press(); /* (!) Возможна captcha -> необходима точка остановки */
        closePopups();
    }

    /* Нажатие на кнопку входа */
    private void clickOnLogin() {
        loginButtons = List.of(
                Button.byText("Вход и регистрация"),
                Button.byText("Войти")
        );
        firstLoginButton = Button.findOneVisible(loginButtons);
        firstLoginButton.press();
    }

    /* Закрытие всплывающих окон */
    private void closePopups() {
        List<Button> closeButtons = List.of(Button.byAriaLabel("Закрыть"), Button.byText("Ок"), Button.byText("Не надо")).stream().filter(Button::isDisplayed).collect(Collectors.toList());

        for (Button button : closeButtons) {
            try {
                button.press();
            } catch (Exception e) {
                System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
            }
        }
    }

}
