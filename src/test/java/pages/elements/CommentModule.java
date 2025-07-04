package pages.elements;

/** Модуль комментария **/
public class CommentModule extends BaseElement {

    /* Имя автора комментария */
    private static final String AUTHOR_NAME_XPATH = "//span[contains(@class, 'wdp-comment-author-module__author-name-inner') and contains(text(), '%s')]";

    /* Текст комментария */
    private static final String COMMENT_TEXT_XPATH = "//p[contains(@class, 'wdp-description-module__description') and contains(text(), '%s')]";

    /* Кнопка меню комментария (троеточие) */
    private static final String MENU_BUTTON_XPATH = "//button[contains(@class, 'wdp-comment-list-menu-module__button')]";

    /* Кнопка "Удалить" в выпадающем меню */
    private static final String DELETE_BUTTON_XPATH = "//div[contains(@class, 'wdp-comment-list-menu-module__dropdown')]//button[contains(text(), 'Удалить')]";

    /* Кнопка подтверждения удаления */
    private static final String CONFIRM_DELETE_BUTTON_XPATH = "//button[contains(text(), 'Удалить комментарий')]";

    private CommentModule(String xpath, String param) {
        super(xpath, param);
    }

    /** Формирование Xpath комментария по имени автора */
    public static CommentModule byAuthorName(String authorName) {
        return new CommentModule(AUTHOR_NAME_XPATH, authorName);
    }

    /** Формирование Xpath комментария по тексту комментария */
    public static CommentModule byCommentText(String commentText) {
        return new CommentModule(COMMENT_TEXT_XPATH, commentText);
    }

    /** Нажатие на меню комментария (троеточие) */
    public void pressMenuButton() {
        Button.byXPath(MENU_BUTTON_XPATH).press();
    }

    /** Удаление комментария */
    public void deleteComment() {
        pressMenuButton();
        Button.byXPath(DELETE_BUTTON_XPATH).press();
        Button.byXPath(CONFIRM_DELETE_BUTTON_XPATH).press();
    }
}