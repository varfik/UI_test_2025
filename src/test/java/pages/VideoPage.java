package pages;
import pages.elements.Button;

import pages.elements.Button;
import pages.elements.Input;
import pages.elements.CommentModule;

public class VideoPage extends BasePage {
    /* Кнопка "Поделиться */
    private final Button shareButton = Button.byText("Поделиться");

    /* Поле в "Поделиться" с ссылкой на видео */
    private final Input shareInput = Input.byWrapper("inputWrapper");
  
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

    /* Кнопка лайка */
    private final Button likeButton = Button.byXPath(
            "//button[contains(@class, 'wdp-video-like-dislike-reactions-module__reaction') " +
                    "and @title='Нравится']");

    /* SVG элемент для проверки состояния лайка */
    private final Button likeSvg = Button.byXPath(
            "//button[contains(@class, 'wdp-video-like-dislike-reactions-module__reaction')]" +
                    "//*[local-name()='svg' and @fill]");

    /* Счетчик лайков */
    private final Button likesCounter = Button.byXPath(
            "//span[contains(@class, 'wdp-video-like-dislike-reactions-module__counter')]");


    /*
    Проверка, выставлен ли лайк - возвращает булево значение:
    - Проверка цвета кнопки лайка (не закрашен - кнопка сейчас не активна)
    - Проверка состояния кнопки ("active" - лайк уже стоит)
    - Если две первые проверки прошли, возвращает true, иначе - false
    */
    public boolean isLiked() {
        try {
            String fill = likeSvg.getAttribute("fill");
            if (!"none".equals(fill)) return true;

            String classes = likeButton.getAttribute("class");
            return classes.contains("active");
        } catch (Exception e) {
            return false;
        }
    }

    /*
    Действие "Поставить лайк":
    - Если лайк еще не стоит, нажимается кнопка лайка
    */
    public VideoPage likeVideo() {
        if (!isLiked()) {
            likeButton.press();
        }
        return this;
    }

    /*
    Действие "Удалить лайк":
    - Если лайк стоит, нажимается кнопка лайка
    */
    public VideoPage unlikeVideo() {
        if (isLiked()) {
            likeButton.press();
        }
        return this;
    }


    /*
    Метод получения количества лайков:
    - Проверяется существование элемента, отвечающего за счетчик лайков (при 0 лайков он не существует):
    -- Если он не существует, то возвращается дефолтное значение
    - Получение и обработка текстового вида числа
    - Возвращается полученное число лайков
    - При неуспехе возвращается дефолтное значение
     */
    public int getLikesCount(int defaultValue) {
        try {
            if (!isLikesCounterExists()) {
                return defaultValue;
            }

            String text = likesCounter.getText()
                    .replace("тыс.", "000")
                    .replaceAll("[^0-9]", "");

            return text.isEmpty() ? 0 : Integer.parseInt(text);

        } catch (Exception e) {
            return defaultValue;
        }
    }

    /*
    Проверяет существование элемента:
    - Попытка получить текст с кнопки:
    -- При успехе - кнопка существует, иначе - нет
    */
    public boolean isLikesCounterExists() {
        try {
            likesCounter.getText();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
      
    /* Нажатие на кнопку "Поделиться" */
    public void clickShareButton() {
        shareButton.press();
    }

    /* Получения ссылки из поля "Поделиться" */
    public String getLinkToShare() {
        return shareInput.getValue();
    }

}
