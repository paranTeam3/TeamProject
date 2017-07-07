package com.paran.exercise1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EduInfoManager {
	public static void main(String[] args) throws IOException {
		StudentReadWriter eduInfo = new StudentReadWriter();
		
		for (int i = 0 ; i < eduInfo.schoolList.size(); i++) {
			for (int j = 0 ; j < eduInfo.schoolList.get(i).getClassList().size(); j++) {
				GradeRank.gradeRankByMidTerm(eduInfo.schoolList.get(i).getClassList().get(j));
			}
		}
		
		eduInfo.writeToFile();
	}
}

class StudentReadWriter {
	List<School> schoolList = new ArrayList<School>();
	private static String fileDir = "D:\\EMS\\";
	private static String fileName = "SchoolCollection.txt";
	private static String[] strBuffer;
	private static String folderDir;
	
	public StudentReadWriter() throws IOException {
		readFromFile();
	}

	public void readFromFile() throws IOException {
		BufferedReader schoolFile =
				new BufferedReader(new FileReader(fileDir + fileName));
		while(true) {
			String fileLine = schoolFile.readLine();
			if (fileLine == null) break;
			strBuffer = fileLine.split("\t");
			switch (Integer.parseInt(strBuffer[0])) {
				case 1: 
					insertSchool(1, strBuffer[1]);
					break;
				case 2:
					insertSchool(2, strBuffer[1]);
					break;
				case 3:
					insertSchool(3, strBuffer[1]);
					break;
				case 4:
					insertSchool(4, strBuffer[1]);
					break;
				default:
					break;
			}

		}
		schoolFile.close();
	}

	public void writeToFile() throws IOException {
		BufferedWriter outputFile;
		String outputString;
		for (int i = 0 ; i < schoolList.size(); i++) {
			for (int j = 0 ; j < schoolList.get(i).getClassList().size(); j++) {
				outputFile = new BufferedWriter
						(new FileWriter(schoolList.get(i).getClassList().get(j).getClassFolderDirs().getPath() + "\\ClassInfo.txt"));
				outputString = "";
				for (int k = 0 ; k < schoolList.get(i).getClassList().get(j).getStuList().size(); k++) {
					outputString += schoolList.get(i).getClassList().get(j).getStuList().get(k).showInfo();
				}
				outputFile.write(outputString);
				outputFile.close();
			}
		}
		for (int i = 0 ; i < schoolList.size(); i++) {
				outputFile = new BufferedWriter
						(new FileWriter(schoolList.get(i).getSchoolFolderDirs().getPath() + "\\SchoolInfo.txt"));
				outputString = "";
				for (int j = 0 ; j < schoolList.get(i).getClassList().size(); j++) {
					outputString += schoolList.get(i).getClassList().get(j).getYears() + " - " + schoolList.get(i).getClassList().get(j).getClassNo() + "\r\n";
					for (int k = 0 ; k < schoolList.get(i).getClassList().get(j).getStuList().size(); k++) {
						outputString += schoolList.get(i).getClassList().get(j).getStuList().get(k).showInfo();
					}
				}
				outputFile.write(outputString);
				outputFile.close();
		}
		
	}
	
	public void insertSchool(int schoolLevel, String schoolName) {
		boolean schoolExist = false;
		int duplicatedPosition = 0;
		
		if (schoolList.size() == 0) {
			schoolList.add(new School(schoolLevel, schoolName));
			return;
		}
		for (int i = 0 ; i < schoolList.size(); i++) {
			if (!(schoolList.get(i).getSchoolName().equals(schoolName)) && !(schoolList.get(i).getSchoolLevel() == schoolLevel)) {
				schoolExist = false;
			}
			else {
				schoolExist = true;
				duplicatedPosition = i;
				break;
			}
		}
		if (schoolExist == false) {
			schoolList.add(new School(schoolLevel, schoolName));
		}
		else {
			schoolList.get(duplicatedPosition).isDuplicated();
		}
	}
	
	public static String getFileDir() {
		return fileDir;
	}

	public static String[] getStrBuffer() {
		return strBuffer;
	}

	public static String getFolderDir() {
		return folderDir;
	}
	
	public static void setFolderDir(String folderDir) {
		StudentReadWriter.folderDir = folderDir;
	}

	public List<School> getSchoolList() {
		return schoolList;
	}
}


class School {
	private String schoolName;
	private int schoolLevel;
	private List<ClassInfo> classList = new ArrayList<ClassInfo>();
	private File schoolFolderDirs;
	
