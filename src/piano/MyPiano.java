
package piano;

//http://jszx-jxpt.cuit.edu.cn/JavaAPI/javax/sound/midi/MidiChannel.html
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.print.attribute.standard.JobName;
import javax.security.auth.Refreshable;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import org.jfugue.*;
import org.jfugue.StreamingPlayer;

import com.jgoodies.looks.Fonts;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
//import com.mysql.jdbc.UpdatableResultSet;



public class MyPiano extends JFrame implements WindowListener, ActionListener {
	private static final long serialVersionUID = -1907889725234272213L;
	public static final int VoiceRangeLow = 48;
	public static final int VoiceRangeHigh = 95;
	public JButton[] btn;
	JButton btnPianoWhite[];
	JButton btnPianoBlack[];
	Player player;
	StreamingPlayer streamingPlayer ;
	JFrame f = new JFrame("���������ɶ�");
	JLabel background;
	JFrame fAdvancedSettings = new JFrame("�߼�����");
	JPanel pUp = new JPanel();
	JPanel pLyrics = new JPanel();
	JPanel pMenu = new JPanel();
	JPanel pControl = new JPanel();
	JPanel pPiano = new JPanel();
	MyPanel pKey;
	WuXianPu lblWuXianPu;
	JLabel lblOutput = new JLabel("����");
	JLabel lblSetInstrument = new JLabel("������ɫ");
	JComboBox jbxSetInstrument = new JComboBox();
	JLabel lblSetTone = new JLabel("��������");;
	JComboBox jbxSetTone = new JComboBox();// ����ʹ�������˵�ʵ��
	JLabel lblSetDuration = new JLabel("��������");
	JComboBox jbxSetDuration = new JComboBox();
	JTextField txtOutput = new JTextField(50);
	
	JButton btnStatistic = new JButton();
	JButton btnStart = new JButton("��ʼ����");
	JButton plus = new JButton(" + ");
	JButton minor = new JButton(" - ");
	//JSlider jsPressForce = new JSlider();
	//JLabel lblvalue = new JLabel("����");
	JMenuBar mBar = new JMenuBar();
	JMenu mFile = new JMenu(" �ļ� ");
	JMenu mMode = new JMenu(" ģʽ ");
	JMenu mSettings = new JMenu(" ���� ");
	JMenu mViews = new JMenu(" ��ͼ ");
	JMenu mHelp = new JMenu(" ���� ");
	//JMenu mLanguage = new JMenu(" ���� ");
	//JButton mLogin=new JButton(" ��¼ ");
	JMenuItem itemOpen= new JMenuItem(" �� ");
	JMenuItem itemSave = new JMenuItem(" ���� ");
	JMenuItem itemSaveAs = new JMenuItem("���Ϊ ");
	JMenu mShow = new JMenu(" ��ʾ ");
	JMenuItem itemSoftKey = new JMenuItem(" �����  ");
	JMenuItem itemPianoKey = new JMenuItem("���ټ��� ");
	JMenuItem itemTool = new JMenuItem("������   ");
	JMenuItem itemSkin = new JMenuItem(" ����  ");
	JMenuItem itemStyle = new JMenuItem(" ��� ");

	JMenuItem itemSettings = new JMenuItem("�߼�����...");
	//JMenuItem itemChinese = new JMenuItem("��������  ��");
	//JMenuItem itemEnglish = new JMenuItem("English");
	//JMenuItem itemJapanese = new JMenuItem("�ձ��Z");
	JMenuItem itemHelp = new JMenuItem(" ���� ");

	JMenuItem itemFreeplay = new JMenuItem("�������� ��");
	JMenuItem itemTeachplay=new JMenuItem("��ѧģʽ");
	JMenuItem itemRecord=new JMenuItem("���׼�¼");
	JMenuItem itemCreate=new JMenuItem("����ģʽ");
	JMenuItem itemPlay=new JMenuItem(" ���� ");
	JTextArea textArea = new JTextArea();

	boolean isPlaying=false;

	public String pressForce, duration = "q", instrument = "0";
	public String remember;//remember��������������������Ϣ
	public String timeString="";//ÿ��������������ʱ���ַ���
	public int channelNum, tone, colorNum,chooseIndex=2;
	public Color myColor[] = { Color.red, Color.ORANGE, Color.YELLOW, Color.green, Color.pink, Color.BLUE,
			Color.GRAY };
	Color myPink = new Color(255, 174, 185);
	Color myGay = new Color(171, 130, 255);

