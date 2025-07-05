package pages;

import pages.elements.Button;
import pages.elements.Input;
import pages.elements.RadioButton;
import pages.elements.TextElement;

/**
 * Страница профиля пользователя
 */
public class ProfilePage extends BasePage {
    private final Button changeDataButton = Button.byTextInsideA("Изменить личные данные");
    private final Button saveButton = Button.byText("Сохранить");

    private final Input nameInput = Input.byLabel("Имя и название канала");

    private final Input dateInput = Input.byClass("freyja_char-datepicker__input_vhYpt");

    private final RadioButton femaleRadioButton = RadioButton.byLabelText("Женский");

    private final TextElement dateText = TextElement.byLabelText("Дата рождения");

    private final TextElement nameText = TextElement.byLabelText("Имя и название канала");

    /**
     * Конструктор класса
     */
    public ProfilePage() {
        super(ProfilePage.class, "profile");
    }

    /**
     * Нажатие на кнопку "Изменить личные данные"
     */
    public void clickChangeDataButton() {
        changeDataButton.press();
    }

    /**
     * Нажатие на кнопку "Сохранить"
     */
    public void clickSaveButton() {
        saveButton.press();
    }

    /**
     * Заполнение поля "Имя и название канала"
     */
    public void fillNameInput(String name) {
        nameInput.fill(name);
    }

    /**
     * Заполнение поля "Дата рождения"
     */
    public void fillDateInput(String date) {
        dateInput.fill(date);
    }

    /**
     * Выбор кнопки "Женский" в переключателе пола
     */
    public void clickFemaleRadioButton() {
        femaleRadioButton.select();
    }

    /**
     * Получение цвета кнопки "Женский" в переключателе пола
     */
    public String getFemaleRadioButtonColor() {
        return femaleRadioButton.getBackgroundColor();
    }

    /**
     * Получение значения в сохраненном поле "Дата рождения"
     */
    public String getDisplayedDate() {
        return dateText.getText();
    }

    /**
     * Получение значения в сохраненном поле "Имя и название канала"
     */
    public String getDisplayedName() {
        return nameText.getText();
    }
}
