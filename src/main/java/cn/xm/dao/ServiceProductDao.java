package cn.xm.dao;

import cn.xm.entity.ServiceProduct;

import javax.xml.ws.ServiceMode;
import java.util.List;

public class ServiceProductDao extends BassDao {
    //根据指定商品分类编号查询服务产品
    public List<ServiceProduct> queryAll(Integer c_id){
        String sql = "select * from serviceProduct where c_id = ?";
        return super.queryAll(ServiceProduct.class,sql,c_id );
    }
    //根据指定商品分类编号查询服务产品
    public ServiceProduct queryOne(Integer ser_id){
        String sql = "select * from serviceProduct where ser_id = ?";
        return super.queryOne(ServiceProduct.class,sql,ser_id );
    }
}
