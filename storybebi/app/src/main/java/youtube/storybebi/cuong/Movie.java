package youtube.storybebi.cuong;

/**
 * Created by QUOC CUONG on 31/08/2017.
 */

public class Movie {
    private String image;
    private String title;

    public Movie(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
