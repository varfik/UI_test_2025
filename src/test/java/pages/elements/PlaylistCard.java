package pages.elements;

/**
 * Класс карточки плейлиста
 */
public class PlaylistCard extends BaseElement {

    /**
     * Название плейлиста
     */
    private static final String PLAYLIST_NAME_XPATH = "//div[contains(@class, 'titleWrapper')]//a[contains(text(), " +
            "'%s')]";

    private PlaylistCard(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Нажатие на карточку плейлиста
     */
    public void press() { baseElement.click(); }

    /**
     * Формирование Xpath карточки по названию видео
     */
    public static PlaylistCard byPlaylistName(String title) {
        return new PlaylistCard(PLAYLIST_NAME_XPATH, title);
    }

    /**
     * Видимость плейлиста
     */
    public boolean isVisible(String playlistName) {
        return new PlaylistCard(
                PLAYLIST_NAME_XPATH,
                playlistName
        ).isDisplayed();
    }
}