package pages;

import pages.elements.VideoCardModule;
import services.SearchType;

/** Страница результатов поиска **/
public class ResultsOfSearchPage extends BasePage {



    /* Карточка видео, которая содержит название видео или канала, которое ищется */
    private VideoCardModule videoCardModule;

    /* Конструктор класса */
    public ResultsOfSearchPage(String searchQuery, SearchType type) {
        super(ResultsOfSearchPage.class, "resultsOfSearch");

        if (type == SearchType.VIDEO) {
            this.videoCardModule = VideoCardModule.byVideoTitle(searchQuery);
        } else if (type == SearchType.CHANNEL) {
            this.videoCardModule = VideoCardModule.byChannelName(searchQuery);
        }
    }

    /* Нажатие на название канала в карточке видео */
    public MainChannelPage clickChannelNameVideoCardModule() {
        videoCardModule.press();
        return new MainChannelPage();
    }

    /* Нажатие на название видео в карточке видео */
    public VideoPage clickVideoNameVideoCardModule() {
        videoCardModule.press();
        return new VideoPage();
    }

}
