package pages.elements;

/** Карточка видео в плейлисте "Смотреть позже" */
public class WatchLaterCard {
    private final String videoTitle;

    public WatchLaterCard(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void openMenu() {
        String cardXpath = String.format("//ytd-playlist-video-renderer[.//*[contains(text(), '%s')]]", videoTitle);
        Button.byXPath(cardXpath + "//button[contains(@class, 'menu-button')]").press();
    }

    public void removeFromWatchLater() {
        Button.byText("Удалить из «Смотреть позже»").press();
    }
}
