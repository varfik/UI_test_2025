package pages;
import pages.elements.Button;

import pages.elements.Button;
import pages.elements.Input;

public class VideoPage extends BasePage {
    /* Кнопка "Поделиться */
    private final Button shareButton = Button.byText("Поделиться");

    /* Поле в "Поделиться" с ссылкой на видео */
    private final Input shareInput = Input.byWrapper("inputWrapper");

    /* Конструктор класса */
    public VideoPage() {
        super(VideoPage.class, "video");
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


    /* Поле для комментария (Input) */
    /* Кнопка для отправки комментария */


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
      
    /* Нажатие на кнопку "Поделиться" */
    public void clickShareButton() {
        shareButton.press();
    }

    /* Получения ссылки из поля "Поделиться" */
    public String getLinkToShare() {
        return shareInput.getValue();
    }

}
