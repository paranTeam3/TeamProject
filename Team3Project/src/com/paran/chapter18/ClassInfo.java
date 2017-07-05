package com.paran.chapter18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassInfo {
	int count;

	String stuId;
	String name;
	String code;
	int korScore;
	int engScore;

	List<Student> stuList = new ArrayList<Student>();

	public void insertStudent() {
		String fileName = "학생리스트.txt";
		try {
			BufferedReader in = new BufferedReader(new FileReader("D:\\dg\\Test\\" + fileName));
			String s;
			
			while ((s = in.readLine()) != null) {
				count++;

				String[] split = s.split("\t");

				stuId = split[0].trim();
				name = split[1].trim();
				code = split[2].trim();
				korScore = Integer.valueOf(split[3]);
				engScore = Integer.valueOf(split[4]);
				if (split[5].trim().equals("내국인")) {
					stuList.add(new DomeStudent(stuId, name, code, korScore, engScore));
				} else {
					stuList.add(new ForeStudent(stuId, name, code, korScore, engScore));
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		ClassInfo infoStudent = new ClassInfo();
		StuRank sRank = new StuRank();

		infoStudent.insertStudent();
		sRank.rankCheck(infoStudent);
	}
}

abstract class Student {
	private String name;
	private String stuId;
	private int korScore;
	private int engScore;
	private int totScore;
	private float avgScore;

	public int getTotScore() {
		return totScore;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public int getKorScore() {
		return korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public String getName() {
		return name;
	}

	public String getStuId() {
		return stuId;
	}

	public void setTotScore() {
		this.totScore = korScore + engScore;
	}

	public void setAvgScore() {
		this.avgScore = totScore / 2;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public abstract void showInfo();
}

class DomeStudent extends Student {
	private String resiId;

	public String getResiId() {
		return resiId;
	}

	public void setResiId(String resiId) {
		this.resiId = resiId;
	}

	public DomeStudent(String stuId, String name, String code, int korScore, int engScore) {
		super();
		setStuId(stuId);
		setName(name);
		setResiId(code);
		setKorScore(korScore);
		setEngScore(engScore);
		setTotScore();
		setAvgScore();
	}

	public void showInfo() {
		System.out.print("name : " + getName());
		System.out.print("\t stuId : " + getStuId());
		System.out.print("\t korScore : " + getKorScore());
		System.out.print("\t engScore : " + getEngScore());
		System.out.print("\t totScore : " + getTotScore());
		System.out.print("\t avgScore : " + getAvgScore());
		System.out.print("\t resiId : " + getResiId());
		System.out.println();
	}
}

class ForeStudent extends Student {
	private String foreignId;

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public ForeStudent(String stuId, String name, String code, int korScore, int engScore) {
		super();
		setStuId(stuId);
		setName(name);
		setForeignId(code);
		setKorScore(korScore);
		setEngScore(engScore);
		setTotScore();
		setAvgScore();
	}

	public void showInfo() {
		System.out.print("name : " + getName());
		System.out.print("\t stuId : " + getStuId());
		System.out.print("\t korScoree : " + getKorScore());
		System.out.print("\t engScoree : " + getEngScore());
		System.out.print("\t totScoree : " + getTotScore());
		System.out.print("\t avgScoree : " + getAvgScore());
		System.out.print("\t foreignId : " + getForeignId());
		System.out.println();
	}
}

class RankUtil {
	
}


class StuRank {
	Student[] stuR;

	public void insertArr(ClassInfo infoStudent) {
		stuR = new Student[infoStudent.count];

		for (int i = 0; i < stuR.length; i++) {
			stuR[i] = infoStudent.stuList.get(i);
		}
	}

	public void rankCheck(ClassInfo infoStudent) {
		insertArr(infoStudent);

		int size = stuR.length;
		int max;
		Student temp;

		for (int i = 0; i < size - 1; i++) {
			max = i;
			for (int j = i + 1; j < size; j++) {
				if (stuR[max].getAvgScore() < stuR[j].getAvgScore()) {
					max = j;
				}
			}
			temp = stuR[max];
			stuR[max] = stuR[i];
			stuR[i] = temp;
		}
		printStuRank();
	}

	private void printStuRank() {
		for (int i = 0; i < stuR.length; i++) {
			System.out.print("rank : " + (i + 1) + "\t");
			stuR[i].showInfo();
		}
	}
}