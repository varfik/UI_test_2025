package pages.elements;

/**
 * Класс карточки плейлиста
 */
public class PlaylistCard extends BaseElement {

    /**
     * Название плейлиста
     */
    private static final String PLAYLIST_NAME_XPATH = "//div[contains(@class, " +
            "'wdp-card-description-module__titleWrapper')]//a[contains(@class, 'wdp-card-description-module__title')]";

    /**
     * Кнопка меню плейлиста
     */
    private static final String PLAYLIST_MENU_BUTTON_XPATH = "//div[contains(@class, " +
            "'wdp-card-description-module__titleWrapper')]//button[contains(@class, 'freyja_char-more-menu__more-menu')]";

    /**
     * Кнопка удаления в выпадающем меню
     */
    private static final String DELETE_PLAYLIST_BUTTON_XPATH =
            "//div[contains(@class, 'freyja_dropdown-menu-module__menu')]//button[contains(text(), 'Удалить') " +
                    "or contains(text(), 'Delete')]";

    /**
     * Кнопка "Сохранить плейлист" в выпадающем меню
     */
    private static final String SAVE_PLAYLIST_MENU_OPTION_XPATH =
            "//div[contains(@class, 'freyja_dropdown-menu-module__menu')]//button[contains(., 'Сохранить') " +
                    "or contains(., 'Save')]";

    private PlaylistCard(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Нажатие на меню плейлиста (троеточие)
     */
    public void pressMenuButton() {
        Button.byXPath(PLAYLIST_MENU_BUTTON_XPATH).press();
    }

    /**
     * Нажатие на кнопку сохранения плейлиста
     */
    public void pressSaveButton() {
        Button.byXPath(SAVE_PLAYLIST_MENU_OPTION_XPATH).press();
    }

    /**
     * Нажатие на кнопку удаления плейлиста
     */
    public void pressDeleteButton() {
        Button.byXPath(DELETE_PLAYLIST_BUTTON_XPATH).press();
    }

    /**
     * Видимость плейлиста
     */
    public boolean isVisible() {
        return new PlaylistCard(
                "//div[contains(@class, 'wdp-card-wrapper-module__wrapper')][.//a[contains(text(), '%s')]]",
                "Тестовый плейлист"
        ).isDisplayed();
    }
}