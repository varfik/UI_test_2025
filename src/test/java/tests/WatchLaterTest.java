package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.WatchLaterPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

/**
 * Плейлист "Смотреть позже"
 * @author Soldunova
 */
public class WatchLaterTest extends BaseTest {
    private final String TEST_VIDEO = "Искусственный интеллект и его применение в жизни / Практика 2025 ЛЭТИ 3383";

    /**
     * Тестирование добавления видео в "Смотреть позже"
     * - авторизация
     * - поиск канала по названию тестового видео
     * - открывание меню у найденного видео
     * - добавление видео в "Смотреть позже"
     * - перейти на страницу "Смотреть позже"
     * - проверка наличия видео в списке
     */
    @Test
    public void testAddToWatchLater() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(TEST_VIDEO, SearchType.CHANNEL);

        resultsOfSearchPage.openVideoMenu();
        resultsOfSearchPage.addToWatchLater();

        WatchLaterPage watchLater = mainPageAfterLogin.openWatchLater();
        Assertions.assertTrue(watchLater.isVideoVisible(TEST_VIDEO), "Видео не добавилось в 'Смотреть позже'");
    }

    /**
     * Тестирование добавления видео в "Смотреть позже" и его удаления:
     * - авторизация
     * - перейти на страницу "Смотреть позже"
     * - удалить видео из "Смотреть позже"
     * - проверка отсутствие видео в списке
     */
    @Test
    public void testRemoveFromWatchLater() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        WatchLaterPage watchLater = mainPageAfterLogin.openWatchLater();

        watchLater.openVideoMenu();
        watchLater.deleteFromWatchLater();
        Assertions.assertFalse(watchLater.isVideoVisible(TEST_VIDEO), "Видео не удалилось из 'Смотреть позже'");
    }
}