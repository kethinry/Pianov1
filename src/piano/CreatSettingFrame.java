package piano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobName;
import javax.swing.*;

import com.jgoodies.forms.builder.PanelBuilder;


public class CreatSettingFrame extends JFrame{
	MyPiano myPiano;
	CreatSettingFrame creatSettingFrame;
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JLabel lblTitle=new JLabel("标题");
	JTextField txtTitle = new JTextField("未命名",10);
	JLabel lblAuthor=new JLabel("作者");
	JTextField txtAuthor = new JTextField("未知作者",10);
	JLabel lblDate=new JLabel("时间");
	JTextField txtDate = new JTextField("2018.9",10);
	JLabel lblPace=new JLabel("速度（拍/分钟）");
	JTextField txtPace=new JTextField("80",10);
	JLabel lblBeat1=new JLabel("节拍");
	JTextField txtUp=new JTextField("4",5);//每小节有几拍
	JTextField txtDown = new JTextField("4",5);//以几分音符为一拍
	JLabel lblBeat2=new JLabel("/");
	JLabel lblSetTone = new JLabel("基础音高1=");
	JComboBox jbxSetTone = new JComboBox();// 下拉框
	JLabel lblPart=new JLabel("声部数");
	JTextField txtPart=new JTextField("1",5);
	JLabel lblMaxLength=new JLabel("最大音符数");
	JTextField txtMaxLength=new JTextField("1000",8);
	JButton btnConfirm=new JButton("开始录入乐谱");
	JTextArea textArea1=new JTextArea();
	
	int pace;
	int paceTime;
	int instrument;
	int tone;
	
