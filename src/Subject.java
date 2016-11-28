/**
 * Created by thoma on 28/11/2016.
 */
public interface Subject<T> {
    void attach(Observer observer);
    void deatach(Observer observer);
}
