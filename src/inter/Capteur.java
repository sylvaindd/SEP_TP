package inter;

import java.util.Observer;

/**
 * Created by Sylvain on 21/11/2016.
 */
public interface Capteur extends Subject {
	void attach(Observer o);

	void detach(Observer o);

	Integer getValue();

	void tick();

}
