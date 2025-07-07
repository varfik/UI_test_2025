package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.TimeoutException;
import pages.elements.Button;
import pages.elements.Input;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import org.apache.commons.io.FilenameUtils;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class StudioRutubePage extends BasePage {

    /**
     * Поле ввода для названия видео
     */
    private final Input titleInput = Input.byXPath("//textarea[@name='title']");

    /**
     * Поле ввода для описания видео
     */
    private final Input descriptionInput = Input.byXPath("//textarea[@name='description']");

    /**
     * Кнопка публикации видео
     */
    private final Button publishButton = Button.byXPath(
            "//button[contains(@class, 'freyja_char-base-button__contained-accent') " +
                    "and .//span[text()='Опубликовать']]");

    /**
     * Кнопка выбора приватного доступа "Только по ссылке"
     */
    private final Button privateAccessOption = Button.byXPath(
            "//div[contains(@class, 'vl-uploader-video-form-content-module__customOption')" +
                    " and contains(., 'Только по ссылке')]");

    /**
     * Чекбокс для установки возрастного ограничения 18+
     */
    private final SelenideElement ageRestrictionCheckbox = $x("//input[@name='adult']/..");

    /**
     * Чекбокс для отключения комментариев
     */
    private final SelenideElement disableCommentsCheckbox = $x("//input[@name='hide_comments']/..");

    /**
     * Конструктор класса
     */
    public StudioRutubePage() {
        super(StudioRutubePage.class, "studioRutube");
    }

    /**
     * Загружает видеофайл
     * */
    public StudioRutubePage uploadVideoFile(String fileName, String title) {
        String filePath = getResourcePath(fileName);
        validateVideoFile(filePath);
        $("input[type='file']")
                .uploadFile(new File(filePath));
        $("div[class*='progress']").shouldBe(visible, Duration.ofSeconds(30));
        setVideoTitle(title);
        return this;
    }

    /**
     * Загружает обложку видео (изображение)
     * */
    public StudioRutubePage uploadCoverImage(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла обложки не может быть пустым");
        }

        String filePath = getResourcePath(fileName);
        validateImageFile(filePath);
        try {
            $x("//div[contains(@class, 'video-cover-uploader-module__button')]")
                    .shouldBe(visible, Duration.ofSeconds(30))
                    .scrollIntoView("{block: 'center'}");

            SelenideElement fileInput = $("input[type='file'][name='cover']")
                    .should(exist, Duration.ofSeconds(10));

            executeJavaScript("arguments[0].style.display = 'block'; " +
                    "arguments[0].style.visibility = 'visible';", fileInput);
            fileInput.shouldBe(visible, Duration.ofSeconds(5))
                    .uploadFile(new File(filePath));

            $x("//div[contains(@class, 'freyja_pen-popup__popup--visible')]")
                    .shouldBe(visible, Duration.ofSeconds(30));

            Button.byId("update-button-videoCover").press();

        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить обложку", e);
        }

        return this;
    }

    /**
     * Заполняет поле названия видео
     * */
    public StudioRutubePage setVideoTitle(String title) {
        try {
            titleInput.clearField();
            titleInput.fill(title);
            return this;
        } catch (Exception e){
            titleInput.fill("Видео");
            return this;
        }
    }

    /**
     * Заполняет поле описания видео
     * */
    public StudioRutubePage setVideoDescription(String description) {
        descriptionInput.clearField();
        descriptionInput.fill(description);
        return this;
    }

    /**
     * Выбирает категорию видео из выпадающего списка)
     */
    public StudioRutubePage selectCategory(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            categoryName = "Наука";
        }

        $("input[class*='vl-dropdown-layout-module__input'][placeholder='Выберите категорию']")
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();

        $("div[class*='freyja_char-popper-portal__popperWrapper']")
                .shouldBe(visible, Duration.ofSeconds(5));

        String categoryXpath = String.format(
                "//div[contains(@class, 'vl-dropdown-layout-module__dropdownListWrapper')]" +
                        "//div[contains(@class, 'category-selector-module__customOption') " +
                        "and contains(., '%s')]",
                categoryName);

        if ($x(categoryXpath).exists()) {
            $x(categoryXpath).shouldBe(visible).click();
        } else {
            System.out.println("Категория '" + categoryName + "' не найдена. Выбираем 'Наука'");
            $x("//div[contains(@class, 'category-selector-module__customOption') and contains(., 'Наука')]")
                    .shouldBe(visible)
                    .click();
            categoryName = "Наука";
        }

        $("input[class*='vl-dropdown-layout-module__input']")
                .shouldHave(value(categoryName), Duration.ofSeconds(5));

        return this;
    }

    /**
     * Добавляет видео в указанный плейлист (если существует)
     */
    public StudioRutubePage addToPlaylist(String playlistName) {
        if (playlistName == null || playlistName.trim().isEmpty()) {
            closePlaylistDropdown();
            return this;
        }

        $("input[placeholder='Выберите плейлист']").shouldBe(visible, Duration.ofSeconds(10)).click();
        $("div[class*='freyja_char-popper-portal__popperWrapper']")
                .shouldBe(visible, Duration.ofSeconds(5));

        SelenideElement playlist = $x(String.format(
                "//div[contains(@class, 'vl-dropdown-multiselect-module__option') " +
                        "and contains(., '%s')]", playlistName));

        if (playlist.exists()) {
            playlist.click();
            $("input[placeholder='Выберите плейлист']")
                    .shouldHave(value(playlistName), Duration.ofSeconds(3));
        } else {
            System.out.println("Плейлист '" + playlistName + "' не найден");
        }
        closePlaylistDropdown();
        return this;
    }

    /**
     * Устанавливает возрастное ограничение 18+ для видео
     */
    public StudioRutubePage setAgeRestriction(boolean enabled) {
        if (enabled != ageRestrictionCheckbox.isSelected()) {
            ageRestrictionCheckbox.click();
        }
        return this;
    }

    /**
     * Устанавливает тип доступа к видео
     */
    public StudioRutubePage setAccessType(String accessType) {
        if (accessType == null) {
            return this;
        }
        $("input[name='access'][readonly]").click();
        if (accessType.equals("Только по ссылке")) {
            privateAccessOption.press();
        }
        $("input[name='access'][readonly]").shouldHave(value(accessType));
        return this;
    }

    /**
     * Включает или отключает комментарии к видео
     */
    public StudioRutubePage setCommentsDisabled(boolean disabled) {
        if (disabled != disableCommentsCheckbox.isSelected()) {
            disableCommentsCheckbox.click();
        }
        return this;
    }

    /**
     * Выполняет полный процесс загрузки и настройки видео
     */
    public void completeUpload(String videoFile, String coverFile, String title, String desc,
                               String category, String playlist, String isPublicAccess,
                               boolean ageRestriction, boolean commentsDisabled) {
        uploadVideoFile(videoFile, title).setVideoDescription(desc)
                .selectCategory(category).addToPlaylist(playlist)
                .setAccessType(isPublicAccess).uploadCoverImage(coverFile)
                .setAgeRestriction(ageRestriction).setCommentsDisabled(commentsDisabled)
                .waitForPublicationComplete();
    }


    /**
     * Получает текущий статус видео
     */
    public String getCurrentStatus() {
        try {
            ElementsCollection allStatuses = $$x("//div[contains(@class, 'progressOneTitle')]");
            for (SelenideElement status : allStatuses) {
                String color = status.getCssValue("color");
                boolean isActive = color.contains("51, 199, 89") ||
                        status.getAttribute("style").contains("--pen-success");

                if (isActive && status.isDisplayed()) {
                    return status.getText().trim();
                }
            }
            for (SelenideElement status : allStatuses) {
                if (status.isDisplayed()) { return status.getText().trim();}
            }
            return "Статус не определен";

        } catch (Exception e) {
            System.err.println("Ошибка определения статуса: " + e.getMessage());
            return "Ошибка определения статуса";
        }
    }

    /**
     * Ожидает завершения публикации и публикует видео
     */
    public void waitForPublicationComplete() {
        try {
            waitUntilReadyForPublish(Duration.ofMinutes(5), Duration.ofSeconds(5));
            publishButton.getBaseElement().click();
            open();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось завершить публикацию", e);
        }
    }

    /**
     * Ожидает, пока статус видео не станет "Готово к публикации"
     * Вызывает исключение, если видео отклонено или превышено количество попыток
     */

    public void waitUntilReadyForPublish(Duration timeout, Duration pollInterval) {
        long endTime = System.currentTimeMillis() + timeout.toMillis();
        while (System.currentTimeMillis() < endTime) {
            String currentStatus = getCurrentStatus();
            System.out.println(currentStatus);
            if ("Готово к публикации".equals(currentStatus)) {return;}
            if ("Отклонено".equals(currentStatus)) { throw new RuntimeException("Видео отклонено модерацией");}
            try {
                $("html").shouldBe(Condition.and("Ждём следующей проверки", Condition.visible,
                                Condition.match("", el -> {
                                    long remaining = endTime - System.currentTimeMillis();
                                    return remaining <= 0 ||
                                            !pollInterval.minus(Duration.ofMillis(remaining)).isNegative();
                                })), pollInterval);
            } catch (Throwable ignored) {}
        }

        String finalStatus = getCurrentStatus();
        if (!"Готово к публикации".equals(finalStatus)) {
            throw new RuntimeException(String.format(
                    "Не дождались готовности за %s. Последний статус: '%s'",
                    timeout, finalStatus));
        }
    }

    /**
     * Получает путь к ресурсу в проекте
     * - Вызывает исключение, если файл не найден
     */
    private String getResourcePath(String fileName) {
        String projectRoot = System.getProperty("user.dir");
        Path filePath = Paths.get(projectRoot, "src", "test", "java", "tests","resources", fileName);
        System.out.println("Ищем файл: " + filePath.toAbsolutePath());
        if (!Files.exists(filePath)) {
            throw new RuntimeException("Файл не найден: " + filePath.toAbsolutePath());
        }
        return filePath.toAbsolutePath().toString();
    }

    /**
     * Закрывает выпадающий список плейлистов, если он открыт
     */
    private void closePlaylistDropdown() {
        if ($("div[class*='freyja_char-popper-portal__popperWrapper']").exists()) {
            $("body").click();
            $("div[class*='freyja_char-popper-portal__popperWrapper']")
                    .shouldNotBe(visible, Duration.ofSeconds(3));
        }
    }

    /**
     * Проверяет валидность видеофайла
     * - Вызывает исключение, если файл не соответствует требованиям
     */
    private void validateVideoFile(String filePath) {
        if (!isFileValid(filePath,
                new String[]{"mp4", "avi", "mov", "wmv", "flv", "mpeg", "webm", "mkv", "3gp", "m4v"},
                25L * 1024 * 1024 * 1024)) {
            throw new IllegalArgumentException("Некорректный видеофайл: " + filePath);
        }
    }

    /**
     * Проверяет валидность изображения
     * - Вызывает исключение, если файл не соответствует требованиям
     */
    private void validateImageFile(String filePath) {
        if (!isFileValid(filePath,
                new String[]{"jpg", "jpeg", "png", "gif"},
                10 * 1024 * 1024)) {
            throw new IllegalArgumentException("Некорректное изображение: " + filePath);
        }
    }

    /**
     * Проверяет файл на соответствие требованиям
     * - Возвращает true если файл валиден, false в противном случае
     */
    private boolean isFileValid(String filePath, String[] allowedFormats, long maxSizeBytes) {
        File file = new File(filePath);
        if (!file.exists() || file.length() > maxSizeBytes) {
            return false;
        }
        String extension = FilenameUtils.getExtension(filePath).toLowerCase();
        for (String format : allowedFormats) {
            if (format.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        System.out.println(extension);
        return false;
    }
}