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
		SchoolReadWriter eduInfo = new SchoolReadWriter();
		eduInfo.writeToFile();
	}
}

class SchoolReadWriter {
	List<School> schoolList = new ArrayList<School>();
	private static String fileDir = "D:\\EMS\\";
	private static String fileName = "SchoolCollection.txt";
	private static String[] strBuffer;
	private static String folderDir;
	
	public SchoolReadWriter() throws IOException {
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
					initSchool(1, strBuffer[1]);
					break;
				case 2:
					initSchool(2, strBuffer[1]);
					break;
				case 3:
					initSchool(3, strBuffer[1]);
					break;
				case 4:
					initSchool(4, strBuffer[1]);
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
		
	}
	
	public void initSchool(int schoolLevel, String schoolName) {
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
			schoolList.get(duplicatedPosition).duplicatedSchool();
		}
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
	public void duplicatedSchool() {
		initClass();
	}
	
	public void initClass() {
		boolean classExist = false;
		int duplicatedPosition = 0;
		
		if (classList.size() == 0) {
			classList.add(new ClassInfo());
			return;
		}
		for (int i = 0 ; i < classList.size(); i++) {
			if (!(classList.get(i).getYears() == Integer.parseInt(SchoolReadWriter.getStrBuffer()[2]))
					&& !(classList.get(i).getClassNo() == Integer.parseInt(SchoolReadWriter.getStrBuffer()[3]))) {
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
			classList.get(duplicatedPosition).duplicatedClass();
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
	
}

class ClassInfo {
	private int years;
	private int classNo;
	private List<Student> stuList = new ArrayList<Student>();
	private File classFolderDirs;
	
	public ClassInfo() {
		this.years = Integer.parseInt(SchoolReadWriter.getStrBuffer()[2]);
		this.classNo = Integer.parseInt(SchoolReadWriter.getStrBuffer()[3]);
		
		classFolderDirs = new File(SchoolReadWriter.getFolderDir() + "\\" + years + "\\" + classNo);
		if (!classFolderDirs.exists()) classFolderDirs.mkdirs();
		
		initStudent();
	}

	public void duplicatedClass(){
		initStudent();
	}
	
	public void initStudent() {
		if (Integer.parseInt(SchoolReadWriter.getStrBuffer()[6]) == 0) {
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

abstract class Student implements Comparable<Student>{
	private String name;
	private String stuID;
	private List<TermGrade> examResult = new ArrayList<TermGrade>();
	
	public abstract String showInfo();
	public void evaluateTest() {
		int midStart = 0;
		int finalStart = 0; 
		for (int i = 8 ; i < SchoolReadWriter.getStrBuffer().length ; i++) {
			if (SchoolReadWriter.getStrBuffer()[i].equalsIgnoreCase("Mid")) {
				midStart = i;
			}
			else if (SchoolReadWriter.getStrBuffer()[i].equalsIgnoreCase("Final")) {
				finalStart = i;
			}
		}
		examResult.add(new TermGrade(midStart, finalStart));
		examResult.add(new TermGrade(finalStart, SchoolReadWriter.getStrBuffer().length));
	}
	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		if (this.getTestResult().get(0).getAvgScore() > stu.getTestResult().get(0).getAvgScore()) {
			return -1;
		}
		else if (this.getTestResult().get(0).getAvgScore() == stu.getTestResult().get(0).getAvgScore()) {
			return 0;
		}
		else return 1;
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
	
	public List<TermGrade> getTestResult() {
		return examResult;
	}
	public void setTestResult(List<TermGrade> examResult) {
		this.examResult = examResult;
	}
}


class DomeStudent extends Student {
	private String resiID;
	
	public DomeStudent() {
		super.setName(SchoolReadWriter.getStrBuffer()[4]);
		super.setStuID(SchoolReadWriter.getStrBuffer()[5]);
		this.resiID = SchoolReadWriter.getStrBuffer()[7];
		
		evaluateTest();
	}
	
	@Override
	public String showInfo() {
		// TODO Auto-generated method stub
		String studentInfo = "Name : " + getName() + "\tStu ID : " + getStuID() +
				"\tResidence ID : " + resiID + "\r\n";
		studentInfo += "Mid Term : \t";
		for (int i = 0 ; i < super.getTestResult().get(0).getSubjectList().size(); i++) {
			studentInfo += super.getTestResult().get(0).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTestResult().get(0).getSubjectList().get(i).getTestScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTestResult().get(0).getAvgScore()
				+ "\r\nFinal Term : \t";
		for (int i = 0 ; i < super.getTestResult().get(1).getSubjectList().size(); i++) {
			studentInfo += super.getTestResult().get(1).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTestResult().get(1).getSubjectList().get(i).getTestScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTestResult().get(1).getAvgScore() + "\r\n";
		return studentInfo;
	}	
}

class ForeStudent extends Student {
	private String foreignID;
	
	public ForeStudent() {
		super.setName(SchoolReadWriter.getStrBuffer()[4]);
		super.setStuID(SchoolReadWriter.getStrBuffer()[5]);
		this.foreignID = SchoolReadWriter.getStrBuffer()[7];
		
		evaluateTest();
	}
	
	@Override
	public String showInfo() {
		// TODO Auto-generated method stub
		String studentInfo = "Name : " + getName() + "\tStu ID : " + getStuID() +
				"\tForeign ID : " + foreignID + "\r\n";
		studentInfo += "Mid Term : \t";
		for (int i = 0 ; i < super.getTestResult().get(0).getSubjectList().size(); i++) {
			studentInfo += super.getTestResult().get(0).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTestResult().get(0).getSubjectList().get(i).getTestScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTestResult().get(0).getAvgScore()
				+ "\r\nFinal Term : \t";
		for (int i = 0 ; i < super.getTestResult().get(1).getSubjectList().size(); i++) {
			studentInfo += super.getTestResult().get(1).getSubjectList().get(i).getSubjectName()
					+ " " + super.getTestResult().get(1).getSubjectList().get(i).getTestScore() + " ";
		}
		studentInfo += "\tAverage Score : " + super.getTestResult().get(1).getAvgScore() + "\r\n";
		return studentInfo;
	}	
}

class TermGrade {
	private double avgScore;
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	public TermGrade(int frontPoint, int endPoint) {
		inputTermResult(frontPoint, endPoint);
		
		for (Subject subj : subjectList) {
			avgScore += subj.getTestScore();
		}
		
		avgScore /= subjectList.size();
	}
	
	public void inputTermResult(int frontPoint, int endPoint) {
		for (int i = frontPoint + 1 ; i < endPoint ; i += 2) {
			subjectList.add(new Subject(SchoolReadWriter.getStrBuffer()[i], Integer.parseInt(SchoolReadWriter.getStrBuffer()[i+1])));
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
	private int testScore;
	
	public Subject(String subjectName, int testScore) {
		this.subjectName = subjectName;
		this.testScore = testScore;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public int getTestScore() {
		return testScore;
	}
}

class GradeRank {
	void gradeClassRank(List<Student> stuList) {

	}
	void gradeSchoolRank(Student stu) {
		
	}
}