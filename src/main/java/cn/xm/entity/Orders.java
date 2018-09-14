package cn.xm.entity;

public class Orders {
    private Integer o_id ;
    private Integer u_id;
    private Integer r_id;
    private String orderCreatTime;
    private Double orderPrice;
    private Integer orderState;
    private String orderPayTime;
    private String orderFinishTime;

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Orders(Integer u_id, Integer r_id, Double orderPrice, Integer orderState) {
        this.u_id = u_id;
        this.r_id = r_id;
        this.orderPrice = orderPrice;
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "o_id=" + o_id +
                ", u_id=" + u_id +
                ", r_id=" + r_id +
                ", orderCreatTime='" + orderCreatTime + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderState=" + orderState +
                ", orderPayTime='" + orderPayTime + '\'' +
                ", orderFinishTime='" + orderFinishTime + '\'' +
                '}';
    }

    public Orders() {
    }

    public Orders(Integer o_id, Integer u_id, Integer r_id, String orderCreatTime, Double orderPrice, Integer orderState, String orderPayTime,String orderFinishTime) {
        this.o_id = o_id;
        this.u_id = u_id;
        this.r_id = r_id;
        this.orderCreatTime = orderCreatTime;
        this.orderPrice = orderPrice;
        this.orderState = orderState;
        this.orderPayTime = orderPayTime;
        this.orderFinishTime = orderFinishTime;

    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public String getOrderCreatTime() {
        return orderCreatTime;
    }

    public void setOrderCreatTime(String orderCreatTime) {
        this.orderCreatTime = orderCreatTime;
    }

    public String getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}
