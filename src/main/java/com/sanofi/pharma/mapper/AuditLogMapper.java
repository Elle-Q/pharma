package com.sanofi.pharma.mapper;

import com.sanofi.pharma.dto.request.AuditLogSearchRequest;
import com.sanofi.pharma.entity.AuditLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuditLogMapper {

    @Insert("INSERT INTO audit_log (operation_type,prescription_id,patient_id,pharmacy_id,operator_name,status," +
            "drugs_requested,drugs_dispensed,failure_reasons,create_time,update_time) " +
            "VALUES (#{operationType},#{prescriptionId},#{patientId},#{pharmacyId},#{operatorName},#{status}," +
            "#{drugsRequested},#{drugsDispensed},#{failureReasons},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(AuditLog auditLog);


    @Select("""
        <script>
            SELECT * FROM audit_log 
            <where>
                <if test="request.patientId != null and request.patientId != ''">
                    AND patient_id = #{request.patientId}
                </if>
                <if test="request.pharmacyId != null and request.pharmacyId != ''">
                    AND pharmacy_id = #{request.pharmacyId}
                </if>
                <if test="request.status != null and request.status != ''">
                    AND status = #{request.status}
                </if>
            </where>
            ORDER BY create_time DESC
        </script>
        """)
    List<AuditLog> findBy(@Param("request")AuditLogSearchRequest request);
}
