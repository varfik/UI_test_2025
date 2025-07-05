package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.WatchLaterPage;
import pages.elements.Button;
import pages.elements.VideoCard;
import services.AuthService;
import services.SearchService;
import services.SearchType;

public class WatchLaterPage extends BaseTest {
    private final String TEST_VIDEO = "Искусственный интеллект и его применение в жизни / Практика 2025 ЛЭТИ 3383";

    @Test
    public void testAddAndRemoveFromWatchLater() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(
                TEST_VIDEO, SearchType.CHANNEL);

        new VideoCard(TEST_VIDEO)
                .openMenu()
                .addToWatchLater();

        WatchLaterPage watchLaterPage = new WatchLaterPage();
        Assertions.assertTrue(watchLaterPage.isVideoPresent(TEST_VIDEO),
                "Видео не добавилось в 'Смотреть позже'");

        watchLaterPage.removeVideo(TEST_VIDEO);

        Assertions.assertFalse(watchLaterPage.isVideoPresent(TEST_VIDEO),
                "Видео не удалилось из 'Смотреть позже'");
    }
}
