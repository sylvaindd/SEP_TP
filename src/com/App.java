package com;

import com.algos.DiffusionAtomique;
import com.algos.DiffusionEpoque;
import com.algos.DiffusionSequentielle;
import com.impls.Canal;
import com.impls.CapteurImpl;
import com.interfaces.Observer;
import com.utils.Utils;
import com.utils.SchedulerSingleton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to launch the main App
 */
public class App {

    private static CapteurImpl capteur;
    private static Timer sTimer;
    private static DiffusionAtomique diffusionAtomique;
    private static DiffusionSequentielle diffusionSequentielle;
    private static DiffusionEpoque diffusionEpoque;
    private static boolean sIsStarted = false;
    private static JLabel mJLabelValueCapteur;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        diffusionAtomique = new DiffusionAtomique();
        diffusionSequentielle = new DiffusionSequentielle();
        diffusionEpoque = new DiffusionEpoque();

        SchedulerSingleton.INSTANCE.initScheduler();

        capteur = new CapteurImpl(diffusionAtomique);

        for (int i = 0; i < Utils.NB_CAPTEUR; i++) {
            capteur.attach(new Canal(i, capteur));
        }
        displayWindow();
    }

    /**
     * Initialing the main view of the App
     */
    private static void displayWindow() {
        JFrame sJFrame = new JFrame();

        sJFrame.setTitle("Main App ");
        sJFrame.setSize(300, 250);
        sJFrame.setResizable(false);
        sJFrame.setLocationRelativeTo(null);
        sJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanelMain = new JPanel();
        jPanelMain.setLayout(new BorderLayout());
        jPanelMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        sJFrame.getContentPane().add(jPanelMain);

        JPanel panelValue = new JPanel();
        panelValue.setLayout(new BorderLayout());
        panelValue.add(new JLabel("Valeur Capteur : "), BorderLayout.WEST);
        mJLabelValueCapteur = new JLabel("null");
        panelValue.add(mJLabelValueCapteur, BorderLayout.CENTER);

        jPanelMain.add(panelValue, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BorderLayout());

        addButtons(panelButtons);

        jPanelMain.add(panelButtons, BorderLayout.CENTER);

        sJFrame.setVisible(true);
    }

    /**
     * Add the main buttons to the app
     *
     * @param panelButtons Jpanel of the buttons
     */
    private static void addButtons(JPanel panelButtons) {
        ButtonGroup bgAlgoDiffusion = new ButtonGroup();
        final JRadioButton jbrDiffusionAtomique = new JRadioButton("DiffusionAtomique");
        jbrDiffusionAtomique.setSelected(true);

        JRadioButton jbrDiffusionSequentielle = new JRadioButton("DiffusionSequentielle");

        JRadioButton jbrDiffusionEpoque = new JRadioButton("DiffusionEpoque");
        //RadioButton to change the Dissemination algorithm
        ActionListener changeDiffusionSelected = e -> {
            if (jbrDiffusionAtomique.isSelected()) {
                capteur.setAlgo(diffusionAtomique);
            } else if (jbrDiffusionSequentielle.isSelected()) {
                capteur.setAlgo(diffusionSequentielle);
            } else if (jbrDiffusionEpoque.isSelected()) {
                capteur.setAlgo(diffusionEpoque);
            }
        };

        jbrDiffusionSequentielle.addActionListener(changeDiffusionSelected);
        jbrDiffusionAtomique.addActionListener(changeDiffusionSelected);
        jbrDiffusionEpoque.addActionListener(changeDiffusionSelected);

        bgAlgoDiffusion.add(jbrDiffusionAtomique);
        bgAlgoDiffusion.add(jbrDiffusionSequentielle);
        bgAlgoDiffusion.add(jbrDiffusionEpoque);

        //button to start capteurs
        JButton jButtonStart = new JButton("START");
        jButtonStart.addActionListener(e -> {
            if (sIsStarted) {
                sTimer.cancel();
                sIsStarted = false;
                ((JButton) e.getSource()).setText("START");
            } else {
                sTimer = new Timer();
                sTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        capteur.tick();
                        mJLabelValueCapteur.setText(capteur.getV().toString());
                    }
                }, 0, 1000);
                sIsStarted = true;
                ((JButton) e.getSource()).setText("STOP");
            }
        });

        //Button to add a canal view
        JButton jButtonAddCanal = new JButton("Add Canal");
        jButtonAddCanal.addActionListener(e -> {
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
        });

        JCheckBox jCheckBoxDebug = new JCheckBox("Debug");
        jCheckBoxDebug.setSelected(false);
        jCheckBoxDebug.addActionListener(e -> Utils.DEBUG = jCheckBoxDebug.isSelected());

        JPanel panelChoice = new JPanel();
        panelChoice.setLayout(new GridLayout(3, 1));
        panelChoice.add(jbrDiffusionAtomique);
        panelChoice.add(jbrDiffusionSequentielle);
        panelChoice.add(jbrDiffusionEpoque);

        JPanel subPanelButtons = new JPanel();
        subPanelButtons.setLayout(new GridLayout(3, 1));
        subPanelButtons.add(jButtonAddCanal);
        subPanelButtons.add(jButtonStart);
        subPanelButtons.add(jCheckBoxDebug);

        panelButtons.add(panelChoice, BorderLayout.CENTER);
        panelButtons.add(subPanelButtons, BorderLayout.SOUTH);
    }
}
