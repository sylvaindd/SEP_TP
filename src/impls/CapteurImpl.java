package impls;

import interfaces.Capteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class CapteurImpl implements Capteur {

	List<Observer>	observers;
	Integer			v;

	public CapteurImpl() {
		this.observers = new ArrayList<Observer>();
		this.v = 0;
	}

	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public Integer getValue() {
		return v;
	}

	@Override
	public void tick() {
		v++;
	}
}
