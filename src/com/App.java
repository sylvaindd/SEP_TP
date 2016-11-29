package com;

import com.algos.DiffusionAtomique;
import com.impls.Canal;
import com.impls.CapteurImpl;
import com.interfaces.Capteur;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guillaume on 29/11/2016.
 */
public class App {

    public static void main(String[] args) {
        final Capteur capteur = new CapteurImpl(new DiffusionAtomique());

        capteur.attach(new Canal("canal 1"));
        capteur.attach(new Canal("canal 2"));
        capteur.attach(new Canal("canal 3"));
        capteur.attach(new Canal("canal 4"));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                capteur.tick();
                System.out.println("valeur capteur : " + capteur.getValue());
            }
        }, 0, 1000);
    }
}
