package cn.xm.entity;

public class Receiver {
    private Integer r_id;
    private Integer u_id;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private Integer postcode;
    private String receiverAddressNickName;

    @Override
    public String toString() {
        return "Receiver{" +
                "r_id=" + r_id +
                ", u_id=" + u_id +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", postcode=" + postcode +
                ", receiverAddressNickName='" + receiverAddressNickName + '\'' +
                '}';
    }

    public Receiver() {
    }

    public Receiver(Integer r_id, Integer u_id, String receiverName, String receiverPhone, String receiverAddress, Integer postcode, String receiverAddressNickName) {
        this.r_id = r_id;
        this.u_id = u_id;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.postcode = postcode;
        this.receiverAddressNickName = receiverAddressNickName;
    }

    public Receiver(Integer u_id, String receiverName, String receiverPhone, String receiverAddress, Integer postcode, String receiverAddressNickName) {
        this.u_id = u_id;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.postcode = postcode;
        this.receiverAddressNickName = receiverAddressNickName;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getReceiverAddressNickName() {
        return receiverAddressNickName;
    }

    public void setReceiverAddressNickName(String receiverAddressNickName) {
        this.receiverAddressNickName = receiverAddressNickName;
    }
}

