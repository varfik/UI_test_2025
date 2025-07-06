package services;

import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;

/**
 * Сервис поиска по запросу в поисковой строке
 * - открытие авторизированной главной страницы
 * - ввод запроса для поиска
 * - нажатие на кнопку поиска (переход на страницу результатов)
 */
public class SearchService {

    /**
     * Метод вводит запрос заданного типа в поисковую строку
     * @param searchQuery Запрос для поиска
     * @param type Тип запроса (видео или канал)
     * @return Страница с результатами поиска
     */
    public static ResultsOfSearchPage search(String searchQuery, SearchType type) {
        MainAfterLoginPage mainAfterLoginPage = new MainAfterLoginPage();
        mainAfterLoginPage.fillSearchInput(searchQuery);
        mainAfterLoginPage.clickSearchButton();
        return new ResultsOfSearchPage(searchQuery, type);
    }
}
