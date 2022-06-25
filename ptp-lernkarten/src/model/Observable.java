package model;

import view.Observer;

public abstract class Observable {
	public abstract void registerObserver(Observer o);

	public abstract void deleteObserver(Observer o);

	public abstract void notifyObserver();
}
