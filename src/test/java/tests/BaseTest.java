package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/** Общий класс для других тестовых классов
 *  - от него наследуются другие классы
 *  - экземпляр класса не создается (abstract) **/
abstract public class BaseTest {

    /* URL главной страницы сайта */
    protected final static String BASE_URL = "https://rutube.ru/";

    /* Выполнение метода перед каждым запуском тестов */
    @BeforeEach
    public void init(){
        setUp();
        open();
    }

    /* Выполнение метода после каждого закрытия тестов */
    @AfterEach
    public void tearDown() { Selenide.closeWebDriver(); }

    /* Открытие страницы по базовой URL */
    protected void open() {
        Selenide.open(BASE_URL);
        $("body").shouldBe(visible);
    }

    /* Установка, настройка, инициализация браузера */
    private void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 40_000;
    }

}
