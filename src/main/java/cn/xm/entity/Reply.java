package cn.xm.entity;

public class Reply {
    private Integer r_id;
    private Integer co_id;
    private String userName;
    private String r_content;
    private String r_create_time;

    public Reply(Integer r_id, Integer co_id, String userName, String r_content, String r_create_time) {
        this.r_id = r_id;
        this.co_id = co_id;
        this.userName = userName;
        this.r_content = r_content;
        this.r_create_time = r_create_time;
    }

    public Reply() {
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getR_content() {
        return r_content;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public String getR_create_time() {
        return r_create_time;
    }

    public void setR_create_time(String r_create_time) {
        this.r_create_time = r_create_time;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "r_id=" + r_id +
                ", co_id=" + co_id +
                ", userName='" + userName + '\'' +
                ", r_content='" + r_content + '\'' +
                ", r_create_time='" + r_create_time + '\'' +
                '}';
    }
}
