package pages.elements;

/** Карточка канала <a> 0**/
public class CardModule extends BaseElement {
    private static final String SPAN_XPATH = "//a[.//span[contains(., '%s')]]";

    /* Конструктор класса */
    private CardModule(String xpath, String param) {
        super(xpath, param);
    }

    /* Формированию Xpath по span */
    public static CardModule bySpan(String spanText) {
        return new CardModule(SPAN_XPATH, spanText);
    }

    /* Нажатие на карточку канала */
    public void press() {
        baseElement.click();
    }

}
