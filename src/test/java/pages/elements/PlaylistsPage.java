package pages;

import pages.elements.PlaylistCard;

public class PlaylistsPage extends BasePage {
    public PlaylistsPage() {
        super(PlaylistsPage.class, "playlists");
    }

    public void deletePlaylist(String playlistName) {
        PlaylistCard playlist = new PlaylistCard(playlistName);
        playlist.openMenu().selectAction("Удалить из сохраненных").confirmDeletion();
    }

    public boolean isPlaylistVisible(String playlistName) {
        return (new PlaylistCard(playlistName)).isDisplayed();
    }
}