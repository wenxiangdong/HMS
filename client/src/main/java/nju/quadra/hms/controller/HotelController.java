package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelController {
    private HotelBLService hotelBL = BLServiceFactory.getHotelBLService();

    public ArrayList<AreaVO> getAllArea() {
        try {
            return hotelBL.getAllArea();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HotelVO> search(int areaId) {
        try {
            return hotelBL.search(areaId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HotelVO> getAll() {
        try {
            return hotelBL.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HotelVO getDetail(int id) {
        try {
            return hotelBL.getDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HotelVO getByStaff(String staff) {
        try {
            return hotelBL.getByStaff(staff);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(HotelVO vo) {
        try {
            return hotelBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int id) {
        try {
            return hotelBL.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(HotelVO vo) {
        try {
            return hotelBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage changeStaff(int id, String username) {
        try {
            return hotelBL.changeStaff(id, username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
