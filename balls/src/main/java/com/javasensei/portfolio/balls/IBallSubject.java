package com.javasensei.portfolio.balls;

public interface IBallSubject {
	void registerObserver(IBallObserver ballObserver);
	void removeObserver(IBallObserver ballObserver);
	void notifyObservers();
}
