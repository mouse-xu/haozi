package cn.xm.entity;

public class Comment {
    private Integer co_id;
    private Integer p_id;
    private String userName;
    private String co_content;
    private String co_create_time;
    private String co_img;
    private Integer ct_id;
    private Integer up;

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCo_content() {
        return co_content;
    }

    public void setCo_content(String co_content) {
        this.co_content = co_content;
    }

    public String getCo_create_time() {
        return co_create_time;
    }

    public void setCo_create_time(String co_create_time) {
        this.co_create_time = co_create_time;
    }

    public String getCo_img() {
        return co_img;
    }

    public void setCo_img(String co_img) {
        this.co_img = co_img;
    }

    public Integer getCt_id() {
        return ct_id;
    }

    public void setCt_id(Integer ct_id) {
        this.ct_id = ct_id;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Comment(Integer co_id, Integer p_id, String userName, String co_content, String co_create_time, String co_img, Integer ct_id, Integer up) {
        this.co_id = co_id;
        this.p_id = p_id;
        this.userName = userName;
        this.co_content = co_content;
        this.co_create_time = co_create_time;
        this.co_img = co_img;
        this.ct_id = ct_id;
        this.up = up;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "co_id=" + co_id +
                ", p_id=" + p_id +
                ", userName='" + userName + '\'' +
                ", co_content='" + co_content + '\'' +
                ", co_create_time='" + co_create_time + '\'' +
                ", co_img='" + co_img + '\'' +
                ", ct_id=" + ct_id +
                ", up=" + up +
                '}';
    }
}
