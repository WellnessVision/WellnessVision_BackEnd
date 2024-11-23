package com.example.WellnessVision.repository;


import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.model.AppointmentBookingPaymentForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminPrivilegeUserRepository extends JpaRepository<AdminPrivilegeUser, Integer> {

    @Query(value = "SELECT * FROM admin_privilege_user WHERE id = ?1", nativeQuery = true)
    AdminPrivilegeUser getAdminPrivilegeUserDetailsForAdminPrivilegeUsers(int adminPrivilegeUserId);

    @Query(value = "SELECT * FROM admin_privilege_user WHERE ( first_name LIKE ?1 OR last_name LIKE ?1 ) AND admin_type LIKE ?2", nativeQuery = true)
    List<AdminPrivilegeUser> adminViewAllSystemPrivilegeUserForAdmin(String modifiedSearchNameCode, String modifiedSearchTypeCode);

}
