package com.impls;

import com.interfaces.CapteurAsync;
import com.interfaces.ObserveurDeCapteurAsync;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Afficheur implements ObserveurDeCapteurAsync {

	private final int			mNumber;
	private final Canal			mCanal;
	protected JLabel			mJLabelValue;
	protected final String		mCanalName;

	private static final double	screenHeight;
	private static final double	screenWidth;
	private static final int	nbByWidth;
	private static final int	marginWidth;

	static {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();
		nbByWidth = (int) ((screenWidth - 50) / 300);
		marginWidth = (int) ((screenWidth - (nbByWidth * 300)) / (nbByWidth - 1));
	}

	public Afficheur(String canalName, int number, Canal canal) {
		mCanalName = canalName;
		mNumber = number;
		mCanal = canal;
		displayWindow();
	}

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
				// TODO : unregister
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

	@Override
	public void update(CapteurAsync capteurAsync) {
		try {
			String value = capteurAsync.getValue().get().toString();
			mJLabelValue.setText(value);
			System.out.println("Valeur " + mCanalName + " : " + value);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
