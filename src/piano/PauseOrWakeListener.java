package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class PauseOrWakeListener implements ActionListener{
    int pauseOrwakeFlag;
	Playing playing;
    PlayPanel panel;
    
	public PauseOrWakeListener(Playing playing,PlayPanel panel){
		this.playing=playing;
		this.panel = panel;
		this.pauseOrwakeFlag = 0;
	}
	public void actionPerformed(ActionEvent e) {
		pauseOrwakeFlag++;
		if(pauseOrwakeFlag % 2==1){
		    playing.pause();
		    panel.btnPauseOrWake.setIcon(new ImageIcon("image/play.png"));
		}
		else{
			playing.wake();
			panel.btnPauseOrWake.setIcon(new ImageIcon("image/pause.png"));
		}
	}
}
