package pages;

import pages.elements.Button;
import pages.elements.Input;
import pages.elements.CommentModule;

public class VideoPage extends BasePage {
    /* Кнопка лайка (Button) */
    /* Поле для комментария (Input) */
    private final Input commentInput = Input.byClass("wdp-comment-input-module__textarea");

    /* Кнопка для отправки комментария */
    private final Button sendCommentButton = Button.byAriaLabel("Отправить");

    /* Конструктор класса */
    public VideoPage() {
        super(VideoPage.class, "video");
    }

    /* Ввод комментария в поле */
    public void fillCommentInput(String searchQuery) {
        commentInput.fill(searchQuery);
    }

    /* Нажатие на кнопку отправки комментария */
    public void clickSendCommentButton() { sendCommentButton.press(); }

    /* Поиск комментария по имени автора */
    public CommentModule findCommentByAuthor(String authorName) {
        return CommentModule.byAuthorName(authorName);
    }

    /* Поиск комментария по тексту */
    public CommentModule findCommentByText(String commentText) {
        return CommentModule.byCommentText(commentText);
    }

    /* Удаление комментария по тексту */
    public void deleteCommentByText(String commentText) {
        findCommentByText(commentText).deleteComment();
    }

    /* Удаление комментария по автору */
    public void deleteCommentByAuthor(String authorName) {
        findCommentByAuthor(authorName).deleteComment();
    }
}
