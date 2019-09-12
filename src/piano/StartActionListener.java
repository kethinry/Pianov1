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
		case 0://��������
			myPiano.refresh();
			break;
		case 1://ʾ��ģʽ
			TeachModeThread teachModeThread = new TeachModeThread(myPiano);
			if(myPiano.btnStart.getText().equals("��ʼʾ��")){
				myPiano.btnStart.setText("ֹͣʾ��");
				teachModeThread.start();
			}else{//����ʱ����ֹͣʾ��
				myPiano.mode=0;//�л�����������ģʽ
				teachModeThread.stop();
				myPiano.lblWuXianPu.x_note = myPiano.lblWuXianPu.left_bound;
				myPiano.refresh();
				myPiano.btnStart.setText("��ʼ����");
			}
			break;
		case 2://��¼ģʽ
			if(myPiano.btnStart.getText().equals("��ʼ����")){
				myPiano.refresh();
				myPiano.btnStart.setText("ֹͣ¼��");
				myPiano.timeString="";
				myPiano.beginTime=System.currentTimeMillis();
			}else{
				new SaveFrame(myPiano);
				myPiano.btnStart.setText("��ʼ����");
			}
			break;
		case 3:break;//����ģʽ
		case 4:
			if(myPiano.btnStart.getText().equals("��ʼ����")){
				myPiano.btnStart.setText("ֹͣ����");

				try {
					this.playMode = new PlayMode(myPiano);
				} catch (InterruptedException | MidiUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{//����ʱ����ֹͣʾ��
				myPiano.mode=0;//�л�����������ģʽ
				playMode.stopAllThreads();
				myPiano.refresh();
				myPiano.btnStart.setText("��ʼ����");
			}
		
			break;//����ģʽ
		default:break;//ʾ��ģʽ
		}
		
		myPiano.duration = myPiano.trans.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
		myPiano.instrument = "I" + String.valueOf(myPiano.trans.transformInstrument(myPiano.jbxSetInstrument.getSelectedIndex()));
		myPiano.tone = myPiano.jbxSetTone.getSelectedIndex();
		if (myPiano.tone >= 7)
			myPiano.tone -= 12;
		myPiano.btn[0].requestFocus();
	}

}
