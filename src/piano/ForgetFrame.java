package piano;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;
//import sun.security.util.Password;

import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.*;
import java.awt.event.*;

class ForgetFrame extends JFrame {
	MyPiano myPiano;
	JFrame f = new JFrame("Forget");
	JLabel label = new JLabel();
	JPasswordField value = new JPasswordField();
	JLabel mail = new JLabel("邮箱：");
	JLabel newPassword = new JLabel("新密码：");
	JPanel pConfirmOrCancel = new JPanel();
	JButton btnConfirm = new JButton("确定");
	JButton btnCancel = new JButton("取消");
	JTextField txtMail = new JTextField();

	public ForgetFrame(MyPiano myPiano) {
		this.myPiano = myPiano;
		f.setVisible(true);
		f.setSize(300, 300);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.setResizable(false);
		f.add(value);
		f.add(mail);
		f.add(label);
		f.add(newPassword);
		f.add(txtMail);
		f.add(btnConfirm);
		f.add(btnCancel);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		label.setBounds(40, 150, 200, 30);
		value.setBounds(100, 75, 150, 30);
		mail.setBounds(40, 20, 80, 30);
		newPassword.setBounds(40, 75, 100, 30);
		txtMail.setBounds(100, 20, 150, 30);
		//pConfirmOrCancel.setBounds(0,120,300,50);
		//f.add(pConfirmOrCancel);
		
		btnConfirm.setBounds(50, 140, 80, 30);
		btnCancel.setBounds(150, 140, 80, 30);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

	}

}
