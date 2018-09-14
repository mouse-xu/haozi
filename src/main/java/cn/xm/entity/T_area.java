package cn.xm.entity;

public class T_area {
    private Integer areaId;
    private String areaCode;
    private String areaName;
    private Integer level;
    private String cityCode;
    private String center;
    private Integer parentId;

    @Override
    public String toString() {
        return "T_area{" +
                "areaId=" + areaId +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", level=" + level +
                ", cityCode='" + cityCode + '\'' +
                ", center='" + center + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public T_area() {
    }

    public T_area(Integer areaId, String areaCode, String areaName, Integer level, String cityCode, String center, Integer parentId) {
        this.areaId = areaId;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.level = level;
        this.cityCode = cityCode;
        this.center = center;
        this.parentId = parentId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
