package com.sanofi.pharma.mapper;

import com.sanofi.pharma.entity.PrescriptionItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface PrescriptionItemMapper {

    @Insert("INSERT INTO prescription_item (prescription_id, drug_id, drug_name, dosage, amount, instruction) " +
            "VALUES (#{prescriptionId}, #{drugId}, #{drugName}, #{dosage}, #{amount}, #{instruction})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(PrescriptionItem item);

    @Select("select * from prescription_item where prescription_id = #{prescriptionId}")
    List<PrescriptionItem> findByPrescriptionId(Long prescriptionId);
}
