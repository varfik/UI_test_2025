package pages;

import pages.elements.Image;

/** Главная страница Rutube.ru после авторизации **/
public class MainAfterLoginPage extends BasePage {

    private final Image channelIconImage = Image.byClass("freyja_char-header-user-menu__avatar");

    /* Конструктор класса */
    public MainAfterLoginPage() {
        super(MainAfterLoginPage.class, "mainAfterLogin");
    }

    /* Иконка канала отображается */
    public boolean isChannelIconVisible() {
        return channelIconImage.isDisplayed();
    }

}
