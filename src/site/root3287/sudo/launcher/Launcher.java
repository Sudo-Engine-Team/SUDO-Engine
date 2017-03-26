package site.root3287.sudo.launcher;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.json.JSONObject;

import site.root3287.sudo.logger.LogLevel;
import site.root3287.sudo.logger.Logger;
import site.root3287.sudo.settings.Config;

public class Launcher implements Runnable{
	private static JTextArea logArea;
	private static final int width = 900, height = 900/16*9;
	private static final JFrame frame = new JFrame();
	
	boolean requestClose = false;
	boolean running = true;
	Thread launcher;
	
	boolean l_closeAfterStart = false;
	int l_logLevel = -1;
	int logStyle = 1;
	
	private static JSONObject profiles = new Config("profiles", "res/settings/profiles.json").getJSON();
	private static JSONObject currentProfile = new Config("default-profile", "res/settings/profiles/default.json").getJSON();
	
	public Launcher(){
		// Get our profiles
		
		//Set up the JFrame
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		frame.setTitle("Test Launcher");
		frame.setSize(new Dimension(width, height));
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false); //TODO: Make launcher resizable
		
		//Everything to Jframe
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setBounds(width, height, 0, 0);
		tabPane.add("Play", play());
		tabPane.add("Logs", logs());
		
		frame.pack();
		frame.add(tabPane);
		frame.setVisible(true);
		
		start();
	}
	
	public synchronized void start() {
		launcher = new Thread(this);
		Logger.log(LogLevel.INFO, "Starting launcher...");
		launcher.start();
	}
	
	@Override
	public synchronized void run() {
		float time = 0;
		while(running){
			if(requestClose){
				Logger.log(LogLevel.INFO, "Requested to close the launcher...");
				running = false;
				break;
			}
			time+= 0.0000001f;
			if(time > 10000000){
				Logger.log(LogLevel.DEBUG_RENDER, "Updating logs");
				logArea.setText(Logger.getLogByString());
				time = 0;
			}
			time++;
		}
		stop();
	}

	private synchronized void stop() {
		Logger.log(LogLevel.INFO, "Stopping Launcher...");
		try {
			frame.dispose();
			launcher.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JPanel play(){
		//Play Tab
		JPanel playPanel = new JPanel();
		playPanel.setLayout(null);
		
		JComboBox<String> profilesList = new JComboBox<>();
		for(Object keys : profiles.getJSONArray("data").toList()){
			profilesList.addItem((String) keys);
		}
		profilesList.setBounds((int) ((width/2)*0.10f), (int) (height*0.80f), 80, 20);
		playPanel.add(profilesList);
		JButton updateProfile = new JButton("Update");
		updateProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.log(LogLevel.DEBUG, "Setting current profile to "+profilesList.getItemAt(profilesList.getSelectedIndex()));
				currentProfile = new Config("currentProfile", "res/settings/profiles/"+profilesList.getItemAt(profilesList.getSelectedIndex())+".json").getJSON();
				l_closeAfterStart = currentProfile.getJSONObject("launcher_property").getBoolean("closeOnPlay");
				frame.revalidate();
			}
		});
		updateProfile.setBounds((int)(width*0.15f), (int)(height*0.80f), 80, 20);
		playPanel.add(updateProfile);
		JButton play = new JButton("Play");
		play.setBounds((width/2)-(80/2), (int)(height*0.77f), 80, 40);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(l_closeAfterStart){
					frame.dispose();
					requestClose = true;
				}
				new GameLauncher();
			}
		});
		playPanel.add(play);
		return playPanel;
	}
	
	private JPanel logs(){
		// Logs
		JPanel logs = new JPanel();
		logs.setLayout(null);
		logArea =new JTextArea();
		JScrollPane scroll = new JScrollPane(logArea);
		scroll.setSize((int) (width*0.98f), (int) (height*0.80f));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		logArea.setBounds(0,0, width,(int)(height*0.80f)); 
		logArea.setEditable(false);
		JButton refresh = new JButton("Refresh");
		refresh.setBounds((width/2)-80, (int)(height*0.815f), 80, 20);
		refresh.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.log(LogLevel.INFO, "Refreshing logs");
				logArea.setText(Logger.getLogByString());
			}
		});
		logs.add(scroll);
		logs.add(refresh);
		return logs;
	}
}
