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
 * Подписка (отписка) на канал
 * @author Varfolomeeva
 */
public class SubscribeTest extends BaseTest {
    /**
     * Тест проверяет подписку на канал:
     * - авторизация
     * - ввод в поиске "Практика Лэти Тестирование 2025"
     * - нажатие на карточку канала
     * - если уже есть подписка на канал, нажатие кнопки "Вы подписаны", выбор "Отписаться"
     * - нажатие кнопки "Подписаться"
     * - проверка, что текст кнопки после нажатия соответствует "Вы подписаны"
     */
    @Test
    public void subscribeButtonIsChangedAfterSubscription() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Практика Лэти Тестирование 2025",
                                                                       SearchType.CHANNEL);
        MainChannelPage mainChannelPage = resultsOfSearchPage.clickChannelNameVideoCardModule();
        if (mainChannelPage.isSubscribedButtonVisible()) {
            mainChannelPage.clickSubscribedButton();
            mainChannelPage.clickUnsubscribeButton();
        }
        mainChannelPage.clickSubscribeButton();
        Assertions.assertTrue(mainChannelPage.isSubscribedButtonVisible(),
                              "Текст кнопки не заменен на 'Вы подписаны'!");
    }

    /**
     * Тест проверяет отписку от канала:
     * - авторизация
     * - ввод в поиске "Практика Лэти Тестирование 2025"
     * - нажатие на карточку канала
     * - если ещё нет подписки на канал, нажатие кнопки "Подписаться"
     * - нажатие кнопки "Вы подписаны"
     * - нажатие "Отписаться"
     * - проверка, что текст кнопки после нажатия соответствует "Подписаться"
     */
    @Test
    public void subscribeButtonIsChangedAfterUnsubscription() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Практика Лэти Тестирование 2025",
                                                                       SearchType.CHANNEL);
        MainChannelPage mainChannelPage = resultsOfSearchPage.clickChannelNameVideoCardModule();
        if (!mainChannelPage.isSubscribedButtonVisible()) {
            mainChannelPage.clickSubscribeButton();
        }
        mainChannelPage.clickSubscribedButton();
        mainChannelPage.clickUnsubscribeButton();
        Assertions.assertFalse(mainChannelPage.isSubscribedButtonVisible(),
                               "Текст кнопки не заменен на 'Подписаться'!");
    }

}
