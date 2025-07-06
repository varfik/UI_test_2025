package pages;

import pages.elements.Button;
import pages.elements.VideoCardModule;

/**
 * Страница "Смотреть позже"
 */
public class WatchLaterPage extends BasePage {

    private VideoCardModule videoCard;

    public WatchLaterPage() {
        super(WatchLaterPage.class, "playlist?list=WL");
    }

    /**
     * Открытие меню видео
     */
    public void openVideoMenu() { videoCard.openMenu(); }

    /**
     * Удаление видео из "Смотреть позже"
     */
    public void deleteFromWatchLater() {
        videoCard.deleteFromWatchLater();
    }

    /**
     * Проверка отображения видео
     */
    public boolean isVideoVisible(String videoTitle) {
        String videoXpath = String.format(
                "//ytd-playlist-video-renderer[.//*[contains(text(), '%s')]]",
                videoTitle);
        return Button.byXPath(videoXpath).isDisplayed();
    }
}