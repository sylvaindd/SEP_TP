package com;

import com.algos.DiffusionAtomique;
import com.algos.DiffusionSequentielle;
import com.impls.Canal;
import com.impls.CapteurImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guillaume on 29/11/2016.
 */
public class App {

    private static JFrame sJFrame;
    private static CapteurImpl capteur;
    private static Timer sTimer;

    public static void main(String[] args) {
        capteur = new CapteurImpl(new DiffusionAtomique());

        capteur.attach(new Canal("canal 1"));
        capteur.attach(new Canal("canal 2"));
        capteur.attach(new Canal("canal 3"));
        capteur.attach(new Canal("canal 4"));

        sTimer = new Timer();

        displayWindow();

    }

    private static void displayWindow() {
        sJFrame = new JFrame();

        sJFrame.setTitle("Main App ");
        sJFrame.setSize(300, 100);
        sJFrame.setResizable(false);
        sJFrame.setLocationRelativeTo(null);
        sJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mJPanelMain = new JPanel();
        mJPanelMain.setLayout(new BorderLayout());
        mJPanelMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        sJFrame.getContentPane().add(mJPanelMain);

        JPanel panelValue = new JPanel();
        panelValue.setLayout(new BorderLayout());

        addButtons(panelValue);

        mJPanelMain.add(panelValue, BorderLayout.CENTER);

        sJFrame.setVisible(true);
    }

    private static void addButtons(JPanel panelValue) {
        ButtonGroup bgAlgoDiffusion = new ButtonGroup();
        final JRadioButton jbrDiffusionAtomique = new JRadioButton("DiffusionAtomique");
        jbrDiffusionAtomique.setSelected(true);

        JRadioButton jbrDiffusionSequentielle = new JRadioButton("DiffusionSequentielle");

        ActionListener changeDiffusionSelected = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jbrDiffusionAtomique.isSelected()) {
                    capteur.setAlgo(new DiffusionAtomique());
                } else {
                    capteur.setAlgo(new DiffusionSequentielle());
                }
            }
        };

        jbrDiffusionSequentielle.addActionListener(changeDiffusionSelected);
        jbrDiffusionAtomique.addActionListener(changeDiffusionSelected);

        bgAlgoDiffusion.add(jbrDiffusionAtomique);
        bgAlgoDiffusion.add(jbrDiffusionSequentielle);

        JButton jButtonStart = new JButton("START");
        jButtonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        capteur.tick();
                        System.out.println("valeur capteur : " + capteur.getValue());
                    }
                }, 0, 1000);
            }
        });

        panelValue.add(jbrDiffusionAtomique, BorderLayout.WEST);
        panelValue.add(jbrDiffusionSequentielle, BorderLayout.EAST);
        panelValue.add(jButtonStart, BorderLayout.SOUTH);
    }

}
