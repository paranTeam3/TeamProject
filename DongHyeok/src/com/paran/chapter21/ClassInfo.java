package com.paran.chapter21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>학생 정보를 관리하고 성적에 따른 순위를 계산하여 출력</b><br/>
 * File I/O를 ClassInfo class에 정의하고<br/>
 * 순위를 계산하는 class를 stdRankManager로 분리
 * @author ehdgur316
 *
 */
public class ClassInfo {
	/*
	 * Generic Class
	 * stdList 안에는 DomeStudent, ForeStudent 두 가지 타입의 변수 할당
	 * IS-A 관계의 변수를 모두 포함할 수 있는 복합자료체 Generic
	 */
	List<Student> stuList = new ArrayList<Student>();
	
	public static void main(String[] args) throws IOException {
		BufferedWriter resultFile = new BufferedWriter(new FileWriter("C:\\Users\\j\\lecture\\HelloJava_\\src\\com\\paran\\chapter21\\result.txt"));
		ClassInfo stuReportCard = new ClassInfo();
		stuReportCard.initStudent();
		StuRankManager stuRank = new StuRankManager(stuReportCard.stuList);
		int[][] classRank = stuRank.getRankArray();
		
		// 입력할 내용을 하나의 String type 변수에 전부 넣고 파일에 한 번에 Output
//		String outputBuffer = "======================= Student Info =======================\r\n";
//		for (Student std : stdReportCard.stdList) {
//			outputBuffer += std.showInfo();
//			for (int i = 0 ; i < classRank[0].length ; i++) {
//				if (classRank[0][i] == Integer.parseInt(std.getStdID())) {
//					outputBuffer += "Class Rank => " + classRank[1][i] + "\r\n";
//					break;
//				}
//			}
//		}
//		outputBuffer += "========================= Info End =========================";
//		resultFile.write(outputBuffer);
		
		// 출력할 내용이 있을 때마다 파일에 즉시 Output
		resultFile.write("======================= Student Info =======================\r\n");
//		for (Student stu : stuReportCard.stuList) {
//			resultFile.write(stu.showInfo());
//			for (int i = 0 ; i < classRank[0].length ; i++) {
//				if (classRank[0][i] == Integer.parseInt(stu.getStuID())) {
//					resultFile.write(("Class Rank => " + classRank[1][i] + "\r\n"));
//					break;
//				}
//			}
//		}
		
		// classRank Array의 순위 순서대로 stuList의 Student 객체 정보 출력
		for (int i = 0 ; i < stuReportCard.stuList.size(); i++) {
			for (int j = 0; j < classRank[0].length; j++) {
				if (classRank[1][j] == (i + 1)) {
					resultFile.write(stuReportCard.stuList.get(j).showInfo());
					resultFile.write(("Class Rank =>" + classRank[1][j] + "\r\n"));
				}
			}
		}
		resultFile.write("========================= Info End =========================");
		
		resultFile.close();
	}
	
	public void initStudent() throws IOException {
		String fileDirectory =
				"C:\\Users\\j\\lecture\\HelloJava_\\src\\com\\paran\\chapter21\\";
		BufferedReader fileBuffer =
				new BufferedReader(new FileReader(fileDirectory + "StudentData.txt"));
		
		while(true) {
			String fileLine = fileBuffer.readLine();
			if (fileLine == null) break;
			String[] strBuffer = fileLine.split("\t");
			
			if (strBuffer[5].equals("D")) {
				stuList.add(new DomeStudent(strBuffer[0], strBuffer[1],
						Integer.parseInt(strBuffer[2]), Integer.parseInt(strBuffer[3]),
						Integer.parseInt(strBuffer[4]), strBuffer[6]));
			}
			else {
				stuList.add(new ForeStudent(strBuffer[0], strBuffer[1],
						Integer.parseInt(strBuffer[2]), Integer.parseInt(strBuffer[3]),
						Integer.parseInt(strBuffer[4]), strBuffer[6]));
			}
		}
		fileBuffer.close();
	}
}

/*
 * abstract class -> Instance 생성이 불가능한 class
 */
/**
 * 내국인/외국인 학생의 공통 부모 class인 Student class
 * @author ehdgur316
 *
 */
