package pages;

import pages.elements.Button;

import javax.swing.*;

/** Главная страница канала **/
public class MainChannelPage extends BasePage {

    /* Кнопка подписаться */
    private final Button subscribeButton = Button.byText("Подписаться");
    private final Button subscribedButton = Button.byText("Вы подписаны");

    /* Конструктор класса */
    public MainChannelPage() {
        super(MainChannelPage.class, "mainChannel");
    }

    /* Нажатие на кнопку "Подписаться" */
    public void clickSubscribeButton() {
        subscribeButton.press();
    }

    /* На странице отображается кнопка "Вы подписаны" */
    public boolean isSubscribedButtonVisible() {
        return subscribedButton.isDisplayed();
    }
}
