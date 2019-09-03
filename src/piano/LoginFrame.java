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
	JLabel l1 = new JLabel("用户名:");
	JLabel l2 = new JLabel("密码:");
	JButton btnLogin = new JButton("登录");
	JButton btnRegister = new JButton("注册");
	JButton btnForget = new JButton("忘记密码");
	

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
				String data = "用户：" + users + ",密码：" + passwords;
				boolean flag = true;// true表示首先默认用户名存在
				label.setText(data);
				String password = null;
				try {
					password = UserDeal.getUserByUserName(users).getPassword();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "您输入的用户名不存在！");
					flag = false;// 说明用户名不存在
				}
				if (flag && password.equals(passwords))
					dispose();
				else if (flag)
					JOptionPane.showMessageDialog(null, "密码错误！");

			}
		});
		btnRegister.addActionListener(new RegisterActionListener() {
			 
		});
		btnForget.addActionListener(new ForgetActionListener() {
			 
		});

	}

}
