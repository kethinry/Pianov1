
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
	JFrame f = new JFrame("键盘音乐派对");
	JLabel background;
	JFrame fAdvancedSettings = new JFrame("高级设置");
	JPanel pUp = new JPanel();
	JPanel pLyrics = new JPanel();
	JPanel pMenu = new JPanel();
	JPanel pControl = new JPanel();
	JPanel pPiano = new JPanel();
	MyPanel pKey;
	WuXianPu lblWuXianPu;
	JLabel lblOutput = new JLabel("歌谱");
	JLabel lblSetInstrument = new JLabel("设置音色");
	JComboBox jbxSetInstrument = new JComboBox();
	JLabel lblSetTone = new JLabel("设置音高");;
	JComboBox jbxSetTone = new JComboBox();// 将来使用下拉菜单实现
	JLabel lblSetDuration = new JLabel("设置音符");
	JComboBox jbxSetDuration = new JComboBox();
	JTextField txtOutput = new JTextField(50);
	
	JButton btnStatistic = new JButton();
	JButton btnStart = new JButton("开始演奏");
	JButton plus = new JButton(" + ");
	JButton minor = new JButton(" - ");
	//JSlider jsPressForce = new JSlider();
	//JLabel lblvalue = new JLabel("音量");
	JMenuBar mBar = new JMenuBar();
	JMenu mFile = new JMenu(" 文件 ");
	JMenu mMode = new JMenu(" 模式 ");
	JMenu mSettings = new JMenu(" 设置 ");
	JMenu mViews = new JMenu(" 视图 ");
	JMenu mHelp = new JMenu(" 帮助 ");
	//JMenu mLanguage = new JMenu(" 语言 ");
	//JButton mLogin=new JButton(" 登录 ");
	JMenuItem itemOpen= new JMenuItem(" 打开 ");
	JMenuItem itemSave = new JMenuItem(" 保存 ");
	JMenuItem itemSaveAs = new JMenuItem("另存为 ");
	JMenu mShow = new JMenu(" 显示 ");
	JMenuItem itemSoftKey = new JMenuItem(" 软键盘  ");
	JMenuItem itemPianoKey = new JMenuItem("钢琴键盘 ");
	JMenuItem itemTool = new JMenuItem("工具栏   ");
	JMenuItem itemSkin = new JMenuItem(" 换肤  ");
	JMenuItem itemStyle = new JMenuItem(" 风格 ");

	JMenuItem itemSettings = new JMenuItem("高级设置...");
	//JMenuItem itemChinese = new JMenuItem("简体中文  √");
	//JMenuItem itemEnglish = new JMenuItem("English");
	//JMenuItem itemJapanese = new JMenuItem("日本語");
	JMenuItem itemHelp = new JMenuItem(" 帮助 ");

	JMenuItem itemFreeplay = new JMenuItem("自由演奏 √");
	JMenuItem itemTeachplay=new JMenuItem("教学模式");
	JMenuItem itemRecord=new JMenuItem("乐谱记录");
	JMenuItem itemCreate=new JMenuItem("创作模式");
	JMenuItem itemPlay=new JMenuItem(" 播放 ");
	JTextArea textArea = new JTextArea();

	boolean isPlaying=false;

	public String pressForce, duration = "q", instrument = "0";
	public String remember;//remember是用来记忆歌谱里面的信息
	public String timeString="";//每个音符弹奏的相对时间字符串
	public int channelNum, tone, colorNum,chooseIndex=2;
	public Color myColor[] = { Color.red, Color.ORANGE, Color.YELLOW, Color.green, Color.pink, Color.BLUE,
			Color.GRAY };
	Color myPink = new Color(255, 174, 185);
	Color myGay = new Color(171, 130, 255);

	public int mode=0;// 0表示free自由弹奏，1表示teach示教，2表示record乐谱记录，3表示create创作，4表示play播放
	public boolean isColorful = true;// 是否是多彩键盘模式
	public boolean isBothPlay = false;// 是否是字母+数字发音模式
	long beginTime;//按下开始演奏按钮的时间
	String title;
	String date;
	String author;
	int numOfNote;//从1开始，统计乐谱中含有多少个音符
	int[] keyCode;//从lzh的mypattern中粘贴
	int[] character;
	int[] buttonCode;
	String[] subArray;
	String[] subArrayOnlyNum;
	String[] durations;
	boolean isTruePressed = false;
	int currentNum = 0;//示教模式下的音符数量计数器，每次按对一个音符，就加一
	int pressCount=0;//任意模式下的弹奏计数器，只要按下键盘，就加一
	KeyManager km = new KeyManager(1);//每个键盘键的各类属性

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
			//下面设置特殊皮肤
	       	switch(Integer.parseInt(flag)){
				case 1:ImageIcon img1 = new ImageIcon("image/背景1.jpg");background = new JLabel(img1); break; //添加图片
				case 2:ImageIcon img2 = new ImageIcon("image/背景2.jpg");background = new JLabel(img2); break;
				case 3:ImageIcon img3 = new ImageIcon("image/背景3.jpg");background = new JLabel(img3); break;
				case 4:ImageIcon img4 = new ImageIcon("image/背景4.jpg");background = new JLabel(img4); break;
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
		jbxSetInstrument.addItem(" 钢琴      ");
		jbxSetInstrument.addItem(" 打击乐 ");
		jbxSetInstrument.addItem(" 风琴      ");
		jbxSetInstrument.addItem(" 吉他      ");
		jbxSetInstrument.addItem(" 贝斯      ");
		jbxSetInstrument.addItem(" 弦乐器 ");
		jbxSetInstrument.addItem(" 合奏      ");
		jbxSetInstrument.addItem("铜管乐器");
		jbxSetInstrument.addItem(" 牧笛      ");
		jbxSetInstrument.addItem(" 管乐器 ");
		jbxSetInstrument.addItem("合成领奏");
		jbxSetInstrument.addItem("合成柔音");
		jbxSetInstrument.addItem("铜管乐器");
		jbxSetInstrument.addItem("合成效果音色");
		jbxSetInstrument.addItem("民族音乐");
		jbxSetInstrument.addItem("打击乐     ");

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
		jbxSetDuration.addItem(" 全音符     ");
		jbxSetDuration.addItem(" 1/2音符  ");
		jbxSetDuration.addItem(" 1/4音符  ");
		jbxSetDuration.addItem(" 1/8音符  ");
		jbxSetDuration.addItem(" 1/16音符");
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
		itemSoftKey.addActionListener(new ActionListener() {// 语言切换菜单点击事件
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "显示", "不显示" }; // 自定义按钮上的文字
				int m = JOptionPane.showOptionDialog(null, "是否显示软键盘？", "软键盘", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (m == 0)
					pKey.setVisible(true);
				if (m == 1)
					pKey.setVisible(false);
			}
		});
		itemPianoKey.addActionListener(new ActionListener() {// 语言切换菜单点击事件
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "显示", "不显示" }; // 自定义按钮上的文字
				int m = JOptionPane.showOptionDialog(null, "是否显示钢琴键盘？", "钢琴键盘", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (m == 0)
					pPiano.setVisible(true);
				if (m == 1)
					pPiano.setVisible(false);
			}
		});
		itemTool.addActionListener(new ActionListener() {// 语言切换菜单点击事件
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "显示", "不显示" }; // 自定义按钮上的文字
				int m = JOptionPane.showOptionDialog(null, "是否显示工具栏？", "工具栏", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (m == 0)
					pControl.setVisible(true);
				if (m == 1)
					pControl.setVisible(false);
			}
		});
		/*
		itemChinese.addActionListener(new ActionListener() {// 语言切换菜单点击事件
			public void actionPerformed(ActionEvent e) {
				itemChinese.setText("简体中文  √");
				itemEnglish.setText("English");// 将来把所有名字全换成英文
				itemJapanese.setText("日本語");
			}
		});
		itemEnglish.addActionListener(new ActionListener() {// 语言切换菜单点击事件
			public void actionPerformed(ActionEvent e) {
				itemChinese.setText("简体中文 ");
				itemEnglish.setText("English √");// 将来把所有名字全换成英文
				itemJapanese.setText("日本語");
			}
		});
		itemJapanese.addActionListener(new ActionListener() {// 语言切换菜单点击事件
			public void actionPerformed(ActionEvent e) {
				itemChinese.setText("简体中文 ");
				itemEnglish.setText("English");// 将来把所有名字全换成日语
				itemJapanese.setText("日本語  √");
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
				itemFreeplay.setText("自由弹奏  √");
				itemTeachplay.setText("教学模式");
				itemRecord.setText("乐谱记录");
				itemCreate.setText("自由创作");
				itemPlay.setText("播放");
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
			//下面设置特殊皮肤
	        try {
				UIManager.setLookAndFeel(temp);
				 //com.jtattoo.plaf.noire.NoireLookAndFeel  柔和黑
		         /*   com.jtattoo.plaf.smart.SmartLookAndFeel 木质感+xp风格
		            com.jtattoo.plaf.mint.MintLookAndFeel  椭圆按钮+黄色按钮背景
		            com.jtattoo.plaf.mcwin.McWinLookAndFeel 椭圆按钮+绿色按钮背景
		            com.jtattoo.plaf.luna.LunaLookAndFeel  纯XP风格
		            com.jtattoo.plaf.hifi.HiFiLookAndFeel  黑色风格
		            com.jtattoo.plaf.fast.FastLookAndFeel  普通swing风格+蓝色边框
		            com.jtattoo.plaf.bernstein.BernsteinLookAndFeel  黄色风格
		            com.jtattoo.plaf.aluminium.AluminiumLookAndFeel 椭圆按钮+翠绿色按钮背景+金属质感（默认）
		            com.jtattoo.plaf.aero.AeroLookAndFeel xp清新风格
		            com.jtattoo.plafacryl.AcrylLookAndFeel 布质感+swing纯风格*/
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
	public String transiformCharacterToNewCharacter(int character) {
		if (character<0)return "-1";
		String s = "";
		int x,y;
		x = character%12;
		y = character/12;
		switch (x) {
		case 0:
			s = "C";
			break;
		case 1:
			s = "Db";
			break;
		case 2:
			s = "D";
			break;
		case 3:
			s = "Eb";
			break;
		case 4:
			s = "E";
			break;
		case 5:
			s = "F";
			break;
		case 6:
			s = "Gb";
			break;
		case 7:
			s = "G";
			break;
		case 8:
			s = "Ab";
			break;
		case 9:
			s = "A";
			break;
		case 10:
			s = "Bb";
			break;
		case 11:
			s = "B";
			break;
		default:
			s = "";
			break;
		}
		return s+y;
	}
	
	public int PressurePlus() {
		plus.addActionListener(new ActionListener() {// 开始演奏按钮点击事件
			public void actionPerformed(ActionEvent e) {
				instrument = String.valueOf(transformInstrument(jbxSetInstrument.getSelectedIndex()));
				//player.changeInstrument(transformInstrument(jbxSetInstrument.getSelectedIndex()));
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
	//下面这个函数按理说取消切换音长快捷键之后应该没有用了，但是第一个用的地方貌似跟是否是最后一个音符有关，没太明白
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

	public boolean isUpperLetter() {// 判断大写锁定是否打开
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
	}

	public synchronized int getChannel(int instrument){
		if(instrument>=32||(instrument>=16&&instrument<=23)) return 0;
		channelNum++;
		if (channelNum == 9)
			channelNum++;// V9音轨特殊，不能播放声调，只能打鼓点，因此遇到 9跳过
		else if (channelNum == 16)
			channelNum = 1;// 达到16以后重新从1开始
		return channelNum;
	}
	public String getString(int keyCode,boolean isControl) {// 通过键盘编码和已有设置构成可以play的note字符串
		String note = "",wxpNote="";
		KeyProperty key = km.findByCode(keyCode);
		int character ;
		if(isControl)
			character = key.getRisecharacter();
		else
			character = key.getCharacter();
		if (character == -1)
			return note;
		wxpNote=String.valueOf(character) + duration;
		lblWuXianPu.addNote(wxpNote);
		note = "V" + String.valueOf(getChannel(transformInstrument(jbxSetInstrument.getSelectedIndex()))) + " " + instrument + " " + wxpNote + " ";
		numOfNote++;
		//System.out.println("note="+note);
		
		return note;
	}

	public String getStreamString(boolean isPress,int keyCode,int channel,boolean isControl) {// 通过键盘编码和已有设置构成可以play的note字符串
		String note = "",wxpNote="";
		KeyProperty key = km.findByCode(keyCode);
		String newcharacter ;
		int character ;
		if(isControl){
			newcharacter = key.getNewRiseCharacter();
			character = key.getRisecharacter();
		}
		else{
			newcharacter = key.getNewcharacter();
			character = key.getCharacter();
		}
		//System.out.println("newcharacter="+newcharacter);
		if (newcharacter == "n")
			return "";
		/*if (isUpperLetter())
			character++;*/
		wxpNote=String.valueOf(character) + duration;
		//lblWuXianPu.addNote(wxpNote);
		if(isPress)note = "V" + String.valueOf(channel) + " " + instrument + " " + newcharacter + "o- ";
		else {
			note = "V" + String.valueOf(channel) + " " + instrument + " " + newcharacter + "-o ";
			lblWuXianPu.addNote(wxpNote);
		}

		numOfNote++;
		//System.out.println("note="+note);

		return note;
	}


	public void settingwhitekey(int whitecode){
		if (whitecode>=0&&whitecode<52) {
			if (whitecode == 0)
				btnPianoWhite[whitecode].setIcon(new ImageIcon("image/0.jpg"));
			else if (whitecode == 1)
				btnPianoWhite[whitecode].setIcon(new ImageIcon("image/7.jpg"));
			switch ((whitecode - 2) % 7) {
				case 0:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/1.jpg"));break;
				case 1:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/2.jpg"));break;
				case 2:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/3.jpg"));break;
				case 3:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/4.jpg"));break;
				case 4:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/5.jpg"));break;
				case 5:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/6.jpg"));break;
				case 6:
					btnPianoWhite[whitecode].setIcon(new ImageIcon("image/7.jpg"));break;
				default:
					btnPianoWhite[whitecode].setIcon(null);break;
			}
		}
	}
	public void setkeycolor(int btni,int mode){
		switch(mode) {
			case 1:
				if (!isColorful) {// 黑白键盘模式
					btn[btni].setBackground(Color.BLACK);
					if (btni == 41) //41键是shift，另一边52也要显示
						btn[btni].setBackground(Color.BLACK);
					if (btni == 53) //53和57都是ctrl
						btn[57].setBackground(Color.BLACK);
				} else {// 多彩键盘模式
					if (colorNum >= 6)
						colorNum = 0;
					if (btni != -1)
						btn[btni].setBackground(myColor[colorNum]);
					if (btni == 41)
						btn[52].setBackground(myColor[colorNum]);
					if (btni == 53)
						btn[57].setBackground(myColor[colorNum]);
					colorNum++;
				}break;
			case 2:
				btn[btni].setBackground(Color.GREEN);
				if (btni == 41)
					btn[52].setBackground(Color.GREEN);
				if (btni == 53)
					btn[57].setBackground(Color.GREEN);
				break;
			case 3:
				btn[btni].setBackground(Color.RED);
			case 4:
				btn[btni].setBackground(myPink);
				if (btni == 41)
					btn[52].setBackground(myPink);
				if (btni == 53)
					btn[57].setBackground(myPink);
			case 5:
				btn[btni].setBackground(Color.WHITE);
				if (btni == 41)
					btn[52].setBackground(Color.WHITE);
				if (btni == 53)
					btn[57].setBackground(Color.WHITE);
				break;
		}
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