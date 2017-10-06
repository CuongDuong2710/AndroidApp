package organization.cuong.orderfoods.model;

/**
 * Created by QUOC CUONG on 06/10/2017.
 */

public class User {
    // need same Case with firebase name
    private String Name;
    private String Password;

    public User() {
    }

    public User(String name, String password) {
        this.Name = name;
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
