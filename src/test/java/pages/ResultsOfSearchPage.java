package pages;

import pages.elements.CardModule;

/** Страница результатов поиска **/
public class ResultsOfSearchPage extends BasePage {

    /* Карточка канала или видео (кликабельное название), которое ищется */
    private CardModule cardModule;

    /* Конструктор класса */
    public ResultsOfSearchPage(String searchQuery) {
        super(ResultsOfSearchPage.class, "resultsOfSearch");
        this.cardModule = CardModule.bySpan(searchQuery);
    }

    /* Нажатие на карточку элемента, который ищется */
    public MainChannelPage clickCard() {
        cardModule.press();
        return new MainChannelPage();
    }

}
