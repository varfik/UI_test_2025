package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.MainChannelPage;
import pages.ResultsOfSearchPage;
import services.AuthService;
import services.SearchService;

public class SubscribeTest extends BaseTest {


    @Test
    public void subscribeButtonIsChangedAfterSubscription() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Практика Лэти Тестирование 2025");
        MainChannelPage mainChannelPage = resultsOfSearchPage.clickChannelCard();
        mainChannelPage.clickSubscribeButton();

        Assertions.assertTrue(mainChannelPage.isSubscribedButtonVisible(), "Текст кнопки не заменен на 'Вы подписаны'!");
    }

    @Test
    public void subscribeButtonIsChangedAfterUnsubscription() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Практика Лэти Тестирование 2025");
        MainChannelPage mainChannelPage = resultsOfSearchPage.clickChannelCard();
        mainChannelPage.clickSubscribeButton();

        Assertions.assertFalse(mainChannelPage.isSubscribedButtonVisible(), "Текст кнопки не заменен на 'Подписаться'!");
    }

}
