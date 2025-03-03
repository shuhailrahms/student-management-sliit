package com.shuhail.mh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shuhail.mh.model.Student;

public interface StudentRepo extends JpaRepository<Student, String> {

	@Query("from Student where studentName like %?1%")
	List<Student> findByStudentNameLike(String studentName);

	List<Student> findByCourse(String course);
	
	@Query("from Student order by date DESC")
	List<Student> orderByDate();
}
