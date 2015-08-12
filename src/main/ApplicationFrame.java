package main;

import gamestate.GamePanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javafx.scene.Cursor;

import javax.swing.JFrame;

public class ApplicationFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public ApplicationFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Water Simulator");
		Dimension dim = new Dimension(1280,720);
		//setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		
		add(new GamePanel());
		getContentPane().setPreferredSize(dim);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
//		setCursor(getToolkit().createCustomCursor(
//	            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
//	            "null"));
		setVisible(true);
	}

	public static void main(String[] args) {
		new ApplicationFrame();
	}

}