	public CreatSettingFrame(MyPiano myPiano) {
		this.myPiano=myPiano;
		//this.wuXianPu=wuXianPu;
		//this.creatSettingFrame=creatSettingFrame;
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setTitle("歌谱基础信息设置");
		add(p1);
		add(p2);
		add(p3);
		p1.setLayout(null);
		p2.setLayout(null);
		p3.setLayout(null);
		p1.setBounds(50, 0, 150, 350);
		p2.setBounds(200, 0, 150, 350);
		p3.setBounds(50, 350, 300, 200);
		p1.add(lblTitle);
		p2.add(txtTitle);
		p1.add(lblAuthor);
		p2.add(txtAuthor);
		p1.add(lblDate);
		p2.add(txtDate);
		p1.add(lblPace);
		p2.add(txtPace);
		p1.add(lblBeat1);
		p2.add(txtUp);
		p2.add(lblBeat2);
		p2.add(txtDown);
		p1.add(lblSetTone);
		p2.add(jbxSetTone);
		p1.add(lblPart);
		p2.add(txtPart);
		p1.add(lblMaxLength);
		p2.add(txtMaxLength);
		p3.add(textArea1);
		p3.add(btnConfirm);
		lblTitle.setBounds(0, 20, 150, 20);
		lblAuthor.setBounds(0, 60, 150, 20);
		lblDate.setBounds(0, 100, 150, 20);
		lblPace.setBounds(0, 140, 150, 20);
		lblBeat1.setBounds(0, 180, 150, 20);
		lblSetTone.setBounds(0, 220, 150, 20);
		lblPart.setBounds(0, 260, 150, 20);
		lblMaxLength.setBounds(0, 300, 150, 20);
		
		txtTitle.setBounds(0, 20, 150, 20);
		txtAuthor.setBounds(0, 60, 150, 20);
		txtDate.setBounds(0, 100, 150, 20);
		txtPace.setBounds(0, 140, 150, 20);
		txtUp.setBounds(0, 180, 50, 20);
		lblBeat2.setBounds(60, 180, 20, 20);
		txtDown.setBounds(100, 180, 50, 20);
		jbxSetTone.setBounds(0, 220, 150, 20);
		txtPart.setBounds(0, 260, 150, 20);
		txtMaxLength.setBounds(0, 300, 150, 20);
		textArea1.setBounds(0, 0, 300, 50);
		btnConfirm.setBounds(0, 80, 300, 50);
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
		
		textArea1.setText("中间的空格/空格表示该乐谱是几几拍子的，对我们先阶段录谱没有帮助\n我们不用填");
		
		btnConfirm.addActionListener(new ActionListener() {//点击确认按钮以后，打开新增加伴奏的对话框，增加第一个伴奏（也就是主旋律）
			public void actionPerformed(ActionEvent e) {
				Music music=new Music(myPiano);//构造一个乐谱对象，然后逐步添加信息
				music.title=txtTitle.getText();
				music.author=txtAuthor.getText();
				music.date=txtDate.getText();
				music.pace=Integer.parseInt(txtPace.getText());
				music.part=Integer.parseInt(txtPart.getText());
				music.maxLength=Integer.parseInt(txtMaxLength.getText());
				music.standard = jbxSetTone.getSelectedIndex();
				if (music.standard >= 7)
					music.standard -= 12;
				music.note=new Note[music.part][music.maxLength];//初始化note数组
				music.noteCount=new int[music.part];
				music.instrument=new int[music.part];
				dispose();
				new AddPart(myPiano, music,0);
			}
		});
		
		
	}
	class AddPart extends JFrame{//这个对话框是点击新增伴奏的时候弹出的对话框，设置伴奏的音符数量、乐器
		JFrame frame=new JFrame("乐谱录入");
		JPanel panel=new JPanel();
		JPanel pButton=new JPanel();
		JLabel lblIntroduction=new JLabel();
		JLabel lblNoteCount= new JLabel("音符数量");
		JTextField txtNoteCount = new JTextField("1000",10);
		JLabel lblInstrument= new JLabel("乐器");
		JTextField txtInstrument=new JTextField("0",10);
		JButton btnConfirm=new JButton("确定");
		Music music ;
		Note note;
		int index;//表示这是第几个伴奏，每录增加1
		public AddPart(MyPiano myPiano,Music music,int index){
			this.music=music;
			this.index=index;
			setVisible(true);
			setSize(400, 200);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("请输入该伴奏的音符数和使用乐器");
			add(panel,BorderLayout.NORTH);
			add(pButton,BorderLayout.SOUTH);
			panel.add(lblIntroduction);
			panel.add(lblNoteCount);
			panel.add(txtNoteCount);
			panel.add(lblInstrument);
			panel.add(txtInstrument);
			pButton.add(btnConfirm);
			
			btnConfirm.addActionListener(new ActionListener() {//输入完新增伴奏的音符个数和乐器以后，点击确定按钮，打开新增音符的对话框
				public void actionPerformed(ActionEvent arg0) {
					music.instrument[index]=Integer.parseInt(txtInstrument.getText());
					new CreatFrame(myPiano, creatSettingFrame, music,index);
					dispose();
				}
			});	
		}
		
	}
	class CreatFrame extends JFrame{//这个对话框是录入每一个音符的时候用的
		JFrame frame=new JFrame("乐谱录入");
		JPanel panel1=new JPanel();//用来存放设置音长的按钮们
		JPanel pButton=new JPanel();//用来存放5*7个按钮
		JPanel panel2=new JPanel();//用来手动输入和点击下一个,在右侧
		JPanel panel3=new JPanel();//用来显示当前录入的音符数和历史信息，在下侧
		JPanel panel4=new JPanel();//用来修改和点击下一个,在右侧
		JButton btn[]=new JButton[9];//前5个分别表示音长，后两个表示是否升高或降低半个音,最后一个是休止符
		JButton btnFuDian=new JButton("浮点");
		JButton btnNote[]=new JButton[35];
		JLabel lblNote= new JLabel("音符(阿拉伯数字)");
		JTextField txtNote = new JTextField("-1",15);
		JLabel lblHigh= new JLabel("音高(几个八度)");
		JTextField txtHigh=new JTextField("0",15);
		JLabel lblHalf = new JLabel("半音");//是否升了半音，1代表升了半音，-1代表降了半音
		JTextField txtHalf =new JTextField("0",15);
		JTextField txtLength =new JTextField("1",15);
		JLabel lblLength=new JLabel("几分之一拍");
		JLabel lblCount=new JLabel("当前是第1个伴奏声部，已录制0个音符");
		JButton btnNext=new JButton("下一个");
		JButton btnFinish = new JButton("结束录入");
		JButton btnLast=new JButton("修改上一个");
		JButton btnAddPart= new JButton("新增伴奏");
		TextArea textArea=new TextArea();
		Music music ;
		CreatSettingFrame creatSettingFrame;
		String notes="";
		String stringNote="";
		int timeSum=2000;
		int time;
		int note;
		int index=0;
		String times="2000";
		int noteCount=1;
		int channelNum=0;
		public CreatFrame(MyPiano myPiano,CreatSettingFrame creatSettingFrame,Music music,int index){
			this.creatSettingFrame=creatSettingFrame;
			setVisible(true);
			setSize(650, 600);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(null);
			add(panel1);
			add(pButton);
			add(panel2);
			add(panel3);
			add(panel4);
			panel1.setBounds(0,0,420,100);
			pButton.setBounds(10,120,420,260);
			panel2.setBounds(440,0,180,200);
			panel3.setBounds(0,400,600,180);
			panel4.setBounds(440,220,180,180);
			pButton.setLayout(new GridLayout(5,7,10,10));
			btn[0] =new JButton("1拍");
			btn[1] =new JButton("1/2拍");
			btn[2] =new JButton("1/4拍");
			btn[3] =new JButton("2拍");
			btn[4] =new JButton("4拍");
			btn[5] =new JButton("浮点");
			btn[6] =new JButton("升半音");
			btn[7] =new JButton("降半音");
			btn[8] = new JButton("休止符");
			panel1.setLayout(null);
			panel3.setLayout(null);
			for(int i=0;i<=8;i++)panel1.add(btn[i]);
			for(int i=0;i<=4;i++)btn[i].setBounds(10+85*i, 10, 70, 40);
			for(int i=5;i<=8;i++)btn[i].setBounds(10+100*(i-5), 60, 90, 40);
			
			for(int i=0;i<35;i++){
				btnNote[i]=new JButton();
				pButton.add(btnNote[i]);
				btnNote[i].setText(String.valueOf(i%7+1)+"("+String.valueOf(i/7-2)+")");
			}
			panel2.add(lblNote);
			panel2.add(txtNote);
			panel2.add(lblHigh);
			panel2.add(txtHigh);
			panel2.add(lblHalf);
			panel2.add(txtHalf);
			panel2.add(lblLength);
			panel2.add(txtLength);
			panel3.add(textArea);
			panel3.add(lblCount);
			panel4.add(btnNext);
			panel4.add(btnLast);
			panel4.add(btnAddPart);
			panel4.add(btnFinish);
			panel4.setLayout(new GridLayout(4, 1,30,10));
			lblCount.setBounds(100, 0, 400, 30);
			textArea.setBounds(20, 30, 600, 100);
			
			for(int i=0;i<35;i++)
				btnNote[i].addActionListener(new NoteActionListener(i,this));
			
			for(int i=0;i<=8;i++)
				btn[i].addActionListener(new DurationActionListener(i,this));
			btnNext.addActionListener(new NextActionListener(music,this,index));
			btnLast.addActionListener(new LastActionListener(music,this,index));
			btnFinish.addActionListener(new ActionListener(){//结束录入歌谱
				public void actionPerformed(ActionEvent e) {
					int option=JOptionPane.showConfirmDialog(CreatFrame.this, "确定要结束吗");
					if (option == JOptionPane.YES_OPTION) {
						music.saveAsFile();
						dispose();
					}
				}
			});
			btnAddPart.addActionListener(new AddpartActionListener(myPiano,music,this,index));
			if(index+1==Integer.parseInt(txtPart.getText()))btnAddPart.setEnabled(false);
		}
		public String getDuration(String duration){
			double temp=4*Double.parseDouble(duration);
			int d=(int)temp;
			switch (d) {
			case 1:return "w";
			case 2:return "h";
			case 4:return "q";
			case 8:return "i";
			case 16:return "s";
			case 32:return "t";
			case 64:return "x";
			case 128:return "o";
			default:
				return "q";
			}
		}
		public int transformTone(int tone){
			switch (tone) {
			case 1:return 0;
			case 2:return 2;
			case 3:return 4;
			case 4:return 5;
			case 5:return 7;
			case 6:return 9;
			case 7:return 11;
			default:
				return 0;

			}
		}
		public int getNote(int tone,int octave){
			int note;
			if(tone==-1)return tone;//是休止符就不用处理，返回-1
			note=(5+octave)*12+transformTone(tone);
			return note;
		}
		
