package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

public interface IBallSubject {
	void registerObserver(IBallObserver ballObserver);
	void removeObserver(IBallObserver ballObserver);
	void notifyObservers();
}
