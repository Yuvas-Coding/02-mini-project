package com.vtalent.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vtalent.entity.EligibiletyDetails;

public interface EligibilityDetailsRepository extends JpaRepository<EligibiletyDetails, Integer>  {

	
	@Query("select distinct(planName) from EligibiletyDetails")
	public List<String> findPlanNames();
	
	@Query("select distinct(planStatus) from EligibiletyDetails")
	public List<String> findPlanStatus();
}
