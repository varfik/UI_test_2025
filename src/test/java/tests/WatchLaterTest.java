//package tests;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import pages.MainAfterLoginPage;
//import pages.ResultsOfSearchPage;
//import pages.WatchLaterPage;
//import pages.elements.Button;
//import pages.elements.VideoCard;
//import services.AuthService;
//import services.SearchService;
//import services.SearchType;
//
///**
// * Плейлист "Смотреть позже"
// * @author Soldunova
// */
//public class WatchLaterTest extends BaseTest {
//    private final String TEST_VIDEO = "Искусственный интеллект и его применение в жизни / Практика 2025 ЛЭТИ 3383";
//
//    /**
//     * Тестирование добавления видео в "Смотреть позже" и его удаления:
//     * - авторизация
//     * - поиск канала по названию тестового видео
//     * - открывание меню у найденного видео
//     * - добавление видео в "Смотреть позже"
//     * - перейти на страницу "Смотреть позже"
//     * - проверка наличия видео в списке
//     * - удалить видео из "смотреть позже"
//     * - проверка отсутствие видео в списке
//     */
//    @Test
//    public void testAddAndRemoveFromWatchLater() {
//        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
//        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(TEST_VIDEO, SearchType.CHANNEL);
//
//        VideoCard video = resultsOfSearchPage.openMenu().addToWatchLater();
//        WatchLaterPage watchLaterPage = new WatchLaterPage();
//        Assertions.assertTrue(watchLaterPage.isVideoPresent(TEST_VIDEO), "Видео не добавилось в 'Смотреть позже'");
//
//        watchLaterPage.removeVideo(TEST_VIDEO);
//
//        Assertions.assertFalse(watchLaterPage.isVideoPresent(TEST_VIDEO), "Видео не удалилось из 'Смотреть позже'");
//    }
//}
