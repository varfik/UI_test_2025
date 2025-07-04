package tests;

import org.junit.jupiter.api.*;
import pages.*;
import services.AuthService;
import services.SearchService;
import services.SearchType;


public class LikeVideoTest extends BaseTest {
    private VideoPage videoPage;

    @Test
    @DisplayName("Проверка постановки лайка")
    public void testLikeVideo() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(
                "Зачем нужна математика? / Практика 2025 ЛЭТИ 3383",
                SearchType.VIDEO
        );
        videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();

        // Подготовка - убедимся, что лайк не поставлен
        if (videoPage.isLiked()) {
            waitFor(3000);
            videoPage.unlikeVideo();
            waitFor(3000);
            Assertions.assertFalse(videoPage.isLiked(), "Лайк должен быть неактивен перед тестом");
        }

        // Получаем начальное количество лайков
        int initialLikes = videoPage.getLikesCount(0);

        // Действие - ставим лайк
        videoPage.likeVideo();
        waitFor(5000);

        // Проверяем состояние лайка
        Assertions.assertTrue(videoPage.isLiked(), "Лайк должен стать активным");

        // Проверяем счетчик лайков
        int actualLikes = videoPage.getLikesCount(initialLikes + 1);
        Assertions.assertEquals(initialLikes + 1, actualLikes,
                "Количество лайков должно увеличиться на 1");
    }

    @Test
    @DisplayName("Проверка удаления лайка")
    public void testUnlikeVideo() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(
                "Зачем нужна математика? / Практика 2025 ЛЭТИ 3383",
                SearchType.VIDEO
        );
        videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();

        // Подготовка - убедимся, что лайк поставлен
        if (!videoPage.isLiked()) {
            waitFor(3000);
            videoPage.likeVideo();
            waitFor(3000);
            Assertions.assertTrue(videoPage.isLiked(), "Лайк должен быть активен перед тестом");
        }

        // Получаем начальное количество лайков
        int initialLikes = videoPage.getLikesCount(0);

        // Действие
        videoPage.unlikeVideo();
        waitFor(5000);

        // Проверяем состояние лайка
        Assertions.assertFalse(videoPage.isLiked(), "Лайк должен стать неактивным");

        // Проверяем счетчик лайков
        int actualLikes = videoPage.getLikesCount(initialLikes - 1);
        Assertions.assertEquals(initialLikes - 1, actualLikes,
                "Количество лайков должно уменьшиться на 1");

    }

    private void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
