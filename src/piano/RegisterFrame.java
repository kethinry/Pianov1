package piano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import sun.security.util.Password;

public class RegisterFrame extends JFrame {
	MyPiano myPiano;
	JFrame f = new JFrame("Register");
	JPanel panel=new JPanel();
	JPasswordField pfPassword1 = new JPasswordField(20);
	JPasswordField pfPassword2 = new JPasswordField(20);
	JLabel lblUsername = new JLabel("  用户名:");
	JLabel lblPassword1 = new JLabel("    密码:");
	JLabel lblPassword2 = new JLabel("确认密码:");
    JLabel lblBirthday = new JLabel("出生日期");

	JLabel lblUserPhone = new JLabel("电话:");
	JTextField textUserPhone = new JTextField(20);
	JTextField textUsername = new JTextField(20);
	JLabel lblUserEmail = new JLabel("邮箱:");
	JTextField textUserEmail = new JTextField(20);
	//JLabelDayChooser = new JLabel("");
	JButton btnFinish = new JButton("点击注册");
	JButton btnCancel=new JButton(" 取消 ");
  
	BirthDayChooser birthDayChooser=new BirthDayChooser(); 
    
	public RegisterFrame(MyPiano myPiano) {
		this.myPiano = myPiano;
		f.setSize(350, 350);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(null);
		f.add(panel);
		panel.setSize(350, 350);
		panel.setLayout(null);
		
		panel.add(lblUsername);
		panel.add(textUsername);
		panel.add(lblPassword1);
		panel.add(pfPassword1);
		panel.add(lblPassword2);
		panel.add(pfPassword2);
		panel.add(lblUserPhone);
		panel.add(textUserPhone);
		panel.add(lblUserEmail);
		panel.add(textUserEmail);
		panel.add(btnFinish);
		panel.add(btnCancel);
		panel.add(lblBirthday);
		panel.add(birthDayChooser);
		lblUsername.setBounds(20, 20, 60, 20);
		textUsername.setBounds(100, 20, 200, 20);
		lblPassword1.setBounds(20, 50, 60, 20);
		pfPassword1.setBounds(100, 50, 200, 20);
		lblPassword2.setBounds(20, 100, 60, 20);
		pfPassword2.setBounds(100, 100, 200, 20);
		lblUserPhone.setBounds(20, 130, 60, 20);
		textUserPhone.setBounds(100, 130, 200, 20);
		lblUserEmail.setBounds(20, 180, 60, 20);
		textUserEmail.setBounds(100, 180, 200, 20);
		lblBirthday.setBounds(20, 220, 60, 20);
		birthDayChooser.setBounds(100, 220, 120, 30);
		btnFinish.setBounds(80, 260, 80, 30);
		btnCancel.setBounds(200, 260, 80, 30);

		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String userpassword =  new String(pfPassword1.getPassword());
				String birthDayString = birthDayChooser.getBirthDay();
			    String[] temp =null;
			    temp=birthDayString.split("-");
			    String userBirthYear=temp[0];
			    String userBirthMonth=temp[1];
			    String userBirthDay = temp[2];
                String userPhone = textUserPhone.getText();
                String userEmail = textUserEmail.getText();
                boolean flag = true;//注册成功
                User user1 = new User(username,userpassword,userBirthYear,userBirthMonth,userBirthDay,userPhone,userEmail);
                try {
					UserDeal.insert(user1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					flag = false;//注册失败
					JOptionPane.showMessageDialog(null, "注册失败！");
					e1.printStackTrace();
				}
                if(flag)
                	dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});
	}

}
