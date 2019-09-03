package piano;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class StyleFrame extends JFrame {
	MyPiano myPiano;
	JFrame f = new JFrame("Style");
	JPanel pSetStyle = new JPanel();
	JPanel pConfirmOrCancel = new JPanel();
	JButton btnConfirm = new JButton("ȷ��");
	JButton btnCancel = new JButton("ȡ��");
	JLabel lblSetStyle = new JLabel("������");
	JComboBox jbxSetStyle = new JComboBox();
	Writer writer=null;
	public StyleFrame(MyPiano myPiano) {
		this.myPiano=myPiano;
		f.setVisible(true);
		f.setSize(400, 400);
		f.setTitle("������");
		f.setLayout(new GridLayout(3, 2));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.add(pSetStyle);
		f.add(pConfirmOrCancel);
		pSetStyle.add(lblSetStyle);
		pSetStyle.add(jbxSetStyle);
		jbxSetStyle.addItem("��ͺ�");
		jbxSetStyle.addItem("ľ�ʸе�XP���");
		jbxSetStyle.addItem("Mac OS���");
		jbxSetStyle.addItem("��XP");
		jbxSetStyle.addItem("��ɫ���");
		jbxSetStyle.addItem("java���ط��");
		jbxSetStyle.addItem("��ɫ���");
		jbxSetStyle.addItem("�����ʸ�");
		jbxSetStyle.setSelectedIndex(myPiano.chooseIndex);
		pConfirmOrCancel.add(btnConfirm);
		pConfirmOrCancel.add(btnCancel);

		//com.jtattoo.plaf.noire.NoireLookAndFeel  ��ͺ�
	    /*   com.jtattoo.plaf.smart.SmartLookAndFeel ľ�ʸ�+xp���
	       com.jtattoo.plaf.mint.MintLookAndFeel  ��Բ��ť+��ɫ��ť����
	       com.jtattoo.plaf.mcwin.McWinLookAndFeel ��Բ��ť+��ɫ��ť����
	       com.jtattoo.plaf.luna.LunaLookAndFeel  ��XP���
	       com.jtattoo.plaf.hifi.HiFiLookAndFeel  ��ɫ���
	       com.jtattoo.plaf.fast.FastLookAndFeel  ��ͨswing���+��ɫ�߿�
	       com.jtattoo.plaf.bernstein.BernsteinLookAndFeel  ��ɫ���
	       com.jtattoo.plaf.aluminium.AluminiumLookAndFeel ��Բ��ť+����ɫ��ť����+�����ʸУ�Ĭ�ϣ�
	*/
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPiano.chooseIndex=jbxSetStyle.getSelectedIndex();
				switch(jbxSetStyle.getSelectedIndex()){
				case 0:try {
					
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						String temp="";
						writer.write("com.jtattoo.plaf.noire.NoireLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
						UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
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
					}break;
				case 1:try {
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						String temp="";
						writer.write("com.jtattoo.plaf.smart.SmartLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
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
				}break;
				case 2:try {
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						String temp="";
						writer.write("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
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
				}break;
				case 3:try {
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						String temp="";
						writer.write("com.jtattoo.plaf.luna.LunaLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
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
				}break;
				case 4:try {
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						writer.write("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
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
				}break;
				case 5:try {
					
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						writer.write("com.jtattoo.plaf.fast.FastLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
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
				}break;
				case 6:try {
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						String temp="";
						writer.write("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
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
				}break;
				case 7:try {
					try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//style");
						String temp="";
						writer.write("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
				        
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
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
				}break;
				default:break;
				}
				for(int i=0;i<60;i++)
				myPiano.btn[i].updateUI();
				for(int i=0;i<52;i++)
				myPiano.btnPianoWhite[i].updateUI();
				for(int i=0;i<37;i++)
				myPiano.btnPianoBlack[i].updateUI();
				myPiano.btnStart.updateUI();
				myPiano.btnStatistic.updateUI();
				myPiano.mBar.updateUI();
				myPiano.mFile.updateUI();
				myPiano.mHelp.updateUI();
				
				f.dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
	}
}
