package cn.xm.entity;

public class User {
    private Integer u_id;//唯一，可作为登陆账号
    private String userName;//唯一，可作为登陆账号
    private String passWord;
    private String email;//唯一，可作为登陆账号
    private String phone;//唯一，可作为登陆账号
    private String idCard;//唯一
    private String address;
    private Integer level;
    private String question;//密保问题
    private String answer;//密保答案
    private String userimg;

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User() {
    }

    public User(Integer u_id, String email, String phone,String passWord) {
        this.u_id = u_id;
        this.passWord = passWord;
        this.email = email;
        this.phone = phone;
    }

    public User(Integer u_id, String userName, String passWord, String email, String phone, String idCard, String address, Integer level, String question, String answer) {
        this.u_id = u_id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phone = phone;
        this.idCard = idCard;
        this.address = address;
        this.level = level;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", level=" + level +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
