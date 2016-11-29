package com;

import com.algos.DiffusionAtomique;
import com.algos.DiffusionSequentielle;
import com.impls.Canal;
import com.impls.CapteurImpl;
import com.interfaces.Capteur;

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

    private static JFrame mJFrame;
    private static CapteurImpl capteur;

    public static void main(String[] args) {

        displayWindow();

    }

    private static void displayWindow() {
        mJFrame = new JFrame();

        mJFrame.setTitle("Main App ");
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
        ButtonGroup bgAlgoDiffusion = new ButtonGroup();
        JRadioButton jbrDiffusionAtomique = new JRadioButton("DiffusionAtomique");
        jbrDiffusionAtomique.setSelected(true);
        capteur = new CapteurImpl(new DiffusionAtomique());

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
        });

        panelValue.add(jbrDiffusionAtomique, BorderLayout.WEST);
        panelValue.add(jbrDiffusionSequentielle, BorderLayout.EAST);
        panelValue.add(jButtonStart, BorderLayout.SOUTH);

        mJPanelMain.add(panelValue, BorderLayout.CENTER);

        mJFrame.setVisible(true);
    }

}
