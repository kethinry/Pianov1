

package piano;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jfugue.player.Player;

//import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;

public class MyKeyListener implements KeyListener {
	MyPiano myPiano;
	long lastTime = 0;
	String musicString;
	String lastMusicString = "";
	boolean isReleased[];
	public MyKeyListener(MyPiano myPiano) {
		this.myPiano = myPiano;
		isReleased = new boolean[129];
		for(int i=0;i<=128;i++)isReleased[i]=true;//所有按钮都 被释放
	}

	public void keyPressed(KeyEvent arg0) {
		int a = arg0.getKeyCode();
		if(a>128)return;
		if(isReleased[a]){
			long t = System.currentTimeMillis() - myPiano.beginTime;
			if (myPiano.transformKeyCodeToCharacter(a) > 0) {
				myPiano.timeString = myPiano.timeString + String.valueOf(t) + " ";
				
			}
			int userCharacter = myPiano.transformKeyCodeToCharacter(a);
			if (myPiano.isUpperLetter()) {
				userCharacter += 1;
			}
			int userButton = myPiano.transformKeyCodeToButtonCode(a);
			
			switch (myPiano.mode) {
			case 0:
			case 2:
				myPiano.player.play(myPiano.getString(a));
				if (!myPiano.isColorful) {// 黑白键盘模式
					myPiano.btn[myPiano.transformKeyCodeToButtonCode(a)].setBackground(Color.BLACK);
					if (myPiano.transformKeyCodeToButtonCode(a) == 41)
						myPiano.btn[52].setBackground(Color.BLACK);
					if (myPiano.transformKeyCodeToButtonCode(a) == 53)
						myPiano.btn[57].setBackground(Color.BLACK);
				} else {// 多彩键盘模式
					if (myPiano.colorNum >= 6)
						myPiano.colorNum = 0;
					
					if (myPiano.transformKeyCodeToButtonCode(a) != -1)
						myPiano.btn[myPiano.transformKeyCodeToButtonCode(a)]
								.setBackground(myPiano.myColor[myPiano.colorNum]);
					if (myPiano.transformKeyCodeToButtonCode(a) == 41)
						myPiano.btn[52].setBackground(myPiano.myColor[myPiano.colorNum]);
					if (myPiano.transformKeyCodeToButtonCode(a) == 53)
						myPiano.btn[57].setBackground(myPiano.myColor[myPiano.colorNum]);
					myPiano.colorNum++;
				}
				break;

			case 1:
				if (myPiano.currentNum < myPiano.numOfNote) {
					if (userCharacter == myPiano.character[myPiano.currentNum]) { // 输入正确
						myPiano.player.play(myPiano.getString(a));
						myPiano.btn[userButton].setBackground(Color.GREEN);

						if (userButton == 41)
							myPiano.btn[52].setBackground(Color.GREEN);
						if (userButton == 53)
							myPiano.btn[57].setBackground(Color.GREEN);

						if (myPiano.currentNum == myPiano.numOfNote - 1
								|| myPiano.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum]) != myPiano
										.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum + 1])) // 最后一个音符
																													// 或者当前的音符不等于下一个音符，那么当前音符变白
							myPiano.btn[myPiano.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum])]
									.setBackground(Color.WHITE);

					} else {
						if ((userButton >= 15 && userButton <= 24) || (userButton >= 29 && userButton <= 37)
								|| (userButton >= 42 && userButton <= 48)) {
							File file1 = new File("Windows Critical Stop.wav");
							@SuppressWarnings("deprecation")
							AudioClip sound1 = null;
							try {
								sound1 = Applet.newAudioClip(file1.toURL());
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							sound1.play();
							myPiano.btn[userButton].setBackground(Color.RED);
							if (userButton == 41)
								myPiano.btn[52].setBackground(Color.RED);
							if (userButton == 53)
								myPiano.btn[57].setBackground(Color.RED);
						} else {
							myPiano.btn[userButton].setBackground(myPiano.myPink);
							if (userButton == 41)
								myPiano.btn[52].setBackground(myPiano.myPink);
							if (userButton == 53)
								myPiano.btn[57].setBackground(myPiano.myPink);
						}
						myPiano.isTruePressed = false;

					}

				} else {
					
					myPiano.refresh();
					JOptionPane.showMessageDialog(null, "歌谱已结束！");

				}
				break;
			}
			if (myPiano.transformKeyCodeToButtonCode(a) >= 15 && myPiano.transformKeyCodeToButtonCode(a) <= 24) {
				switch ((myPiano.transformKeyCodeToButtonCode(a) + 13) % 7) {
				case 0:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/1.jpg"));
					break;
				case 1:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/2.jpg"));
					break;
				case 2:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/3.jpg"));
					break;
				case 3:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/4.jpg"));
					break;
				case 4:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 5:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/6.jpg"));
					break;
				case 6:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15]
							.setIcon(new ImageIcon("image/7.jpg"));
					break;
				default:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15].setIcon(null);
					break;
				}
			} else if (myPiano.transformKeyCodeToButtonCode(a) >= 29 && myPiano.transformKeyCodeToButtonCode(a) <= 37) {
				switch ((myPiano.transformKeyCodeToButtonCode(a) - 8) % 7) {
				case 0:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/1.jpg"));
					break;
				case 1:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/2.jpg"));
					break;
				case 2:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/3.jpg"));
					break;
				case 3:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/4.jpg"));
					break;
				case 4:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 5:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/6.jpg"));
					break;
				case 6:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6]
							.setIcon(new ImageIcon("image/7.jpg"));
					break;
				default:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6].setIcon(null);
					break;
				}
			} else if (myPiano.transformKeyCodeToButtonCode(a) >= 42 && myPiano.transformKeyCodeToButtonCode(a) <= 48) {
				switch ((myPiano.transformKeyCodeToButtonCode(a) - 28) % 7) {
				case 0:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/1.jpg"));
					break;
				case 1:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/2.jpg"));
					break;
				case 2:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/3.jpg"));
					break;
				case 3:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/4.jpg"));
					break;
				case 4:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 5:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/6.jpg"));
					break;
				case 6:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26]
							.setIcon(new ImageIcon("image/7.jpg"));
					break;
				default:
					myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26].setIcon(null);
					break;
				}
			}
			if (myPiano.transformKeyCodeToButtonCode(a) == 55) {
				myPiano.jbxSetInstrument.setSelectedIndex((myPiano.jbxSetInstrument.getSelectedIndex() + 1) % 14);
				myPiano.instrument = "I"
						+ String.valueOf(myPiano.transformInstrument(myPiano.jbxSetInstrument.getSelectedIndex()));
			}
			if (myPiano.transformKeyCodeToButtonCode(a) == 0) {
				myPiano.jbxSetTone.setSelectedIndex((myPiano.jbxSetTone.getSelectedIndex() + 1) % 12);
				myPiano.tone = myPiano.jbxSetTone.getSelectedIndex();
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 25) {
				myPiano.jbxSetDuration.setSelectedIndex(0);
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 26) {
				myPiano.jbxSetDuration.setSelectedIndex(1);
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 27) {
				myPiano.jbxSetDuration.setSelectedIndex(2);
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 38) {
				myPiano.jbxSetDuration.setSelectedIndex(3);
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 39) {
				myPiano.jbxSetDuration.setSelectedIndex(4);
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 49) {//当时相加音高	
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 50) {//当时相加音高		
				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			} else if (myPiano.transformKeyCodeToButtonCode(a) == 51) {//当时相加音高

				myPiano.duration = myPiano.transformDuration(myPiano.jbxSetDuration.getSelectedIndex());
			}
			if (myPiano.transformKeyCodeToButtonCode(a) >= 1 && myPiano.transformKeyCodeToButtonCode(a) <= 12) {
				// myPiano.numOfNote++;
				if (myPiano.channelNum == 9)
					myPiano.channelNum++;// V9音轨特殊，不能播放声调，只能打鼓点，因此遇到 9跳过
				else if (myPiano.channelNum == 16)
					myPiano.channelNum = 0;// 达到16以后重新从0开始
				switch (myPiano.transformKeyCodeToButtonCode(a)) {
				case 1:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+64q+67q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 2:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+64q+67q+69q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					myPiano.btnPianoWhite[28].setIcon(new ImageIcon("image/6.jpg"));
					break;
				case 3:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+63q+67q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoBlack[17].setBackground(new Color(135, 206, 250));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 4:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+63q+67q+69q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoBlack[17].setBackground(new Color(135, 206, 250));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					myPiano.btnPianoWhite[28].setIcon(new ImageIcon("image/6.jpg"));
					break;
				case 5:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+63q+66q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoBlack[17].setBackground(new Color(135, 206, 250));
					myPiano.btnPianoBlack[18].setBackground(new Color(135, 206, 250));
					break;
				case 6:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+64q+67q+71q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					myPiano.btnPianoWhite[29].setIcon(new ImageIcon("image/7.jpg"));
					break;
				case 7:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+64q+68q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoBlack[19].setBackground(new Color(135, 206, 250));
					break;
				case 8:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "64q+67q+70q+74q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					myPiano.btnPianoWhite[29].setIcon(new ImageIcon("image/7.jpg"));
					myPiano.btnPianoWhite[31].setIcon(new ImageIcon("image/2.jpg"));
					break;
				case 9:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+65q+67q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[26].setIcon(new ImageIcon("image/4.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 10:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+64q+67q+70q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					myPiano.btnPianoBlack[20].setBackground(new Color(135, 206, 250));
					break;
				case 11:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+62q+64q+67q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoWhite[24].setIcon(new ImageIcon("image/2.jpg"));
					myPiano.btnPianoWhite[25].setIcon(new ImageIcon("image/3.jpg"));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					break;
				case 12:
					myPiano.player.play("V" + String.valueOf(myPiano.channelNum) + " " + myPiano.instrument + " "
							+ "60q+63q+67q+70q" + " ");
					myPiano.channelNum++;
					myPiano.btnPianoWhite[23].setIcon(new ImageIcon("image/1.jpg"));
					myPiano.btnPianoBlack[17].setBackground(new Color(135, 206, 250));
					myPiano.btnPianoWhite[27].setIcon(new ImageIcon("image/5.jpg"));
					myPiano.btnPianoBlack[20].setBackground(new Color(135, 206, 250));
					break;
				default:
					break;
				}

			}
		}
		isReleased[a]=false;

	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent e) {
		int a = e.getKeyCode();
		if(a>128)return;
		
		isReleased[a]=true;
		if (myPiano.mode == 1) {
			int character = myPiano.transformKeyCodeToCharacter(a);
			if (myPiano.isUpperLetter())
				character += 1;
			if (myPiano.currentNum < myPiano.numOfNote && character == myPiano.character[myPiano.currentNum]) {
				myPiano.currentNum++;
				myPiano.isTruePressed = true;
			

			}
			if (myPiano.character[myPiano.currentNum] != 48 && myPiano.character[myPiano.currentNum] != 50
					&& myPiano.character[myPiano.currentNum] != 52 && myPiano.character[myPiano.currentNum] != 53
					&& myPiano.character[myPiano.currentNum] != 55 && myPiano.character[myPiano.currentNum] != 57
					&& myPiano.character[myPiano.currentNum] != 59 && myPiano.character[myPiano.currentNum] != 60
					&& myPiano.character[myPiano.currentNum] != 62 && myPiano.character[myPiano.currentNum] != 64
					&& myPiano.character[myPiano.currentNum] != 65 && myPiano.character[myPiano.currentNum] != 67
					&& myPiano.character[myPiano.currentNum] != 69 && myPiano.character[myPiano.currentNum] != 71
					&& myPiano.character[myPiano.currentNum] != 72 && myPiano.character[myPiano.currentNum] != 74
					&& myPiano.character[myPiano.currentNum] != 76 && myPiano.character[myPiano.currentNum] != 77
					&& myPiano.character[myPiano.currentNum] != 79 && myPiano.character[myPiano.currentNum] != 81
					&& myPiano.character[myPiano.currentNum] != 83 && myPiano.character[myPiano.currentNum] != 84
					&& myPiano.character[myPiano.currentNum] != 86 && myPiano.character[myPiano.currentNum] != 88) {
				if(myPiano.isUpperLetter() ||( !myPiano.isUpperLetter() && a!=myPiano.keyCode[myPiano.currentNum]))
					if (myPiano.transformKeyCodeToButtonCode(a) >= 0){
						
						myPiano.btn[myPiano.transformKeyCodeToButtonCode(a)].setBackground(Color.WHITE);
					}
			}
			else if(!myPiano.isUpperLetter() && (myPiano.character[myPiano.currentNum] == 48 || myPiano.character[myPiano.currentNum] == 50
					|| myPiano.character[myPiano.currentNum] == 52 || myPiano.character[myPiano.currentNum] == 53
					|| myPiano.character[myPiano.currentNum] == 55 || myPiano.character[myPiano.currentNum] == 57
					|| myPiano.character[myPiano.currentNum] == 59 || myPiano.character[myPiano.currentNum] == 60
					|| myPiano.character[myPiano.currentNum] == 62 || myPiano.character[myPiano.currentNum] == 64
					|| myPiano.character[myPiano.currentNum] == 65 || myPiano.character[myPiano.currentNum] == 67
					|| myPiano.character[myPiano.currentNum] == 69 || myPiano.character[myPiano.currentNum] == 71
					|| myPiano.character[myPiano.currentNum] == 72 || myPiano.character[myPiano.currentNum] == 74
					|| myPiano.character[myPiano.currentNum] == 76 || myPiano.character[myPiano.currentNum] == 77
					|| myPiano.character[myPiano.currentNum] == 79 || myPiano.character[myPiano.currentNum] == 81
					|| myPiano.character[myPiano.currentNum] == 83 || myPiano.character[myPiano.currentNum] == 84
					|| myPiano.character[myPiano.currentNum] == 86 || myPiano.character[myPiano.currentNum] == 88)){
				myPiano.btn[myPiano.transformKeyCodeToButtonCode(a)].setBackground(Color.WHITE);
				
			}
			else if(myPiano.isUpperLetter() && !myPiano.isTruePressed)
				;
			else if(myPiano.isUpperLetter() && myPiano.isTruePressed)
				myPiano.btn[myPiano.transformKeyCodeToButtonCode(a)].setBackground(Color.WHITE);
			if (myPiano.isTruePressed && myPiano.currentNum < myPiano.numOfNote) {
				if (myPiano.character[myPiano.currentNum] != 48 && myPiano.character[myPiano.currentNum] != 50
						&& myPiano.character[myPiano.currentNum] != 52 && myPiano.character[myPiano.currentNum] != 53
						&& myPiano.character[myPiano.currentNum] != 55 && myPiano.character[myPiano.currentNum] != 57
						&& myPiano.character[myPiano.currentNum] != 59 && myPiano.character[myPiano.currentNum] != 60
						&& myPiano.character[myPiano.currentNum] != 62 && myPiano.character[myPiano.currentNum] != 64
						&& myPiano.character[myPiano.currentNum] != 65 && myPiano.character[myPiano.currentNum] != 67
						&& myPiano.character[myPiano.currentNum] != 69 && myPiano.character[myPiano.currentNum] != 71
						&& myPiano.character[myPiano.currentNum] != 72 && myPiano.character[myPiano.currentNum] != 74
						&& myPiano.character[myPiano.currentNum] != 76 && myPiano.character[myPiano.currentNum] != 77
						&& myPiano.character[myPiano.currentNum] != 79 && myPiano.character[myPiano.currentNum] != 81
						&& myPiano.character[myPiano.currentNum] != 83 && myPiano.character[myPiano.currentNum] != 84
						&& myPiano.character[myPiano.currentNum] != 86 && myPiano.character[myPiano.currentNum] != 88) {
					myPiano.btn[28].setBackground(Color.YELLOW);
					myPiano.btn[myPiano.buttonCode[myPiano.currentNum]].setBackground(Color.YELLOW);
				} else {
					if (myPiano.character[myPiano.currentNum-1] != 48 && myPiano.character[myPiano.currentNum-1] != 50
							&& myPiano.character[myPiano.currentNum-1] != 52 && myPiano.character[myPiano.currentNum-1] != 53
							&& myPiano.character[myPiano.currentNum-1] != 55 && myPiano.character[myPiano.currentNum-1] != 57
							&& myPiano.character[myPiano.currentNum-1] != 59 && myPiano.character[myPiano.currentNum-1] != 60
							&& myPiano.character[myPiano.currentNum-1] != 62 && myPiano.character[myPiano.currentNum-1] != 64
							&& myPiano.character[myPiano.currentNum-1] != 65 && myPiano.character[myPiano.currentNum-1] != 67
							&& myPiano.character[myPiano.currentNum-1] != 69 && myPiano.character[myPiano.currentNum-1] != 71
							&& myPiano.character[myPiano.currentNum-1] != 72 && myPiano.character[myPiano.currentNum-1] != 74
							&& myPiano.character[myPiano.currentNum-1] != 76 && myPiano.character[myPiano.currentNum-1] != 77
							&& myPiano.character[myPiano.currentNum-1] != 79 && myPiano.character[myPiano.currentNum-1] != 81
							&& myPiano.character[myPiano.currentNum-1] != 83 && myPiano.character[myPiano.currentNum-1] != 84
							&& myPiano.character[myPiano.currentNum-1] != 86 && myPiano.character[myPiano.currentNum-1] != 88)
						myPiano.btn[28].setBackground(myPiano.myGay);
					
					myPiano.btn[myPiano.buttonCode[myPiano.currentNum]].setBackground(Color.YELLOW);
				}
				myPiano.btn[myPiano.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum])]
						.setBackground(myPiano.myGay);

			}
			myPiano.isTruePressed = false;
		} else{
			if (myPiano.transformKeyCodeToButtonCode(a) >= 0)
				myPiano.btn[myPiano.transformKeyCodeToButtonCode(a)].setBackground(Color.WHITE);
		}

		if (myPiano.transformKeyCodeToButtonCode(a) == 41)
			myPiano.btn[52].setBackground(Color.WHITE);
		if (myPiano.transformKeyCodeToButtonCode(a) == 53)
			myPiano.btn[57].setBackground(Color.WHITE);
		if (myPiano.transformKeyCodeToButtonCode(a) == 15)
			myPiano.btn[36].setBackground(Color.WHITE);
		if (myPiano.transformKeyCodeToButtonCode(a) == 16)
			myPiano.btn[37].setBackground(Color.WHITE);
		if (myPiano.transformKeyCodeToButtonCode(a) == 36)
			myPiano.btn[15].setBackground(Color.WHITE);
		if (myPiano.transformKeyCodeToButtonCode(a) == 37)
			myPiano.btn[16].setBackground(Color.WHITE);

		if (myPiano.transformKeyCodeToButtonCode(a) >= 15 && myPiano.transformKeyCodeToButtonCode(a) <= 24) {
			myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15].setIcon(null);
		} else if (myPiano.transformKeyCodeToButtonCode(a) >= 29 && myPiano.transformKeyCodeToButtonCode(a) <= 37) {
			myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6].setIcon(null);
		} else if (myPiano.transformKeyCodeToButtonCode(a) >= 42 && myPiano.transformKeyCodeToButtonCode(a) <= 48) {
			myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26].setIcon(null);
		} else if (myPiano.transformKeyCodeToButtonCode(a) >= 1 && myPiano.transformKeyCodeToButtonCode(a) <= 12)
			switch (myPiano.transformKeyCodeToButtonCode(a)) {
			case 1:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				break;
			case 2:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				myPiano.btnPianoWhite[28].setIcon(null);
				break;
			case 3:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoBlack[17].setBackground(Color.BLACK);
				myPiano.btnPianoWhite[27].setIcon(null);
				break;
			case 4:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoBlack[17].setBackground(Color.BLACK);
				myPiano.btnPianoWhite[27].setIcon(null);
				myPiano.btnPianoWhite[28].setIcon(null);
				break;
			case 5:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoBlack[17].setBackground(Color.BLACK);
				myPiano.btnPianoBlack[18].setBackground(Color.BLACK);
				break;
			case 6:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				myPiano.btnPianoWhite[29].setIcon(null);
				break;
			case 7:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoBlack[19].setBackground(Color.BLACK);
				break;
			case 8:
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				myPiano.btnPianoWhite[29].setIcon(null);
				myPiano.btnPianoWhite[31].setIcon(null);
				break;
			case 9:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[26].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				break;
			case 10:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				myPiano.btnPianoBlack[20].setBackground(Color.BLACK);
				break;
			case 11:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoWhite[24].setIcon(null);
				myPiano.btnPianoWhite[25].setIcon(null);
				myPiano.btnPianoWhite[27].setIcon(null);
				break;
			case 12:
				myPiano.btnPianoWhite[23].setIcon(null);
				myPiano.btnPianoBlack[17].setBackground(Color.BLACK);
				myPiano.btnPianoWhite[27].setIcon(null);
				myPiano.btnPianoBlack[20].setBackground(Color.BLACK);
				break;
			default:
				break;
			}

	}
}
