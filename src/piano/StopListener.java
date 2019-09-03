package piano;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class StopListener implements ActionListener{
	Music music;
	public StopListener(Music music, PlayPanel panel) {
		// TODO Auto-generated constructor stub
		this.music=music;

	}
	public void actionPerformed(ActionEvent e) {
		music.stop();

	}
}
