package com.example.norcomapllication.repository;

import com.example.norcomapllication.model.entity.MobilePlan;
import com.example.norcomapllication.model.view.MobilePlanDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobilePlanRepository extends JpaRepository<MobilePlan,Long> {
}
