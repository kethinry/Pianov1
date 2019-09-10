package piano;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.jfugue.realtime.RealtimePlayer;

class PianoPanel extends JPanel {
	MyPiano myPiano;
	RealtimePlayer player;
	JPanel mainPanel = new JPanel();
	
	int NewPianoCode[] = new int[88];	//NewPianoCode+21恰好是Character

	public int transformWhitePianoCodeToNewPianoCode(int num) {
		for (int i = 0; i < 88; i++)
			if (num == NewPianoCode[i])
				return i;
		return -1;
	}

	public int transformBlackPianoCodeToNewPianoCode(int num) {
		for (int i = 0; i < 88; i++)
			if (num + 52 == NewPianoCode[i])
				return i;
		return -1;
	}
	public static int transformCharaterToWhitePianoCode(int charater){
		if(charater<=21) return -1;
		int octave=charater%12;
		int quotient=charater/12;
		int i=0;
		switch(octave){
			case 0: i=0;break;
			case 1: return -1;
			case 2: i=1;break;
			case 3: return -1;
			case 4: i=3;break;
			case 5: i=4;break;
			case 6: return -1;
			case 7: i=5;break;
			case 8: return -1;
			case 9: i=6;break;
			case 10: return -1;
			case 11: i=7;break;
			default:return -1;
		}
		
		return (quotient-2)*7+2+i;
	}

	public PianoPanel(MyPiano myPiano) throws MidiUnavailableException {
		this.myPiano=myPiano;
		player = new RealtimePlayer();
		add(mainPanel);
		mainPanel.setBounds(65, 20, 867, 100);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);
		
		myPiano.btnPianoWhite = new JButton[52];
		myPiano.btnPianoBlack = new JButton[37];
		
		setLayout(null);
		NewPianoCode[0] = 0;
		NewPianoCode[1] = 52;
		NewPianoCode[2] = 1;
		for (int i = 0; i < 7; i++) {
			NewPianoCode[i * 12 + 3] = 2 + 7 * i;
			NewPianoCode[i * 12 + 4] = 53 + 5 * i;
			NewPianoCode[i * 12 + 5] = 3 + 7 * i;
			NewPianoCode[i * 12 + 6] = 54 + 5 * i;
			NewPianoCode[i * 12 + 7] = 4 + 7 * i;
			NewPianoCode[i * 12 + 8] = 5 + 7 * i;
			NewPianoCode[i * 12 + 9] = 55 + 5 * i;
			NewPianoCode[i * 12 + 10] = 6 + 7 * i;
			NewPianoCode[i * 12 + 11] = 56 + 5 * i;
			NewPianoCode[i * 12 + 12] = 7 + 7 * i;
			NewPianoCode[i * 12 + 13] = 57 + 5 * i;
			NewPianoCode[i * 12 + 14] = 8 + 7 * i;

		}
		NewPianoCode[87] = 51;

		for (int i = 0; i < 37; i++) {
			myPiano.btnPianoBlack[i] = new JButton();
			myPiano.btnPianoBlack[i].setBackground(Color.BLACK);
			mainPanel.add(myPiano.btnPianoBlack[i], new JLayeredPane(), 0);
			myPiano.btnPianoBlack[i].addMouseListener(new BlackMouseListener(i));
		}
		myPiano.btnPianoBlack[0].setBounds(11, 0, 12, 50);
		for (int i = 0; i < 7; i++) {// 添加黑键
			myPiano.btnPianoBlack[5 * i + 1].setBounds(45 + 119 * i, 0, 12, 50);
			myPiano.btnPianoBlack[5 * i + 2].setBounds(45 + 119 * i + 17, 0, 12, 50);
			myPiano.btnPianoBlack[5 * i + 3].setBounds(45 + 119 * i + 50, 0, 12, 50);
			myPiano.btnPianoBlack[5 * i + 4].setBounds(45 + 119 * i + 67, 0, 12, 50);
			myPiano.btnPianoBlack[5 * i + 5].setBounds(45 + 119 * i + 84, 0, 12, 50);
		}

		// btnPianoBlack[36].setBounds(844, 0, 12, 50);

		for (int i = 0; i < 52; i++) {// 添加白键
			myPiano.btnPianoWhite[i] = new JButton();
			mainPanel.add(myPiano.btnPianoWhite[i], new JLayeredPane(), -1);
			myPiano.btnPianoWhite[i].setOpaque(false);
			myPiano.btnPianoWhite[i].setBounds(17 * i, 0, 17, 100);
			myPiano.btnPianoWhite[i].setBackground(Color.WHITE);
			myPiano.btnPianoWhite[i].addMouseListener(new WhiteMouseListener(i));
		}

	}

	class WhiteMouseListener implements MouseListener {
		int i;
		public WhiteMouseListener(int i) {
			this.i = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int character = transformWhitePianoCodeToNewPianoCode(i) + 21;
			player.play("V2 I0 " + String.valueOf(character) + "q");
			myPiano.txtOutput.setText(myPiano.txtOutput.getText() + "V2 I0 " + String.valueOf(character) + "q");
		}// 钢琴白键的点击事件

		@Override
		public void mousePressed(MouseEvent e) {
			myPiano.settingwhitekey(i);
			int character = transformWhitePianoCodeToNewPianoCode(i) + 21;
			KeyProperty key = myPiano.km.findByCharacter(character);
			if(key!=null) {
				int btnCode = key.getIndex();

				if (!myPiano.isColorful)
					myPiano.btn[btnCode].setBackground(Color.BLACK);
				else {
					if (myPiano.colorNum >= 6)
						myPiano.colorNum = 0;
					if (btnCode >= 0) {
						myPiano.btn[btnCode].setBackground(myPiano.myColor[myPiano.colorNum]);
						myPiano.colorNum++;
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int character = transformWhitePianoCodeToNewPianoCode(i) + 21;
			myPiano.btnPianoWhite[i].setIcon(null);
			KeyProperty key = myPiano.km.findByCharacter(character);
			if(key!=null){
				int btnCode = key.getIndex();
				if (btnCode >= 0) myPiano.btn[btnCode].setBackground(Color.WHITE);
			}
			// TODO Auto-generated method stub
            
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class BlackMouseListener implements MouseListener {
		int i;

		public BlackMouseListener(int i) {
			this.i = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int character = transformBlackPianoCodeToNewPianoCode(i) + 21;
			player.play("V2 I0 " + String.valueOf(character) + "q");
			myPiano.txtOutput.setText(myPiano.txtOutput.getText() + "V2 I0 " + String.valueOf(character) + "q ");
		}// 钢琴白键的点击事件

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
