package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;

public class Observable {
	
	private List<Observer> observerList = new ArrayList<>();
	
	public void registerObserver(Observer o) {
		observerList.add(o);
	}

	public void deleteObserver(Observer o) {
		observerList.remove(o);
	}

	public void notifyObserver() {
		System.out.println("notify!");
		for (Observer o: observerList) {
			o.update();
		}		
	}
}
