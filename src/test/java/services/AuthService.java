package services;

import pages.MainAfterLoginPage;
import pages.MainPage;

public class AuthService {
    public static MainAfterLoginPage auth() {
        MainPage mainPage = MainPage.openMainPage();
        mainPage.closePopups();
        mainPage.clickLoginButton();
        mainPage.switchToLoginFrame();
        mainPage.fillPhone();
        mainPage.clickContinue();
        mainPage.fillPassword();
        MainAfterLoginPage mainAfterLoginPage = mainPage.clickLogin();
        mainAfterLoginPage.closePopups();
        return mainAfterLoginPage;
    }

}
