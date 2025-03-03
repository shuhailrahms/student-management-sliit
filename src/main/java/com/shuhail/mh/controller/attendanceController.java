package com.shuhail.mh.controller;

import java.util.List;

import com.shuhail.mh.dao.AttendaceRepo;
import com.shuhail.mh.dao.CourseRepo;
import com.shuhail.mh.dao.StudentRepo;
import com.shuhail.mh.model.Attendance;
import com.shuhail.mh.model.Course;
import com.shuhail.mh.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class attendanceController {

	@Autowired
    CourseRepo courseRepo;
	
	@Autowired
    StudentRepo studentRepo;
	
	@Autowired
    AttendaceRepo repo;
	
	@RequestMapping("/new-attendance")
	public ModelAndView newAttendance() {
		List<Course> coursList = courseRepo.findAll();
		
		ModelAndView mv = new ModelAndView("new-attendance");
		mv.addObject("courseList", coursList);
		return mv;
	}
	
	@RequestMapping("/attendance-list")
	public ModelAndView attendanceList(Attendance attendance) {
		List<Student> students = studentRepo.findByCourse(attendance.getCourse());
		ModelAndView mv = new ModelAndView("new-attendance-mark");
		mv.addObject("studentList", students);
		mv.addObject("attendance", attendance);
		return mv;
	}
	
	@RequestMapping("/mark-attendance")
	public ModelAndView markAttendance(Attendance attendance) {
		String attendanceStr = attendance.getAttendance();
		String[] attendanceStrSplit = attendanceStr.split(",");
		
		String registrationNum = attendance.getRegistrationNumber();
		String[] registrationNumSplit = registrationNum.split(",");
	
		int count = 0;
		
		for(String a : attendanceStrSplit) {
			count++;
		}
		
		for(int i = 0; i < count; i++) {
			repo.save(new Attendance(registrationNumSplit[i], attendance.getDate(),
				attendanceStrSplit[i], attendance.getCourse()));
		}
		
		ModelAndView mv = new ModelAndView("new-attendance");
		return mv;
	}
	
	@RequestMapping("view-attendance")
	public ModelAndView viewAttendance() {
		List<Attendance> attendanceList = repo.findAll();
		
		ModelAndView mv = new ModelAndView("view-attendance");
		mv.addObject("attendanceList", attendanceList);
		return mv;
	}
	
	@RequestMapping("/delete-attendance")
	public ModelAndView deleteAttendance(int delete) {
		repo.deleteById(delete);
		return viewAttendance();
	}
	
	@RequestMapping("/serachAttendance")
	public ModelAndView search(String search)
	{
		List<Attendance> attendances = repo.findByRegistrationNumberLike(search);
		ModelAndView mv = new ModelAndView("view-attendance");
		mv.addObject("attendanceList", attendances);
		return mv;
	}
	
	@RequestMapping("/update-attendance-page")
	public ModelAndView updateAttendancePage(int update) {
		Attendance attendance = repo.getReferenceById(update);
		
		ModelAndView mv = new ModelAndView("update-attendance");
		mv.addObject("attendance", attendance);
		return mv;
	}
	
	@RequestMapping("/update-attendance")
	public ModelAndView updateAttendance(String registrationNumber, String date, String attendance, int id) {
		Attendance attendanceObj = repo.getReferenceById(id);
		
		attendanceObj.setAttendance(attendance);
		attendanceObj.setDate(date);
		
		repo.save(attendanceObj);
		
		return viewAttendance();
	}
}
