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

    private static final int NB_CAPTEUR = 4;
    private static JFrame sJFrame;
    private static CapteurImpl capteur;
    private static Timer sTimer;
    private static DiffusionAtomique diffusionAtomique;
    private static DiffusionSequentielle diffusionSequentielle;

    public static void main(String[] args) {
        capteur = new CapteurImpl(new DiffusionAtomique());

        for (int i = 0; i < NB_CAPTEUR; i++) {
            capteur.attach(new Canal("canal " + (i + 1), i));
        }

        sTimer = new Timer();

        diffusionAtomique = new DiffusionAtomique();
        diffusionSequentielle = new DiffusionSequentielle();

        displayWindow();
    }

    private static void displayWindow() {
        sJFrame = new JFrame();

        sJFrame.setTitle("Main App ");
        sJFrame.setSize(300, 150);
        sJFrame.setResizable(false);
        sJFrame.setLocationRelativeTo(null);
        sJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mJPanelMain = new JPanel();
        mJPanelMain.setLayout(new BorderLayout());
        mJPanelMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        sJFrame.getContentPane().add(mJPanelMain);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BorderLayout());

        addButtons(panelButtons);

        mJPanelMain.add(panelButtons, BorderLayout.CENTER);

        sJFrame.setVisible(true);
    }

    private static void addButtons(JPanel panelButtons) {
        ButtonGroup bgAlgoDiffusion = new ButtonGroup();
        final JRadioButton jbrDiffusionAtomique = new JRadioButton("DiffusionAtomique");
        jbrDiffusionAtomique.setSelected(true);

        JRadioButton jbrDiffusionSequentielle = new JRadioButton("DiffusionSequentielle");

        ActionListener changeDiffusionSelected = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jbrDiffusionAtomique.isSelected()) {
                    capteur.setAlgo(diffusionAtomique);
                } else {
                    capteur.setAlgo(diffusionSequentielle);
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

        JPanel panelChoice = new JPanel();
        panelChoice.setLayout(new GridLayout(2, 1));
        panelChoice.add(jbrDiffusionAtomique);
        panelChoice.add(jbrDiffusionSequentielle);

        panelButtons.add(panelChoice, BorderLayout.CENTER);
        panelButtons.add(jButtonStart, BorderLayout.SOUTH);
    }

}
