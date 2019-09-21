

package piano;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jfugue.*;

//import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;

public class MyKeyListener implements KeyListener {
	MyPiano myPiano;
	int noteChannel[];//按下键盘时存储每个音的音轨
	public MyKeyListener(MyPiano myPiano) {
		this.myPiano = myPiano;
		noteChannel = new int[129];
		for(int i=0;i<=128;i++)noteChannel[i]=-1;//所有按钮都 被释放
	}

	public void keyPressed(KeyEvent arg0) {
		int a = arg0.getKeyCode();
		boolean isCtrol = arg0.isControlDown();
		if(a>128)return;

		KeyProperty key = myPiano.km.findByCode(a);
		int userCharacter;
		if(isCtrol)
			userCharacter = key.getRisecharacter();
		else userCharacter=key.getCharacter();
		int userButton = key.getIndex();
		
		if(noteChannel[a] < 0){
			noteChannel[a] = myPiano.getChannel(myPiano.trans.transformInstrument(myPiano.jbxSetInstrument.getSelectedIndex()));
			long t = System.currentTimeMillis() - myPiano.beginTime;//当前时间减去按下开始演奏的时间
			if (userCharacter > 0) {
				myPiano.timeString = myPiano.timeString + String.valueOf(t) + " ";
				
			}
			
			switch (myPiano.mode) {  //不同模式，按下键盘的色彩不同
			case 0://自由演奏
			case 2://乐谱记录
				myPiano.streamingPlayer.stream(myPiano.createString.getStreamString(true,a,noteChannel[a],isCtrol));
				myPiano.settingkey.setkeycolor(userButton,1);
				break;

			case 1://教学模式
				if (myPiano.currentNum < myPiano.numOfNote) {
					if (userCharacter == myPiano.character[myPiano.currentNum]) { // 输入正确
						myPiano.streamingPlayer.stream(myPiano.createString.getStreamString(true,a,noteChannel[a],isCtrol));
						//myPiano.player.play(myPiano.getString(a));
						myPiano.settingkey.setkeycolor(userButton,2);
						/*
						if (myPiano.currentNum == myPiano.numOfNote - 1
								|| myPiano.trans.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum]) != myPiano.
										trans.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum + 1])) // 最后一个音符
																													// 或者当前的音符不等于下一个音符，那么当前音符的duration对应的btn变白
							myPiano.btn[myPiano.trans.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum])]
									.setBackground(Color.WHITE);
						//就是上面这个if没懂
						*/
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
							myPiano.settingkey.setkeycolor(userButton,3);
						} else {
							myPiano.settingkey.setkeycolor(userButton,4);
						}
						myPiano.isTruePressed = false;
					}

				} else {
					myPiano.refresh();
					JOptionPane.showMessageDialog(null, "歌谱已结束！");

				}
				break;
			}
			int whitecode = key.getWhitecode();
			myPiano.settingkey.settingwhitekey(whitecode);
			if (userButton == 55) {
				myPiano.jbxSetInstrument.setSelectedIndex((myPiano.jbxSetInstrument.getSelectedIndex() + 1) % 14);
				myPiano.instrument = "I"
						+ String.valueOf(myPiano.trans.transformInstrument(myPiano.jbxSetInstrument.getSelectedIndex()));
			}

		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent e) {
		int a = e.getKeyCode();
		boolean isControl = e.isControlDown();
		if (a > 128) return;
		if(myPiano.mode==0||myPiano.mode==2)
			myPiano.streamingPlayer.stream(myPiano.createString.getStreamString(false,a,noteChannel[a],isControl));
		KeyProperty key = myPiano.km.findByCode(a);
		int userCharacter ;
		if(isControl)
			userCharacter = key.getRisecharacter();
		else
		 	userCharacter = key.getCharacter();
		int userButton = key.getIndex();
		int whitecode = key.getWhitecode();

		if (myPiano.mode == 1) {
			if (myPiano.currentNum < myPiano.numOfNote && userCharacter == myPiano.character[myPiano.currentNum]) {
				myPiano.streamingPlayer.stream(myPiano.createString.getStreamString(false,a,noteChannel[a],isControl));
				myPiano.currentNum++;
				myPiano.isTruePressed = true;
			}
			if (myPiano.currentNum < myPiano.numOfNote && myPiano.character[myPiano.currentNum] != 48 && myPiano.character[myPiano.currentNum] != 50
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
				if (myPiano.isUpperLetter() || (!myPiano.isUpperLetter() && a != myPiano.keyCode[myPiano.currentNum]))
					if (userButton >= 0) {
						myPiano.btn[userButton].setBackground(Color.WHITE);
					}
			} else if (myPiano.currentNum < myPiano.numOfNote && !myPiano.isUpperLetter() && (myPiano.character[myPiano.currentNum] == 48 || myPiano.character[myPiano.currentNum] == 50
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
					|| myPiano.character[myPiano.currentNum] == 86 || myPiano.character[myPiano.currentNum] == 88)) {
				myPiano.btn[userButton].setBackground(Color.WHITE);

			} else if (myPiano.isUpperLetter() && !myPiano.isTruePressed)
				;
			else if (myPiano.isUpperLetter() && myPiano.isTruePressed)
				myPiano.btn[userButton].setBackground(Color.WHITE);
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
					if (myPiano.currentNum < myPiano.numOfNote - 1) {  // 后面还有音 那么下一个音变色
						//System.out.println(myPiano.currentNum);
						//System.out.println(myPiano.numOfNote);
						int nextCharacter = myPiano.character[myPiano.currentNum+1];
						if (nextCharacter != myPiano.character[myPiano.currentNum]) {
							KeyProperty nextKey = myPiano.km.findByCharacter(nextCharacter);
							if(nextKey.getIndex()== -1){
								nextKey = myPiano.km.findByRiseCharacter(nextCharacter);
								//isControl = true;
							}
							if(nextKey.getIndex()!= -1) {
								int nextButton = nextKey.getIndex();
								myPiano.btn[nextButton].setBackground(myPiano.myGay);
							}	
						}
					}
				} else {
					/*
					if (myPiano.character[myPiano.currentNum - 1] != 48 && myPiano.character[myPiano.currentNum - 1] != 50
							&& myPiano.character[myPiano.currentNum - 1] != 52 && myPiano.character[myPiano.currentNum - 1] != 53
							&& myPiano.character[myPiano.currentNum - 1] != 55 && myPiano.character[myPiano.currentNum - 1] != 57
							&& myPiano.character[myPiano.currentNum - 1] != 59 && myPiano.character[myPiano.currentNum - 1] != 60
							&& myPiano.character[myPiano.currentNum - 1] != 62 && myPiano.character[myPiano.currentNum - 1] != 64
							&& myPiano.character[myPiano.currentNum - 1] != 65 && myPiano.character[myPiano.currentNum - 1] != 67
							&& myPiano.character[myPiano.currentNum - 1] != 69 && myPiano.character[myPiano.currentNum - 1] != 71
							&& myPiano.character[myPiano.currentNum - 1] != 72 && myPiano.character[myPiano.currentNum - 1] != 74
							&& myPiano.character[myPiano.currentNum - 1] != 76 && myPiano.character[myPiano.currentNum - 1] != 77
							&& myPiano.character[myPiano.currentNum - 1] != 79 && myPiano.character[myPiano.currentNum - 1] != 81
							&& myPiano.character[myPiano.currentNum - 1] != 83 && myPiano.character[myPiano.currentNum - 1] != 84
							&& myPiano.character[myPiano.currentNum - 1] != 86 && myPiano.character[myPiano.currentNum - 1] != 88)
						myPiano.btn[28].setBackground(myPiano.myGay);
					*/
					myPiano.btn[myPiano.buttonCode[myPiano.currentNum]].setBackground(Color.YELLOW);
					if (myPiano.currentNum < myPiano.numOfNote - 1) {  // 后面还有音 那么下一个音变色
						//System.out.println(myPiano.currentNum);
						//System.out.println(myPiano.numOfNote);
						int nextCharacter = myPiano.character[myPiano.currentNum+1];
						if (nextCharacter != myPiano.character[myPiano.currentNum]) {
							KeyProperty nextKey = myPiano.km.findByCharacter(nextCharacter);
							if(nextKey.getIndex()== -1){
								nextKey = myPiano.km.findByRiseCharacter(nextCharacter);
								//isControl = true;
							}
							if(nextKey.getIndex()!= -1) {
								int nextButton = nextKey.getIndex();
								myPiano.btn[nextButton].setBackground(myPiano.myGay);
							}	
						}
					}
				}
				//myPiano.btn[myPiano.trans.transformDurationToButtonCode(myPiano.durations[myPiano.currentNum])]
				//		.setBackground(myPiano.myGay);

			}
			myPiano.isTruePressed = false;
		} else {
			if (userButton >= 0)
				myPiano.btn[userButton].setBackground(Color.WHITE);
		}

		if (userButton == 41)
			myPiano.btn[52].setBackground(Color.WHITE);
		if (userButton == 53)
			myPiano.btn[57].setBackground(Color.WHITE);
		if (userButton == 15)
			myPiano.btn[36].setBackground(Color.WHITE);
		if (userButton == 16)
			myPiano.btn[37].setBackground(Color.WHITE);
		if (userButton == 36)
			myPiano.btn[15].setBackground(Color.WHITE);
		if (userButton == 37)
			myPiano.btn[16].setBackground(Color.WHITE);

		if(whitecode<52&&whitecode>=0)
		myPiano.btnPianoWhite[whitecode].setIcon(null);
		noteChannel[a] = -1;
		
	}
	
}
