package piano;

//������Ǳ����ʱ��򿪵ĶԻ�������Ҫ��д���⡢ʱ�䡢���ߵ���Ϣ��������һ�����水ť�������򿪱��������ļ��Ի���.
//����ļ��˵��еı�����������ڣ�¼��ģʽ��ʱ��������¼��Ҳ����������
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

class SaveCreateFrame extends JFrame {
	MyPiano myPiano;
	Music music;
	JFileChooser chooser;
	JButton btnSave;
	JTextField txtTitle = new JTextField(10);
	JTextField txtDate = new JTextField(10);
	JTextField txtAuthor = new JTextField(10);
	JTextArea txtNote = new JTextArea(50, 50);

	public String getTitle() {
		return "TextEditorDemo";
	}

	public SaveCreateFrame(MyPiano myPiano,Music music) throws HeadlessException {// ���캯��
		this.myPiano = myPiano;
		setVisible(true);// ���ÿɼ�
		setSize(600, 600);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		JTextArea textArea = new JTextArea();
		JPanel buttonsPane = new JPanel();
		container.setLayout(new GridLayout(5, 1));
		container.add(new JLabel("����:", JLabel.CENTER), null);
		container.add(txtTitle);
		container.add(new JLabel("ʱ��:", JLabel.CENTER), null);
		container.add(txtDate);
		container.add(new JLabel("����:", JLabel.CENTER), null);
		container.add(txtAuthor);
		pack();

		btnSave = new JButton("����");
		buttonsPane.add(btnSave);// ��ӱ��水ť
		container.add(buttonsPane, BorderLayout.NORTH);// �Ѱ�ť���������������
		pack();// �ô��ڴ�С��Ӧ����Ĵ�С
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				music.saveAsFile();
			}
		});
	}
}
