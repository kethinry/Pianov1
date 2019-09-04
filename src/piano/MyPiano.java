
package piano;

//http://jszx-jxpt.cuit.edu.cn/JavaAPI/javax/sound/midi/MidiChannel.html
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.print.attribute.standard.JobName;
import javax.security.auth.Refreshable;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;

import com.jgoodies.looks.Fonts;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
//import com.mysql.jdbc.UpdatableResultSet;



public class MyPiano extends JFrame implements WindowListener, ActionListener {
	private static final long serialVersionUID = -1907889725234272213L;
	public JButton[] btn;
	JButton btnPianoWhite[];
	JButton btnPianoBlack[];
	RealtimePlayer player;
	JFrame f = new JFrame("���������ɶ�");
	JLabel background;
	JFrame fAdvancedSettings = new JFrame("�߼�����");
	JPanel pUp = new JPanel();
	JPanel pLyrics = new JPanel();
	JPanel pMenu = new JPanel();
	JPanel pKey1 = new JPanel();
	JPanel pKey2 = new JPanel();
	JPanel pKey3 = new JPanel();
	JPanel pKey4 = new JPanel();
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

	public MyPiano() throws MidiUnavailableException, IOException {
		
		
		player = new RealtimePlayer();
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
		pControl.add(lblSetDuration);
		pControl.add(jbxSetDuration);
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
	public void changeFont(Font f1){
		
	}
	public void refresh(){
		remember="";
		txtOutput.setText("");
		for(int i=0;i<127;i++)btn[i].setBackground(Color.WHITE);
		for(int i=0;i<52;i++)btnPianoWhite[i].setIcon(null);
		for(int i=0;i<37;i++)btnPianoBlack[i].setIcon(null);
		
		channelNum=0; tone=0;colorNum=0;
		
		
	}
	public int transformInstrument(int selectIndex) {
		return selectIndex * 8;
	}

	public String transformDuration(int selectIndex) {
		switch (selectIndex) {
		case 0:
			return "w";
		case 1:
			return "h";
		case 2:
			return "q";
		case 3:
			return "i";
		case 4:
			return "s";
		case 5:
			return "t";
		case 6:
			return "x";
		case 7:
			return "o";
		default:
			return "q";
		}
	}

	public int PressurePlus() {
		plus.addActionListener(new ActionListener() {// ��ʼ���ఴť����¼�
			public void actionPerformed(ActionEvent e) {
				player.changeInstrument(transformInstrument(jbxSetInstrument.getSelectedIndex()));
				for (int i = 0; i < 128; i++) {
					btn[i].requestFocus();
				}
			}
		});
		return 0;
	}

	public long transformDurationToTime(String durations,int pace,boolean isFudian) {
		
		pace=60000/pace;
		if(isFudian)
			pace*=1.5;
		switch (durations) {
		case "w":
			return 4*pace;
		case "h":
			return 2*pace;
		case "q":
			return pace;
		case "i":
			return pace/2;
		case "s":
			return pace/4;
		case "t":
			return pace/8;
		case "x":
			return pace/16;
		case "o":
			return pace/32;
		default:
			return 500;
		}
	}

	public int transformButtonCodeToKeyCode(int i) {// ��ť��������ת���ɼ��̵ı���
		if (i >= 1 && i <= 9)
			return 48 + i;
		if (i == 10)
			return 48;
		if ((i >= 0 && i <= 14) || (i >= 25 && i <= 28) || (i >= 38 && i <= 41) || (i >= 49 && i <= 52))
			return -1;
		else {
			switch (i) {
			case 15:
				return 113;
			case 16:
				return 119;
			case 17:
				return 101;
			case 18:
				return 114;
			case 19:
				return 116;
			case 20:
				return 121;
			case 21:
				return 117;
			case 22:
				return 105;
			case 23:
				return 111;
			case 24:
				return 112;
			case 29:
				return 97;
			case 30:
				return 115;
			case 31:
				return 100;
			case 32:
				return 102;
			case 33:
				return 103;
			case 34:
				return 104;
			case 35:
				return 106;
			case 36:
				return 107;
			case 37:
				return 108;
			case 42:
				return 122;
			case 43:
				return 120;
			case 44:
				return 99;
			case 45:
				return 118;
			case 46:
				return 98;
			case 47:
				return 110;
			case 48:
				return 109;
			default:
				return -1;
			}

		}
	}
	public int transformDurationToButtonCode(String durations) {
		switch (durations) {
		case "w":
			return 25;
		case "h":
			return 26;
		case "q":
			return 27;
		case "i":
			return 38;
		case "s":
			return 39;
		case "t":
			return 49;
		case "x":
			return 50;
		case "o":
			return 51;
		default:
			return 27;
		}
	}

	public int transformKeyCodeToButtonCode(int i) {
		if (i >= 49 && i <= 58)//���ּ�������1-10
			return i - 48;
		if (i == 48)
			return i - 38;
		else {//Ӣ����ĸ
			switch (i + 32) {
			case 224:
				return 0;
			case 77:
				return 11;
			case 93:
				return 12;
			case 40:
				return 13;
			case 41:
				return 14;
			case 123:
				return 25;
			case 125:
				return 26;
			case 124:
				return 27;
			case 52:
				return 28;
			case 91:
				return 38;
			case 254:
				return 39;
			case 122:
				return 42;
			case 45:
				return 40;
			case 48:
				return 41;
			case 76:
				return 49;
			case 78:
				return 50;
			case 79:
				return 51;
			case 49:
				return 53;
			case 294:
				return 54;
			case 64:
				return 55;
			case 80:
				return 10;
			case 113:
				return 15;
			case 119:
				return 16;
			case 101:
				return 17;
			case 114:
				return 18;
			case 116:
				return 19;
			case 121:
				return 20;
			case 117:
				return 21;
			case 105:
				return 22;
			case 111:
				return 23;
			case 112:
				return 24;
			case 97:
				return 29;
			case 115:
				return 30;
			case 100:
				return 31;
			case 102:
				return 32;
			case 103:
				return 33;
			case 104:
				return 34;
			case 106:
				return 35;
			case 107:
				return 36;
			case 108:
				return 37;
			case 120:
				return 43;
			case 99:
				return 44;
			case 118:
				return 45;
			case 98:
				return 46;
			case 110:
				return 47;
			case 109:
				return 48;
			case 42:
				return 40;
			default:
				return -1;
			}

		}

	}

	public int transformKeyCodeToCharacter(int keyCode) {// �Ѽ��̱���ת���������ߵͱ�ʾ��
		if (keyCode <= 64 || keyCode >= 91)
			if (isBothPlay == true) {// �������ּ���ģʽ
				keyCode -= 48;
				switch (keyCode) {
				case 1:
					return 84;
				case 2:
					return 86;
				case 3:
					return 88;
				case 4:
					return 89;
				case 5:
					return 91;
				case 6:
					return 93;
				case 7:
					return 95;
				case 8:
					return 96;
				case 9:
					return 98;
				case 0:
					return 100;
				default:
					return -1;
				}
			} else {
				return -1;
			}
		else {
			keyCode += 32;
			switch (keyCode) {
			case 97:
				return 60;// a
			case 98:
				return 55;// b
			case 99:
				return 52;// c
			case 100:
				return 64;// d
			case 101:
				return 76;// e
			case 102:
				return 65;// f
			case 103:
				return 67;// g
			case 104:
				return 69;// h
			case 105:
				return 84;// i
			case 106:
				return 71;// j
			case 107:
				return 72;// k
			case 108:
				return 74;// l
			case 109:
				return 59;// m
			case 110:
				return 57;// n
			case 111:
				return 86;// o
			case 112:
				return 88;// p
			case 113:
				return 72;// q
			case 114:
				return 77;// r
			case 115:
				return 62;// s
			case 116:
				return 79;// t
			case 117:
				return 83;// u
			case 118:
				return 53;// v
			case 119:
				return 74;// w
			case 120:
				return 50;// x
			case 121:
				return 81;// y
			case 122:
				return 48;// z
			default:
				return -1;
			}
		}
	}

	public int transformCharacterToKeyCode(int character) {// �������ߵͱ�ʾ��ת���ɼ��̱���
		switch (character) {
		case 89: case 90:
			return 52;
		case 91:case 92:
			return 53;
		case 93:case 94:
			return 54;
		case 95:
			return 55;
		case 96:case 97:
			return 56;
		case 98:case 99:
			return 57;
		case 100:case 101:
			return 48;

		case 60 :case 61:
			return 65;// a
		case 55: case 56:
			return 66;// b
		case 52:
			return 67;// c
		case 64:
			return 68;// d
		case 76:
			return 69;// e
		case 65:case 66:
			return 70;// f
		case 67:case 68:
			return 71;// g
		case 69:case 70:
			return 72;// h
		case 84:case 85:
			return 73;// i
		case 71:
			return 74;// j
		// case 72:
		// return 75;// k
		// case 74:
		// return 76;// l
		case 59:
			return 77;// m
		case 57:case 58:
			return 78;// n
		case 86:case 87:
			return 79;// o
		case 88:
			return 80;// p
		case 72:case 73:
			return 81;// q
		case 77:case 78:
			return 82;// r
		case 62:case 63:
			return 83;// s
		case 79:case 80:
			return 84;// t
		case 83:
			return 85;// u
		case 53:case 54:
			return 86;// v
		case 74:case 75:
			return 87;// w
		case 50:case 51:
			return 88;// x
		case 81:case 82:
			return 89;// y
		case 48:case 49:
			return 90;// z
		default:
			return -1;
		}
	}

	

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
	public String getString(int keyCode) {// ͨ�����̱�����������ù��ɿ���play��note�ַ���
		String note = "",wxpNote="";
		keyCode = transformKeyCodeToCharacter(keyCode);
		if (keyCode == -1)
			return note;
		if (isUpperLetter())
			keyCode++;
		wxpNote=String.valueOf(keyCode) + duration;
		lblWuXianPu.addNote(wxpNote);
		note = "V" + String.valueOf(getChannel(transformInstrument(jbxSetInstrument.getSelectedIndex()))) + " " + instrument + " " + wxpNote + " ";
		numOfNote++;
		//txtOutput.setText(txtOutput.getText() + note + " ");
		
		return note;
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