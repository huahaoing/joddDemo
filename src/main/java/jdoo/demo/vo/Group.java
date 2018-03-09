package jdoo.demo.vo;

import java.util.List;

public class Group {
    private Integer id;
    private String groupName;
    private List<User> userList;

    public Integer getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", userList=" + userList +
                '}';
    }
}
