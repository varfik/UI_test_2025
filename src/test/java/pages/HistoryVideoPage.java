package pages;

import pages.elements.Button;

/**
 * Страница Истории просмотров
 */
public class HistoryVideoPage extends BasePage {
    /**
     * XPath видео
     */
    private static final String VIDEO_CARD_XPATH =
            "//div[contains(@class, 'wdp-card-wrapper-module_wrapper') and " + ".//a[contains(@title, '%s')]]";

    /**
     * Кнопка меню для видео
     */
    private static final String MENU_BUTTON_XPATH = "//button[contains(@class, ' freyja_char-more-menu__more-menu')]";

    /**
     * Кнопка очистки истории
     */
    private final Button cleanHistoryButton = Button.byXPath(
            "//button[contains(@class, 'wdp-my-delete-history-button-module__button') and .//span[text()='Очистить']]");

    /**
     * Кнопка удаления из истории
     */
    private final Button removeFromHistoryButton = Button.byXPath(
                "//li[contains(@class, 'freyja_char-menu-list__menu-list-item')]" + "//svg[contains(@class, " + "'svg"
                        + "-icon--IconTrash')]");

    /**
     * Кнопка подтверждения удаления из истории
     */
    private final Button popupConfirmButton = Button.byXPath(
                "//div[contains(@class, 'wdp-popup-module_popup')]//button[contains(text(), 'Очистить')]");

    /**
     * Кнопка видео в истории просмотра
     */
    private final Button videoInHisturyButton = Button.byXPath(
                "//div[contains(@class, 'wdp-my-history-module_grid')]" + "//div[@data-pos-num]");
    
    /**
     * Конструктор класса
     */
    public HistoryVideoPage() {
        super(HistoryVideoPage.class, "HistoryVideo");
    }
    
    /**
     * Открытие меню для видео
     * - Получение XPath видео с указанным названием
     * - Получение кнопки меню для этого видео
     * - Открытие меню для видео с помощью нажатия соответствующей кнопки
     */
    public void openVideoMenu(String videoTitle) {
        String videoXpath = String.format(VIDEO_CARD_XPATH, videoTitle);
        Button menuButton = Button.byXPath(videoXpath + MENU_BUTTON_XPATH);
        menuButton.press();
    }

    /**
     * Удаление видео из истории (нажатие на кнопку "Удалить из истории")
     */
    public void removeVideoFromHistory(String videoTitle) { removeFromHistoryButton.press(); }

    /**
     * Проверка наличия видео в Истории просмотров:
     * - Получение XPath видео с указанным названием
     * - Проверка видимости видео в Истории (возвращает true, если есть, иначе - false)
     */
    public boolean isVideoPresent(String videoTitle) {
        try {
            String xpath = String.format(VIDEO_CARD_XPATH, videoTitle);
            return Button.byXPath(xpath).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Очищение Истории просмотров:
     * - Нажатие кнопки "Очистить историю"
     * - В появившемся окне нажатие на кнопку подтверждения "Очистить"
     */
    public void cleanHistory() {
        cleanHistoryButton.press();
        popupConfirmButton.press();
    }

    
    /**
     * Проверка, есть ли видео в Истории просмотров
     * - Получение XPath какого-то видео, которое есть в Истории просмотров
     * - Если такое видео есть и отображается, возвращается true
     * - Иначе (если видео нет) возвращает false
     */
    public boolean isHistoryNotEmpty() {
        try {
            return videoInHisturyButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
