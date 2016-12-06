package com;

import com.algos.DiffusionAtomique;
import com.algos.DiffusionSequentielle;
import com.impls.Canal;
import com.impls.CapteurImpl;
import com.interfaces.Observer;

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

    public static final int NB_CAPTEUR = 10;
    private static JFrame sJFrame;
    private static CapteurImpl capteur;
    private static Timer sTimer;
    private static DiffusionAtomique diffusionAtomique;
    private static DiffusionSequentielle diffusionSequentielle;

    public static void main(String[] args) {
        capteur = new CapteurImpl(new DiffusionAtomique());

        for (int i = 0; i < NB_CAPTEUR; i++) {
            capteur.attach(new Canal(i, capteur));
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
                        // System.out.println("valeur capteur : " +
                        // capteur.getValue());
                    }
                }, 0, 1000);
            }
        });
        JButton jButtonAddCanal = new JButton("Add Canal");
        jButtonAddCanal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int prev = 0, id = 0;
                capteur.getListObserver().sort((Observer o1, Observer o2) -> o1.getId().compareTo(o2.getId()));
                for (Observer observer : capteur.getListObserver()) {
                    if (observer.getId() > prev + 1) {
                        id = prev + 1;
                        break;
                    } else {
                        prev = observer.getId();
                    }
                }
                if (id == 0)
                    id = prev + 1;
                capteur.attach(new Canal(id, capteur));
            }
        });

        JPanel panelChoice = new JPanel();
        panelChoice.setLayout(new GridLayout(2, 1));
        panelChoice.add(jbrDiffusionAtomique);
        panelChoice.add(jbrDiffusionSequentielle);

        JPanel subPanelButtons = new JPanel();
        subPanelButtons.setLayout(new GridLayout(2, 1));
        subPanelButtons.add(jButtonAddCanal);
        subPanelButtons.add(jButtonStart);

        panelButtons.add(panelChoice, BorderLayout.CENTER);
        panelButtons.add(subPanelButtons, BorderLayout.SOUTH);
    }

}
