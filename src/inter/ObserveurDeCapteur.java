package inter;

import inter.Subject;

/**
 * Created by thoma on 28/11/2016.
 */
public interface ObserveurDeCapteur extends Observer {
    @Override
    void update(Subject capteur);

}
