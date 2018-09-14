package cn.xm.dao;

import cn.xm.dao.BassDao;
import cn.xm.entity.T_area;

import java.util.List;
import java.util.Map;

public class T_areaDao extends BassDao{
    //根据父级Id查询所有子级元素
    public List<T_area> queryT_areas(Integer parentId){
        String sql ="select areaId,areaName,parentId from t_area where parentId = ?;";
        return super.queryAll(T_area.class, sql,parentId);
    }
    public List<T_area> queryT_areas(String areaName){
        String sql ="select areaId,areaName,parentId from t_area where areaName = ?;";
        return super.queryAll(T_area.class, sql,areaName);
    }
    //根据父级id查询到指定名字的地区的id
    public T_area queryOneT_rea(Integer parentId,String areaName){
        String sql = "select areaId from t_area where parentId = ? and areaName = ?";
        return super.queryOne(T_area.class,sql, parentId,areaName);
    }

    public static void main(String[] args) {
        T_areaDao td  = new T_areaDao();
        List<T_area> list = td.queryT_areas(810);
        System.out.println(list.size());
        System.out.println(list);
    }
}
