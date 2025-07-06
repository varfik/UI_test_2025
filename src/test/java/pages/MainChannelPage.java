package pages;

import pages.elements.Button;

/**
 * Главная страница канала
 */
public class MainChannelPage extends BasePage {
    /**
     * Кнопка "Подписаться"
     */
    private final Button subscribeButton = Button.byText("Подписаться");

    /**
     * Кнопка "Вы подписаны"
     */
    private final Button subscribedButton = Button.byText("Вы подписаны");

    /**
     * Кнопка "Отписаться"
     */
    private final Button unsubscribeButton = Button.byText("Отписаться");

    /**
     * Кнопка "Плейлисты"
     */
    private final Button playlistsButton = Button.byXPath("//a[contains(@href,'/playlists/')]");

    /**
     * Конструктор класса
     */
    public MainChannelPage() {
        super(MainChannelPage.class, "mainChannel");
    }

    /**
     * Нажатие на кнопку "Подписаться"
     */
    public void clickSubscribeButton() {
        subscribeButton.press();
    }

    /**
     * Нажатие на кнопку "Вы подписаны"
     */
    public void clickSubscribedButton() {
        subscribedButton.press();
    }

    /**
     * Нажатие на кнопку "Отписаться"
     */
    public void clickUnsubscribeButton() {
        unsubscribeButton.press();
    }

    /**
     * На странице отображается кнопка "Вы подписаны"
     */
    public boolean isSubscribedButtonVisible() {
        return subscribedButton.isDisplayed();
    }

    /**
     * Переход на страницу плейлистов канала
     */
    public PlaylistsPage goToPlaylists() {
        playlistsButton.press();
        return new PlaylistsPage();
    }

    /**
     * Переход на страницу плейлистов пользователя
     */
    public PlaylistsPage goToMyPlaylists() {
        playlistsButton.press();
        return new PlaylistsPage();
    }
}
