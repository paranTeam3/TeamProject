package team3.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewClassInfo {
	static List<Student> stuList = new ArrayList<Student>();
	
	public void insertStudent() {
		String stuId;
		String name;
		String code;
		int korScore;
		int engScore;

		String fileName = "학생리스트.txt";
		try {
			BufferedReader in = new BufferedReader(new FileReader("D:\\dg\\Test\\" + fileName));
			String s;

			while ((s = in.readLine()) != null) {
				String[] split = s.split("\t");

				stuId = split[0].trim();
				name = split[1].trim();
				code = split[2].trim();
				korScore = Integer.valueOf(split[3]);
				engScore = Integer.valueOf(split[4]);
				if (split[5].trim().equals("내국인")) {
					stuList.add(new DomeStudent(stuId, name, korScore, engScore, code));
				} else {
					stuList.add(new ForeStudent(stuId, name, korScore, engScore, code));
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		NewClassInfo infoStudent = new NewClassInfo();
		RankUtil sRank = new RankUtil();

		infoStudent.insertStudent();
		sRank.showRank(infoStudent);
		
		// 정렬된 자료
		int count = 1;
		for (Student temp : stuList) {
			System.out.println("Rank : "+count);
			temp.showInfo();
			System.out.println();
			count++;
		}
	}
}

abstract class Student {
	private String name, stuId;
	private int korScore, engScore, totScore;
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
		this.avgScore = totScore / 2.0f;
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

	public Student(String stuId, String name, int korScore, int engScore) {
		setStuId(stuId);
		setName(name);
		setKorScore(korScore);
		setEngScore(engScore);
		setTotScore();
		setAvgScore();
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

	public DomeStudent(String stuId, String name, int korScore, int engScore, String code) {
		super(stuId, name, korScore, engScore);
		setResiId(code);
	}

	public void showInfo() {
		System.out.printf("학번 : %5s , 이름 : %5s , 국어점수 : %3d , 영어점수 : %3d , 총점 : %4d , 평균 : %3.2f , 주민등록번호 : %10d",
				getStuId(), getName(), getKorScore(), getEngScore(), getTotScore(), getAvgScore(), getResiId());
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

	public ForeStudent(String stuId, String name, int korScore, int engScore, String code) {
		super(stuId, name, korScore, engScore);
		setForeignId(code);
	}

	public void showInfo() {
		System.out.printf("학번 : %5s , 이름 : %5s , 국어점수 : %3d , 영어점수 : %3d , 총점 : %4d , 평균 : %3.2f , 주민등록번호 : %10s",
				getStuId(), getName(), getKorScore(), getEngScore(), getTotScore(), getAvgScore(), getForeignId());
		System.out.println();
	}
}

class RankUtil {
	public void showRank(NewClassInfo infoStudent) {
		Collections.sort(infoStudent.stuList, new AscRank());
	}
}

class AscRank implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if(o1.getAvgScore() > o2.getAvgScore())
			return -1;
		else if(o1.getAvgScore() < o2.getAvgScore())
			return 1;
		else
			return 0;
	}
	
}

