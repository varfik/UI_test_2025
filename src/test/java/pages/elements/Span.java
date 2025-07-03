package pages.elements;

public class Span extends BaseElement {

    private static final String TEXT_XPATH = "//span[contains(., 's')] ";

    private Span(String xpath, String param) {
        super(xpath, param);
    }

    /* Формирование Xpath по text */
    public static Span byText(String text) {
        return new Span(TEXT_XPATH, text);
    }

    /* Нажатие на span */
    public void press() {
        baseElement.click();
    }
}
