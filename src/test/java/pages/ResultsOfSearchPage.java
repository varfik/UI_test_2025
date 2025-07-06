package pages;

import pages.elements.VideoCardModule;
import services.SearchType;

import java.util.NoSuchElementException;

/**
 * Страница результатов поиска
 */
public class ResultsOfSearchPage extends BasePage {

    /**
     * Карточка видео
     */
    private VideoCardModule videoCardModule;

    /**
     * Конструктор класса
     */
    public ResultsOfSearchPage(String searchQuery, SearchType type) {
        super(ResultsOfSearchPage.class, "resultsOfSearch");

        if (type == SearchType.VIDEO) {
            this.videoCardModule = VideoCardModule.byVideoTitle(searchQuery);
        }
        else if (type == SearchType.CHANNEL) {
            this.videoCardModule = VideoCardModule.byChannelName(searchQuery);
        }
    }

    /**
     * Нажатие на название канала в карточке видео
     */
    public MainChannelPage clickChannelNameVideoCardModule() {
        videoCardModule.press();
        return new MainChannelPage();
    }

    /**
     * Нажатие на название видео в карточке видео
     */
    public VideoPage clickVideoNameVideoCardModule() {
        videoCardModule.press();
        return new VideoPage();
    }

    /**
     * Нажатие на меню карточки видео
     */
    public WatchLaterPage openVideoMenu() {
        videoCardModule.openMenu();
        return null;
    }

    /**
     * Добавление видео в "Смотреть позже"
     */
    public void addToWatchLater() {
        videoCardModule.addToWatchLater();
    }

    /**
     * Проверяет, отображается ли хотя бы одна карточка видео
     */
    public boolean isVideoCardDisplayed() {
        try {
            return videoCardModule != null && videoCardModule.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
