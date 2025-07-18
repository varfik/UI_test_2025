package tests;

import org.junit.jupiter.api.*;
import pages.HistoryVideoPage;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.VideoPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест "Удаление видео из Истории просмотров и ее Очистка"
 */
public class DeleteFromHistoryTest extends BaseTest {
    private final String testVideoTitle = "Зачем нужна математика? / Практика 2025 ЛЭТИ 3383";

    /**
     * Тест удаления конкретного видео из истории просмотров.
     * - Авторизация на сайте
     * - Переход на страницу истории просмотров
     * - Если тестового видео нет в истории:
     *    - Выполняется поиск видео
     *    - Открывается и просматривается видео
     *    - Обновляется страница
     * - Проверка наличия видео в истории перед удалением
     * - Удаление видео из истории
     * - Проверка отсутствия видео в истории после удаления
     */
    @Test
    public void testRemoveSingleVideoFromHistory() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        HistoryVideoPage historyPage = mainAfterLoginPage.openHistoryVideo();

        if (!historyPage.isVideoPresent(testVideoTitle)) {
            ResultsOfSearchPage resultsOfSearchPage = SearchService.search(testVideoTitle, SearchType.VIDEO);
            VideoPage videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();
            videoPage.watchVideo();
            videoPage.refresh();
            videoPage.openHistoryVideo();
            historyPage.refresh();
        }

        assertTrue(historyPage.isVideoPresent(testVideoTitle),
                "Видео должно присутствовать в истории перед удалением");
        historyPage.removeVideoFromHistory(testVideoTitle);
        assertFalse(historyPage.isVideoPresent(testVideoTitle), "Видео должно быть удалено из истории");
    }

    /**
     * Тест очистки всей истории просмотров.
     * - Авторизация на сайте
     * - Переход на страницу истории просмотров
     * - Очистка всей истории просмотров
     * - Проверка, что история просмотров пуста
     */
    @Test
    public void testCleanEntireHistory() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        HistoryVideoPage historyPage = mainAfterLoginPage.openHistoryVideo();
        historyPage.cleanHistory();
        assertTrue(historyPage.isHistoryEmpty(), "История должна быть пустой после очистки");
    }
}
