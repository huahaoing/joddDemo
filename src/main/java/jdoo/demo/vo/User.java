package jdoo.demo.vo;

import jodd.vtor.constraint.MaxLength;
import jodd.vtor.constraint.Min;

public class User {
    @Min(value = 10,message = "超过下限")
    private Integer id;
    @MaxLength(value = 20,message = "长度超限")
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
