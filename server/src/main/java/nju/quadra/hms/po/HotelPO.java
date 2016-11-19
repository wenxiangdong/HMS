package nju.quadra.hms.po;

import nju.quadra.hms.vo.HotelVO;

/**
 * Created by adn55 on 16/10/14.
 */
public class HotelPO {
    /**
     * 酒店ID
     */
    private int id;
    /**
     * 酒店名
     */
    private String name;
    /**
     * 城市ID
     */
    private int cityId;
    /**
     * 商圈ID
     */
    private int areaId;
    /**
     * 地址
     */
    private String address;
    /**
     * 简介
     */
    private String description;
    /**
     * 设施服务
     */
    private String facilities;
    /**
     * 工作人员用户名
     */
    private String staff;

    public HotelPO(int id, String name, int cityId, int areaId, String address, String description, String facilities, String staff) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.areaId = areaId;
        this.address = address;
        this.description = description;
        this.facilities = facilities;
        this.staff = staff;
    }

    public HotelPO(HotelVO vo) {
        this(vo.id, vo.name, vo.cityId, vo.areaId, vo.address, vo.description, vo.facilities, vo.staff);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}
