package nju.quadra.hms.vo;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelRoomVO {
    /**
     * 客房ID
     */
    public int id;
    /**
     * 酒店ID
     */
    public int hotelId;
    /**
     * 客房名称
     */
    public String name;
    /**
     * 总数量
     */
    public int total;
    /**
     * 原始价格
     */
    public double price;

    public HotelRoomVO(int id, int hotelId, String name, int total, double price) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.total = total;
        this.price = price;
    }
}
