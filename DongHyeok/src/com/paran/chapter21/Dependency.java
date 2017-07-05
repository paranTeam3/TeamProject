package com.paran.chapter21;

/*
 * UML에서의 의존성(------->(점선), dependency) 개념
 *  메서드의 매개변수나 반환값으로 다른 class가 사용되는 경우
 *  현재 Dependency class --------> Student class 관계 성립
 *  Student class에 변화가 생기면 Dependency class에도 영향이 미칠 가능성이 존재
 *  
 * UML에서의 연관성(------->(실선), Association) 개념
 *  한 class의 내부에 다른 class가 멤버 변수로 선언되어 있는 경우
 *  의존성과 달리 연관관계 자체는 멤버 변수 class의 변화가 있어도 무관
 *  
 * UML에서의 상속(--------▷(실선), Inheritance) 개념
 *  상속 관계 표현(HEAD가 부모 class를 가리키도록)
 *  
 * UML에서의 포함(--------◆(실선), Composition) 개념
 *  n:n(n >= 0) 관계 표현
 */
public class Dependency {
	public void showInfo(Students instStd) {
		System.out.println(instStd.getInfo());
	}
}

@SuppressWarnings("unused")
class Association {
	private Students stdinfo;
}

class Students {
	String name;
	String stdID;
	public Students(String name, String stdID) {
		this.name = name;
		this.stdID = stdID;
	}
	
	public String getInfo() {
		return (name + " " + stdID);
	}
}