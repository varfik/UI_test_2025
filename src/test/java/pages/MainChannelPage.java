package pages;

import pages.elements.Button;

/** Главная страница канала **/
public class MainChannelPage extends BasePage {

    /* Кнопка подписаться */
    private final Button subscribeButton = Button.byText("Подписаться");

    /* Конструктор класса */
    public MainChannelPage() {
        super(MainChannelPage.class, "mainChannel");
    }

    /* Нажатие на кнопку "Подписаться" */
    public void clickSubscribeButton() {
        subscribeButton.press();
    }

}
