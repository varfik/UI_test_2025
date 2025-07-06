package pages.elements;

/**
 * Карточка видео <div>, отображающаяся на странице результатов поиска
 */
public class VideoCardModule extends BaseElement {

    /**
     * Название видео
     */
    private static final String VIDEO_TITLE_XPATH = "//div[contains(@class, 'titleWrapper')]//a[contains(text(), " +
            "'%s')]";

    /**
     * Название канала
     */
    private static final String CHANNEL_NAME_XPATH =
            "//div[contains(@class, 'authorWrapper')]//a[contains(text(), " + "'%s')]";

    /**
     * Кнопка меню видео
     */
    private final Button videoMenuButton = Button.byXPath("//button[contains(@class, " +
            "'freyja_char-more-menu__more-menu--anchor_bFSMm')]");

    /**
     * Кнопка добавления видео в "Смотреть позже"
     */
    private final Button addToWatchLaterButton = Button.byText("Смотреть позже");

    /**
     * Кнопка удаления видео из "Смотреть позже"
     */
    private final Button deleteFromWatchLaterButton = Button.byText("Удалить");

    /**
     * Конструктор класса
     */
    private VideoCardModule(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Клик по карточке (по названию видео или канала)
     */
    public void press() {
        baseElement.click();
    }

    /**
     * Отображение карточки видео
     */
    public boolean isCardsDisplayed() {
        return baseElement.isDisplayed();
    }

    /**
     * Формирование Xpath карточки по названию видео
     */
    public static VideoCardModule byVideoTitle(String title) {
        return new VideoCardModule(VIDEO_TITLE_XPATH, title);
    }

    /**
     * Формирование Xpath карточки по названию канала
     */
    public static VideoCardModule byChannelName(String channelName) {
        return new VideoCardModule(CHANNEL_NAME_XPATH, channelName);
    }

    /**
     * Открытие меню видео
     */
    public void openMenu() {
        videoMenuButton.press();
    }

    /**
     * Добавить видео в "Смотреть позже"
     */
    public void addToWatchLater() {
        addToWatchLaterButton.press();
    }

    /**
     * Удалить видео из "Смотреть позже"
     */
    public void deleteFromWatchLater() {
        deleteFromWatchLaterButton.press();
    }

    /**
     * Точный поиск
     **/
    public static VideoCardModule byExactVideoTitle(String title) {
        String exactXpath = String.format("//a[normalize-space()='%s']", title);
        return new VideoCardModule(exactXpath, title);
    }
}
