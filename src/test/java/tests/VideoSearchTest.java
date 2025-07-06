package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.MainChannelPage;
import pages.ResultsOfSearchPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

/**
 * Поиск видео
 * @author Soldunova
 */
public class VideoSearchTest extends BaseTest{
    /**
     * Тест поиск существующего видео по полному названию:
     * - авторизация
     * - нажатие на поле "Поиск"
     * - ввод полного названия существующего видео "Искусственный интеллект и его применение в жизни / Практика 2025
     * ЛЭТИ 3383"
     * - нажатие значка поиска в правом углу поисковой строки
     */
    @Test
    public void searchFullNameVideo() {
        String fullVideoTitle = "Искусственный интеллект и его применение в жизни / Практика Лэти Тестирование 2025";

        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(fullVideoTitle, SearchType.CHANNEL);

        Assertions.assertNotNull(resultsOfSearchPage, "Страница результатов поиска не загрузилась");
        Assertions.assertDoesNotThrow(resultsOfSearchPage::clickVideoNameVideoCardModule,
                                      "Видео с полным названием не найдено");
    }

    /**
     * Тест поиск существующего видео по части названия:
     * - авторизация
     * - нажатие на поле "Поиск"
     * - ввод части названия существующего видео "Практика 2025 ЛЭТИ 3383"
     * - нажатие значка поиска в правом углу поисковой строки
     */
    @Test
    public void searchPartialNameVideo() {
        String partialQuery = "Практика 2025 ЛЭТИ 3383";

        ResultsOfSearchPage resultsPage = SearchService.search(partialQuery, SearchType.VIDEO);
        Assertions.assertNotNull(resultsPage, "Страница результатов не загрузилась");

        /**
         * Проверяем, что можно кликнуть (хотя бы один элемент найден)
         */
        Assertions.assertDoesNotThrow(resultsPage::clickVideoNameVideoCardModule,
                                      "Ни одно видео не содержит запрос '" + partialQuery + "'");
    }


    /**
     * Тест поиск несуществующего видео:
     * - авторизация
     * - нажатие на поле "Поиск"
     * - ввод ""
     * - нажатие значка поиска в правом углу поисковой строки
     */
    public void searchNonExistVideo() {
        String nonExistQuery = "Несуществующее видео " + System.currentTimeMillis();

        ResultsOfSearchPage resultsPage = SearchService.search(nonExistQuery, SearchType.VIDEO);
        Assertions.assertNotNull(resultsPage, "Страница результатов не загрузилась");

        /**
         * Проверяем, что элемент не найден
         */
        Assertions.assertFalse(resultsPage.isVideoCardDisplayed(),
                               "Карточка видео не должна отображаться для несуществующего запроса");
    }
}
