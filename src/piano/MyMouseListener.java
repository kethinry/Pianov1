package piano;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

class MyMouseListener implements MouseListener {
	int i,a;
	MyPiano myPiano;
	public MyMouseListener(int i,MyPiano myPiano) {
		this.i = i;
		this.myPiano=myPiano;
	}

	public void mouseClicked(MouseEvent m) {
		if (!myPiano.isColorful)
			myPiano.btn[i].setBackground(Color.BLACK);
		else {
			if (myPiano.colorNum >= 6)
				myPiano.colorNum = 0;
			myPiano.btn[i].setBackground(myPiano.myColor[myPiano.colorNum]);
			myPiano.colorNum++;
		}
		int keyCode = myPiano.transformButtonCodeToKeyCode(i);
		if (keyCode >= 97 && keyCode <= 122) {
			a=myPiano.transformButtonCodeToKeyCode(i) - 32;
			myPiano.player.play(myPiano.getString(myPiano.transformButtonCodeToKeyCode(i) - 32));
		}
		if (keyCode >= 48 && keyCode <= 58) {
			a=myPiano.transformButtonCodeToKeyCode(i);
			myPiano.player.play(myPiano.getString(myPiano.transformButtonCodeToKeyCode(i)));
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
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		myPiano.btn[i].setBackground(Color.WHITE);
		if (myPiano.transformKeyCodeToButtonCode(a) >= 15 && myPiano.transformKeyCodeToButtonCode(a) <= 24) {
			myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) + 15].setIcon(null);
		} 
		else if (myPiano.transformKeyCodeToButtonCode(a) >= 29 && myPiano.transformKeyCodeToButtonCode(a) <= 37) {
		     myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 6].setIcon(null);
			}
		else if (myPiano.transformKeyCodeToButtonCode(a) >= 42 && myPiano.transformKeyCodeToButtonCode(a) <= 48) {
			myPiano.btnPianoWhite[myPiano.transformKeyCodeToButtonCode(a) - 26].setIcon(null);
		}
		else if(myPiano.transformKeyCodeToButtonCode(a)>=1&&myPiano.transformKeyCodeToButtonCode(a)<=12)
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
				myPiano.btnPianoBlack[17].setBackground(Color.BLACK );
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

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

