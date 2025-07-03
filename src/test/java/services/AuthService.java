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
        mainPage.clickLogin();
        mainPage.closePopups();
        return new MainAfterLoginPage();
    }

}
