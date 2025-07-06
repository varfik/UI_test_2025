package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ResultsOfSearchPage;
import pages.VideoPage;
import services.AuthService;
import services.SearchService;
import services.SearchType;

/**
 * Комментарии
 * @author Soldunova
 **/
public class CommentTest extends BaseTest {

    private final String TEST_VIDEO = "Космос и его составляющие / Практика 2025 ЛЭТИ 3383";
    private final String COMMENT = "Хорошее видео!";

    /**
     * Тест проверяет добавление комментария:
     * - авторизация
     * - поиск видео с названием "Космос и его составляющие / Практика 2025 ЛЭТИ 3383"
     * - ввод в поле "Ваш комментарий" строки "Хорошее видео!"
     * - нажатие кнопки "Отправить"
     * - проверка, что введённый комментарий отображается под видео
     */
    @Test
    public void AddedCommentIsDisplayed() {
        AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(TEST_VIDEO, SearchType.VIDEO);
        VideoPage mainVideoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();

        mainVideoPage = mainVideoPage.clickCommentField();
        mainVideoPage.fillCommentInput(COMMENT);
        mainVideoPage.clickSendCommentButton();

        boolean commentIsExist = true;
        try {
            mainVideoPage.findCommentByText(COMMENT);
        } catch (Exception e) {
            commentIsExist = false;
        }
        Assertions.assertTrue(commentIsExist, "Введённый комментарий не отображается!");
    }

    /**
     * Тест проверяет удаление комментария:
     * - авторизация
     * - поиск видео с названием "Космос и его составляющие / Практика 2025 ЛЭТИ 3383"
     * - поиск под видео комментария "Хорошее видео!"
     * - нажатие на кнопку "Троеточие" для комментария
     * - нажатие кнопки "Удалить"
     * - проверка, что удалённый комментарий не отображается под видео
     */
    @Test
    public void DeletedCommentIsNotDisplayed() {
        AuthService.auth();
        ResultsOfSearchPage resultsOfSearchPage = SearchService.search(TEST_VIDEO, SearchType.VIDEO);
        VideoPage mainVideoPage = resultsOfSearchPage.clickVideoNameVideoCardModule();

        boolean commentIsDeleted = true;
        try {
            mainVideoPage.deleteCommentByText(COMMENT);
        } catch (Exception e) {
            commentIsDeleted = false;
        }
        Assertions.assertTrue(commentIsDeleted, "Введённый комментарий не удалён!");
    }
}
