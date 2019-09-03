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
	int halfNum; // ��������Ŀ �ɸ�
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

	public String getNoteString(Note note, int channelNum, int instrument) {// �õ�����V0
																			// I0
																			// 60q�����
		// ���������һ���õ�
		String noteString = "";
		return noteString;
		// �õ�����V0 I0 60q�����
	}

	public void playNote() throws InterruptedException, MidiUnavailableException {// �����������
		durationInt = myPiano.transformDurationToTime(durationString, music.pace, isFudian);

		if (!music.isPlayed[index][num - 1] && beginTime >= 0) {
			Thread.sleep(beginTime);
			if (character >= 0) {
				myPiano.player.play("V" + String.valueOf(myPiano.getChannel(instrument)) + " " + "I"
						+ String.valueOf(instrument) + " " + String.valueOf(character) + durationString);
				music.isPlayed[index][num - 1] = true;
				int i = PianoPanel.transformCharaterToWhitePianoCode(character);

				if (i != -1) {
					if (i == 0)
						myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/0.jpg"));
					else if (i == 1)
						myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/7.jpg"));
					else {
						switch ((i - 2) % 7) {
						case 0:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/1.jpg"));
							break;
						case 1:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/2.jpg"));
							break;
						case 2:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/3.jpg"));
							break;
						case 3:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/4.jpg"));
							break;
						case 4:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/5.jpg"));
							break;
						case 5:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/6.jpg"));
							break;
						case 6:
							myPiano.btnPianoWhite[i].setIcon(new ImageIcon("image/7.jpg"));
							break;
						default:
							myPiano.btnPianoWhite[i].setIcon(null);
							break;
						}

					}
				}
				if (myPiano.transformKeyCodeToButtonCode(myPiano.transformCharacterToKeyCode(character)) >= 0
						&& myPiano.transformKeyCodeToButtonCode(myPiano.transformCharacterToKeyCode(character)) < 127) {
					myPiano.btn[myPiano.transformKeyCodeToButtonCode(myPiano.transformCharacterToKeyCode(character))]
							.setBackground(Color.GREEN);
					myPiano.btn[myPiano.transformDurationToButtonCode(durationString)].setBackground(myPiano.myGay);
					Thread.sleep(durationInt);
					myPiano.btn[myPiano.transformKeyCodeToButtonCode(myPiano.transformCharacterToKeyCode(character))]
							.setBackground(Color.WHITE);
					myPiano.btn[myPiano.transformDurationToButtonCode(durationString)].setBackground(Color.WHITE);
					
					if (i != -1) {
						myPiano.btnPianoWhite[i].setIcon(null);
					}
				
				}else{
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

	public int getCharacter(int tone, int octave) {
		int character;
		character = (5 + octave) * 12 + transformTone(tone);
		return character;
	}

	public int transformTone(int tone) {
		switch (tone) {
		case 1:
			return 0;
		case 2:
			return 2;
		case 3:
			return 4;
		case 4:
			return 5;
		case 5:
			return 7;
		case 6:
			return 9;
		case 7:
			return 11;
		default:
			return 0;

		}
	}
}
