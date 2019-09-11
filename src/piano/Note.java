package piano;

import java.awt.Color;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.ImageIcon;

import org.jfugue.realtime.RealtimePlayer;

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
		durationInt = myPiano.transformDurationToTime(durationString, music.pace, isFudian);

		if (!music.isPlayed[index][num - 1] && beginTime >= 0) {
			Thread.sleep(beginTime);
			if (character >= 0) {
				//myPiano.player.play("V" + String.valueOf(myPiano.getChannel(instrument)) + " " + "I"
						//+ String.valueOf(instrument) + " " + String.valueOf(character) + durationString);
				music.isPlayed[index][num - 1] = true;
				int i = PianoPanel.transformCharaterToWhitePianoCode(character);

				myPiano.settingwhitekey(i);

				KeyProperty key = myPiano.km.findByCharacter(character);
				boolean isControl = false;
				if(key.getIndex()== -1){
					key = myPiano.km.findByRiseCharacter(character);
					isControl = true;
				}
				if(key.getIndex()!= -1) {
					int userButton = key.getIndex();
					if (userButton >= 0 && userButton < 60) {
						myPiano.setkeycolor(userButton, 2);
						if(isControl){
							myPiano.setkeycolor(53,2);//ctrol����
							myPiano.setkeycolor(57,2);
						}
						Thread.sleep(durationInt);
						myPiano.setkeycolor(userButton, 5);
						if(isControl) {
							myPiano.setkeycolor(53, 5);
							myPiano.setkeycolor(57,2);
						}
						if (i != -1) {
							myPiano.btnPianoWhite[i].setIcon(null);
						}

					} else {
						if (i != -1) {
							Thread.sleep(durationInt);
							myPiano.btnPianoWhite[i].setIcon(null);
						}
					}
				}
				else{
					if (i != -1) {
						Thread.sleep(durationInt);
						myPiano.btnPianoWhite[i].setIcon(null);
					}
				}
			} else {

				music.isPlayed[index][num - 1] = true;
			}

		}
	}

}
