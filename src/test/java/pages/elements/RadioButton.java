package pages.elements;

import org.openqa.selenium.support.Color;

/** Элемент типа "Переключатель" **/
public class RadioButton extends BaseElement {

    private static final String LABEL_TEXT_XPATH = "//label[.//div[text()='%s']]";

    private RadioButton(String xpath, String param) {
        super(xpath, param);
    }

    /* Нажатие на сегмент в радиокнопке */
    public void select() {
        baseElement.click();
    }

    /* Формирование Xpath по тексту в label */
    public static RadioButton byLabelText(String labelText) {
        return new RadioButton(LABEL_TEXT_XPATH, labelText);
    }

    /* Получение цвета заднего фона элемента в формате () */
    public String getBackgroundColor() {
        try {
            String cssColor = baseElement.getCssValue("background-color");
            return Color.fromString(cssColor).asHex();
        } catch (Exception e) {
            return null;
        }
    }

}
