package pages;

import pages.elements.Button;
import pages.elements.Image;
import pages.elements.Input;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница Rutube.ru после авторизации
 **/
public class MainAfterLoginPage extends BasePage {
    /**
     * Иконка канала пользователя
     */
    private final Image channelIconImage = Image.byClass("freyja_char-header-user-menu__avatar");

    /**
     * Поисковая строка
     */
    private final Input searchInput = Input.byClass("wdp-search-line-module__input");

    /**
     * Кнопка поиска
     */
    private final Button searchButton = Button.byAriaLabel("Отправить форму поиска");

    /**
     * Кнопка перехода в плейлисты
     */
    private final Button playlistsButton = Button.byXPath(
            "//a[contains(@class,'wdp-link-module__link') and contains(@href,'/my/playlists/')]");

    /**
     * Кнопка перехода в "Смотреть позже"
     */
    private final Button watchLaterButton = Button.byText("Смотреть позже");

    /**
     * Кнопка "Профиль"
     */
    private final Button profileButton = Button.byTextInsideA("Профиль");

    private final Button addButton = Button.byXPath(
            "//section[contains(@class, 'wdp-header-right-module__uploader')]" +
                    "//button[@aria-label='Добавить' and contains(@class, 'freyja_char-icon-round-button__button_kkH9C')]"
    );

    private final Button uploadVideoButton = Button.byXPath(
            "//ul[contains(@class, 'freyja_char-header-video-menu__list')]//*[contains(text(), 'Загрузить видео')]"
    );
    /**
     * Конструктор класса
     */
    public MainAfterLoginPage() {
        super(MainAfterLoginPage.class, "mainAfterLogin");
    }

    /**
     * Иконка канала отображается
     */
    public boolean isChannelIconVisible() {
        return channelIconImage.isDisplayed();
    }

    /**
     * Ввод запроса в строку поиска
     */
    public void fillSearchInput(String searchQuery) {
        searchInput.fill(searchQuery);
    }

    /**
     * Нажатие на кнопку поиска
     */
    public void clickSearchButton() {
        searchButton.press();
    }

    /**
     * Переход на страницу плейлистов
     */
    public PlaylistsPage goToPlaylists() {
        playlistsButton.press();
        return new PlaylistsPage();
    }

    /**
     * Переход на страницу "Смотреть позже"
     */
    public WatchLaterPage openWatchLater() {
        watchLaterButton.press();
        return new WatchLaterPage();
    }

    /**
     * Нажатие на иконку пользователя
     */
    public void clickChannelIconImage() {
        channelIconImage.press();
    }

    /**
     * Нажатие на раздел "Профиль"
     */
    public ProfilePage clickProfileButton() {
        profileButton.press();
        return new ProfilePage();
    }

    /**
     * Закрытие всплывающих окон
     */
    public void closePopups() {

        List<Button> closeButtons = List.of(Button.byAriaLabel("Закрыть"), Button.byText("Ок"),
                                            Button.byText("Не надо")).stream().filter(Button::isDisplayed)
                                        .collect(Collectors.toList());

        for (Button button : closeButtons) {
            try {
                button.press();
            } catch (Exception e) {
                System.out.println("Не удалось кликнуть по кнопке: " + e.getMessage());
            }
        }
    }

    /**
     * Открытие раздела Истории просмотров
     */
    public HistoryVideoPage openHistoryVideo() {
        Button.byXPath(
                      "//section[@aria-label='Моё']" + "//ul[@class='menu-my-group-links-module__linksList']" + "//a" +
                              "[contains(@href, '/my/history/')]" + "/div[@class='menu-link-module__link']" + "/div" +
                              "[@class='menu-link-module__linkContent' and text()='История просмотра']" + "/ancestor" +
                              "::a")
              .press();
        return new HistoryVideoPage();
    }
    
    public StudioRutubePage openStudioRutubePage() {
        try {
            addButton.press();

            // 2. Ожидаем появление меню (используем $x для XPath)
            $x("//ul[contains(@class, 'freyja_char-header-video-menu__list')]")
                    .shouldBe(visible, Duration.ofSeconds(30));

            // 3. Кликаем по кнопке "Загрузить видео" в меню
            uploadVideoButton
                    .getBaseElement()
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .shouldBe(enabled)
                    .click();

            return new StudioRutubePage();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось открыть студию Rutube: " + e.getMessage(), e);
        }
    }
}
