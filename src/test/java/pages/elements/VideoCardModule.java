package pages.elements;

/** Карточка видео <div>, отображающаяся на странице результатов поиска **/
public class VideoCardModule extends BaseElement {

    /* Название видео */
    private static final String VIDEO_TITLE_XPATH = "//div[contains(@class, 'titleWrapper')]//a[contains(text(), '%s')]";

    /* Название канала */
    private static final String CHANNEL_NAME_XPATH = "//div[contains(@class, 'authorWrapper')]//a[contains(text(), '%s')]";

    private VideoCardModule(String xpath, String param) {
        super(xpath, param);
    }

    /** Формирование Xpath карточки по названию видео */
    public static VideoCardModule byVideoTitle(String title) {
        return new VideoCardModule(VIDEO_TITLE_XPATH, title);
    }

    /** Формирование Xpath карточки по названию канала */
    public static VideoCardModule byChannelName(String channelName) {
        return new VideoCardModule(CHANNEL_NAME_XPATH, channelName);
    }

    /** Клик по карточке (по названию видео или канала) */
    public void press() {
        baseElement.click();
    }

}
