package pages.elements;

import static com.codeborne.selenide.Selenide.*;

public class PlaylistCard {
    private final String playlistName;
    private final String menuButton = "//div[contains(@class,'playlist-card')][.//*[contains(text(),'%s')]]//button[contains(@class,'menu-button')]";

    public PlaylistCard(String playlistName) {
        this.playlistName = playlistName;
    }

    public PlaylistCard openMenu() {
        $x(String.format(menuButton, playlistName)).click();
        return this;
    }

    public PlaylistCard selectAction(String action) {
        $x(String.format("//*[contains(@class,'menu-item')][contains(text(),'%s')]", action)).click();
        return this;
    }

    public void confirmDeletion() {
        Button.byText("Удалить").press();
    }

    public boolean isDisplayed() {
        return $x(String.format("//div[contains(@class,'playlist-card')][.//*[contains(text(),'%s')]]", playlistName))
                .isDisplayed();
    }
}
