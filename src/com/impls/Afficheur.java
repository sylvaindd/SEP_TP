package com.impls;

import com.interfaces.Capteur;
import com.interfaces.ObserveurDeCapteur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Afficheur implements ObserveurDeCapteur {

    private JLabel mJLabelValue;
    private JFrame mJFrame;
    private final Canal mCanal;

    public Afficheur(Canal canal) {
        mCanal = canal;

        displayWindow();
    }

    private void displayWindow() {
        mJFrame = new JFrame();

        mJFrame.setTitle("Afficheur " + mCanal.getName());
        mJFrame.setSize(300, 100);
        mJFrame.setResizable(false);
        mJFrame.setLocationRelativeTo(null);
        mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mJPanelMain = new JPanel();
        mJPanelMain.setLayout(new BorderLayout());
        mJPanelMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        mJFrame.getContentPane().add(mJPanelMain);

        JPanel panelValue = new JPanel();
        panelValue.setLayout(new BorderLayout());
        panelValue.add(new JLabel("Valeur : "), BorderLayout.WEST);
        mJLabelValue = new JLabel("null");
        panelValue.add(mJLabelValue, BorderLayout.CENTER);

        mJPanelMain.add(panelValue, BorderLayout.CENTER);

        mJFrame.setVisible(true);
    }

    @Override
    public void update(Capteur capteur) {
        try {
            String value = mCanal.getValue().get().toString();
            mJLabelValue.setText(value);
            System.out.println("Valeur " + mCanal.getName() + " : " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