	public int mode=0;// 0��ʾfree���ɵ��࣬1��ʾteachʾ�̣�2��ʾrecord���׼�¼��3��ʾcreate������4��ʾplay����
	public boolean isColorful = true;// �Ƿ��Ƕ�ʼ���ģʽ
	public boolean isBothPlay = false;// �Ƿ�����ĸ+���ַ���ģʽ
	long beginTime;//���¿�ʼ���ఴť��ʱ��
	String title;
	String date;
	String author;
	int numOfNote;//��1��ʼ��ͳ�������к��ж��ٸ�����
	int[] keyCode;//��lzh��mypattern��ճ��
	int[] character;
	int[] buttonCode;
	String[] subArray;
	String[] subArrayOnlyNum;
	String[] durations;
	boolean isTruePressed = false;
	int currentNum = 0;//ʾ��ģʽ�µ�����������������ÿ�ΰ���һ���������ͼ�һ
	int pressCount=0;//����ģʽ�µĵ����������ֻҪ���¼��̣��ͼ�һ
	KeyManager km = new KeyManager(1);//ÿ�����̼��ĸ�������
	Transform trans = new Transform();//����ת�������ĵ���
	SettingKey settingkey = new SettingKey(this);
	CreateString createString = new CreateString(this);

	public MyPiano() throws MidiUnavailableException, IOException {
		
		streamingPlayer = new StreamingPlayer();
		player = new Player();
		btn = new JButton[128];
		for (int i = 0; i < 127; i++) {
			btn[i] = new JButton();
			btn[i].setBackground(Color.WHITE);
			btn[i].setText(String.valueOf((char) i) + String.valueOf(i));
			btn[i].addActionListener(this);
			btn[i].addKeyListener(new MyKeyListener(this));
		}
		
		pKey = new MyPanel(this);
		pPiano = new PianoPanel(this);
		f.setSize(1000, 750);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.add(pUp);
		pUp.setBounds(0, 0, 1000, 180);
		pUp.setLayout(null);

		pUp.add(pMenu);   pMenu.setBounds(0, 0, 220, 33);
		pUp.add(pControl);pControl.setBounds(230, 0, 520, 33);
		pUp.add(pLyrics);pLyrics.setBounds(190, 35, 580, 135);
		pLyrics.setLayout(null);
		lblWuXianPu = new WuXianPu(this);

		f.add(pKey);
		pKey.setBounds(0, 180, 1000, 300);
		f.add(pPiano);
		pPiano.setBounds(0, 480, 1000, 1500);
		pMenu.add(mBar);
		f.add(btnStatistic ,new JLayeredPane(), 0);
		
		
		btnStatistic.setBounds(850, 20, 100, 100);
		btnStatistic.setIcon(new ImageIcon("image/logo.png"));

		btnStatistic.addActionListener(new LoginFrameActionListener(this));
		
		((JPanel)f.getContentPane()).setOpaque(false);
		FileInputStream fin = null;
		try {
			fin=new FileInputStream(".//data//skin");
			FileReader fr=new FileReader(".//data//skin");
			BufferedReader br=new BufferedReader(fr);
			String flag="";
			flag=br.readLine();
			//������������Ƥ��
	       	switch(Integer.parseInt(flag)){
				case 1:ImageIcon img1 = new ImageIcon("image/����1.jpg");background = new JLabel(img1); break; //���ͼƬ
				case 2:ImageIcon img2 = new ImageIcon("image/����2.jpg");background = new JLabel(img2); break;
				case 3:ImageIcon img3 = new ImageIcon("image/����3.jpg");background = new JLabel(img3); break;
				case 4:ImageIcon img4 = new ImageIcon("image/����4.jpg");background = new JLabel(img4); break;
				default:break;
	        }
	        
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
		background.setBounds(0, 0, f.getWidth(), f.getHeight());
		f.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		setVisible(false);
		pUp.setOpaque(false);
		pKey.setOpaque(false);
		pPiano.setOpaque(false);
		
		mBar.add(mFile);
		mBar.add(mViews);
		mBar.add(mSettings);
		mBar.add(mMode);
		mBar.add(mHelp);
		mFile.add(itemOpen);
		mFile.add(itemSave);
		mFile.add(itemSaveAs);
		mViews.add(mShow);
		mViews.add(itemSkin);
		mViews.add(itemStyle);

		mShow.add(itemSoftKey);
		mShow.add(itemPianoKey);
		mShow.add(itemTool);
		mSettings.add(itemSettings);
		//mSettings.add(mLanguage);
		//mLanguage.add(itemChinese);
		//mLanguage.add(itemEnglish);
		//mLanguage.add(itemJapanese);
		mHelp.add(itemHelp);
		
		
		
		//pLyrics.add(txtOutput);
		pControl.add(lblSetInstrument);
		pControl.add(jbxSetInstrument);
		jbxSetInstrument.addItem(" ����      ");
		jbxSetInstrument.addItem(" ����� ");
		jbxSetInstrument.addItem(" ����      ");
		jbxSetInstrument.addItem(" ����      ");
		jbxSetInstrument.addItem(" ��˹      ");
		jbxSetInstrument.addItem(" ������ ");
		jbxSetInstrument.addItem(" ����      ");
		jbxSetInstrument.addItem("ͭ������");
		jbxSetInstrument.addItem(" ����      ");
		jbxSetInstrument.addItem(" ������ ");
		jbxSetInstrument.addItem("�ϳ�����");
		jbxSetInstrument.addItem("�ϳ�����");
		jbxSetInstrument.addItem("ͭ������");
		jbxSetInstrument.addItem("�ϳ�Ч����ɫ");
		jbxSetInstrument.addItem("��������");
		jbxSetInstrument.addItem("�����     ");

		pControl.add(lblSetTone);
		pControl.add(jbxSetTone);
		jbxSetTone.addItem(" C(0)   ");
		jbxSetTone.addItem(" Db(+1) ");
		jbxSetTone.addItem(" D(+2)  ");
		jbxSetTone.addItem(" Eb(+3) ");
		jbxSetTone.addItem(" E(+4)  ");
		jbxSetTone.addItem(" F(+5)  ");
		jbxSetTone.addItem(" F#(+6) ");
		jbxSetTone.addItem(" G(+7)  ");
		jbxSetTone.addItem(" Ab(-4) ");
		jbxSetTone.addItem(" A(-3)  ");
		jbxSetTone.addItem(" Bb(-2) ");
		jbxSetTone.addItem(" B(-1)  ");
		//pControl.add(lblSetDuration);
		//pControl.add(jbxSetDuration);
		jbxSetDuration.addItem(" ȫ����     ");
		jbxSetDuration.addItem(" 1/2����  ");
		jbxSetDuration.addItem(" 1/4����  ");
		jbxSetDuration.addItem(" 1/8����  ");
		jbxSetDuration.addItem(" 1/16����");
		jbxSetDuration.setSelectedIndex(2);
		pControl.add(btnStart);
		
		/*LZH*/
		//pControl.add(btnPauseOrWake);
		/*LZH*/
		
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
		btn[0].requestFocus();
		itemSave.addActionListener(new SaveActionListener(this));
		itemOpen.addActionListener(new OpenListener(this));
		itemSettings.addActionListener(new SettingsFrameActionListener(this));
		itemSkin.addActionListener(new SkinActionListener(this));
		itemHelp.addActionListener(new HelpFrameActionListener(this));
		itemSoftKey.addActionListener(new ActionListener() {// �����л��˵�����¼�
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "��ʾ", "����ʾ" }; // �Զ��尴ť�ϵ�����
				int m = JOptionPane.showOptionDialog(null, "�Ƿ���ʾ����̣�", "�����", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (m == 0)
					pKey.setVisible(true);
				if (m == 1)
					pKey.setVisible(false);
			}
		});
		itemPianoKey.addActionListener(new ActionListener() {// �����л��˵�����¼�
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "��ʾ", "����ʾ" }; // �Զ��尴ť�ϵ�����
				int m = JOptionPane.showOptionDialog(null, "�Ƿ���ʾ���ټ��̣�", "���ټ���", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (m == 0)
					pPiano.setVisible(true);
				if (m == 1)
					pPiano.setVisible(false);
			}
		});
		itemTool.addActionListener(new ActionListener() {// �����л��˵�����¼�
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "��ʾ", "����ʾ" }; // �Զ��尴ť�ϵ�����
				int m = JOptionPane.showOptionDialog(null, "�Ƿ���ʾ��������", "������", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (m == 0)
					pControl.setVisible(true);
				if (m == 1)
					pControl.setVisible(false);
			}
		});
		/*
		itemChinese.addActionListener(new ActionListener() {// �����л��˵�����¼�
			public void actionPerformed(ActionEvent e) {
				itemChinese.setText("��������  ��");
				itemEnglish.setText("English");// ��������������ȫ����Ӣ��
				itemJapanese.setText("�ձ��Z");
			}
		});
		itemEnglish.addActionListener(new ActionListener() {// �����л��˵�����¼�
			public void actionPerformed(ActionEvent e) {
				itemChinese.setText("�������� ");
				itemEnglish.setText("English ��");// ��������������ȫ����Ӣ��
				itemJapanese.setText("�ձ��Z");
			}
		});
		itemJapanese.addActionListener(new ActionListener() {// �����л��˵�����¼�
			public void actionPerformed(ActionEvent e) {
				itemChinese.setText("�������� ");
				itemEnglish.setText("English");// ��������������ȫ��������
				itemJapanese.setText("�ձ��Z  ��");
			}
		});
		*/
		itemStyle.addActionListener(new StyleFrameActionListener(this));

		btnStart.addActionListener(new StartActionListener(this));
		mMode.add(itemFreeplay);
		mMode.add(itemTeachplay);
		mMode.add(itemRecord);
		mMode.add(itemCreate);
		mMode.add(itemPlay);
		itemFreeplay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mode=0;
				itemFreeplay.setText("���ɵ���  ��");
				itemTeachplay.setText("��ѧģʽ");
				itemRecord.setText("���׼�¼");
				itemCreate.setText("���ɴ���");
				itemPlay.setText("����");
			}
		});
		itemTeachplay.addActionListener(new TeachingListener(this));
		itemRecord.addActionListener(new RecordListener(this));
		itemCreate.addActionListener(new CreatFrameActionListener(this));
		itemPlay.addActionListener(new PlayListener(this));
		new Samp(this);
		setVisible(false);
		pUp.setOpaque(false);
		pKey.setOpaque(false);
		pPiano.setOpaque(false);
		pMenu.setOpaque(false);
		//pLyrics.setOpaque(false);
		pControl.setOpaque(false);
	}
	public static void main(String[] args) throws MidiUnavailableException, IOException {
		FileInputStream fin = null;
		try {
			fin=new FileInputStream(".//data//style");
			FileReader fr=new FileReader(".//data//style");
			BufferedReader br=new BufferedReader(fr);
			String temp="";
			temp=br.readLine();
			//������������Ƥ��
	        try {
				UIManager.setLookAndFeel(temp);
				 //com.jtattoo.plaf.noire.NoireLookAndFeel  ��ͺ�
		         /*   com.jtattoo.plaf.smart.SmartLookAndFeel ľ�ʸ�+xp���
		            com.jtattoo.plaf.mint.MintLookAndFeel  ��Բ��ť+��ɫ��ť����
		            com.jtattoo.plaf.mcwin.McWinLookAndFeel ��Բ��ť+��ɫ��ť����
		            com.jtattoo.plaf.luna.LunaLookAndFeel  ��XP���
		            com.jtattoo.plaf.hifi.HiFiLookAndFeel  ��ɫ���
		            com.jtattoo.plaf.fast.FastLookAndFeel  ��ͨswing���+��ɫ�߿�
		            com.jtattoo.plaf.bernstein.BernsteinLookAndFeel  ��ɫ���
		            com.jtattoo.plaf.aluminium.AluminiumLookAndFeel ��Բ��ť+����ɫ��ť����+�����ʸУ�Ĭ�ϣ�
		            com.jtattoo.plaf.aero.AeroLookAndFeel xp���·��
		            com.jtattoo.plafacryl.AcrylLookAndFeel ���ʸ�+swing�����*/
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
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
        new MyPiano();
	}

	public void refresh(){
		remember="";
		txtOutput.setText("");
		for(int i=0;i<127;i++)btn[i].setBackground(Color.WHITE);
		for(int i=0;i<52;i++)btnPianoWhite[i].setIcon(null);
		for(int i=0;i<37;i++)btnPianoBlack[i].setIcon(null);
		
		channelNum=0; tone=0;colorNum=0;
		
		
	}

/*	public int PressurePlus() {
		plus.addActionListener(new ActionListener() {// ��ʼ���ఴť����¼�
			public void actionPerformed(ActionEvent e) {
				instrument = String.valueOf(transformInstrument(jbxSetInstrument.getSelectedIndex()));
				//player.changeInstrument(transformInstrument(jbxSetInstrument.getSelectedIndex()));
				for (int i = 0; i < 128; i++) {
					btn[i].requestFocus();
				}
			}
		});
		return 0;
	}*/

	public boolean isUpperLetter() {// �жϴ�д�����Ƿ��
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
	}

	public synchronized int getChannel(int instrument){
		if(instrument>=32||(instrument>=16&&instrument<=23)) return 0;
		channelNum++;
		if (channelNum == 9)
			channelNum++;// V9�������⣬���ܲ���������ֻ�ܴ�ĵ㣬������� 9����
		else if (channelNum == 16)
			channelNum = 1;// �ﵽ16�Ժ����´�1��ʼ
		return channelNum;
	}

	public String f2dot(String s) {//���ڼ��������汾��ԭ�汾������f��ʾ���øú���ת����.
		int len = s.length();
		if(len <= 1)return s;
		if(s.substring(len-1).equals("f"))
			return s.substring(0,len-1)+".";	
		return s;
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		player.close();
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void actionPerformed(ActionEvent e) {
	}

}