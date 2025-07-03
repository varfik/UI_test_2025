package pages;

import pages.elements.Span;

/** Страница результатов поиска **/
public class ResultsOfSearchPage extends BasePage {

    /* Название (кликабельное) канала */
    private final Span channelSpan = Span.byText("Практика Лэти Тестирование 2025");

    /* Конструктор класса */
    public ResultsOfSearchPage() {
        super(ResultsOfSearchPage.class, "resultsOfSearch");
    }

    /* Нажатие на название канала */
    public void clickChannelSpan() {
        channelSpan.press();
    }

}
