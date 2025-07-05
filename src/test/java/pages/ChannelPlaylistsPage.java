package pages;

import pages.elements.PlaylistCard;

public class ChannelPlaylistsPage extends BasePage {
    public ChannelPlaylistsPage() {
        super(ChannelPlaylistsPage.class, "channelPlaylists");
    }

    public void savePlaylist(String playlistName) {
        new PlaylistCard(playlistName)
                .openMenu()
                .selectAction("Сохранить плейлист");
    }
}