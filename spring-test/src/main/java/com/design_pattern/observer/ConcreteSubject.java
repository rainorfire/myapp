package com.design_pattern.observer;

import java.util.LinkedList;
import java.util.List;

public class ConcreteSubject implements Subject {
	
	private static List<SubjectObserver> observerList = new LinkedList<SubjectObserver>();

	public void addObserver(SubjectObserver observer) {
		observerList.add(observer);
	}

	public void removeObserver(SubjectObserver observer) {
		if(observerList.contains(observer)){
			observerList.remove(observerList);
		}
	}

	public void notifyObserver() {
		for(SubjectObserver o : observerList){
			o.executeProcess();
		}
	}

}
