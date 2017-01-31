package com.impls;

import com.interfaces.CapteurAsync;
import com.interfaces.ObserveurDeCapteurAsync;
import com.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;

/**
 * Implementation and display of Observateur de capteur asynchrone
 */
public class Afficheur implements ObserveurDeCapteurAsync {

    private final int mNumber;
    private final Canal mCanal;
    private JLabel mJLabelValue;
    private final String mCanalName;
    private static final double screenWidth;
    private static final int nbByWidth;
    private static final int marginWidth;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        nbByWidth = (int) ((screenWidth - 50) / 300);
        marginWidth = (int) ((screenWidth - (nbByWidth * 300)) / (nbByWidth - 1));
    }

    /**
     * Instantiates a new Afficheur.
     *
     * @param number the id of an Afficheur
     * @param canal  the canal
     */
    public Afficheur(int number, Canal canal) {
        mCanalName = "Canal " + number;
        mNumber = number;
        mCanal = canal;
        displayWindow();
    }

    /**
     * Initializing view of an Afficheur
     */
    private void displayWindow() {
        JFrame mJFrame = new JFrame();

        mJFrame.setTitle("Afficheur " + mCanalName);
        mJFrame.setSize(300, 100);
        int posX = marginWidth * (mNumber % nbByWidth) + 300 * (mNumber % nbByWidth);
        int posY = mNumber / nbByWidth * 100 + 30 * (mNumber / nbByWidth);
        mJFrame.setLocation(posX, posY);
        mJFrame.setResizable(false);
        mJFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                mCanal.detach();
            }
        });

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

    /**
     * Update an afficheur
     * @param capteurAsync the capteurasync(canal) that the afficheur is connected to
     */
    @Override
    public void update(CapteurAsync capteurAsync) {
        try {
            String value = capteurAsync.getValue().get().toString();
            mJLabelValue.setText(value);
            Utils.showDebug(this.getClass(), "Valeur " + mCanalName + " : " + value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
