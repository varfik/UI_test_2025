package pages.elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Базовый класс для элемента типа SelenideElement
 */
public class BaseElement {
    protected static final int WAIT_SECONDS = 8;
    protected final SelenideElement baseElement;

    /**
     * Конструктор базового элемента
     */
    protected BaseElement(String xpath, String attributeValue) {
        baseElement = $x(String.format(xpath, attributeValue));
    }

    /**
     * Проверка видимости элемента
     */
    public boolean isDisplayed() {
        try {
            return baseElement.shouldBe(visible, Duration.ofSeconds(WAIT_SECONDS)).isDisplayed();
        } catch (UndeclaredThrowableException | ElementNotFound e) {
            return false;
        }
    }
}
