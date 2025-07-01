package pages.elements;

/** Класс элемента типа изображение (img) **/
public class Image extends BaseElement {
    private static final String CLASS_XPATH = "//img[contains(@class, '%s')]";

    private Image(String xpath, String param){
        super(xpath, param);
    }

    /* Формирование Xpath по class */
    public static Image byClass(String class_name){
        return new Image(CLASS_XPATH, class_name);
    }
}
