package piano;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class StopListener implements ActionListener{
	Playing playing;
	public StopListener(Playing playing) {
		// TODO Auto-generated constructor stub
		this.playing = playing;
	}
	public void actionPerformed(ActionEvent e) {
		playing.stopPlaying();
	}
}