abstract class Student {
	private String name;
	private String stuID;
	private int korScore;
	private int mathScore;
	private int engScore;
	
	/**
	 * Student class의 private 구성요소를 출력하는 method
	 * @return String
	 */
	public abstract String showInfo(); // abstract method -> 구현체가 없이 선언만 되어있는 메서드
	
	/*
	 * private 선언된 요소에 접근하는 getter, setter 메서드
	 */
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

	public int getKorScore() {
		return korScore;
	}
	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getEngScore() {
		return engScore;
	}
	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}
	
}

/**
 * 내국인 학생 class인 DomeStudent class
 * @author ehdgur316
 *
 */
class DomeStudent extends Student {
	public DomeStudent(String name, String stuID, int korScore, int mathScore,
												int engScore, String resiID) {
		super.setName(name);
		super.setStuID(stuID);
		super.setKorScore(korScore);
		super.setMathScore(mathScore);
		super.setEngScore(engScore);
		this.resiID = resiID;
	}
	private String resiID;
	@Override
	public String showInfo() {
		// TODO Auto-generated method stub
		return ("Name => " + getName() + ", Stu ID => " + getStuID() +
												", Residence ID => " + resiID + "\r\n");
	}
}

/**
 * 외국인 학생 class인 ForeStudent class
 * @author ehdgur316
 *
 */
class ForeStudent extends Student {
	public ForeStudent(String name, String stuID, int korScore, int mathScore,
												int engScore, String foreignID) {
		super.setName(name);
		super.setStuID(stuID);
		super.setKorScore(korScore);
		super.setMathScore(mathScore);
		super.setEngScore(engScore);
		this.foreignID = foreignID;
	}
	private String foreignID;
	@Override
	public String showInfo() {
		// TODO Auto-generated method stub
		return ("Name => " + getName() + ", Stu ID => " + getStuID() +
												", Foreign ID => " + foreignID + "\r\n");
	}
}

/**
 * 학생 정보를 기반으로 순위를 계산하는 학생 성적관리 class인 stdRankManager class
 * @author ehdgur316
 *
 */
class StuRankManager{
	public StuRankManager(List<Student> stdList) {
		this.stdList = stdList;
		rankScore();
	}
	
	private List<Student> stdList;
	private int[][] rankArray; /* index를 사용한 Matching은 굉장히 위험한 발상 -> 식별자를 할당해보자
							    * rankArray[0]은 stuList의 학번(식별자)을 할당
							    * rankArray[1]은 rankScore에 의해 순위 할당
							    */
	private int[] totalScoreArray;
	
	public int[][] getRankArray() {
		return rankArray;
	}
	
	/**
	 * 조건문 반복문 활용 순위 부여 메서드
	 */
	private void rankScore() {
		rankArray = new int[2][stdList.size()];
		totalScoreArray = new int[stdList.size()];
		
		int rank = 1;
		int mostPosition;
		int mostScore;
		
		for (int i = 0; i< stdList.size() ; i++) {
			rankArray[0][i] = Integer.parseInt(stdList.get(i).getStuID());
		}
		
		// 총점을 계산하여 총점 배열에 할당(이 메서드 내에서만 사용함. Getter/Setter 메서드 없음)
		for (int i = 0; i < stdList.size(); i++) {
			totalScoreArray[i] = stdList.get(i).getKorScore()
					+ stdList.get(i).getMathScore() + stdList.get(i).getEngScore();
		}
		
		/* 
		 * 총 인원수만큼 반복, 총점 배열 최고값을 0으로 바꿔가며 순위 할당
		 * 총점이나 평균이 필요할 경우 다른 메서드에서 생성하자
		 */
		while(rank <= stdList.size()) {
			mostScore = 0;
			mostPosition = 0;
			
			for (int i = 0; i < stdList.size(); i++) {
				if (mostScore < totalScoreArray[i]) {
					mostScore = totalScoreArray[i];
					mostPosition = i;
				}
			}

			if (mostScore == 0) {
				return;
			}

			rankArray[1][mostPosition] = rank++;
			totalScoreArray[mostPosition] = 0;
		}
	}
}