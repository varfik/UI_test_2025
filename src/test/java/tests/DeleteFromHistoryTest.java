package tests;

import org.junit.jupiter.api.*;
import pages.HistoryVideoPage;
import pages.MainAfterLoginPage;
import services.AuthService;
import static org.junit.jupiter.api.Assertions.*;


/* Тест удаления видео из истории и очистки истории */
public class DeleteFromHistoryTest extends BaseTest{
    /* Тест: Удаление видео из истории просмотра
    - Авторизация на сайте
    - Переход на Историю просмотра
    - Проверка, что указанное видео есть в Истории просмотра
    - Открытие меню для указанного видео и удаление его с помощью кнопки "Удалить из истории"
    - Проверка, что видео было удалено из Истории просмотра
    */
    @Test
    public void testRemoveSingleVideoFromHistory() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        HistoryVideoPage historyPage = mainAfterLoginPage.openHistoryVideo();
        String testVideoTitle = "Зачем нужна математика? / Практика 2025 ЛЭТИ 3383";

        assertTrue(historyPage.isVideoPresent(testVideoTitle),
                "Видео должно присутствовать в истории перед удалением");

        historyPage.openVideoMenu(testVideoTitle);
        historyPage.removeVideoFromHistory(testVideoTitle);

        assertFalse(historyPage.isVideoPresent(testVideoTitle),
                "Видео должно быть удалено из истории");
    }

    /* Тест: Удаление видео из истории просмотра
    - Авторизация на сайте
    - Переход на Историю просмотра
    - Проверка, что История не пуста
    - Очистка Истории просмотра
    - Проверка, что История просмотров пуста
    */
    @Test
    public void testCleanEntireHistory() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        HistoryVideoPage historyPage = mainAfterLoginPage.openHistoryVideo();

        assertTrue(historyPage.isHistoryNotEmpty(),
                "История должна содержать хотя бы одно видео перед очисткой");

        historyPage.cleanHistory();

        assertFalse(historyPage.isHistoryNotEmpty(),
                "История должна быть пустой после очистки");
    }
}