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
		myPiano.itemFreeplay.setText("���ɵ���");
		myPiano.itemTeachplay.setText("��ѧģʽ");
		myPiano.itemRecord.setText("���׼�¼  ��");
		myPiano.itemCreate.setText("���ɴ���");
		myPiano.itemPlay.setText("����");
	}

}
