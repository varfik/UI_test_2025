package tests;

import org.junit.jupiter.api.*;
import pages.*;
import services.AuthService;
import services.SearchService;
import services.SearchType;

/* Тест проверяет действия "Поставить лайк" и "Удалить лайк" */
public class LikeVideoTest extends BaseTest {
    /*
    Тест для действия "Поставить лайк":
    * - Авторизация на сайте
    * - Переход к заготовленному видео
    * - Перед операцией проверяется возможность поставить лайк - при необходимости лайк убирается
    * - Контроль кол-ва лайков до и после действия
    * - Действие "Поставить лайк видео"
    * - Проверка состояния лайка
    * - Проверка кол-ва лайков после действия
    */
    @Test
    public void testLikeVideo() {
        // Переход к видео
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(
                "Зачем нужна математика? / Практика 2025 ЛЭТИ 3383",
                SearchType.VIDEO
        );
        VideoPage videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();

        // Подготовка
        if (videoPage.isLiked()) {
            videoPage.unlikeVideo();
            Assertions.assertFalse(videoPage.isLiked(), "Лайк должен быть неактивен перед тестом");
        }

        // Начальное количество лайков
        int initialLikes = videoPage.getLikesCount(0);

        // Действие - ставим лайк
        videoPage.likeVideo();

        // Проверка состояния лайка
        Assertions.assertTrue(videoPage.isLiked(), "Лайк должен стать активным");

        // Проверка счетчика лайков
        int actualLikes = videoPage.getLikesCount(initialLikes + 1);
        Assertions.assertEquals(initialLikes + 1, actualLikes,
                "Количество лайков должно увеличиться на 1");
    }

    /*
    Тест для действия "Удалить лайк":
    * - Авторизация на сайте
    * - Переход к заготовленному видео
    * - Перед операцией проверяется возможность удалить лайк - при необходимости лайк ставится
    * - Контроль кол-ва лайков до и после действия
    * - Действие "Удалить лайк видео"
    * - Проверка состояния лайка
    * - Проверка кол-ва лайков после действия
    */
    @Test
    public void testUnlikeVideo() {
        // Переход к видео
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(
                "Зачем нужна математика? / Практика 2025 ЛЭТИ 3383",
                SearchType.VIDEO
        );
        VideoPage videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();

        // Подготовка
        if (!videoPage.isLiked()) {
            videoPage.likeVideo();
            Assertions.assertTrue(videoPage.isLiked(), "Лайк должен быть активен перед тестом");
        }

        // Начальное количество лайков
        int initialLikes = videoPage.getLikesCount(0);

        // Действие
        videoPage.unlikeVideo();

        // Проверка состояния лайка
        Assertions.assertFalse(videoPage.isLiked(), "Лайк должен стать неактивным");

        // Проверка счетчика лайков
        int actualLikes = videoPage.getLikesCount(initialLikes - 1);
        Assertions.assertEquals(initialLikes - 1, actualLikes,
                "Количество лайков должно уменьшиться на 1");

    }
}
