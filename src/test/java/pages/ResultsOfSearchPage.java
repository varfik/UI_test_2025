package pages;

import pages.elements.ChannelCardModule;

/** Страница результатов поиска **/
public class ResultsOfSearchPage extends BasePage {

    /* Название (кликабельное) канала, которое ищется */
    private ChannelCardModule channelCardToSearch = ChannelCardModule.bySpan("Практика Лэти Тестирование 2025");

    /* Конструктор класса */
    public ResultsOfSearchPage() {
        super(ResultsOfSearchPage.class, "resultsOfSearch");
    }

    /* Нажатие на название канала */
    public MainChannelPage clickChannelCard() {
        channelCardToSearch.press();
        return new MainChannelPage();
    }

}
