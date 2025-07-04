package services;

import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;

/** Сервис поиска по запросу в поисковой строке
 *  Возвращает страницу с результатами поиска (ResultsOfSearchPage)
 *      - открытие авторизированной главной страницы
 *      - ввод запроса для поиска
 *      - нажатие на кнопку поиска (переход на страницу результатов) **/
public class SearchService {
    public static ResultsOfSearchPage search(String searchQuery) {
        MainAfterLoginPage mainAfterLoginPage = new MainAfterLoginPage();
        mainAfterLoginPage.fillSearchInput(searchQuery);
        mainAfterLoginPage.clickSearchButton();
        return new ResultsOfSearchPage(searchQuery);
    }
}
