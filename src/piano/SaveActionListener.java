package piano;
//��������ļ��˵��еı��水ť�˵����¼�����������������󣬵�������Ի��򣬼�SaveFrame�࣬������ǲ���ʱ�����������
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveActionListener implements ActionListener{
	MyPiano myPiano;
	public SaveActionListener(MyPiano myPiano){
		this.myPiano=myPiano;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			new SaveFrame(myPiano);
	}
}
