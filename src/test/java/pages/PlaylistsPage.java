package pages;

import pages.elements.Button;
import pages.elements.PlaylistCard;

/**
 * Страница с плейлистами пользователя
 */
public class PlaylistsPage extends BasePage {

    /**
     * Карточка плейлиста
     */
    private PlaylistCard playlistCard;

    /**
     * Конструктор класса
     */
    public PlaylistsPage() {
        super(PlaylistsPage.class, "playlists");
    }

    /**
     * Нажатие на кнопку меню в карточке плейлиста
     */
    public void clickMenuCardModule() {
        playlistCard.pressMenuButton();
    }

    /**
     * Удаление плейлиста
     */
    public void deletePlaylist() {
        playlistCard.pressDeleteButton();
    }

    /**
     * Сохранение плейлиста
     */
    public void savePlaylist() {
        playlistCard.pressSaveButton();
    }

    /**
     * Видимость плейлиста
     */
    public boolean isPlaylistVisible() {
        return playlistCard.isVisible();
    }
}
