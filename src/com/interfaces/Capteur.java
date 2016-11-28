package com.interfaces;

import java.util.Observer;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by Sylvain on 21/11/2016.
 */
public interface Capteur extends Subject {
	void attach(Observer o);

	void detach(Observer o);

	Integer getValue();

	void tick();

	void setFuture(ScheduledFuture future);
}
