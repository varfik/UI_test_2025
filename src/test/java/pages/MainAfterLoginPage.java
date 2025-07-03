package pages;

import pages.elements.Button;
import pages.elements.Image;
import pages.elements.Input;

/** Главная страница Rutube.ru после авторизации **/
public class MainAfterLoginPage extends BasePage {

    private final Image channelIconImage = Image.byClass("freyja_char-header-user-menu__avatar");

    private final Input searchInput = Input.byClass("wdp-search-line-module__input");

    private final Button searchButton = Button.byAriaLabel("Отправить форму поиска");


    /* Конструктор класса */
    public MainAfterLoginPage() {
        super(MainAfterLoginPage.class, "mainAfterLogin");
    }

    /* Иконка канала отображается */
    public boolean isChannelIconVisible() {
        return channelIconImage.isDisplayed();
    }

    /* Ввод запроса в строку поиска */
    public void fillSearchInput(String searchQuery) {
        searchInput.fill(searchQuery);
    }


    /* Нажатие на кнопку поиска */
    public void clickSearchButton() {
        searchButton.press();
    }




}
