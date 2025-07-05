//package pages;
//
//import pages.elements.Button;
//import pages.elements.PlaylistCard;
//
///**
// * Страница с плейлистами пользователя
// */
//public class PlaylistsPage extends BasePage {
//
//    /**
//     * Конструктор класса
//     */
//    public PlaylistsPage() {
//        super(PlaylistsPage.class, "playlists");
//    }
//
//    /**
//     * Удаление плейлиста
//     */
//    public void deletePlaylist(String playlistName) {
//        PlaylistCard playlist = new PlaylistCard(playlistName);
//        playlist.openMenu().selectAction("Удалить из сохраненных").confirmDeletion();
//    }
//
//    /**
//     * Видимость плейлиста
//     */
//    public boolean isPlaylistVisible(String playlistName) {
//        return new PlaylistCard(playlistName).isDisplayed();
//    }
//}
