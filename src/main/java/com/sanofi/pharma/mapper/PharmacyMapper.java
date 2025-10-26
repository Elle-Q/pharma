package com.sanofi.pharma.mapper;

import com.sanofi.pharma.entity.Pharmacy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface PharmacyMapper {

    @Select("select * from pharmacy where id = #{id}")
    Pharmacy getById(@Param("id") Long id);

    @Select("select * from pharmacy order by id desc")
    List<Pharmacy> findAll();
}
