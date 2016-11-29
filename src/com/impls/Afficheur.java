package com.impls;

import com.interfaces.CapteurAsync;
import com.interfaces.ObserveurDeCapteurAsync;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Afficheur implements ObserveurDeCapteurAsync {

    private JLabel mJLabelValue;
    private final String mCanalName;

    public Afficheur(String canalName) {
        mCanalName = canalName;

        displayWindow();
    }

    private void displayWindow() {
        JFrame mJFrame = new JFrame();

        mJFrame.setTitle("Afficheur " + mCanalName);
        mJFrame.setSize(300, 100);
        mJFrame.setResizable(false);
        mJFrame.setLocationRelativeTo(null);
        mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanelMain = new JPanel();
        jPanelMain.setLayout(new BorderLayout());
        jPanelMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        mJFrame.getContentPane().add(jPanelMain);

        JPanel panelValue = new JPanel();
        panelValue.setLayout(new BorderLayout());
        panelValue.add(new JLabel("Valeur : "), BorderLayout.WEST);
        mJLabelValue = new JLabel("null");
        panelValue.add(mJLabelValue, BorderLayout.CENTER);

        jPanelMain.add(panelValue, BorderLayout.CENTER);

        mJFrame.setVisible(true);
    }

    @Override
    public void update(CapteurAsync capteurAsync) {
        try {
            String value = capteurAsync.getValue().get().toString();
            mJLabelValue.setText(value);
            System.out.println("Valeur " + mCanalName + " : " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
