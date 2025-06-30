package pages.elements;
import java.time.Duration;
import java.util.List;
import com.codeborne.selenide.ex.ElementNotFound;

/** Класс элемента типа Button **/
public class Button extends BaseElement {
    private static final String ARIA_LABEL_XPATH = "//button[@aria-label='%s']";
    private static final String TEXT_XPATH = "//button[contains(., '%s')]";

    private static final String ID_XPATH = "//button[@id='%s']";

    private Button(String xpath, String param){
        super(xpath, param);
    }

    /* Нажатие на кнопку */
    public void press(){
        baseElement.click();
    }

    /* Формирование Xpath по aria-label */
    public static Button byAriaLabel(String aria_label) {
        return new Button(ARIA_LABEL_XPATH, aria_label);
    }

    /* Формирование Xpath по ID */
    public static Button byId(String id) {
        return new Button(ID_XPATH, id);
    }

    /* Формирование Xpath по содежащемуся тексту */
    public static Button byText(String text) {
        return new Button(TEXT_XPATH, text);
    }

    public static Button byXPath(String xpath) {
        return new Button(xpath, "");
    }

    public static Button findOneVisible(List<Button> buttons) {
        return buttons.stream()
                .filter(Button::isDisplayed)
                .findFirst()
                .orElseThrow();
    }
}


