package cn.xm.util;

import java.util.List;

public class PageUtil<T> {
    public Integer index;//第几页
    public Integer size;//每页显示几条数据
    public Integer total;//总共多少条数据
    public Integer page;//总页数
    public List<T> list;
}