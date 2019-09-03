package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenListener implements ActionListener{
	MyPiano myPiano;
	//WuXianPu wuXianPu;
	JFileChooser chooser;
	public OpenListener(MyPiano myPiano){
		this.myPiano=myPiano;
		//this.wuXianPu=wuXianPu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!myPiano.isPlaying) {
			Music music=new Music(myPiano);
			if(music.getMusicFromFile()){
				music.play();
				myPiano.isPlaying=true;
			}
			
		}
		else{
			JOptionPane.showMessageDialog(null, "���и������ڲ��ţ�");
		}
		/*

		//�������Ҫ��ʾ���˵��ļ���ʽ������3�п��Բ�Ҫ
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("��ʾGifͼƬ�ļ�","gif","GIF"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("��ʾJPegͼƬ�ļ�","jpg","jpeg"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("��ʾdoc�ļ�","doc","DOC"));
		int ret=chooser.showOpenDialog(null);
*/
	}

}
