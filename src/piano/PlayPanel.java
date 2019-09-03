package piano;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class PlayPanel extends JPanel {

	Music music;
	JLabel author = new JLabel();
	JLabel title = new JLabel();
	JButton btnPauseOrWake = new JButton();
	JButton btnStop = new JButton();
	public PlayPanel(){
		
	}
	public PlayPanel(Music music) {
		
		this.music=music;
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setSize(190, 130);
		setLocation(10, 10);
		setVisible(true);
		setLayout(null);
		setOpaque(false);
		this.add(author);
		this.add(title);
		this.add(btnPauseOrWake);
		this.add(btnStop);
		title.setBounds(20, 20, 100, 30);
		title.setText(music.title);
		author.setBounds(20,55, 100, 30);
		author.setText(music.author);
		btnPauseOrWake.setBounds(20, 90, 40, 40);
		btnPauseOrWake.setOpaque(false);
		
		ImageIcon btnpauseimge = new ImageIcon("image/pause.png");
		Image pauseimg=btnpauseimge.getImage();
		pauseimg = pauseimg.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		btnpauseimge.setImage(pauseimg);
		btnPauseOrWake.setIcon(btnpauseimge);
		btnStop.setBounds(90, 90, 40, 40);
		ImageIcon btnstopimge = new ImageIcon("image/stop.png");
		Image stopimg=btnstopimge.getImage();
		stopimg = stopimg.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		btnstopimge.setImage(stopimg);
		btnStop.setIcon(btnstopimge);
		btnPauseOrWake.addActionListener(new PauseOrWakeListener(music,this));
		btnStop.addActionListener(new StopListener(music,this));
	}
}
