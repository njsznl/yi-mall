package com.yi.mall.member.dao;

import com.yi.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author yi
 * @email yilaokela@gmail.com
 * @date 2022-09-10 16:20:55
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
