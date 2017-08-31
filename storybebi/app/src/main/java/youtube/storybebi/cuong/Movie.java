package youtube.storybebi.cuong;

/**
 * Created by QUOC CUONG on 31/08/2017.
 */

public class Movie {
    private Integer image;
    private String title;
    private String videoId;

    public Movie(Integer image, String title, String videoId) {
        this.image = image;
        this.title = title;
        this.videoId = videoId;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
