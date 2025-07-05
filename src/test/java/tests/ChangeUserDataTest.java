package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainAfterLoginPage;
import pages.ProfilePage;
import services.AuthService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Изменение данных о пользователе
 * @author Varfolomeeva
 */
public class ChangeUserDataTest extends BaseTest {
    /**
     * Тест проверяет, что данные в поле "Имя и название канала" изменены на "Арина"
     * - авторизация
     * - нажатие на иконку профиля
     * - отрытие раздела "Профиль"
     * - нажатие на кнопку "Изменить личные данные"
     * - ввод "Арина" в поле "Имя и название канала"
     * - нажатие кнопки "Сохранить"
     * - проверка, что поле содержит "Арина"
     */
    @Test
    public void NameChangedInUserProfile() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        mainPageAfterLogin.clickChannelIconImage();
        ProfilePage profilePage = mainPageAfterLogin.clickProfileButton();
        if (Objects.equals(profilePage.getDisplayedName(), "Арина")) {
            profilePage.clickChangeDataButton();
            profilePage.fillNameInput("Александр");
            profilePage.clickSaveButton();
        }
        profilePage.clickChangeDataButton();
        profilePage.fillNameInput("Арина");
        profilePage.clickSaveButton();
        String currentName = profilePage.getDisplayedName();
        Assertions.assertEquals(currentName, "Арина",
                                String.format("Имя и название канала не изменено на 'Арина'!\n"
                                                      + "Текущее имя: %s", currentName));
    }

    /**
     * Тест проверяет, что данные в поле "Дата рождения" изменены на "11.04.2005"
     * - авторизация
     * - нажатие на иконку профиля
     * - отрытие раздела "Профиль"
     * - нажатие на кнопку "Изменить личные данные"
     * - ввод "11.04.2005" в поле "Дата рождения"
     * - нажатие кнопки "Сохранить"
     * - проверка, что поле содержит "11.04.2005"
     */
    @Test
    public void DateChangedInUserProfile() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        mainPageAfterLogin.clickChannelIconImage();
        ProfilePage profilePage = mainPageAfterLogin.clickProfileButton();
        if (Objects.equals(profilePage.getDisplayedDate(), "11 апреля 2005")) {
            profilePage.clickChangeDataButton();
            profilePage.fillDateInput("10.10.2000");
            profilePage.clickSaveButton();
        }
        profilePage.clickChangeDataButton();
        profilePage.fillDateInput("11.04.2005");
        profilePage.clickSaveButton();
        String currentDate = profilePage.getDisplayedDate();
        Assertions.assertEquals("11 апреля 2005", currentDate, "Дата рождения не изменена на '11 апреля 2005'!");
    }

    /**
     * Тест проверяет, что выбрано поле "Женский" в переключателе пола
     * - авторизация
     * - нажатие на иконку профиля
     * - отрытие раздела "Профиль"
     * - нажатие на кнопку "Изменить личные данные"
     * - выбор поля со значением "Женский" в переключателе пола
     * - проверка, что цвет поля со значением "Женский" имеет голубой цвет
     */
    @Test
    public void GenderChangedInUserProfile() {
        MainAfterLoginPage mainPageAfterLogin = AuthService.auth();
        mainPageAfterLogin.clickChannelIconImage();
        ProfilePage profilePage = mainPageAfterLogin.clickProfileButton();
        profilePage.clickChangeDataButton();
        profilePage.clickFemaleRadioButton();
        assertEquals("#00a1e7", profilePage.getFemaleRadioButtonColor(), "Кнопка со значением 'Женский' не голубого цвета");
    }
}
