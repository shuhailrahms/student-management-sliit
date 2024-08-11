package com.shuhail.mh.dao;

import java.util.List;

import com.shuhail.mh.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttendaceRepo extends JpaRepository<Attendance, Integer> {

	@Query("from Attendance where registrationNumber like %?1%")
	List<Attendance> findByRegistrationNumberLike(String search);
	
	@Query("from Attendance where date = ?1")
	List<Attendance> findByDate(String date);

}
