package site.root3287.lwjgl.launcher;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import javafx.scene.canvas.Canvas;
import site.root3287.lwjgl.LWJGL;

public class Launcher extends Canvas{
	JButton play;
	JPanel window;
	Rectangle boundPlay;
	public Launcher(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			// TODO Auto-generated catch blocks
			e1.printStackTrace();
		}
		JFrame frame = new JFrame();
		window = new JPanel();
		frame.setTitle("Test Launcher");
		frame.setVisible(true);
		frame.setSize(new Dimension(900, 900/16*9));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		window.setLayout(null);
		
		play = new JButton("Play");
		boundPlay = new Rectangle((900/2)-(80/2), ((900/16*9)/2), 80, 40);
		play.setBounds(boundPlay);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LWJGL();
			}
		});
		window.add(play);
		
		frame.getContentPane().add(window);
	}
}
