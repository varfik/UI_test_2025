package pages.elements;

/**
 * Текстовый элемент страницы
 */
public class TextElement extends BaseElement {
    private static final String TEXT_XPATH = "//h4[text()='%s']/following-sibling::p";

    /**
     * Конструктор класса
     */
    private TextElement(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Получение отображаемого текста элемента
     */
    public String getText() {
        return baseElement.getText();
    }

    /**
     * Формирование Xpath по text
     */
    public static TextElement byLabelText(String text) {
        return new TextElement(TEXT_XPATH, text);
    }

}
