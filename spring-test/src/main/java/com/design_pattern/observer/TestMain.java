package com.design_pattern.observer;

public class TestMain {

	public static void main(String[] args) {
		
		SubjectObserver subjectObserverOne = new SubjectObserverOne();
		SubjectObserver subjectObserverTwo = new SubjectObserverTwo();
		
		Subject subject = new ConcreteSubject();
		subject.addObserver(subjectObserverOne);
		subject.addObserver(subjectObserverTwo);
		
		subject.notifyObserver();
	}

}
