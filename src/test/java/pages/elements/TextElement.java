package pages.elements;

public class TextElement extends BaseElement {

    private static final String TEXT_XPATH = "//h4[text()='%s']/following-sibling::p";

    private TextElement(String xpath, String param) {
        super(xpath, param);
    }

    /* Формирование Xpath по text */
    public static TextElement byLabelText(String text) {
        return new TextElement(TEXT_XPATH, text);
    }
    public String getText() {
        return baseElement.getText();
    }
}
