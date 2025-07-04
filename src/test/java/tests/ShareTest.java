package tests;

import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.VideoPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

public class ShareTest extends BaseTest {
    /** Тест проверяет, что скопированная ссылка при действии "Поделиться" совпадает с ссылкой открытого видео
     * - авторизация
     * - ввод в поиске "Космос и его составляющие / Практика 2025 ЛЭТИ 3383"
     * - нажатие на карточку видео
     * - нажатие кнопки "Поделиться"
     * - сравнение текущей ссылки и полученной в результате копирования **/
    @Test
    public void copiedLinkEquelsOpen() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Космос и его составляющие / Практика 2025 ЛЭТИ 3383", SearchType.VIDEO);
        VideoPage videoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();
        /* continue */
    }
}
