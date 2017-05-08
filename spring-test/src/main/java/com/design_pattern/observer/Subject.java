package com.design_pattern.observer;

public interface Subject {
	
	void addObserver(SubjectObserver observer);
	
	void removeObserver(SubjectObserver observer);
	
	void notifyObserver();
}
