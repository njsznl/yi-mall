package com.yi.mall.product.vo;

import lombok.Data;

/**
 * @author yi
 */
@Data
public class AttrResponseVo extends AttrVO{

    private String catelogName;

    private String groupName;

    private Long[] catelogPath;
}
