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
        String CHANNEL_NAME = "Практика Лэти Тестирование 2025";

        AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(CHANNEL_NAME, SearchType.CHANNEL);
        MainChannelPage channelPage = resultsOfSearchPage.clickChannelNameVideoCardModule();

        PlaylistsPage playlistsPage = channelPage.goToPlaylists(PLAYLIST_NAME);
        PlaylistPage playlistPage = playlistsPage.clickPlaylist();
        playlistPage.savePlaylist();

        playlistPage.openMenu();
        PlaylistsPage myPlaylists = playlistPage.goToMyPlaylists(PLAYLIST_NAME);
        Assertions.assertTrue(myPlaylists.isPlaylistVisible(PLAYLIST_NAME), "Плейлист не отображается!");
    }

    /**
     * Тестирование удаления плейлиста:
     * - авторизация
     * - переход в раздел "Мои плейлисты"
     * - удаление плейлиста
     * - проверка отсутствия плейлиста
     */
    @Test
    public void deletePlaylist() {
        MainAfterLoginPage mainPage = AuthService.auth();

        PlaylistsPage playlistsPage = mainPage.goToPlaylists(PLAYLIST_NAME);
        PlaylistPage playlistPage = playlistsPage.clickPlaylist();
        playlistPage.deletePlaylist();

        PlaylistsPage myPlaylists = playlistPage.goToMyPlaylists(PLAYLIST_NAME);
        Assertions.assertFalse(myPlaylists.isPlaylistVisible(PLAYLIST_NAME), "Плейлист не удален!");
    }
}
