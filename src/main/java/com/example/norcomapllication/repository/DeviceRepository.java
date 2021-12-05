package com.example.norcomapllication.repository;

import com.example.norcomapllication.model.entity.Device;
import com.example.norcomapllication.model.view.DeviceSummaryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    @Query("SELECT d FROM Device AS d WHERE d.name LIKE CONCAT('%',:keyword,'%') ")
    List<Device> searchDevice(String keyword);

}
