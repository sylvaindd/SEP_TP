package interfaces;

import java.util.Observer;

/**
 * Created by Sylvain on 28/11/2016.
 */
public interface Subject {
	void attach(Observer o);
	void detach(Observer o);
}
