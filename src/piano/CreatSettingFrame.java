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
	JLabel lblTitle=new JLabel("����");
	JTextField txtTitle = new JTextField("δ����",10);
	JLabel lblAuthor=new JLabel("����");
	JTextField txtAuthor = new JTextField("δ֪����",10);
	JLabel lblDate=new JLabel("ʱ��");
	JTextField txtDate = new JTextField("2018.9",10);
	JLabel lblPace=new JLabel("�ٶȣ���/���ӣ�");
	JTextField txtPace=new JTextField("80",10);
	JLabel lblBeat1=new JLabel("����");
	JTextField txtUp=new JTextField("4",5);//ÿС���м���
	JTextField txtDown = new JTextField("4",5);//�Լ�������Ϊһ��
	JLabel lblBeat2=new JLabel("/");
	JLabel lblSetTone = new JLabel("��������1=");
	JComboBox jbxSetTone = new JComboBox();// ������
	JLabel lblPart=new JLabel("������");
	JTextField txtPart=new JTextField("1",5);
	JLabel lblMaxLength=new JLabel("���������");
	JTextField txtMaxLength=new JTextField("1000",8);
	JButton btnConfirm=new JButton("��ʼ¼������");
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
		setTitle("���׻�����Ϣ����");
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
		
		textArea1.setText("�м�Ŀո�/�ո��ʾ�������Ǽ������ӵģ��������Ƚ׶�¼��û�а���\n���ǲ�����");
		
		btnConfirm.addActionListener(new ActionListener() {//���ȷ�ϰ�ť�Ժ󣬴������Ӱ���ĶԻ������ӵ�һ�����ࣨҲ���������ɣ�
			public void actionPerformed(ActionEvent e) {
				Music music=new Music(myPiano);//����һ�����׶���Ȼ���������Ϣ
				music.title=txtTitle.getText();
				music.author=txtAuthor.getText();
				music.date=txtDate.getText();
				music.pace=Integer.parseInt(txtPace.getText());
				music.part=Integer.parseInt(txtPart.getText());
				music.maxLength=Integer.parseInt(txtMaxLength.getText());
				music.standard = jbxSetTone.getSelectedIndex();
				if (music.standard >= 7)
					music.standard -= 12;
				music.note=new Note[music.part][music.maxLength];//��ʼ��note����
				music.noteCount=new int[music.part];
				music.instrument=new int[music.part];
				dispose();
				new AddPart(myPiano, music,0);
			}
		});
		
		
	}
	class AddPart extends JFrame{//����Ի����ǵ�����������ʱ�򵯳��ĶԻ������ð������������������
		JFrame frame=new JFrame("����¼��");
		JPanel panel=new JPanel();
		JPanel pButton=new JPanel();
		JLabel lblIntroduction=new JLabel();
		JLabel lblNoteCount= new JLabel("��������");
		JTextField txtNoteCount = new JTextField("1000",10);
		JLabel lblInstrument= new JLabel("����");
		JTextField txtInstrument=new JTextField("0",10);
		JButton btnConfirm=new JButton("ȷ��");
		Music music ;
		Note note;
		int index;//��ʾ���ǵڼ������࣬ÿ¼����1
		public AddPart(MyPiano myPiano,Music music,int index){
			this.music=music;
			this.index=index;
			setVisible(true);
			setSize(400, 200);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("������ð������������ʹ������");
			add(panel,BorderLayout.NORTH);
			add(pButton,BorderLayout.SOUTH);
			panel.add(lblIntroduction);
			panel.add(lblNoteCount);
			panel.add(txtNoteCount);
			panel.add(lblInstrument);
			panel.add(txtInstrument);
			pButton.add(btnConfirm);
			
			btnConfirm.addActionListener(new ActionListener() {//������������������������������Ժ󣬵��ȷ����ť�������������ĶԻ���
				public void actionPerformed(ActionEvent arg0) {
					music.instrument[index]=Integer.parseInt(txtInstrument.getText());
					new CreatFrame(myPiano, creatSettingFrame, music,index);
					dispose();
				}
			});	
		}
		
	}
	class CreatFrame extends JFrame{//����Ի�����¼��ÿһ��������ʱ���õ�
		JFrame frame=new JFrame("����¼��");
		JPanel panel1=new JPanel();//����������������İ�ť��
		JPanel pButton=new JPanel();//�������5*7����ť
		JPanel panel2=new JPanel();//�����ֶ�����͵����һ��,���Ҳ�
		JPanel panel3=new JPanel();//������ʾ��ǰ¼�������������ʷ��Ϣ�����²�
		JPanel panel4=new JPanel();//�����޸ĺ͵����һ��,���Ҳ�
		JButton btn[]=new JButton[9];//ǰ5���ֱ��ʾ��������������ʾ�Ƿ����߻򽵵Ͱ����,���һ������ֹ��
		JButton btnFuDian=new JButton("����");
		JButton btnNote[]=new JButton[35];
		JLabel lblNote= new JLabel("����(����������)");
		JTextField txtNote = new JTextField("-1",15);
		JLabel lblHigh= new JLabel("����(�����˶�)");
		JTextField txtHigh=new JTextField("0",15);
		JLabel lblHalf = new JLabel("����");//�Ƿ����˰�����1�������˰�����-1�����˰���
		JTextField txtHalf =new JTextField("0",15);
		JTextField txtLength =new JTextField("1",15);
		JLabel lblLength=new JLabel("����֮һ��");
		JLabel lblCount=new JLabel("��ǰ�ǵ�1��������������¼��0������");
		JButton btnNext=new JButton("��һ��");
		JButton btnFinish = new JButton("����¼��");
		JButton btnLast=new JButton("�޸���һ��");
		JButton btnAddPart= new JButton("��������");
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
			btn[0] =new JButton("1��");
			btn[1] =new JButton("1/2��");
			btn[2] =new JButton("1/4��");
			btn[3] =new JButton("2��");
			btn[4] =new JButton("4��");
			btn[5] =new JButton("����");
			btn[6] =new JButton("������");
			btn[7] =new JButton("������");
			btn[8] = new JButton("��ֹ��");
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
			btnFinish.addActionListener(new ActionListener(){//����¼�����
				public void actionPerformed(ActionEvent e) {
					int option=JOptionPane.showConfirmDialog(CreatFrame.this, "ȷ��Ҫ������");
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
			if(tone==-1)return tone;//����ֹ���Ͳ��ô�������-1
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
				channelNum++;// V9�������⣬���ܲ���������ֻ�ܴ�ĵ㣬������� 9����
			else if (channelNum == 16)
				channelNum = 0;// �ﵽ16�Ժ����´�0��ʼ
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
	class NoteActionListener implements ActionListener{//�����е����ɰ�ť�����������ڷ�������
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
			creatFrame.btn[8].setBackground(null);//��ֹ�������ɫ
			creatFrame.btnNote[i].setBackground(Color.GREEN);
		}
	}
	class DurationActionListener implements ActionListener{//�����е����ɰ�ť�����������ڷ�������
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
			case 5://��������
				if(creatFrame.btn[buttonIndex].getBackground()==Color.GREEN){//ԭ����ɫ�����ڱ����ɫ
					creatFrame.btn[buttonIndex].setBackground(null);
				}
				else{
					creatFrame.btn[buttonIndex].setBackground(Color.GREEN);//ԭ����ɫ�����ڱ����ɫ
				}
					
				break;
			case 6://��������ť
				creatFrame.txtHalf.setText(String.valueOf(Integer.parseInt(creatFrame.txtHalf.getText())+1));
				creatFrame.btn[7].setBackground(null);
				creatFrame.btn[6].setBackground(Color.GREEN);
				break;
			case 7://��������ť
				creatFrame.txtHalf.setText(String.valueOf(Integer.parseInt(creatFrame.txtHalf.getText())-1));
				creatFrame.btn[6].setBackground(null);
				creatFrame.btn[7].setBackground(Color.GREEN);
				break;
			default://��ֹ��
				creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
				for(int i=0;i<35;i++)creatFrame.btnNote[i].setBackground(null);
				creatFrame.txtNote.setText("-1");
				break;
			}
			if(buttonIndex!=5)creatFrame.btn[buttonIndex].setBackground(Color.GREEN);
		}
	}
	class NextActionListener implements ActionListener{//��һ��note�İ�ť������������¼����һ������
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
			note.character+=tone+Integer.parseInt(creatFrame.txtHalf.getText());//��ȡ����������������� ת���Ժ���ϱ�׼�������Ƿ��а������Ľ��
			note.durationString=creatFrame.getDuration(creatFrame.txtLength.getText());//�����Ǽ���֮һ��
			note.isFudian=(creatFrame.btn[5].getBackground()==Color.GREEN);
			music.addNote(note,index);//������µ�note��ӵ�ԭ�е�music��
			creatFrame.textArea.append("��"+music.noteCount[index]+"��������"+String.valueOf(note.tone)+",");
			creatFrame.textArea.append("������"+String.valueOf(note.octave)+",");
			creatFrame.textArea.append("��������Ϊ"+String.valueOf(creatFrame.txtLength.getText())+"��֮һ��,");
			creatFrame.textArea.append("����������"+String.valueOf(note.isFudian)+",");
			creatFrame.textArea.append("���ֻ�������"+String.valueOf(note.character)+note.durationString+"\r\n");
			creatFrame.lblCount.setText("��ǰ�ǵ�"+(index+1)+"��������������¼��"+String.valueOf(music.noteCount[index])+"������");
			for(int i=0;i<35;i++)creatFrame.btnNote[i].setBackground(null);//����������ť�����ɫ����������������
			creatFrame.btn[8].setBackground(null);//��ֹ���͸���Ҳ�����ɫ
			creatFrame.btn[5].setBackground(null);//��ֹ���͸���Ҳ�����ɫ
			creatFrame.btn[6].setBackground(null);//�������������ɫ
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
			if(music.noteCount[index]>=1){//�Ϸ��������޸���һ�����������ǰ���������С������¼��ʱ�Ḳ��
				creatFrame.textArea.append("��������޸���һ��������ť��������¼���"+music.noteCount[index]+"������\r\n");
				music.noteCount[index]--;
			}
			else JOptionPane.showMessageDialog(null, "����δ¼��������");//�Ƿ�������������ʾ
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


