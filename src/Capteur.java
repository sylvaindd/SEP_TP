import java.util.Observer;

/**
 * Created by Sylvain on 21/11/2016.
 */
public interface Capteur {
	public void attach(Observer o);

	public void detach(Observer o);

}
