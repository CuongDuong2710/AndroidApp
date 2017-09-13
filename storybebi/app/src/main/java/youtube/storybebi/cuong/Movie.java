package youtube.storybebi.cuong;

import java.io.Serializable;

/**
 * Created by QUOC CUONG on 31/08/2017.
 */

public class Movie implements Serializable {
    private String image;
    private String title;
    private String videoId;
    private String length;

    public Movie(String image, String title, String videoId, String length) {
        this.image = image;
        this.title = title;
        this.videoId = videoId;
        this.length = length;
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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
