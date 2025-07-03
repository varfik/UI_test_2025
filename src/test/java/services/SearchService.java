package services;

import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;

public class SearchService {
    public static ResultsOfSearchPage search(String searchQuery) {
        MainAfterLoginPage mainAfterLoginPage = new MainAfterLoginPage();
        mainAfterLoginPage.fillSearchInput(searchQuery);
        mainAfterLoginPage.clickSearchButton();
        return new ResultsOfSearchPage();
    }
}
