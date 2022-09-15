package com.yi.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * 采购单的VO数据
 *
 * @author yi
 */
@Data
public class PurchaseDoneVO {

    private Long id;

    private List<PurchaseItemDoneVO> items;

}
