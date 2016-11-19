package nju.quadra.hms.data.mysql;

import nju.quadra.hms.dataservice.OrderDataService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.po.OrderPO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by admin on 2016/11/16.
 */
public class OrderDataServiceImpl implements OrderDataService {
    @Override
    public ArrayList<OrderPO> getByCustomer(String username) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `orders` WHERE `username` = ? ORDER BY `startdate` DESC");
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();

        ArrayList<OrderPO> result = convertToArrayList(rs);
        if(!rs.isClosed()) rs.close();
        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();
        return result;
    }

    @Override
    public ArrayList<OrderPO> getByHotel(int hotelId) throws Exception{
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `orders` WHERE `hotelid` = ? ORDER BY `startdate` DESC");
        pst.setInt(1, hotelId);
        ResultSet rs = pst.executeQuery();

        ArrayList<OrderPO> result = convertToArrayList(rs);
        if(!rs.isClosed()) rs.close();
        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();
        return result;
    }

    @Override
    public ArrayList<OrderPO> getByState(OrderState state) throws Exception{
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `orders` WHERE `state` = ? ORDER BY `startdate` DESC");
        pst.setInt(1, state.ordinal());
        ResultSet rs = pst.executeQuery();

        ArrayList<OrderPO> result = convertToArrayList(rs);
        if(!rs.isClosed()) rs.close();
        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();
        return result;
    }

    @Override
    public void insert(OrderPO po) throws Exception{
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("INSERT INTO `orders` (`id`, `username`, `hotelid`, `startdate`, `enddate`, `roomid`, `roomcount`, `personcount`, `persons`, `haschildren`, `price`, `state`, `rank`, `comment`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        if (po.getId() > 0)
            pst.setInt(1, po.getId());
        else
            pst.setNull(1, Types.INTEGER);
        pst.setString(2, po.getUsername());
        pst.setInt(3, po.getHotelId());
        pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(po.getStartDate()));
        pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(po.getEndDate()));
        pst.setInt(6, po.getRoomId());
        pst.setInt(7, po.getRoomCount());
        pst.setInt(8, po.getPersonCount());
        pst.setString(9, po.getPersons());
        pst.setBoolean(10, po.isHasChildren());
        pst.setDouble(11, po.getPrice());
        pst.setInt(12, po.getState().ordinal());
        pst.setInt(13, po.getRank());
        pst.setString(14, po.getComment());
        pst.executeUpdate();

        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();
    }

    @Override
    public void delete(OrderPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `orders` WHERE `id` = ?");
        pst.setInt(1, po.getId());
        int result = pst.executeUpdate();
        if (result == 0) {
            throw new Exception("Order not found");
        }

        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();
    }

    @Override
    public void update(OrderPO po) throws Exception{
        delete(po);
        insert(po);
    }

    private ArrayList<OrderPO> convertToArrayList(ResultSet rs) throws Exception{
        ArrayList<OrderPO> result = new ArrayList<>();
        while (rs.next()) {
            OrderPO po = new OrderPO(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getInt("hotelid"),
                    rs.getDate("startdate"),
                    rs.getDate("enddate"),
                    rs.getInt("roomid"),
                    rs.getInt("roomcount"),
                    rs.getInt("personcount"),
                    rs.getString("persons"),
                    rs.getBoolean("haschildren"),
                    rs.getDouble("price"),
                    OrderState.getById(rs.getInt("state")),
                    rs.getInt("rank"),
                    rs.getString("comment")
            );
            result.add(po);
        }
        return result;
    }
}
