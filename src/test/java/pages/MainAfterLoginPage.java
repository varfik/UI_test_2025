package pages;

import pages.elements.Button;
import pages.elements.Image;
import pages.elements.Input;

import java.util.List;
import java.util.stream.Collectors;

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
     * Кнопка открывания главного меню
     */
    private final Button openMenuButton = Button.byXPath(
            "//button[contains(@class, 'header-module__headerLeftBurgerMenu')]");

    /**
     * Кнопка перехода в плейлисты
     */
    private final Button playlistsButton = Button.byXPath(
            "//a[contains(@class,'wdp-link-module__link') and contains(@href,'/my/playlists/')]");

    /**
     * Кнопка перехода в "Смотреть позже"
     */
    private final Button watchLaterButton = Button.byXPath("//a[@href='/my/future/']");

    /**
     * Кнопка "Профиль"
     */
    private final Button profileButton = Button.byTextInsideA("Профиль");


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
    //public PlaylistsPage goToPlaylists() {
    //    playlistsButton.press();
    //    return new PlaylistsPage();
    //}

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

    /**
     * Открытие раздела плейлистов
     */
    public PlaylistsPage goToPlaylists(String playlistName) {
        closePopups();
        openMenuButton.press();
        playlistsButton.press();
        return new PlaylistsPage(playlistName);
    }

    /**
     * Открытие раздела "Смотреть позже"
     */
    public WatchLaterPage openWatchLater() {
        closePopups();
        openMenuButton.press();
        watchLaterButton.press();
        return new WatchLaterPage();
    }
}
