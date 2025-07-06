package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import services.AuthService;
import services.SearchService;
import services.SearchType;

/**
 * Плейлисты
 * @author Soldunova
 */
public class PlaylistTest extends BaseTest {
    private final String CHANNEL_NAME = "Практика Лэти Тестирование 2025";
    private final String PLAYLIST_NAME = "Тестовый плейлист";

    /**
     * Тестирование сохранения и удаления плейлиста:
     * - авторизация
     * - поиск видео по запросу "Практика 2025 лэти 3383"
     * - переход на страницу канала
     * - открытие раздела плейлистов канала
     * - сохранение плейлиста "Тестовый плейлист"
     * - переход в раздел "Мои плейлисты"
     * - проверка отображения плейлиста
     */
    @Test
    public void savePlaylist() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(CHANNEL_NAME, SearchType.VIDEO);
        MainChannelPage channelPage = resultsOfSearchPage.clickChannelNameVideoCardModule();

        PlaylistsPage playlistsPage = channelPage.goToPlaylists();
        playlistsPage.clickMenuCardModule();
        playlistsPage.savePlaylist();
        PlaylistsPage myPlaylists = channelPage.goToMyPlaylists();

        Assertions.assertFalse(myPlaylists.isPlaylistVisible(), "Плейлист не отображается!");
    }

    /**
     * Тестирование сохранения и удаления плейлиста:
     * - авторизация
     * - поиск видео по запросу "Практика 2025 лэти 3383"
     * - переход на страницу канала
     * - открытие раздела плейлистов канала
     * - сохранение плейлиста "Тестовый плейлист"
     * - переход в раздел "Мои плейлисты"
     * - удаление плейлиста
     * - проверка отсутствия плейлиста
     */
    @Test
    public void deletePlaylist() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        PlaylistsPage myPlaylists = mainAfterLoginPage.goToPlaylists();

        myPlaylists.clickMenuCardModule();
        myPlaylists.deletePlaylist();
        Assertions.assertTrue(myPlaylists.isPlaylistVisible(), "Плейлист не удален!");
    }
}
