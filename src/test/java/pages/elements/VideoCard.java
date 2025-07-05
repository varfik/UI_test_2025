package pages.elements;

/** Карточка видео для добавления в "Смотреть позже" */
public class VideoCard {
    private final String videoTitle;

    public VideoCard(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void openMenu() {
        String cardXpath = String.format("//ytd-video-renderer[.//*[contains(text(), '%s')]]", videoTitle);
        Button.byXPath(cardXpath + "//button[contains(@class, 'menu-button')]").press();
    }

    public void addToWatchLater() {
        Button.byText("Сохранить в «Смотреть позже»").press();
    }
}