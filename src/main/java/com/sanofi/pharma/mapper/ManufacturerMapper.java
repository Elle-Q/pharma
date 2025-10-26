package com.sanofi.pharma.mapper;

import com.sanofi.pharma.entity.Manufacturer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;


/**
 * Drug Mapper
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface ManufacturerMapper {
    @Select("select * from manufacturer where id = #{id}")
    Manufacturer getById(@Param("id") Long id);

}
