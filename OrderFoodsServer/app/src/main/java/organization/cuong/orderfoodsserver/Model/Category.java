package organization.cuong.orderfoodsserver.Model;

/**
 * Created by QUOC CUONG on 17/10/2017.
 */

public class Category {
    private String Name, Image;

    public Category() {
    }

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
