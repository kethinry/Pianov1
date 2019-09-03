package piano;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

//import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;
//import sun.security.util.Password;

import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.event.*;

class LoginFrame extends JFrame {
	MyPiano myPiano;
	JFrame f = new JFrame("Login");
	JLabel label = new JLabel();
	JPasswordField pf = new JPasswordField();
	JTextField txtName = new JTextField();
	JLabel l1 = new JLabel("�û���:");
	JLabel l2 = new JLabel("����:");
	JButton btnLogin = new JButton("��¼");
	JButton btnRegister = new JButton("ע��");
	JButton btnForget = new JButton("��������");
	

	public LoginFrame(MyPiano myPiano) {
		this.myPiano = myPiano;
		f.setVisible(true);
		f.setSize(300, 300);
		f.setResizable(false);
		label.setBounds(40, 150, 200, 50);
		pf.setBounds(100, 75, 150, 30);
		txtName.setBounds(100, 20, 150, 30);
		l1.setBounds(40, 20, 80, 30);
		l2.setBounds(40, 75, 80, 30);
		btnLogin.setBounds(20, 120, 80, 30);
		btnRegister.setBounds(100, 120, 80, 30);
		btnForget.setBounds(180, 120, 80, 30);
		
		f.add(pf);
		f.add(l1);
		f.add(label);
		f.add(l2);
		f.add(btnLogin);
		f.add(btnRegister);
		f.add(btnForget);
		f.add(txtName);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		
		FileInputStream fin = null;
		try {
			fin=new FileInputStream(".//data//username");
			FileReader fr=new FileReader(".//data//username");
			BufferedReader br=new BufferedReader(fr);
			String temp="";
			temp=br.readLine();
			txtName.setText(temp);
			temp=br.readLine();
			pf.setText(temp);
	        
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				fin.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}	
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String users = txtName.getText();
				String passwords = new String(pf.getPassword());
				String data = "�û���" + users + ",���룺" + passwords;
				boolean flag = true;// true��ʾ����Ĭ���û�������
				label.setText(data);
				String password = null;
				try {
					password = UserDeal.getUserByUserName(users).getPassword();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "��������û��������ڣ�");
					flag = false;// ˵���û���������
				}
				if (flag && password.equals(passwords))
					dispose();
				else if (flag)
					JOptionPane.showMessageDialog(null, "�������");

			}
		});
		btnRegister.addActionListener(new RegisterActionListener() {
			 
		});
		btnForget.addActionListener(new ForgetActionListener() {
			 
		});

	}

}
