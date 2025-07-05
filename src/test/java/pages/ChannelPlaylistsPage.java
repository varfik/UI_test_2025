package pages;

import pages.elements.Button;
import pages.elements.PlaylistCard;

public class ChannelPlaylistsPage extends BasePage {
    public ChannelPlaylistsPage() {
        super(ChannelPlaylistsPage.class, "channelPlaylists");
    }

    public void savePlaylist(String playlistName) {
        PlaylistCard playlist = new PlaylistCard(playlistName);
        playlist.openMenu()
                .selectAction("Сохранить плейлист");
    }
}