package piano;
//这个类是文件菜单中的保存按钮菜单的事件监听器，点击触发后，弹出保存对话框，即SaveFrame类，保存的是不含时间参数的乐谱
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
