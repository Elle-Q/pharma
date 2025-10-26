package com.sanofi.pharma.mapper;

import com.sanofi.pharma.entity.Prescription;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
/**
 * @author ELLE Q
 * @since 2025-10-24
 */
@Mapper
public interface PrescriptionMapper {

    @Select("select * from prescription where id = #{id}")
    Prescription findById(@Param("id") Long id);

    @Select("select * from prescription where number = #{number}")
    Prescription findByNumber(@Param("number") String number);

    @Insert("INSERT INTO prescription (number, pharmacy_id, doctor_name, pharmacist_name, patient_id, diagnosis, status, expire_time, create_time, update_time) " +
            "VALUES (#{number}, #{pharmacyId}, #{doctorName}, #{pharmacistName}, #{patientId}, #{diagnosis}, #{status}, #{expireTime}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Prescription prescription);

    @Update({"<script> UPDATE prescription",
            "SET status = #{status}, update_time = #{updateTime}, version = version + 1 WHERE id = #{id} AND version = #{version} </script>"})
    int updateWithWithVersion(Prescription prescription);

    @Select("SELECT * FROM prescription WHERE id = #{id} AND status = #{status}")
    Prescription findByIdAndStatus(@Param("id") Long id, @Param("status") String status);

    @Update("UPDATE prescriptions SET status = #{newStatus}, version = version + 1 WHERE id = #{id} AND status = #{oldStatus} AND version = #{version}")
    int updateStatusCas(@Param("id") Long id, @Param("oldStatus") String oldStatus, @Param("newStatus") String newStatus, @Param("version") Integer version);

    @Update("""
            UPDATE prescription SET
            number = #{number},
            pharmacy_id = #{pharmacyId},
            doctor_name = #{doctorName},
            pharmacist_name = #{pharmacistName},
            patient_id = #{patientId},
            diagnosis = #{diagnosis},
            status = #{status},
            expire_time = #{expireTime},
            update_time = #{updateTime}
            WHERE id = #{id}""")
    int updateById(Prescription prescription);
}
