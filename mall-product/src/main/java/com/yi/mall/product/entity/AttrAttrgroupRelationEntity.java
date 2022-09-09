package com.yi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ����&���Է������
 * 
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 05:32:23
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ����id
	 */
	private Long attrId;
	/**
	 * ���Է���id
	 */
	private Long attrGroupId;
	/**
	 * ������������
	 */
	private Integer attrSort;

}
