package com.vghs.student.service;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vghs.student.dto.AdmissionOpenDto;
import com.vghs.student.model.AdmissionOpen;
import com.vghs.student.model.StudentData;
import com.vghs.student.repository.AdmissionOpenRepo;
import com.vghs.student.repository.HomeRepo;

@Service
public class HomeService {
	
	@Autowired
	private HomeRepo homeRepo;
	
	@Autowired
	private AdmissionOpenRepo admissionOpenRepo;

	public String addStudentDetails(StudentData studentData) {
		homeRepo.save(studentData);
		return "Successfully student details added";
	}
	
	
	public StudentData getStudentById(Integer studentId) {
		return homeRepo.getStudentById(studentId);
	}
	

	public List<StudentData> getStudentList() {
		List<StudentData> studentList = homeRepo.findAll();
		List<StudentData> studentListWithFemale = new ArrayList<>();
		
		for(StudentData student : studentList) {
			if("male".equalsIgnoreCase(student.getGender())) {
				studentListWithFemale.add(student);
			}
		}
		return studentListWithFemale;
	}


	public String updateStudentDetails(Integer studentId) {
		StudentData studentData = homeRepo.getStudentById(studentId);
		if(studentData != null) {
			studentData.setLastName("Velu");
			studentData.setFirstName("Denesh");
			homeRepo.save(studentData);
		}
		return "Successfully student details updated...";
	}


	public AdmissionOpenDto getAdmissionInfo(Integer admissionId) {
		// TODO Auto-generated method stub
		AdmissionOpenDto admissionOpenDto= new AdmissionOpenDto();
		AdmissionOpen admissionOpen = admissionOpenRepo.findByAdmissionOpenId(admissionId);
		if(admissionOpen != null) {
		admissionOpenDto.setAdmissionOpenId(admissionOpen.getAdmissionOpenId());
		admissionOpenDto.setAdmissionOpenDate(admissionOpen.getAdmissionOpenDate());
		admissionOpenDto.setAdmissionEndDate(admissionOpen.getAdmissionEndDate());
		admissionOpenDto.setAdmissionTitle(admissionOpen.getAdmissionTitle());
		return admissionOpenDto;
		}else {
			return null;
		}
	}

}
