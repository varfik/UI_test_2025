package pages;

import com.codeborne.selenide.SelenideElement;
import pages.elements.Button;
import pages.elements.Image;
import pages.elements.Input;
import services.AuthService;
import java.util.List;
import java.util.stream.Collectors;
import static com.codeborne.selenide.Selenide.*;

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

    /**
     * Кнопка открытия бокового меню
     */
    private final Button openButton = Button.byXPath("//button[contains(@class, 'header-module__headerLeftBurgerMenu')]");

    /**
     * Кнопка "История просмотра"
     */
    private final Button histButton = Button.byXPath("//a[@href='/my/history/']");
    /**
     * Кнопка "Добавить"
     */
    private final Button addButton = Button.byXPath(
            "//section[contains(@class, 'wdp-header-right-module__uploader')]" +
                    "//button[@aria-label='Добавить' and contains(@class, " +
                    "'freyja_char-icon-round-button__button_kkH9C')]");

    /**
     * Кнопка "Загрузить видео"
     */
    private final Button uploadVideoButton = Button.byXPath(
            "//ul[contains(@class, 'freyja_char-header-video-menu__list')]//*[contains(text(), 'Загрузить видео')]");

    /**
     * Меню кнопки "Добавить"
     */
    private final SelenideElement videoMenuList = $x("//ul[contains(@class, 'freyja_char-header-video-menu__list')]");

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
    public void goToPlaylists() {
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
     * Открытие страницы истории просмотров:
     * - Нажатие на кнопку открытия главного меню
     * - Нажатие на кнопку перехода в историю просмотров
     */
    public HistoryVideoPage openHistoryVideo() {
        closePopups();
        openButton.press();
        histButton.press();
        return new HistoryVideoPage();
    }

    /**
     * Открытие страницы загрузки видео:
     * - Нажатие на кнопку "Добавить"
     * - Нажатие на кнопку "Загрузить видео"
     * - Возвращает страницу для загрузки видео
     */
    public StudioRutubePage openStudioRutubePage() {
        // 1. Открываем Studio
        open("https://studio.rutube.ru/uploader");

        // 2. Проверяем авторизацию
        if ($("#login-form").exists()) {
            AuthService.auth(); // Переавторизуемся при необходимости
        }

        // 3. Альтернативный вариант через URL
        return new StudioRutubePage();
    }
}
