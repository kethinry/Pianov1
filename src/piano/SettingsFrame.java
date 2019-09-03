
package piano;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class SettingsFrame extends JFrame {
	MyPiano myPiano;
	JFrame f = new JFrame("test");
	JPanel pSetKeyStyle = new JPanel();
	JPanel pSetKeyType = new JPanel();
	JPanel pConfirmOrCancel = new JPanel();
	JButton btnConfirm = new JButton("ȷ��");
	JButton btnCancel = new JButton("ȡ��");
	JLabel lblSetKeyStyle = new JLabel("����ģʽ");
	JLabel lblSetKeyType = new JLabel("��������");
	ButtonGroup groupKeyStyle = new ButtonGroup();
	JRadioButton colorKey = new JRadioButton("��ʼ���");
	JRadioButton tranditionKey = new JRadioButton("��ͳ����");
	JRadioButton numberKey = new JRadioButton("�����ּ���");
	JRadioButton nonenumberKey = new JRadioButton("�������ּ���");
	ButtonGroup groupKeyType = new ButtonGroup();

	public SettingsFrame(MyPiano myPiano) {
		this.myPiano=myPiano;
		f.setVisible(true);
		f.setSize(400, 400);
		f.setTitle("����");
		f.setLayout(new GridLayout(3, 2));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.add(pSetKeyStyle);
		f.add(pSetKeyType);
		f.add(pConfirmOrCancel);
		pSetKeyStyle.add(lblSetKeyStyle);
		pSetKeyStyle.add(colorKey);
		pSetKeyStyle.add(tranditionKey);
		groupKeyStyle.add(colorKey);
		groupKeyStyle.add(tranditionKey);
		pSetKeyType.add(lblSetKeyType);
		pSetKeyType.add(numberKey);
		pSetKeyType.add(nonenumberKey);
		groupKeyType.add(numberKey);
		groupKeyType.add(nonenumberKey);
		if (myPiano.isColorful) {
			colorKey.setSelected(true);
			tranditionKey.setSelected(false);
		} else {
			colorKey.setSelected(false);
			tranditionKey.setSelected(true);
		}
		if (myPiano.isBothPlay) {
			numberKey.setSelected(true);
			nonenumberKey.setSelected(false);
		} else {
			numberKey.setSelected(false);
			nonenumberKey.setSelected(true);
		}
		pConfirmOrCancel.add(btnConfirm);
		pConfirmOrCancel.add(btnCancel);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPiano.isColorful = colorKey.isSelected();
				myPiano.isBothPlay = numberKey.isSelected();
				f.dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
	}
}
