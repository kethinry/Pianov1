package piano;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiUnavailableException;

import com.jtattoo.plaf.JTattooUtilities;

public class StartActionListener implements ActionListener {
	MyPiano myPiano;
	PlayMode playMode;
	public StartActionListener (MyPiano myPiano) {
		this.myPiano=myPiano;
	}
	public void actionPerformed(ActionEvent e) {
		
		switch(myPiano.mode){
		case 0://自由演奏
			myPiano.refresh();
			break;
		case 1://示教模式
			TeachModeThread teachModeThread = new TeachModeThread(myPiano);
			if(myPiano.btnStart.getText().equals("开始示教")){
				myPiano.btnStart.setText("停止示教");
				teachModeThread.start();
			}else{//按的时候是停止示教
				myPiano.mode=0;//切换回自由演奏模式
				teachModeThread.stop();
				myPiano.lblWuXianPu.x_note = myPiano.lblWuXianPu.left_bound;
				myPiano.refresh();
				myPiano.btnStart.setText("开始演奏");
			}
			break;
		case 2://记录模式
			if(myPiano.btnStart.getText().equals("开始演奏")){
				myPiano.refresh();
				myPiano.btnStart.setText("停止录制");
				myPiano.timeString="";
				myPiano.beginTime=System.currentTimeMillis();
			}else{
				new SaveFrame(myPiano);
				myPiano.btnStart.setText("开始演奏");
			}
			break;
		case 3:break;//创作模式
		case 4:
			if(myPiano.btnStart.getText().equals("开始播放")){
				myPiano.btnStart.setText("停止播放");

				try {
					this.playMode = new PlayMode(myPiano);
				} catch (InterruptedException | MidiUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{//按的时候是停止示教
				myPiano.mode=0;//切换回自由演奏模式
				playMode.stopAllThreads();
				myPiano.refresh();
				myPiano.btnStart.setText("开始演奏");
			}
		
			break;//播放模式
		default:break;//示教模式
		}
		
		myPiano.duration = myPiano.trans.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
		myPiano.instrument = "I" + String.valueOf(myPiano.trans.transformInstrument(myPiano.jbxSetInstrument.getSelectedIndex()));
		myPiano.tone = myPiano.jbxSetTone.getSelectedIndex();
		if (myPiano.tone >= 7)
			myPiano.tone -= 12;
		myPiano.btn[0].requestFocus();
	}

}
