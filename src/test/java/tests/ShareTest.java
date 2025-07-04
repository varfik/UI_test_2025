package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.VideoPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

public class ShareTest extends BaseTest {
    /**
     * Тест проверяет, что скопированная ссылка при действии "Поделиться" совпадает с ссылкой открытого видео
     * - авторизация
     * - ввод в поиске "Космос и его составляющие / Практика 2025 ЛЭТИ 3383"
     * - нажатие на карточку видео
     * - нажатие кнопки "Поделиться"
     * - получение URL для копирования без query-параметров (split("\\?")[0])
     * - получение URL текущей страницы без query-параметров (split("\\?")[0])
     * - проверка, что ссылки совпадают
     **/
    @Test
    public void copiedLinkEqualsOpen() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(
                "Космос и его составляющие / Практика 2025 ЛЭТИ 3383", SearchType.VIDEO);
        VideoPage videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();
        videoPage.clickShareButton();
        String linkToShare = videoPage.getLinkToShare().split("\\?")[0];
        String currentLink = videoPage.getCurrentUrl().split("\\?")[0];
        Assertions.assertEquals(currentLink, linkToShare, String.format(
                        "Базовый URL в поле 'Поделиться' не совпадает с URL открытой страницы!" +
                        "\nТекущая ссылка: %s\nСсылка для копирования: %s",
                        currentLink, linkToShare));
    }
}
