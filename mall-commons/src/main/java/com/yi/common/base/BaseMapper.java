package com.yi.common.base;

import java.util.List;

/**
 * vo转entity
 *
 * @author : xiao on 2022/9/14 3:58
 * @version : 1.0
 */
public interface BaseMapper<V, E> {


    /**
     * Vo 转entity
     *
     * @param vo vo
     * @return E
     */
    E toEntity(V vo);

    /**
     * Entity转DTO
     *
     * @param entity /
     * @return /
     */
    V toVo(E entity);

    /**
     * VO集合转Entity集合
     *
     * @param voList /
     * @return /
     */
    List<E> toEntity(List<V> voList);

    /**
     * Entity集合转VO集合
     *
     * @param entityList /
     * @return /
     */
    List<V> toVo(List<E> entityList);
}
