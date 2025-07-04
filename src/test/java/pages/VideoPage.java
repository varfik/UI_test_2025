package pages;

import pages.elements.Button;
import pages.elements.Input;

public class VideoPage extends BasePage {
    /* Кнопка "Поделиться */
    private final Button shareButton = Button.byText("Поделиться");

    /* Поле в "Поделиться" с ссылкой на видео */
    private final Input shareInput = Input.byWrapper("inputWrapper");

    /* Конструктор класса */
    public VideoPage() {
        super(VideoPage.class, "video");
    }

    /* Нажатие на кнопку "Поделиться" */
    public void clickShareButton() {
        shareButton.press();
    }

    /* Получения ссылки из поля "Поделиться" */
    public String getLinkToShare() {
        return shareInput.getValue();
    }

}
