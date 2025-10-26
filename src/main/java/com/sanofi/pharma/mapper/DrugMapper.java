package com.sanofi.pharma.mapper;

import com.sanofi.pharma.entity.Drug;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Drug Mapper
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface DrugMapper {
    @Select("select * from drug where id = #{id}")
    Drug getById(@Param("id") Long id);

    @Insert("INSERT INTO drug (name,batch_number,manufacturer_id,stock,description,expiry_date,create_time,update_time) " +
            "VALUES (#{name},#{batchNumber},#{manufacturerId},#{stock},#{description},#{expiryDate},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insert(Drug drug);

    @Update("UPDATE drug SET " +
            "stock = stock - #{deductStock}, " +
            "version = version + 1 " +
            "WHERE id = #{id} " +
            "AND stock >= #{deductStock} " +
            "AND version = #{version}")
    int deductStockWithVersion(@Param("id") Long id, @Param("deductStock") Integer deductStock, @Param("version") Integer version);

}
