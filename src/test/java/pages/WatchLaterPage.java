package pages;

import pages.elements.Button;
import pages.elements.VideoCardModule;

/**
 * Страница "Смотреть позже"
 */
public class WatchLaterPage extends BasePage {

    public WatchLaterPage() {
        super(WatchLaterPage.class, "playlist?list=WL");
    }

    /**
     * Нажатие на название видео в карточке видео
     */
    public VideoPage clickVideoNameVideoCardModule(String videoTitle) {
        VideoCardModule.byVideoTitle(videoTitle).press();
        return new VideoPage();
    }

    /**
     * Проверка отображения видео
     */
    public boolean isVideoVisible(String videoTitle) {
        return Button.byTextInsideA(videoTitle).isDisplayed();
    }
}