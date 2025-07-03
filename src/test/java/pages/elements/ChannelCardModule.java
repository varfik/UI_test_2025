package pages.elements;

/** Карточка канала <a> 0**/
public class ChannelCardModule extends BaseElement {
    private static final String SPAN_XPATH = "//a[.//span[contains(., '%s')]]";

    /* Конструктор класса */
    private ChannelCardModule(String xpath, String param) {
        super(xpath, param);
    }

    /* Формированию Xpath по span */
    public static ChannelCardModule bySpan(String spanText) {
        return new ChannelCardModule(SPAN_XPATH, spanText);
    }

    /* Нажатие на карточку канала */
    public void press() {
        baseElement.click();
    }

}
