package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatFrameActionListener implements ActionListener {
	MyPiano myPiano;
	CreatSettingFrame creatSettingFrame;
	public CreatFrameActionListener(MyPiano myPiano){
		this.myPiano=myPiano;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new CreatSettingFrame(myPiano);
		myPiano.mode=3;
		myPiano.itemFreeplay.setText("自由弹奏");
		myPiano.itemTeachplay.setText("教学模式");
		myPiano.itemRecord.setText("乐谱记录");
		myPiano.itemCreate.setText("自由创作  √");
		myPiano.itemPlay.setText("播放");
	}

}
