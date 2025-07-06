package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
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
    // Основные элементы
    private final Button selectFilesButton = Button.byXPath(
            "//button[contains(@class, 'freyja_char-base-button__contained-accent') and .//span[text()='Выбрать файлы']]");
    private final Input videoFileInput = Input.byXPath("//input[@type='file' and contains(@accept, 'video')]");
    private final Input imageFileInput = Input.byXPath("//input[@type='file' and contains(@accept, 'image')]");

    // Поля формы
    private final Input titleInput = Input.byXPath("//textarea[@name='title']");
    private final Input descriptionInput = Input.byXPath("//textarea[@name='description']");
    private final Button categoryDropdown = Button.byXPath("//input[@placeholder='Выберите категорию']");
    private final Button playlistDropdown = Button.byXPath("//input[@placeholder='Выберите плейлист']");
    private final Button coverUploadButton = Button.byXPath("//div[contains(text(), 'Загрузить свою')]");
    private final Button publishButton = Button.byXPath(
            "//button[contains(@class, 'freyja_char-base-button__contained-accent') and .//span[text()='Опубликовать']]");

    // Статусные элементы
    private final Button moderationStatus = Button.byText("На модерации");
    private final Button uploadCompleteStatus = Button.byXPath("//div[contains(text(), 'Загрузка завершена')]");
    private final Button publishedStatus = Button.byXPath("//div[contains(text(), 'Опубликовано')]");

    // Дополнительные элементы
    private final Button accessTypeDropdown = Button.byXPath("//div[contains(text(), 'Доступ')]/following-sibling::div//input");
    private final Button publicAccessOption = Button.byXPath("//div[@role='option' and contains(text(), 'Для всех')]");
    private final Button privateAccessOption = Button.byXPath("//div[@role='option' and contains(text(), 'Только по ссылке')]");
    private final Button ageRestrictionCheckbox = Button.byXPath("//input[@name='adult']/following-sibling::span");
    private final Button disableCommentsCheckbox = Button.byXPath("//input[@name='hide_comments']/following-sibling::span");


    private final SelenideElement uploadForm = $("div.vl-uploader-module__startForm");

    public StudioRutubePage() {
        super(StudioRutubePage.class, "studioRutube");
    }

    /* Навигация */
    public StudioRutubePage openUploadPage() {
        try {
            $x("//button[contains(@class, 'vl-header-video-menu-v2-module__addButton')]")
                    .shouldBe(visible, Duration.ofSeconds(15))
                    .shouldBe(enabled)
                    .scrollIntoView("{block: 'center'}")
                    .click();

            $x("//div[@role='menu']").shouldBe(visible, Duration.ofSeconds(10));


            $x("//div[@role='menu']//*[contains(text(), 'Загрузить видео')]")
                    .shouldBe(visible)
                    .click();

            uploadForm.shouldBe(visible, Duration.ofSeconds(30));

            return this;
        } catch (Exception e) {
            System.out.println("Текущий URL: " + WebDriverRunner.url());
            System.out.println("DOM: " + executeJavaScript("return document.documentElement.outerHTML"));
            throw new RuntimeException("Не удалось открыть страницу загрузки", e);
        }
    }



    /* Загрузка файлов */
    public StudioRutubePage uploadVideoFile(String fileName) {
        String filePath = getResourcePath(fileName);
        validateVideoFile(filePath);

        executeJavaScript(
                "arguments[0].click();",
                selectFilesButton.getBaseElement()
        );

        videoFileInput.getBaseElement().uploadFile(new File(filePath));
        $("div.vl-progress-bar-module__progress").shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    public StudioRutubePage uploadCoverImage(String fileName) {
        String filePath = getResourcePath(fileName);
        validateImageFile(filePath);

        executeJavaScript(
                "arguments[0].click();",
                coverUploadButton.getBaseElement()
        );

        imageFileInput.getBaseElement().uploadFile(new File(filePath));
        $("div.vl-uploader-cover-module__preview").shouldBe(visible, Duration.ofSeconds(15));
        return this;
    }

    /* Заполнение формы */
    public StudioRutubePage setVideoTitle(String title) {
        titleInput.clearField();
        titleInput.fill(title);
        return this;
    }

    public StudioRutubePage setVideoDescription(String description) {
        descriptionInput.clearField();
        descriptionInput.fill(description);
        return this;
    }

    /**
     * Выбирает категорию видео из выпадающего списка
     * Название категории
     * Текущую страницу StudioRutubePage
     */
    public StudioRutubePage selectCategory(String categoryName) {
        categoryDropdown.press();

        Button categoryButton = Button.byXPath(
                String.format("//div[@role='option' and normalize-space()='%s']", categoryName)
        );
        categoryButton.press();
        $("input[placeholder='Выберите категорию']")
                .shouldHave(value(categoryName));

        return this;
    }

    /**
     * Добавляет видео в указанный плейлист (если существует)
     *  playlistName Название плейлиста
     * Текущую страницу StudioRutubePage
     */
    public StudioRutubePage addToPlaylist(String playlistName) {
        playlistDropdown.press();

        if ($("div.playlist-item").exists()) {
            try {
                Button playlistBtn = Button.byXPath(
                        String.format("//div[contains(@class,'playlist-item') and normalize-space()='%s']",
                                playlistName)
                );
                playlistBtn.press();

                $("input[placeholder='Выберите плейлист']")
                        .shouldHave(value(playlistName));
            } catch (ElementNotFound e) {
                System.out.println("Плейлист '" + playlistName + "' не найден");
                playlistDropdown.press();
            }
        } else {
            System.out.println("Нет доступных плейлистов");
            playlistDropdown.press();
        }

        return this;
    }

    public StudioRutubePage setAgeRestriction(boolean enabled) {
        if (enabled != ageRestrictionCheckbox.isSelected()) {
            ageRestrictionCheckbox.press();
        }
        return this;
    }

    /**
     * Устанавливает тип доступа к видео (опционально)
     *  true - "Для всех", false - "Только по ссылке".
     *  Если null - оставляет текущее значение без изменений
     *  Текущую страницу StudioRutubePage
     */
    public StudioRutubePage setAccessType(Boolean isPublic) {
        if (isPublic != null) {
            accessTypeDropdown.press();

            if (isPublic) {
                publicAccessOption.press();
            } else {
                privateAccessOption.press();
            }
            String expectedValue = isPublic ? "Для всех" : "Только по ссылке";
            accessTypeDropdown.getBaseElement().shouldHave(value(expectedValue));
        }
        return this;
    }

    public StudioRutubePage setCommentsDisabled(boolean disabled) {
        if (disabled != disableCommentsCheckbox.isSelected()) {
            disableCommentsCheckbox.press();
        }
        return this;
    }

    public boolean isOnModeration() {
        return moderationStatus.isDisplayed();
    }

    public void waitForPublication() {
        publishedStatus.getBaseElement().shouldBe(visible, Duration.ofMinutes(5));
    }

    /* Вспомогательные методы */
    private String getResourcePath(String fileName) {
        String projectRoot = System.getProperty("user.dir");
        Path filePath = Paths.get(projectRoot, "src", "test", "java", "tests","resources", fileName);

        System.out.println("Ищем файл: " + filePath.toAbsolutePath());

        if (!Files.exists(filePath)) {
            throw new RuntimeException("Файл не найден: " + filePath.toAbsolutePath());
        }

        return filePath.toAbsolutePath().toString();
    }

    private void validateVideoFile(String filePath) {
        if (!isFileValid(filePath,
                new String[]{"mp4", "avi", "mov", "wmv", "flv", "mpeg", "webm", "mkv", "3gp", "m4v"},
                25L * 1024 * 1024 * 1024)) {
            throw new IllegalArgumentException("Некорректный видеофайл: " + filePath);
        }
    }

    private void validateImageFile(String filePath) {
        if (!isFileValid(filePath,
                new String[]{"jpg", "jpeg", "png", "gif"},
                10 * 1024 * 1024)) {
            throw new IllegalArgumentException("Некорректное изображение: " + filePath);
        }
    }

    private boolean isFileValid(String filePath, String[] allowedFormats, long maxSizeBytes) {
        File file = new File(filePath);
        if (!file.exists() || file.length() > maxSizeBytes) {
            System.out.println(file.exists());
            System.out.println(file.length());
            System.out.println(maxSizeBytes);
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

    /* Комплексный метод с полной проверкой статусов */
    public void completeUpload(String videoFile, String coverFile, String title, String desc,
                               String category, String playlist, Boolean isPublicAccess,
                               boolean ageRestriction, boolean commentsDisabled) {

                uploadVideoFile(videoFile)
                .waitForUploadCompletion()
                .setVideoTitle(title)
                .setVideoDescription(desc)
                .selectCategory(category)
                .addToPlaylist(playlist)
                .setAccessType(isPublicAccess)
                .uploadCoverImage(coverFile)
                .setAgeRestriction(ageRestriction)
                .setCommentsDisabled(commentsDisabled)
                .publishAndVerify();
    }

    private StudioRutubePage waitForUploadCompletion() {
        uploadCompleteStatus.getBaseElement().shouldBe(visible, Duration.ofMinutes(3));
        return this;
    }

    private StudioRutubePage publishAndVerify() {
        publishButton.press();
        moderationStatus.getBaseElement().shouldBe(visible, Duration.ofSeconds(30));
        waitForPublication();
        return this;
    }

    public void waitUntilPublished() {
        publishedStatus.getBaseElement().shouldBe(visible, Duration.ofMinutes(5));
    }

    public boolean isPublished() {
        return publishedStatus.isDisplayed();
    }
}