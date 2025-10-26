package com.sanofi.pharma.mapper;

import com.sanofi.pharma.dto.common.ContractDrug;
import com.sanofi.pharma.entity.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface ContractMapper {

    @Select("""
            SELECT d.id as drug_id,
            d.name,
            d.batch_number,
            m.name as manufacturer,
            d.description,
            d.expiry_date,
            c.allocated_amount,
            c.used_amount
            FROM contract c
            INNER JOIN drug d ON c.drug_id = d.id
            INNER JOIN manufacturer m ON d.manufacturer_id = m.id
            WHERE c.pharmacy_id = ${pharmacyId} AND c.status = 1
            ORDER BY d.id""")
    List<ContractDrug> findContractDrugsByPharmacyId(Long pharmacyId);


    @Select("""
            SELECT d.id as drug_id,
            d.name,
            d.batch_number,
            m.name as manufacturer,
            d.description,
            d.expiry_date,
            c.allocated_amount,
            c.used_amount
            FROM contract c
            INNER JOIN drug d ON c.drug_id = d.id
            INNER JOIN manufacturer m ON d.manufacturer_id = m.id
            WHERE c.pharmacy_id = ${pharmacyId} AND c.drug_id = ${drugId} AND c.status = 1
            ORDER BY d.id""")
    ContractDrug findDetailByPharmacyIdAndDrugId(Long pharmacyId, Long drugId);

    @Update("""
            UPDATE contract SET
            used_amount = used_amount + #{usedAmount},
            version = version + 1
            WHERE drug_id = #{drugId}  AND pharmacy_id = #{pharmacyId} AND status = 1 AND version = #{version}""")
    int updateUsageAmountWithVersion(@Param("drugId") Long drugId, @Param("pharmacyId") Long pharmacyId,
                               @Param("usedAmount") Integer usedAmount, @Param("version") Integer version);

    @Select("SELECT * FROM contract WHERE drug_id = #{drugId} AND pharmacy_id = #{pharmacyId}")
    Contract findByDrugIdPharmacyId(@Param("drugId") Long drugId, @Param("pharmacyId") Long pharmacyId);
}
