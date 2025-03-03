package com.shuhail.mh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shuhail.mh.model.Course;

public interface CourseRepo extends JpaRepository<Course, String>{

	@Query("from Course where courseName like %?1%")
	List<Course> findByCourseNameLike(String courseName);
	
}
