package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Базовый класс страницы
 */
public class BasePage {
    private static final String BASE_ELEMENT_XPATH = "//div[contains(@name,'%s')]";
    protected final SelenideElement basePage;
    protected final Class<? extends BasePage> pageClass;

    /**
     * Конструктор страницы
     */
    protected BasePage(Class<? extends BasePage> pageClass, String type) {
        basePage = $(String.format(BASE_ELEMENT_XPATH, type));
        this.pageClass = pageClass;
    }

    /**
     * Обновление страницы
     */
    public <T extends BasePage> T refresh() {
        Selenide.refresh();
        return (T) page(pageClass);
    }

    /**
     * Получение URl страницы
     */
    public String getCurrentUrl() {
        return WebDriverRunner.url();
    }
}

