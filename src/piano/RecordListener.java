package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordListener implements ActionListener {
	MyPiano myPiano;

	public RecordListener(MyPiano myPiano){
		this.myPiano=myPiano;
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		myPiano.remember="";
		myPiano.txtOutput.setText("");
		myPiano.numOfNote=0;
		myPiano.mode=2;
		myPiano.itemFreeplay.setText("自由弹奏");
		myPiano.itemTeachplay.setText("教学模式");
		myPiano.itemRecord.setText("乐谱记录  √");
		myPiano.itemCreate.setText("自由创作");
		myPiano.itemPlay.setText("播放");
	}

}