	public School(int schoolLevel, String schoolName) {
		this.schoolLevel = schoolLevel;
		this.schoolName = schoolName;
		
		StudentReadWriter.setFolderDir(StudentReadWriter.getFileDir());
		switch (schoolLevel) {
			case 1:
				StudentReadWriter.setFolderDir(StudentReadWriter.getFolderDir() + "Elementary");
				break;
			case 2:
				StudentReadWriter.setFolderDir(StudentReadWriter.getFolderDir() + "Middle");
				break;
			case 3:
				StudentReadWriter.setFolderDir(StudentReadWriter.getFolderDir() + "High");
				break;
			case 4:
				StudentReadWriter.setFolderDir(StudentReadWriter.getFolderDir() + "University");
				break;
			default:
				break;
		}
		StudentReadWriter.setFolderDir(StudentReadWriter.getFolderDir() + "\\" + schoolName);
		schoolFolderDirs = new File(StudentReadWriter.getFolderDir());
		if (!schoolFolderDirs.exists()) schoolFolderDirs.mkdirs();
		
		insertClass();
	}
	public void isDuplicated() {
		insertClass();
	}
	
	public void insertClass() {
		boolean classExist = false;
		int duplicatedPosition = 0;
		
		if (classList.size() == 0) {
			classList.add(new ClassInfo());
			return;
		}
		for (int i = 0 ; i < classList.size(); i++) {
			if (!(classList.get(i).getYears() == Integer.parseInt(StudentReadWriter.getStrBuffer()[2]))
					&& !(classList.get(i).getClassNo() == Integer.parseInt(StudentReadWriter.getStrBuffer()[3]))) {
				classExist = false;
			}
			else {
				classExist = true;
				duplicatedPosition = i;
				break;
			}
		}
		if (classExist == false) {
			classList.add(new ClassInfo());
		}
		else {
			classList.get(duplicatedPosition).isDuplicated();
		}
	}

	public List<ClassInfo> getClassList() {
		return classList;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public int getSchoolLevel() {
		return schoolLevel;
	}
	public File getSchoolFolderDirs() {
		return schoolFolderDirs;
	}
}

class ClassInfo {
	private int years;
	private int classNo;
	private List<Student> stuList = new ArrayList<Student>();
	private File classFolderDirs;
	
	public ClassInfo() {
		this.years = Integer.parseInt(StudentReadWriter.getStrBuffer()[2]);
		this.classNo = Integer.parseInt(StudentReadWriter.getStrBuffer()[3]);
		
		classFolderDirs = new File(StudentReadWriter.getFolderDir() + "\\" + years + "\\" + classNo);
		if (!classFolderDirs.exists()) classFolderDirs.mkdirs();
		
		insertStudent();
	}

	public void isDuplicated(){
		insertStudent();
	}
	
	public void insertStudent() {
		if (Integer.parseInt(StudentReadWriter.getStrBuffer()[6]) == 0) {
			stuList.add(new DomeStudent());
		}
		else {
			stuList.add(new ForeStudent());
		}
	}
	
	public File getClassFolderDirs() {
		return classFolderDirs;
	}

	public List<Student> getStuList() {
		return stuList;
	}

	public int getYears() {
		return years;
	}

	public int getClassNo() {
		return classNo;
	}
	
}

abstract class Student{
	private String name;
	private String stuID;
	private List<TermGrade> examResult = new ArrayList<TermGrade>();
	
	public abstract String showInfo();
	public void evaluateGrade() {
		int midStart = 0;
		int finalStart = 0; 
		for (int i = 8 ; i < StudentReadWriter.getStrBuffer().length ; i++) {
			if (StudentReadWriter.getStrBuffer()[i].equalsIgnoreCase("Mid")) {
				midStart = i;
			}
			else if (StudentReadWriter.getStrBuffer()[i].equalsIgnoreCase("Final")) {
				finalStart = i;
			}
		}
		examResult.add(new TermGrade(midStart, finalStart));
		examResult.add(new TermGrade(finalStart, StudentReadWriter.getStrBuffer().length));
	}
	
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
	
	public List<TermGrade> getTermResult() {
		return examResult;
	}
}


class DomeStudent extends Student {
	private String resiID;
	
