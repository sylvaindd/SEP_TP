package interfaces;

/**
 * Created by thoma on 28/11/2016.
 */
public interface ObserveurDeCapteur extends Observer<Capteur> {

    @Override
    void update(Capteur capteur);

}
