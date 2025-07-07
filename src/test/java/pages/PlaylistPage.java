package pages;

import pages.elements.Button;

public class PlaylistPage extends BasePage {
    /**
     * Кнопка сохранения плейлиста
     */
    private final Button savePlaylistButton = Button.byText("Сохранить плейлист");

    /**
     * Кнопка удаления плейлиста
     */
    private final Button deletePlaylistButton = Button.byText("Плейлист сохранён");

    /**
     * Кнопка главного меню
     */
    private final Button menuButton = Button.byXPath("//*[@id='root']/div/div[1]/div[2]/header/section[1]/button");

    /**
     * Кнопка перехода в раздел плейлистов
     */
    private final Button playlistsButton = Button.byXPath(
            "//a[contains(@class,'wdp-link-module__link') and contains(@href,'/my/playlists/')]");

    /**
     * Конструктор класса
     */
    public PlaylistPage() {
        super(PlaylistsPage.class, "playlists");
    }

    /**
     * Сохранение плейлиста
     */
    public void savePlaylist() {
        savePlaylistButton.press();
    }

    /**
     * Удаление плейлиста
     */
    public void deletePlaylist() {
        deletePlaylistButton.press();
    }

    /**
     * Открытие меню
     */
    public void openMenu() { menuButton.press(); }

    /**
     * Переход на страницу плейлистов пользователя
     */
    public PlaylistsPage goToMyPlaylists(String playlistName) {
        playlistsButton.press();
        return new PlaylistsPage(playlistName);
    }
}
