package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.CreditRecordBLService;
import nju.quadra.hms.blservice.OrderBLService;
import nju.quadra.hms.blservice.WebsitePromotionBLService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.OrderDetailVO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/12/4.
 */
public class WebMarketerController {

    private CreditRecordBLService creditBL = BLServiceFactory.getCreditRecordBLService();
    private WebsitePromotionBLService websitePromotionBL = BLServiceFactory.getWebsitePromotionBLService();
    private OrderBLService orderBL = BLServiceFactory.getOrderBLService();

    public ResultMessage creditTopup(String username, int amount) {
        try {
            return creditBL.topup(username, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ArrayList<WebsitePromotionVO> getWebsitePromotion() {
        try {
            return websitePromotionBL.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addWebsitePromotion(WebsitePromotionVO vo) {
        try {
            return websitePromotionBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage modifyWebsitePromotion(WebsitePromotionVO vo) {
        try {
            return websitePromotionBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage deleteWebsitePromotion(int id) {
        try {
            return websitePromotionBL.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ArrayList<OrderDetailVO> getOrders() {
        try {
            ArrayList<OrderDetailVO> orders = new ArrayList<>();
            orders.addAll(orderBL.getByState(OrderState.BOOKED));
            orders.addAll(orderBL.getByState(OrderState.DELAYED));
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage undoDelayedOrder(int orderId, boolean returnAllCredit) {
        try {
            return orderBL.undoDelayed(orderId, returnAllCredit);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
