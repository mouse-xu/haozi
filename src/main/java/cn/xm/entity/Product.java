package cn.xm.entity;

public class Product {
    private Integer p_id;
    private Integer c_id;
    private Integer market_price;
    private Integer shop_price;
    private String p_name;
    private String image;
    private Integer inventory;
    private Integer flag;
    private String description;
    private Integer comments;
    private Integer percent;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Integer market_price) {
        this.market_price = market_price;
    }

    public Integer getShop_price() {
        return shop_price;
    }

    public void setShop_price(Integer shop_price) {
        this.shop_price = shop_price;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String toString() {
        return "Product{" +
                "p_id=" + p_id +
                ", c_id=" + c_id +
                ", market_price=" + market_price +
                ", shop_price=" + shop_price +
                ", p_name='" + p_name + '\'' +
                ", image='" + image + '\'' +
                ", inventory=" + inventory +
                ", flag=" + flag +
                ", description='" + description + '\'' +
                '}';
    }

}
