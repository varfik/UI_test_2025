package pages;

import org.openqa.selenium.By;
import pages.elements.Button;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница Истории просмотров
 */
public class HistoryVideoPage extends BasePage {
    /**
     * XPath кнопки "Меню" для текущего видео
     */
    private static final String MENU_BUTTON_XPATH =
            "/following-sibling::button[contains(@class, 'freyja_char-more-menu__more-menu')]";

    /**
     * XPath кнопки "Удалить из истории" из "Меню" для текущего видео
     */
    private static final String DELETE_BUTTON_XPATH =
            "//li[contains(@class, 'freyja_char-menu-list__menu-list-item')]" +
                    "//*[contains(@class, 'svg-icon--IconTrash')]";

    /**
     * Конструктор класса
     */
    public HistoryVideoPage() {
        super(HistoryVideoPage.class, "HistoryVideo");
    }

    /**
     * Удаление видео с названием videoTitle из Истории просмотра:
     * - Открытие "Меню" для видео
     * - Нажатие кнопки "Удалить из истории"
     * */
    public void removeVideoFromHistory(String videoTitle) {
        openVideoMenu(videoTitle);
        Button.byXPath(DELETE_BUTTON_XPATH).press();
    }

    /**
     * Проверка наличия видео в Истории просмотров:
     * - Получение XPath видео с указанным названием
     * - Проверка видимости видео в Истории (возвращает true, если есть, иначе - false)
     */
    public boolean isVideoPresent(String videoTitle) {
        try {
            if (isHistoryEmpty()) { return false; }
            String xpath = String.format("//a[contains(@class, 'wdp-card-description-module__videoTitle') " +
                            "and contains(text(), '%s')]", videoTitle);

            return !$$(By.xpath(xpath)).filter(visible).isEmpty();

        } catch (Throwable e) {
            System.out.println("Ошибка при проверке наличия видео: " + e.getMessage());
            return false;
        }
    }

    /**
     * Очищение Истории просмотров:
     * - Если видео нет, метод просто завершится без действий.
     * - Нажатие кнопки "Очистить историю"
     * - В появившемся окне нажатие на кнопку подтверждения "Очистить"
     */
    public void cleanHistory() {
        Button cleanHistoryButton = Button.byXPath(
                "//button[contains(@class, 'wdp-my-delete-history-button-module__button')]" +
                        "//span[text()='Очистить историю']/.."
        );
        // Если кнопка есть
        if (cleanHistoryButton.isDisplayed()) {
            cleanHistoryButton.press();
            Button popupConfirmButton = Button.byXPath(
                    "//button[contains(@class, 'wdp-popup-module__button') and text()='Очистить']"
            );
            popupConfirmButton.press();
        }
        // Если кнопки нет - история уже пуста, очистка не требуется.
    }
    
    /**
     * Проверка, есть ли видео в Истории просмотров
     * - Получение XPath какого-то видео, которое есть в Истории просмотров
     * - Если такое видео есть и отображается, возвращается true
     * - Иначе (если видео нет) возвращает false
     */
    public boolean isHistoryEmpty() {
        return Button.byXPath(
                "//section[contains(@class, 'wdp-my-empty-module__empty')]" +
                        "//p[contains(text(), 'Тут пока пусто')]").isDisplayed();
    }

    /**
     * Открытие меню для видео
     * - Получение XPath видео с указанным названием
     * - Получение кнопки меню для этого видео
     * - Открытие меню для видео с помощью нажатия соответствующей кнопки
     */
    private void openVideoMenu(String videoTitle) {
        String fullXpath = String.format("//a[contains(@class, 'wdp-card-description-module__videoTitle') " +
                "and contains(text(), '%s')]", videoTitle) + MENU_BUTTON_XPATH;
        Button.byXPath(fullXpath).press();
    }
}
