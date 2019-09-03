package piano;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HelpFrame {
	MyPiano myPiano;
	JFrame h = new JFrame("help");
	TextField Help = new TextField(10);
	JTextArea txaDisplay = new JTextArea(100, 80);
	JPanel hText = new JPanel();
	JPanel hConfirmOrCancel = new JPanel();
	JButton btnKnow = new JButton("我已了解");
	JButton btnMisunderstand = new JButton("还有疑惑");

	public HelpFrame(MyPiano myPiano) {
		this.myPiano=myPiano;
		h.setVisible(true);
		hText.setVisible(true);
		h.setSize(400, 400);
		h.setTitle("帮助");
		h.add(hText, BorderLayout.CENTER);
		h.add(hConfirmOrCancel, BorderLayout.SOUTH);
		hText.add(txaDisplay);
		//txaDisplay.setText("");
		//Help.setText(" dhsghkdjlxlcfytrugchjdcnvnbfvruyfiruyfuiefhjkd");
		hConfirmOrCancel.add(btnKnow);
		hConfirmOrCancel.add(btnMisunderstand);
		btnKnow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				h.dispose();
			}
		});
		btnMisunderstand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String question;
				question = JOptionPane.showInputDialog(null, "请写下你的问题：\n", "问题", JOptionPane.PLAIN_MESSAGE);
				h.dispose();
			}
		});
	}
}