package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFileChooser;

public class PlayListener implements ActionListener {
	MyPiano myPiano;
	JFileChooser chooser;
	public PlayListener (MyPiano myPiano) {
		this.myPiano=myPiano;
	}
	public void actionPerformed(ActionEvent e){
		myPiano.mode=4;
		myPiano.itemFreeplay.setText("���ɵ���");
		myPiano.itemTeachplay.setText("��ѧģʽ");
		myPiano.itemRecord.setText("���׼�¼");
		myPiano.itemCreate.setText("���ɴ���");
		myPiano.itemPlay.setText("����  ��");
		chooser=new JFileChooser(".");
		
		//�������Ҫ��ʾ���˵��ļ���ʽ������3�п��Բ�Ҫ
		/*chooser.addChoosableFileFilter(new FileNameExtensionFilter("��ʾGifͼƬ�ļ�","gif","GIF"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("��ʾJPegͼƬ�ļ�","jpg","jpeg"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("��ʾdoc�ļ�","doc","DOC"));*/
		int ret=chooser.showOpenDialog(null);
		
		if(ret==JFileChooser.APPROVE_OPTION){//�ж��û�ѡ�����ȷ����ȡ���Ȱ�ť
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
				myPiano.btnStart.setText("��ʼ����");
				
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
