package cn.xm.dao;

import cn.xm.entity.Receiver;

import java.util.List;

public class ReceiverDao extends BassDao {

    //查询指定用户u_id的所有已存收货地址集合
    public List<Receiver> queryAllReceiver(Receiver re){
        String sql = "select * from receiver where u_id = ?";
        return super.queryAll(Receiver.class,sql,re.getU_id());
    }
    //查询指定用户r_id的所有已存收货地址集合
    public Receiver queryReceiver(Integer r_id){
        String sql = "select * from receiver where r_id = ?";
        return super.queryOne(Receiver.class,sql,r_id );
    }
    //增加新的收货地址
    public int insertReceiver(Receiver re){
        String sql = "insert into receiver (u_id,receiverName,receiverPhone,receiverAddress,postcode,receiverAddressNickName) values(?,?,?,?,?,?)";
        return super.update(sql,re.getU_id(),re.getReceiverName(),re.getReceiverPhone(),re.getReceiverAddress(),re.getPostcode(),re.getReceiverAddressNickName());
    }
    //修改收货地址
    public int modifiedReceiver(Receiver re){
        String sql = "update receiver set receiverName=?,receiverPhone=?,receiverAddress=?,postcode=?,receiverAddressNickName=? where r_id = ?";
        return super.update(sql,re.getReceiverName(),re.getReceiverPhone(),re.getReceiverAddress(),re.getPostcode(),re.getReceiverAddressNickName(),re.getR_id());
    }
    //删除指定r_id的地址信息
    public int deleteReceiver(Integer r_id){
        String sql = "delete  from receiver where r_id = ?";
        return super.update(sql,r_id);
    }

    public static void main(String[] args) {
        ReceiverDao rd = new ReceiverDao();
        Receiver r = new Receiver();
        r.setU_id(1020);
        System.out.println( rd.queryAllReceiver(r));
    }
}
