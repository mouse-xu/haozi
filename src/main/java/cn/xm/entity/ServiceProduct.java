package cn.xm.entity;

public class ServiceProduct {
    private Integer ser_id;
    private Integer c_id;
    private String ser_name;
    private Integer ser_priceNow;
    private Integer ser_priceOld;
    private String ser_picture;
    private Integer ser_state;
    private String ser_description;

    @Override
    public String toString() {
        return "ServiceProduct{" +
                "ser_id=" + ser_id +
                ", c_id=" + c_id +
                ", ser_name='" + ser_name + '\'' +
                ", ser_priceNow=" + ser_priceNow +
                ", ser_priceOld=" + ser_priceOld +
                ", ser_picture='" + ser_picture + '\'' +
                ", ser_state=" + ser_state +
                ", ser_description='" + ser_description + '\'' +
                '}';
    }

    public ServiceProduct() {
    }

    public ServiceProduct(Integer c_id, String ser_name, Integer ser_priceNow, Integer ser_priceOld, String ser_picture, Integer ser_state, String ser_description) {
        this.c_id = c_id;
        this.ser_name = ser_name;
        this.ser_priceNow = ser_priceNow;
        this.ser_priceOld = ser_priceOld;
        this.ser_picture = ser_picture;
        this.ser_state = ser_state;
        this.ser_description = ser_description;
    }

    public Integer getSer_id() {
        return ser_id;
    }

    public void setSer_id(Integer ser_id) {
        this.ser_id = ser_id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getSer_name() {
        return ser_name;
    }

    public void setSer_name(String ser_name) {
        this.ser_name = ser_name;
    }

    public Integer getSer_priceNow() {
        return ser_priceNow;
    }

    public void setSer_priceNow(Integer ser_priceNow) {
        this.ser_priceNow = ser_priceNow;
    }

    public Integer getSer_priceOld() {
        return ser_priceOld;
    }

    public void setSer_priceOld(Integer ser_priceOld) {
        this.ser_priceOld = ser_priceOld;
    }

    public String getSer_picture() {
        return ser_picture;
    }

    public void setSer_picture(String ser_picture) {
        this.ser_picture = ser_picture;
    }

    public Integer getSer_state() {
        return ser_state;
    }

    public void setSer_state(Integer ser_state) {
        this.ser_state = ser_state;
    }

    public String getSer_description() {
        return ser_description;
    }

    public void setSer_description(String ser_description) {
        this.ser_description = ser_description;
    }
}
