package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/** Общий класс для других тестовых классов
 *  - от него наследуются другие классы
 *  - экземпляр класса не создается (abstract) **/
abstract public class BaseTest {

    /* Установка, настройка, инициализация браузера */
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 20_000;
    }

    /* Выполнение метода перед каждым запуском тестов */
    @BeforeEach
    public void init(){
        setUp();
    }

    /* Выполнение метода после каждого закрытия тестов */
    @AfterEach
    public void tearDown() { Selenide.closeWebDriver(); }

}
