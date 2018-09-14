package cn.xm.entity;

public class ShoppingCarProduct {
    private Integer car_id;
    private Integer u_id;
    private Integer p_id;
    private Integer ser_id;
    private Integer count;
    private Integer state;
    private String creatTime;

    public ShoppingCarProduct(Integer u_id, Integer p_id, Integer ser_id, Integer count, Integer state, String creatTime) {
        this.u_id = u_id;
        this.p_id = p_id;
        this.ser_id = ser_id;
        this.count = count;
        this.state = state;
        this.creatTime = creatTime;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "ShoppingCarProduct{" +
                "u_id=" + u_id +
                ", p_id=" + p_id +
                ", ser_id=" + ser_id +
                ", count=" + count +
                ", state=" + state +
                '}';
    }


    public ShoppingCarProduct() {
    }

    public ShoppingCarProduct( Integer u_id, Integer p_id,Integer ser_id, Integer count, Integer state) {
        this.u_id = u_id;
        this.p_id = p_id;
        this.ser_id = ser_id;
        this.count = count;
        this.state = state;
    }

    public Integer getSer_id() {
        return ser_id;
    }

    public void setSer_id(Integer ser_id) {
        this.ser_id = ser_id;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