	public DomeStudent() {
		super.setName(StudentReadWriter.getStrBuffer()[4]);
		super.setStuID(StudentReadWriter.getStrBuffer()[5]);
		this.resiID = StudentReadWriter.getStrBuffer()[7];
		
		evaluateGrade();
	}
	
	@Override
	public String showInfo() {
		// TODO Auto-generated method stub
		String studentInfo = "Name : " + getName() + "\tStu ID : " + getStuID() +
				"\tResidence ID : " + resiID + "\r\n";
		studentInfo += "Mid Term : \t";
		for (int i = 0 ; i < super.getTermResult().get(0).getSubjectList().size(); i++) {
			studentInfo += super.getTermResult().get(0).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTermResult().get(0).getSubjectList().get(i).getSujectScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTermResult().get(0).getAvgScore()
				+ "\r\nFinal Term : \t";
		for (int i = 0 ; i < super.getTermResult().get(1).getSubjectList().size(); i++) {
			studentInfo += super.getTermResult().get(1).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTermResult().get(1).getSubjectList().get(i).getSujectScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTermResult().get(1).getAvgScore() + "\r\n";
		return studentInfo;
	}	
}

class ForeStudent extends Student {
	private String foreignID;
	
	public ForeStudent() {
		super.setName(StudentReadWriter.getStrBuffer()[4]);
		super.setStuID(StudentReadWriter.getStrBuffer()[5]);
		this.foreignID = StudentReadWriter.getStrBuffer()[7];
		
		evaluateGrade();
	}
	
	@Override
	public String showInfo() {
		// TODO Auto-generated method stub
		String studentInfo = "Name : " + getName() + "\tStu ID : " + getStuID() +
				"\tForeign ID : " + foreignID + "\r\n";
		studentInfo += "Mid Term : \t";
		for (int i = 0 ; i < super.getTermResult().get(0).getSubjectList().size(); i++) {
			studentInfo += super.getTermResult().get(0).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTermResult().get(0).getSubjectList().get(i).getSujectScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTermResult().get(0).getAvgScore()
				+ "\r\nFinal Term : \t";
		for (int i = 0 ; i < super.getTermResult().get(1).getSubjectList().size(); i++) {
			studentInfo += super.getTermResult().get(1).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTermResult().get(1).getSubjectList().get(i).getSujectScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTermResult().get(1).getAvgScore() + "\r\n";
		return studentInfo;
	}	
}

class TermGrade {
	private double avgScore;
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	public TermGrade(int frontPoint, int endPoint) {
		insertTermResult(frontPoint, endPoint);
		
		for (Subject subj : subjectList) {
			avgScore += subj.getSujectScore();
		}
		
		avgScore /= subjectList.size();
	}
	
	public void insertTermResult(int frontPoint, int endPoint) {
		for (int i = frontPoint + 1 ; i < endPoint ; i += 2) {
			subjectList.add(new Subject(StudentReadWriter.getStrBuffer()[i], Integer.parseInt(StudentReadWriter.getStrBuffer()[i+1])));
		}
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public double getAvgScore() {
		return avgScore;
	}
	
}

class Subject {
	private String subjectName;
	private int subjectScore;
	
	public Subject(String subjectName, int subjectScore) {
		this.subjectName = subjectName;
		this.subjectScore = subjectScore;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public int getSujectScore() {
		return subjectScore;
	}
}

class GradeRank  {
	static void gradeRankByMidTerm(ClassInfo infoStudent) {
		Collections.sort(infoStudent.getStuList(), new Comparator<Student>() {
			@Override
			public int compare(Student stu1, Student stu2) {
				// TODO Auto-generated method stub
				if(stu1.getTermResult().get(0).getAvgScore() > stu2.getTermResult().get(0).getAvgScore())
					return -1;
				else if(stu1.getTermResult().get(0).getAvgScore() < stu2.getTermResult().get(0).getAvgScore())
					return 1;
				else
					return 0;
			}
			
		});
	}
	static void gradeRankByFinalTerm(ClassInfo infoStudent) {
		Collections.sort(infoStudent.getStuList(), new Comparator<Student>() {
			@Override
			public int compare(Student stu1, Student stu2) {
				// TODO Auto-generated method stub
				if(stu1.getTermResult().get(1).getAvgScore() > stu2.getTermResult().get(1).getAvgScore())
					return -1;
				else if(stu1.getTermResult().get(1).getAvgScore() < stu2.getTermResult().get(1).getAvgScore())
					return 1;
				else
					return 0;
			}
			
		});
	}
}