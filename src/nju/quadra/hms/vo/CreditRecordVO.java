package nju.quadra.hms.vo;

import nju.quadra.hms.model.CreditAction;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditRecordVO {
    /**
     * 信用记录ID
     */
    public int id;
    /**
     * 用户名
     */
    public String username;
    /**
     * 发生时间
     */
    public long timestamp;
    /**
     * 相关订单ID
     */
    public int orderId;
    /**
     * 动作
     */
    public CreditAction action;
    /**
     * 信用度变化
     */
    public double diff;
    /**
     * 信用度结果
     */
    public double creditResult;

    public CreditRecordVO(int id, String username, long timestamp, int orderId, CreditAction action, double diff, double creditResult) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
        this.orderId = orderId;
        this.action = action;
        this.diff = diff;
        this.creditResult = creditResult;
    }
}
