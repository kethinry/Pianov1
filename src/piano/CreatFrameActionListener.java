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
		myPiano.itemFreeplay.setText("���ɵ���");
		myPiano.itemTeachplay.setText("��ѧģʽ");
		myPiano.itemRecord.setText("���׼�¼");
		myPiano.itemCreate.setText("���ɴ���  ��");
		myPiano.itemPlay.setText("����");
	}

}
