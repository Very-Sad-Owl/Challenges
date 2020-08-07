package android.projects.pidorsbizzareadventure.pojo;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String nickname;
    private String uid;

    public User(){}

    public User(String email, String password, String nickname, String uid) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.uid = uid;
    }

    public User(User user){
        this.email = user.email;
        this.password = user.password;
        this.password = user.password;
        this.uid = user.uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, nickname, uid);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
