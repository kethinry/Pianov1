package piano;

import javax.swing.JPanel;

class MyPanel extends JPanel {
	MyPiano myPiano;
	public MyPanel(MyPiano myPiano) {
		this.myPiano=myPiano;
		setSize(400, 400);
		setLocation(300, 300);
		setVisible(true);
		setLayout(null);
		for (int i = 0; i <= 57; i++) {
			add(myPiano.btn[i]);
		}

		for (int i = 0; i < 13; i++) {
			myPiano.btn[i].setBounds(100 + 55 * i, 0, 50, 50);
		}
		myPiano.btn[13].setBounds(815, 0, 100, 50);
		myPiano.btn[14].setBounds(100, 60, 75, 50);
		for (int i = 0; i < 12; i++) {
			myPiano.btn[i + 15].setBounds(180 + 55 * i, 60, 50, 50);
		}
		myPiano.btn[27].setBounds(840, 60, 75, 50);
		myPiano.btn[28].setBounds(100, 120, 85, 50);
		for (int i = 0; i < 11; i++) {
			myPiano.btn[i + 29].setBounds(190 + 55 * i, 120, 50, 50);
		}
		myPiano.btn[40].setBounds(795, 120, 120, 50);
		myPiano.btn[41].setBounds(100, 180, 120, 50);
		for (int i = 0; i < 10; i++) {
			myPiano.btn[i + 42].setBounds(225 + 55 * i, 180, 50, 50);
		}
		myPiano.btn[52].setBounds(775, 180, 140, 50);
		myPiano.btn[53].setBounds(100, 240, 120, 50);
		myPiano.btn[54].setBounds(225, 240, 100, 50);
		myPiano.btn[55].setBounds(330, 240, 335, 50);
		myPiano.btn[56].setBounds(670, 240, 100, 50);
		myPiano.btn[57].setBounds(775, 240, 140, 50);

		for (int i = 1; i <= 9; i++)
			myPiano.btn[i].setText(String.valueOf(i));
		for (int i = 14; i <= 53; i++) {
			myPiano.btn[i].setText(String.valueOf((char) (myPiano.transformButtonCodeToKeyCode(i) - 32)));
		}
		myPiano.btn[0].setText("~");
		myPiano.btn[10].setText("0");
		myPiano.btn[11].setText("-");
		myPiano.btn[12].setText("+");
		myPiano.btn[13].setText("Backspace");
		myPiano.btn[14].setText("Tab");
		myPiano.btn[25].setText("[");
		myPiano.btn[26].setText("]");
		myPiano.btn[27].setText("\\");
		myPiano.btn[28].setText("Cap");
		myPiano.btn[38].setText("£»");
		myPiano.btn[39].setText("¡®");
		myPiano.btn[40].setText("Enter");
		myPiano.btn[41].setText("Shift");
		myPiano.btn[49].setText("£¬");
		myPiano.btn[50].setText(".");
		myPiano.btn[51].setText("/");
		myPiano.btn[52].setText("Shift");
		myPiano.btn[53].setText("Ctrl");
		myPiano.btn[54].setText("Alt");
		myPiano.btn[55].setText(" ");
		myPiano.btn[56].setText("Alt");
		myPiano.btn[57].setText("Ctrl");
		for (int i = 0; i <= 57; i++) {
			myPiano.btn[i].addMouseListener(new MyMouseListener(i,myPiano));
		}
	}
}
