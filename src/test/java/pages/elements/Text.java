package pages.elements;

/**
 * Текстовый элемент страницы
 */
public class Text extends BaseElement {
    private static final String TEXT_XPATH = "//h4[text()='%s']/following-sibling::p";

    /**
     * Конструктор класса
     */
    private Text(String xpath, String param) {
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
    public static Text byLabelText(String text) {
        return new Text(TEXT_XPATH, text);
    }

}