		public String getDuration(double duration){
			int d=(int)duration*4;
			switch (d) {
			case 1:return "w";
			case 2:return "h";
			case 4:return "q";
			case 8:return "i";
			case 16:return "s";
			case 32:return "t";
			case 64:return "x";
			case 128:return "o";
			default:
				return "q";
			}
		}
		public String getString(int note){
			String s=String.valueOf(note);
			if (channelNum == 9)
				channelNum++;// V9音轨特殊，不能播放声调，只能打鼓点，因此遇到 9跳过
			else if (channelNum == 16)
				channelNum = 0;// 达到16以后重新从0开始
			s = "V" + String.valueOf(channelNum) + " I"+instrument+" " + s + getDuration(Double.parseDouble(txtLength.getText()))+"  ";
			channelNum++;
			return s;
		}
		public String getTime(String notes){
			String[] noteString=notes.split("  ");
			String finalTimeString="";
			return finalTimeString;
		}
	}
	class NoteActionListener implements ActionListener{//画布中的若干按钮监听器，用于方便输入
		int i;
		CreatFrame creatFrame;
		public NoteActionListener(int i,CreatFrame creatFrame){
			this.i=i;
			this.creatFrame=creatFrame;
		}
		
		public void actionPerformed(ActionEvent e) {
			creatFrame.txtNote.setText(String.valueOf(i%7+1));
			creatFrame.txtHigh.setText(String.valueOf(i/7-2));
			for(int i=0;i<35;i++)creatFrame.btnNote[i].setBackground(null);
			creatFrame.btn[8].setBackground(null);//休止符变回无色
			creatFrame.btnNote[i].setBackground(Color.GREEN);
		}
	}
	class DurationActionListener implements ActionListener{//画布中的若干按钮监听器，用于方便输入
		int buttonIndex;
		CreatFrame creatFrame;
		public DurationActionListener(int i,CreatFrame creatFrame){
			this.buttonIndex=i;
			this.creatFrame=creatFrame;
		}
		
