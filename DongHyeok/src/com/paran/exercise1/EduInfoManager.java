package com.paran.exercise1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EduInfoManager {
	public static void main(String[] args) throws IOException {
		SchoolReadWriter mkdirTest = new SchoolReadWriter(); 
	}
}

class SchoolReadWriter {
	List<School> schoolList = new ArrayList<School>();
	private static String fileDir = "D:\\EMS\\";
	private static String fileName = "SchoolCollection.txt";
	private static String[] strBuffer;
	private static String folderDir;
	
	SchoolReadWriter() throws IOException {
		SchoolReadFromFile();
	}

	public void SchoolReadFromFile() throws IOException {
		BufferedReader schoolFile =
				new BufferedReader(new FileReader(fileDir + fileName));
		while(true) {
			String fileLine = schoolFile.readLine();
			if (fileLine == null) break;
			strBuffer = fileLine.split("\t");
			switch (Integer.parseInt(strBuffer[0])) {
				case 1: 
					initSchool(1);
					break;
				case 2:
					initSchool(2);
					break;
				case 3:
					initSchool(3);
					break;
				case 4:
					initSchool(4);
					break;
				default:
					break;
			}

		}
		schoolFile.close();
	}

	public void SchoolWriteToFile() throws IOException {
		BufferedWriter outputFile = 
				new BufferedWriter(new FileWriter(fileDir));
	}
	
	public void initSchool(int schoolLevel) {
		schoolList.add(new School(schoolLevel));
	}
	
	public static String getFileDir() {
		return fileDir;
	}
	
	public static String getFileName() {
		return fileName;
	}

	public static String[] getStrBuffer() {
		return strBuffer;
	}

	public static String getFolderDir() {
		return folderDir;
	}
	
	public static void setFolderDir(String folderDir) {
		SchoolReadWriter.folderDir = folderDir;
	}
}


class School {
	private String schoolName;
	private int schoolLevel;
	private List<ClassInfo> classList = new ArrayList<ClassInfo>();
	private File schoolFolderDirs;
	
	School(int schoolLevel) {
		this.schoolLevel = schoolLevel;
		this.schoolName = SchoolReadWriter.getStrBuffer()[1];
		
		SchoolReadWriter.setFolderDir(SchoolReadWriter.getFileDir());
		switch (schoolLevel) {
			case 1:
				SchoolReadWriter.setFolderDir(SchoolReadWriter.getFolderDir() + "Elementary");
				break;
			case 2:
				SchoolReadWriter.setFolderDir(SchoolReadWriter.getFolderDir() + "Middle");
				break;
			case 3:
				SchoolReadWriter.setFolderDir(SchoolReadWriter.getFolderDir() + "High");
				break;
			case 4:
				SchoolReadWriter.setFolderDir(SchoolReadWriter.getFolderDir() + "University");
				break;
			default:
				break;
		}
		SchoolReadWriter.setFolderDir(SchoolReadWriter.getFolderDir() + "\\" + schoolName);
		schoolFolderDirs = new File(SchoolReadWriter.getFolderDir());
		if (!schoolFolderDirs.exists()) schoolFolderDirs.mkdirs();
		
		initClass();
	}
	
	public void initClass() {
		classList.add(new ClassInfo());
	}
}

class ClassInfo {
	private int years;
	private int classNo;
	private List<Student> stuList = new ArrayList<Student>();
	private File classFolderDirs;
	
	ClassInfo() {
		this.years = Integer.parseInt(SchoolReadWriter.getStrBuffer()[2]);
		this.classNo = Integer.parseInt(SchoolReadWriter.getStrBuffer()[3]);
		
		classFolderDirs = new File(SchoolReadWriter.getFolderDir() + "\\" + years + "\\" + classNo);
		if (!classFolderDirs.exists()) classFolderDirs.mkdirs();
	}
	
	public void initStudent() {
		stuList.add(new DomeStudent());
		stuList.add(new ForeStudent());
	}
}

abstract class Student implements Comparable<Student>{
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

	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		return 0;
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