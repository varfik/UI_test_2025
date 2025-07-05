package pages;

import pages.elements.Button;
import pages.elements.WatchLaterCard;

public class WatchLaterPage extends BasePage {
    public WatchLaterPage() {
        super(WatchLaterPage.class, "playlist?list=WL");
    }

    public void removeVideo(String videoTitle) {
        WatchLaterCard card = new WatchLaterCard(videoTitle);
        card.openMenu();
        card.removeFromWatchLater();
    }

    public boolean isVideoPresent(String videoTitle) {
        String videoXpath = String.format(
                "//ytd-playlist-video-renderer[.//*[contains(text(), '%s')]]",
                videoTitle);
        return Button.byXPath(videoXpath).isDisplayed();
    }
}