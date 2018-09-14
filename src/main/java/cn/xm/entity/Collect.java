package cn.xm.entity;

public class Collect {
    private Integer col_id;
    private Integer p_id;
    private Integer u_id;

    public Integer getCol_id() {
        return col_id;
    }

    public void setCol_id(Integer col_id) {
        this.col_id = col_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "col_id=" + col_id +
                ", p_id=" + p_id +
                ", u_id=" + u_id +
                '}';
    }

    public Collect(Integer col_id, Integer p_id, Integer u_id) {
        this.col_id = col_id;
        this.p_id = p_id;
        this.u_id = u_id;
    }

    public Collect() {
    }
}
