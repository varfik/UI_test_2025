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

    /** **/
    @Test
    public void saveAndDeletePlaylist() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search("Практика 2025 ЛЭТИ 3383", SearchType.VIDEO);
        MainChannelPage channelPage = resultsOfSearchPage.clickChannelNameVideoCardModule();

        ChannelPlaylistsPage playlistsPage = channelPage.goToPlaylists();
        playlistsPage.savePlaylist(PLAYLIST_NAME);

        PlaylistsPage myPlaylists = channelPage.goToMyPlaylists();
        Assertions.assertTrue(myPlaylists.isPlaylistVisible(PLAYLIST_NAME), "Плейлист не отображается!");

        myPlaylists.deletePlaylist(PLAYLIST_NAME);
        Assertions.assertTrue(myPlaylists.isPlaylistVisible(PLAYLIST_NAME), "Плейлист не удален!");
    }
}