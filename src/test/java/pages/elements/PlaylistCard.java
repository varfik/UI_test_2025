package pages.elements;

import org.openqa.selenium.By;

public class PlaylistCard {
    private final String playlistName;
    private final By cardLocator;
    private final By menuButtonLocator;

    public PlaylistCard(String playlistName) {
        this.playlistName = playlistName;
        this.cardLocator = By.xpath(String.format(
                "//div[contains(@class, 'playlist-card') and contains(., '%s')]",
                playlistName));
        this.menuButtonLocator = By.xpath(String.format(
                "//div[contains(@class, 'playlist-card') and contains(., '%s')]//button[contains(@class, 'menu-button')]",
                playlistName));
    }

    public PlaylistCard openMenu() {
        BasePage.click(menuButtonLocator);
        return this;
    }

    public void selectAction(String actionName) {
        By actionLocator = By.xpath(String.format(
                "//div[@role='menu']//*[contains(text(), '%s')]",
                actionName));
        BasePage.click(actionLocator);
    }
}
