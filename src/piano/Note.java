package piano;

import java.awt.Color;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.ImageIcon;

import org.jfugue.realtime.RealtimePlayer;

public class Note {
	MyPiano myPiano;
	Music music;
	int octave;// 第几个八度取值 -5..5
	int tone;// 音阶调，7个音 1..7
	int character; // 数字 1..127
	int instrument;
	int index;// 表示这个音符在第几个part里
	long durationInt;// 拖延的时间数字表示，例如2000毫秒
	String durationString;// 拖延的时间用一个字符表示，例如q为四分音符
	long beginTime;// 开始播放的时间
	int num;// 表示 这个音符是乐谱中的第几个
	boolean isFudian = false;// 表示这个音符有没有浮点
	boolean isPrint = false;//表示是否在五线谱上显示过了

	public Note(MyPiano myPiano, Music music, int index, int instrument, int num) {// 通过参数得到音符对象
		this.myPiano = myPiano;
		this.music = music;
		this.index = index;
		this.instrument = instrument;
		this.num = num;
	}

	public void playNote() throws InterruptedException, MidiUnavailableException {// 播放这个音符
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

}
