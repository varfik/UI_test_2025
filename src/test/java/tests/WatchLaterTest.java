package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.VideoPage;
import pages.WatchLaterPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

/**
 * Плейлист "Смотреть позже"
 * @author Soldunova
 */
public class WatchLaterTest extends BaseTest {
    /**
     * Тестирование добавления видео в "Смотреть позже"
     * - авторизация
     * - поиск видео по названию
     * - переход на страницу видео
     * - добавление видео в "Смотреть позже"
     * - переход на страницу "Смотреть позже"
     * - проверка наличия видео в списке
     */
    @Test
    public void testAddToWatchLater() {
        String TEST_VIDEO = "Искусственный интеллект и его применение в жизни / Практика 2025 ЛЭТИ 3383";

        AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(TEST_VIDEO, SearchType.VIDEO);

        VideoPage videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();
        videoPage.changeWatchLaterStatus();

        WatchLaterPage watchLater = videoPage.openWatchLater();
        Assertions.assertTrue(watchLater.isVideoVisible(TEST_VIDEO),
                "Видео не добавилось в 'Смотреть позже'");
    }

    /**
     * Тестирование удаление видео из "Смотреть позже":
     * - авторизация
     * - переход на страницу "Смотреть позже"
     * - переход на страницу видео по названию
     * - удаление видео из "Смотреть позже" нажатием кнопки
     * - переход на страницу "Смотреть позже"
     * - проверка отсутствия видео в списке
     */
    @Test
    public void testRemoveFromWatchLater() {
        String TEST_VIDEO = "Космос и его составляющие / Практика 2025 ЛЭТИ 3383";

        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        WatchLaterPage watchLater = mainPageAfterLogin.openWatchLater();

        VideoPage videoPage = watchLater.clickVideoNameVideoCardModule(TEST_VIDEO);
        videoPage.changeWatchLaterStatus();
        watchLater = videoPage.openWatchLater();

        Assertions.assertFalse(watchLater.isVideoVisible(TEST_VIDEO),
                "Видео не удалилось из 'Смотреть позже'");
    }
}