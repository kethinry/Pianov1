package piano;

//这个类是保存的时候打开的对话框，里面要填写标题、时间、作者等信息，下面有一个保存按钮，点击后打开保存的浏览文件对话框.
//点击文件菜单中的保存会打开这个窗口，录制模式的时候点击结束录制也会打开这个窗口
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

class SaveFrame extends JFrame {
	MyPiano myPiano;
	JFileChooser chooser;
	JButton btnSave;
	JTextField txtTitle = new JTextField(10);
	JTextField txtDate = new JTextField(10);
	JTextField txtAuthor = new JTextField(10);
	JTextArea txtNote = new JTextArea(50,50);

	public String getTitle() {
		return "TextEditorDemo";
	}

	public SaveFrame(MyPiano myPiano) throws HeadlessException {// 构造函数
		this.myPiano = myPiano;
		setVisible(true);// 设置可见
		setSize(600, 600);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		JTextArea textArea = new JTextArea();
		JPanel buttonsPane = new JPanel();
		container.setLayout(new GridLayout(5, 1));
		container.add(new JLabel("标题:", JLabel.CENTER), null);
		container.add(txtTitle);
		container.add(new JLabel("时间:", JLabel.CENTER), null);
		container.add(txtDate);
		container.add(new JLabel("作者:", JLabel.CENTER), null);
		container.add(txtAuthor);
		pack();

		btnSave = new JButton("保存");
		buttonsPane.add(btnSave);// 添加保存按钮
		container.add(buttonsPane, BorderLayout.NORTH);// 把按钮画布添加在最上面
		pack();// 让窗口大小适应组件的大小
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(".");
				Writer writer = null;
				int ret = chooser.showSaveDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {// 判断用户选择的是确定、取消等按钮
					dispose();
					try {
						writer = new FileWriter(chooser.getSelectedFile().getPath());
						writer.write("#\r\n");
						writer.write("#title:" + txtTitle.getText() + "\r\n");
						writer.write("#date:" + txtDate.getText() + "\r\n");
						writer.write("#author:" + txtAuthor.getText() + "\r\n");
						writer.write("#"+myPiano.txtOutput.getText()+"\r\n");
						writer.write("#"+myPiano.timeString+"\r\n");
						writer.write("#" + myPiano.numOfNote + "\r\n");
						writer.write("#");
					
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						if (writer != null)
							try {
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					}
				}
			}
		});
	}
}
