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
		KeyProperty key = myPiano.km.findByIndex(i);
		int keyCode = key.getKeycode();
		if (keyCode >= 97 && keyCode <= 122) {
			a = keyCode - 32;
			myPiano. player.play(myPiano.getString(a));
		}
		if (keyCode >= 48 && keyCode <= 58) {
			a = keyCode;
			myPiano.player.play(myPiano.getString(a));
		}
		int whitecode = key.getWhitecode();
		myPiano.settingwhitekey(whitecode);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		myPiano.btn[i].setBackground(Color.WHITE);
		if(i<=58&&i>=0) {
			KeyProperty key = myPiano.km.findByIndex(i);
			if (key.getWhitecode() >= 0&&key.getWhitecode()<52) {
				//System.out.println('%'+key.getWhitecode()+"sha");
				myPiano.btnPianoWhite[key.getWhitecode()].setIcon(null);
			}
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

