package pages.elements;

/**
 * Класс элемента типа изображение (img)
 */
public class Image extends BaseElement {
    private static final String CLASS_XPATH = "//img[contains(@class, '%s')]";

    private static final String TEXT_XPATH = "//img[contains(., '%s')]";

    /**
     * Конструктор класса
     */
    private Image(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Нажатие на изображение
     */
    public void press() {
        baseElement.click();
    }

    /**
     * Формирование Xpath по class
     */
    public static Image byClass(String class_name) {
        return new Image(CLASS_XPATH, class_name);
    }

    /**
     * Формирование Xpath по text
     */
    public static Image byText(String text) {
        return new Image(TEXT_XPATH, text);
    }
}
