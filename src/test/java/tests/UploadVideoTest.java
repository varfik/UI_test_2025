package tests;

import org.junit.jupiter.api.Test;
import pages.*;
import services.AuthService;

import static org.junit.jupiter.api.Assertions.*;

public class UploadVideoTest extends BaseTest {
    private static final String VIDEO_FILE = "ui.mp4";
    private static final String COVER_FILE = "ui.jpeg";
    private static final String VIDEO_TITLE = "UI-тестирование / Практика 2025 ЛЭТИ 3383";
    private static final String VIDEO_DESCRIPTION = "Тестовое видео для демонстрации автоматизированной загрузки";
    private static final String EXPECTED_CHANNEL_NAME = "Практика 2025 ЛЭТИ 3383";
    private static final String PLAYLIST = "Тестовый плейлист";
    private static final String CATEGORY = "Наука";
    private static final boolean AGE_RESTRICT = false;
    private static final boolean COMMENT_DISABLE = false;
    private static final String KEEP_CURRENT_ACCESS = "Только по ссылке";

    /**
     * Тест загрузки видео на канал
     * - Авторизация и загрузка видео
     * - Загрузка видео
     * - Получение URL опубликованного видео
     * - Проверка видео по URL
     * - Проверка все данные опубликованного видео
     */
    @Test
    public void testVideoUpload() {
        MainAfterLoginPage mainAfterLoginPage = AuthService.auth();
        StudioRutubePage studioPage = mainAfterLoginPage.openStudioRutubePage();
        studioPage.refresh();
        studioPage.completeUpload(VIDEO_FILE, COVER_FILE, VIDEO_TITLE, VIDEO_DESCRIPTION,
                CATEGORY, PLAYLIST, KEEP_CURRENT_ACCESS, AGE_RESTRICT, COMMENT_DISABLE);
        String videoUrl = studioPage.getCurrentUrl();
        VideoPage videoPage = VideoPage.openByUrl(videoUrl)
                .waitForContent();
        assertAll("Проверка данных видео",
                () -> assertTrue(videoPage.isPlayerVisible(), "Видеоплеер не отображается"),
                () -> assertEquals(VIDEO_TITLE, videoPage.getTitle(), "Название не совпадает"),
                () -> assertEquals(VIDEO_DESCRIPTION, videoPage.getDescription(), "Описание не совпадает"),
                () -> assertEquals(EXPECTED_CHANNEL_NAME, videoPage.getChannelName(), "Канал не совпадает"),
                () -> assertTrue(videoPage.isCoverImageVisible(), "Обложка не отображается"),
                () -> assertTrue(videoPage.isPublishedStateVisible(), "Статус 'Опубликовано' не отображается")
        );
    }
}