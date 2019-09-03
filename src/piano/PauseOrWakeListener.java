package piano;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class PauseOrWakeListener implements ActionListener{
    int pauseOrwakeFlag;
	Music music;
    PlayPanel panel;
    
	public PauseOrWakeListener(Music music,PlayPanel panel){
		this.music=music;
		this.panel = panel;
		this.pauseOrwakeFlag = 0;
	}
	public void actionPerformed(ActionEvent e) {
		pauseOrwakeFlag++;
		if(pauseOrwakeFlag % 2==1){
		    music.pause();
		    panel.btnPauseOrWake.setIcon(new ImageIcon("image/play.png"));
		}
		else{
			music.wake();
			panel.btnPauseOrWake.setIcon(new ImageIcon("image/pause.png"));
		}
	}
}
