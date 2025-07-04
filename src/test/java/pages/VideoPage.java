package pages;
import pages.elements.Button;
import java.util.Optional;
import java.util.NoSuchElementException;

public class VideoPage extends BasePage {
    // Кнопка лайка
    private final Button likeButton = Button.byXPath(
            "//button[contains(@class, 'wdp-video-like-dislike-reactions-module__reaction') " +
                    "and @title='Нравится']");

    // SVG элемент для проверки состояния лайка
    private final Button likeSvg = Button.byXPath(
            "//button[contains(@class, 'wdp-video-like-dislike-reactions-module__reaction')]" +
                    "//*[local-name()='svg' and @fill]");

    // Счетчик лайков
    private final Button likesCounter = Button.byXPath(
            "//span[contains(@class, 'wdp-video-like-dislike-reactions-module__counter')]");


    /* Поле для комментария (Input) */
    /* Кнопка для отправки комментария */

    /* Конструктор класса */
    public VideoPage() {
        super(VideoPage.class, "video");
    }



    /**
     * Проверить, активен ли лайк
     */
    public boolean isLiked() {
        try {
            // 1. Проверка цвета
            String fill = likeSvg.getAttribute("fill");
            if (!"none".equals(fill)) return true;

            // 2. Доп. проверка
            String classes = likeButton.getAttribute("class");
            return classes.contains("active");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Поставить лайк
     */
    public VideoPage likeVideo() {
        if (!isLiked()) {
            likeButton.press();
        }
        return this;
    }

    /**
     * Убрать лайк
     */
    public VideoPage unlikeVideo() {
        if (isLiked()) {
            likeButton.press();
        }
        return this;
    }


    /**
     * Метод получения количества лайков
     */
    public int getLikesCount(int defaultValue) {
        try {
            if (!isLikesCounterExists()) {
                return defaultValue;
            }

            // 2. Получение и обработка текста
            String text = likesCounter.getText()
                    .replace("тыс.", "000")
                    .replaceAll("[^0-9]", "");

            return text.isEmpty() ? 0 : Integer.parseInt(text);

        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * Проверяет существование элемента
     */
    public boolean isLikesCounterExists() {
        try {
            likesCounter.getText();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
