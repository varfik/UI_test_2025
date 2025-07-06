package pages.elements;

/**
 * Класс элемента типа Button
 */
public class Button extends BaseElement {

    private static final String ARIA_LABEL_XPATH = "//button[@aria-label='%s']";
    private static final String TEXT_XPATH = "//button[contains(., '%s')]";
    private static final String TEXT_INSIDE_A_XPATH = "//a[contains(., '%s')]";
    private static final String ID_XPATH = "//button[@id='%s']";

    private Button(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Нажатие на кнопку
     */
    public void press() {
        baseElement.click();
    }

    /**
     * Возвращает видимый текст элемента кнопки.
     */
    public String getText() {
        return baseElement.getText();
    }

    /**
     * Получает значение указанного атрибута элемента кнопки.
     */
    public String getAttribute(String attributeName) {
        return baseElement.getAttribute(attributeName);
    }

    /**
     * Формирование Xpath по aria-label
     */
    public static Button byAriaLabel(String aria_label) {
        return new Button(ARIA_LABEL_XPATH, aria_label);
    }

    /**
     * Формирование Xpath по ID
     */
    public static Button byId(String id) {
        return new Button(ID_XPATH, id);
    }

    /**
     * Формирование Xpath по содержащемуся тексту в <button>
     */
    public static Button byText(String text) {
        return new Button(TEXT_XPATH, text);
    }

    /**
     * Формирование Xpath по содержащемуся тексту в <a>
     */
    public static Button byTextInsideA(String text) {
        return new Button(TEXT_INSIDE_A_XPATH, text);
    }

    /**
     * Формирование Xpath согласно полученной Xpath строке
     */
    public static Button byXPath(String xpath) {
        return new Button(xpath, "");
    }

}


