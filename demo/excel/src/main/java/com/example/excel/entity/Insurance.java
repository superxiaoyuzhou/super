package com.example.excel.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Insurance {

    /**
     * 编号
     */
    private String number;
    /**
     * 商户名称
     */
    private String name;
    /**
     * 保单号
     */
    private String order;
    /**
     * 险种名称
     */
    private String insuranceName;
    /**
     * 保险分类
     */
    private String classification;
    /**
     * 合作类型
     */
    private String type;
    /**
     * 保险公司
     */
    private String company;
    /**
     * 缴费年期
     */
    private Integer yearDate;
    /**
     * 投保日期及时间
     */
    private Date insuranceDateTime;

    /**
     * 推广人身份证号码
     */
    private String numberCard;

}
