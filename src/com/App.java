package com;

import com.impls.Canal;
import com.impls.CapteurImpl;
import com.interfaces.Capteur;

/**
 * Created by Guillaume on 29/11/2016.
 */
public class App {

    public static void main(String[] args) {
        Capteur capteur = new CapteurImpl();

        Canal canal1 = new Canal();
        Canal canal2 = new Canal();

        capteur.attach(canal1);
        capteur.attach(canal2);

        while (true) {
            capteur.tick();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
