package pages.elements;

/**
 * Модуль комментария
 */
public class CommentModule extends BaseElement {

    /**
     * Имя автора комментария
     */
    private static final String AUTHOR_NAME_XPATH = "//span[contains(@class, 'wdp-comment-author-module__author-name" +
            "-inner') and contains(text(), '%s')]";

    /**
     * Текст комментария
     */
    private static final String COMMENT_TEXT_XPATH = "//p[contains(@class, 'wdp-description-module__description') and" +
            " contains(text(), '%s')]";

    /**
     * Кнопка меню комментария (троеточие)
     */
    private static final Button commentMenuButton = Button.byXPath("//button[contains(@class, " +
            "'wdp-comment-list-menu-module__button')]");

    /**
     * Кнопка "Удалить" в выпадающем меню
     */
    private static final Button deleteCommentButton = Button.byText("Удалить");

    /**
     * Кнопка подтверждения удаления
     */
    private static final Button confirmDeleteCommentButton = Button.byText("Удалить");

    /**
     * Конструктор класса
     */
    private CommentModule(String xpath, String param) {
        super(xpath, param);
    }

    /**
     * Нажатие на меню комментария (троеточие)
     */
    public void pressMenuButton() {
        commentMenuButton.press();
    }

    /**
     * Удаление комментария
     */
    public void deleteComment() {
        pressMenuButton();
        deleteCommentButton.press();
        confirmDeleteCommentButton.press();
    }

    //public boolean commentIsVisible(){ .(); }

    /**
     * Формирование Xpath комментария по имени автора
     */
    public static CommentModule byAuthorName(String authorName) {
        return new CommentModule(AUTHOR_NAME_XPATH, authorName);
    }

    /**
     * Формирование Xpath комментария по тексту комментария
     */
    public static CommentModule byCommentText(String commentText) {
        return new CommentModule(COMMENT_TEXT_XPATH, commentText);
    }
}