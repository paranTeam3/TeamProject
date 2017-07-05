package com.paran.exercise1;

import java.util.ArrayList;
import java.util.List;

public class EducationManagementSystem {
	List<School> schoolList = new ArrayList<School>();
	
	public static void main(String[] args) {

	}
	
	public void initSchool() {
		schoolList.add(new School());
	}
}

class SchoolReadWriter {
	private String fileDirectory = "D:\\EMS\\";
	private String fileName = "";
	
	SchoolReadWriter() {
		SchoolReadFromFile();
	}

	public void SchoolReadFromFile() {

	}

	public void SchoolWriteToFile() {
		
	}
	
}


class School {
	private String schoolName;
	private int schoolLevel;
	private List<ClassInfo> classList = new ArrayList<ClassInfo>();
	
	public void initClass() {
		classList.add(new ClassInfo());
	}
}

class ClassInfo {
	private int years;
	private int classNo;
	private List<Student> stuList = new ArrayList<Student>();
	
	public void initStudent() {
		stuList.add(new DomeStudent());
		stuList.add(new ForeStudent());
	}
}

abstract class Student{
	private String name;
	private String stuID;
	private List<TermGrade> testResult = new ArrayList<TermGrade>();
	
	public abstract void showInfo();
	public abstract void evaluateTest();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStuID() {
		return stuID;
	}
	
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	
	public List<TermGrade> getTestResult() {
		return testResult;
	}
	public void setTestResult(List<TermGrade> testResult) {
		this.testResult = testResult;
	}
}


class DomeStudent extends Student {
	private String resiID;
	
	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateTest() {
		// TODO Auto-generated method stub
		super.getTestResult().add(new TermGrade());
	}
	
}

class ForeStudent extends Student {
	private String foreignID;
	
	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateTest() {
		// TODO Auto-generated method stub
		super.getTestResult().add(new TermGrade());
	}
	
}

class TermGrade {
	private double avgScore;
	private boolean term;
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	public void inputTermResult() {
		subjectList.add(new Subject());
	}
}

class Subject {
	private String subjectName;
	private int testScore;
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getTestScore() {
		return testScore;
	}
	public void setTestScore(int testScore) {
		this.testScore = testScore;
	}
}

class GradeRank {
	void gradeClassRank(Student stu) {
		
	}
	void gradeSchoolRank(Student stu) {
		
	}
}