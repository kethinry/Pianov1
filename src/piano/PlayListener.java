package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

import org.jfugue.*;

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
		
		if (!myPiano.isPlaying) {
			Playing playing = new Playing(myPiano);
			playing.start();			
		}else {
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