		public void actionPerformed(ActionEvent e) {
			switch (buttonIndex) {
			case 0:
				for(int i=0;i<5;i++)creatFrame.btn[i].setBackground(null);
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				creatFrame.txtLength.setText("1");
				break;
			case 1:
				for(int i=0;i<5;i++)creatFrame.btn[i].setBackground(null);
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				creatFrame.txtLength.setText("2");
				break;
			case 2:
				for(int i=0;i<5;i++)creatFrame.btn[i].setBackground(null);
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				creatFrame.txtLength.setText("4");
				break;
			case 3:
				for(int i=0;i<5;i++)creatFrame.btn[i].setBackground(null);
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				creatFrame.txtLength.setText("0.5");
				break;
			case 4:
				for(int i=0;i<5;i++)creatFrame.btn[i].setBackground(null);
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				creatFrame.txtLength.setText("0.25");
				break;
			case 5://浮点设置
				if(creatFrame.btn[buttonIndex].getBackground()==Color.GREEN){//原来绿色，现在变成无色
					creatFrame.btn[buttonIndex].setBackground(null);
				}
				else{
					creatFrame.btn[buttonIndex].setBackground(Color.GREEN);//原来无色，现在变成绿色
				}
					
				break;
			case 6://升半音按钮
				creatFrame.txtHalf.setText(String.valueOf(Integer.parseInt(creatFrame.txtHalf.getText())+1));
				creatFrame.btn[7].setBackground(null);
				creatFrame.btn[6].setBackground(Color.GREEN);
				break;
			case 7://降半音按钮
				creatFrame.txtHalf.setText(String.valueOf(Integer.parseInt(creatFrame.txtHalf.getText())-1));
				creatFrame.btn[6].setBackground(null);
				creatFrame.btn[7].setBackground(Color.GREEN);
				break;
			default://休止符
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				for(int i=0;i<35;i++)creatFrame.btnNote[i].setBackground(null);
				creatFrame.txtNote.setText("-1");
				break;
			}
			if(buttonIndex!=5)creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
		}
	}
	class NextActionListener implements ActionListener{//下一个note的按钮监听器，用于录入下一个音符
		Music music;
		CreatFrame creatFrame;
		int index;
		public NextActionListener(Music music ,CreatFrame creatFrame,int index){
			this.music=music;
			this.creatFrame=creatFrame;
			this.index=index;
		}
		public void actionPerformed(ActionEvent e) {
			Note note=new Note(myPiano,music,index,music.instrument[index],music.noteCount[index]);
			note.tone=Integer.parseInt(creatFrame.txtNote.getText());
			note.octave=Integer.parseInt(creatFrame.txtHigh.getText());
			note.character=creatFrame.getNote(note.tone, note.octave);
			note.character+=tone+Integer.parseInt(creatFrame.txtHalf.getText());//获取的音符是输入的音符 转换以后加上标准音符和是否有半音差距的结果
			note.durationString=creatFrame.getDuration(creatFrame.txtLength.getText());//该拍是几分之一拍
			note.isFudian=(creatFrame.btn[5].getBackground()==Color.GREEN);
			music.addNote(note,index);//把这个新的note添加到原有的music中
			creatFrame.textArea.append("第"+music.noteCount[index]+"个音符是"+String.valueOf(note.tone)+",");
			creatFrame.textArea.append("音高是"+String.valueOf(note.octave)+",");
			creatFrame.textArea.append("播放拍数为"+String.valueOf(creatFrame.txtLength.getText())+"分之一拍,");
			creatFrame.textArea.append("浮点有无是"+String.valueOf(note.isFudian)+",");
			creatFrame.textArea.append("数字化音符是"+String.valueOf(note.character)+note.durationString+"\r\n");
			creatFrame.lblCount.setText("当前是第"+(index+1)+"个伴奏声部，已录制"+String.valueOf(music.noteCount[index])+"个音符");
			for(int i=0;i<35;i++)creatFrame.btnNote[i].setBackground(null);//所有音符按钮变成无色，但保留音长设置
			creatFrame.btn[8].setBackground(null);//休止符和浮点也变回无色
			creatFrame.btn[5].setBackground(null);//休止符和浮点也变回无色
			creatFrame.btn[6].setBackground(null);//升降半音变回无色
			creatFrame.btn[7].setBackground(null);
			creatFrame.txtHalf.setText("0");
		}
		
	}
	class LastActionListener implements ActionListener{
		Music music;
		CreatFrame creatFrame;
		int index;
		public LastActionListener(Music music ,CreatFrame creatFrame,int index){
			this.music=music;
			this.creatFrame=creatFrame;
			this.index=index;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(music.noteCount[index]>=1){//合法操作，修改上一个音符，就是把音符数减小，后面录入时会覆盖
				creatFrame.textArea.append("您点击了修改上一个音符按钮，请重新录入第"+music.noteCount[index]+"个音符\r\n");
				music.noteCount[index]--;
			}
			else JOptionPane.showMessageDialog(null, "您尚未录入音符！");//非法操作，错误提示
		}
	}
	class AddpartActionListener implements ActionListener{
		Music music;
		CreatFrame creatFrame;
		int index;
		
		public AddpartActionListener(MyPiano myPiano,Music music ,CreatFrame creatFrame,int index){
			this.music=music;
			this.creatFrame=creatFrame;
			this.index=index;
		}
		public void actionPerformed(ActionEvent e) {
			index++;
			new AddPart(myPiano, music, index);
			creatFrame.dispose();
		}
		
	}
}


