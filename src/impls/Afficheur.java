package impls;

import interfaces.Capteur;
import interfaces.ObserveurDeCapteur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Afficheur implements ObserveurDeCapteur {

    private final JLabel mJLabelValue;
    private final JFrame mJFrame;

    public Afficheur() {
        mJFrame = new JFrame();

        mJFrame.setTitle("Afficheur");
        mJFrame.setSize(400, 300);
        mJFrame.setMinimumSize(new Dimension(400, 255));
        mJFrame.setResizable(true);
        mJFrame.setLocationRelativeTo(null);
        mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mJPanelMain = new JPanel();
        mJPanelMain.setLayout(new BorderLayout());
        mJPanelMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        mJFrame.getContentPane().add(mJPanelMain);

        JPanel panelValue = new JPanel();
        panelValue.setLayout(new BorderLayout());
        panelValue.add(new JLabel("Valeur : "), BorderLayout.WEST);
        mJLabelValue = new JLabel();
        panelValue.add(panelValue, BorderLayout.CENTER);

        mJPanelMain.add(panelValue, BorderLayout.CENTER);
    }

    @Override
    public void update(Capteur capteur) {
        mJLabelValue.setText(capteur.getValue().toString());
    }
}
