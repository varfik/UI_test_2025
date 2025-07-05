package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.MainChannelPage;
import pages.ResultsOfSearchPage;
import pages.ChannelPlaylistsPage;
import pages.PlaylistsPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

public class PlaylistTest {
    private final String CHANNEL_NAME = "Практика Лэти Тестирование 2025";
    private final String PLAYLIST_NAME = "Тестовый плейлист";

    /** Тестирование сохранения и удаления плейлиста:
      - авторизация 
      - поиск видео по запросу "Практика 2025 лэти 3383"  
      - переход на страницу канала  
      - открытие раздела плейлистов канала  
      - сохранение плейлиста "Тестовый плейлист"  
      - переход в раздел "Мои плейлисты"  
      - проверка отображения плейлиста  
      - удаление плейлиста  
      - проверка отсутствия плейлиста  **/
    @Test
    public void saveAndDeletePlaylist() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Практика 2025 ЛЭТИ 3383", SearchType.VIDEO);
        MainChannelPage channelPage = resultsOfSearchPage.clickChannelNameVideoCardModule();
        ChannelPlaylistsPage playlistsPage = channelPage.goToPlaylists();
        playlistsPage.savePlaylist("Тестовый плейлист");
        PlaylistsPage myPlaylists = channelPage.goToMyPlaylists();
        Assertions.assertFalse(myPlaylists.isPlaylistVisible("Тестовый плейлист"), "Плейлист не отображается!");
        myPlaylists.deletePlaylist("Тестовый плейлист");
        Assertions.assertTrue(myPlaylists.isPlaylistVisible("Тестовый плейлист"), "Плейлист не удален!");
    }
}