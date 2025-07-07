package pages.elements;
import org.openqa.selenium.Keys;

/**
 * Класс элемента типа Input
 **/
public class Input extends BaseElement {
    private static final String ID_XPATH = "//input[@id='%s']";
    private static final String NAME_XPATH = "//input[@name='%s']";
    private static final String CLASS_XPATH = "//input[@class='%s']";
    private static final String LABEL_XPATH = "//input[@label='%s']";
    private static final String WRAPPER_XPATH = "//div[contains(@class, '%s')]/input";

    /**
     * Конструктор класса
     */
    private Input(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Заполнение поля
     */
    public void fill(String value) {
        baseElement.setValue(value);
    }
	
    public void clearField() {
        baseElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }
    
    /**
     * Получение значения в поле
     */
    public String getValue() {
        return baseElement.getValue();
    }

    /**
     * Формирование Xpath по ID
     */
    public static Input byId(String id) {
        return new Input(ID_XPATH, id);
    }

    /**
     * Формирование Xpath по name
     */
    public static Input byName(String name) {
        return new Input(NAME_XPATH, name);
    }

    /**
     * Формирование Xpath по class
     */
    public static Input byClass(String class_name) {
        return new Input(CLASS_XPATH, class_name);
    }

    /**
     * Формирование Xpath по label
     */
    public static Input byLabel(String label) {
        return new Input(LABEL_XPATH, label);
    }

    /**
     * Формирование Xpath по wrapper
     */
    public static Input byWrapper(String wrapper) {
        return new Input(WRAPPER_XPATH, wrapper);
    }


    /**
     * Формирование Xpath по строке xpath
     */
    public static Input byXPath(String xpath) {
        return new Input(xpath, "");
    }
	
}
