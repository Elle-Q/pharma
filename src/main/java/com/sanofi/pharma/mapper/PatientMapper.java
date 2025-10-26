package com.sanofi.pharma.mapper;

import com.sanofi.pharma.entity.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface PatientMapper {
    @Insert("INSERT INTO patient (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insert(Patient patient);

    @Select("select * from patient where id = #{id}")
    Patient getById(@Param("id") Long id);

}
