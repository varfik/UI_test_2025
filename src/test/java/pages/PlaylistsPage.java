package pages;

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
    public PlaylistsPage(String playlistName) {
        super(PlaylistsPage.class, "playlists");
        playlistCard = PlaylistCard.byPlaylistName(playlistName);
    }

    /**
     * Нажатие на карточку плейлиста
     */
    public PlaylistPage clickPlaylist(){
        playlistCard.press();
        return new PlaylistPage();
    }

    /**
     * Видимость плейлиста
     */
    public boolean isPlaylistVisible(String playlistName) {
        return playlistCard.isVisible(playlistName);
    }
}
