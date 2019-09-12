package piano;

import java.awt.Color;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.ImageIcon;

import org.jfugue.*;

public class Note {
	MyPiano myPiano;
	Music music;
	int octave;// �ڼ����˶�ȡֵ -5..5
	int tone;// ���׵���7���� 1..7
	int character; // ���� 1..127
	int instrument;
	int index;// ��ʾ��������ڵڼ���part��
	long durationInt;// ���ӵ�ʱ�����ֱ�ʾ������2000����
	String durationString;// ���ӵ�ʱ����һ���ַ���ʾ������qΪ�ķ�����
	long beginTime;// ��ʼ���ŵ�ʱ��
	int num;// ��ʾ ��������������еĵڼ���
	boolean isFudian = false;// ��ʾ���������û�и���
	boolean isPrint = false;//��ʾ�Ƿ�������������ʾ����

	public Note(MyPiano myPiano, Music music, int index, int instrument, int num) {// ͨ�������õ���������
		this.myPiano = myPiano;
		this.music = music;
		this.index = index;
		this.instrument = instrument;
		this.num = num;
	}

	public void playNote() throws InterruptedException, MidiUnavailableException {// �����������
		
		durationInt = myPiano.trans.transformDurationToTime(durationString, music.pace, isFudian);
		for(int i=0;i<num;i++) {
			if (!music.isPlayed[index][i] && beginTime >= 0) {
				if (character >= 0) {
					music.isPlayed[index][i] = true;
					int whitePianoCode = PianoPanel.transformCharaterToWhitePianoCode(character);

					myPiano.settingkey.settingwhitekey(whitePianoCode);

					KeyProperty key = myPiano.km.findByCharacter(character);
					boolean isControl = false;
					if(key.getIndex()== -1){
						key = myPiano.km.findByRiseCharacter(character);
						isControl = true;
					}
					if(key.getIndex()!= -1) {
						int userButton = key.getIndex();
						if (userButton >= 0 && userButton < 60) {
							myPiano.settingkey.setkeycolor(userButton, 2);
							if(isControl){
								myPiano.settingkey.setkeycolor(53,2);//ctrol����
								myPiano.settingkey.setkeycolor(57,2);
							}
							Thread.sleep(durationInt);
							myPiano.settingkey.setkeycolor(userButton, 5);
							if(isControl) {
								myPiano.settingkey.setkeycolor(53, 5);
								myPiano.settingkey.setkeycolor(57,2);
							}
							if (whitePianoCode != -1) {
								myPiano.btnPianoWhite[whitePianoCode].setIcon(null);
							}

						} else {
							if (whitePianoCode != -1) {
								Thread.sleep(durationInt);
								myPiano.btnPianoWhite[whitePianoCode].setIcon(null);
							}
						}
					}
					else{
						if (whitePianoCode != -1) {
							Thread.sleep(durationInt);
							myPiano.btnPianoWhite[whitePianoCode].setIcon(null);
						}
					}
				} else {

					music.isPlayed[index][i] = true;
				}
		}
		

		}
	}

}
