package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class TeachingListener implements ActionListener {
	MyPiano myPiano;
	JFileChooser chooser;

	public TeachingListener(MyPiano myPiano) {
		this.myPiano = myPiano;
	}

	public void actionPerformed(ActionEvent e) {
		myPiano.mode = 1;
		myPiano.itemFreeplay.setText("自由弹奏");
		myPiano.itemTeachplay.setText("教学模式  √");
		myPiano.itemRecord.setText("乐谱记录");
		myPiano.itemCreate.setText("自由创作");
		myPiano.itemPlay.setText("播放");
		chooser=new JFileChooser(".");
		System.setProperty("swing.defaultlaf","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//采用Windows布局形式

		//如果不需要显示过滤的文件格式，下面3行可以不要
		/*chooser.addChoosableFileFilter(new FileNameExtensionFilter("显示Gif图片文件","gif","GIF"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("显示JPeg图片文件","jpg","jpeg"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("显示doc文件","doc","DOC"));*/
		int ret=chooser.showOpenDialog(null);
		
		if(ret==JFileChooser.APPROVE_OPTION){//判断用户选择的是确定、取消等按钮
			myPiano.refresh();
			
			FileInputStream fin=null;
			
			try {
				fin=new FileInputStream(chooser.getSelectedFile().getPath());
				FileReader fr=new FileReader(chooser.getSelectedFile().getPath());
				BufferedReader br=new BufferedReader(fr);
				String temp="";
				
				while((temp=br.readLine())!=null){
					myPiano.remember+=(temp+"\r\n");
				}
				
				myPiano.txtOutput.setText(myPiano.remember);
				myPiano.btnStart.setText("开始示教");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				try {
					fin.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}		
		}
		

	}
}
