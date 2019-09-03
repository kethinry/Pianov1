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
	JButton btnConfirm = new JButton("确定");
	JButton btnCancel = new JButton("取消");
	JLabel lblSetStyle = new JLabel("主题风格");
	JComboBox jbxSetStyle = new JComboBox();
	Writer writer=null;
	public StyleFrame(MyPiano myPiano) {
		this.myPiano=myPiano;
		f.setVisible(true);
		f.setSize(400, 400);
		f.setTitle("主题风格");
		f.setLayout(new GridLayout(3, 2));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.add(pSetStyle);
		f.add(pConfirmOrCancel);
		pSetStyle.add(lblSetStyle);
		pSetStyle.add(jbxSetStyle);
		jbxSetStyle.addItem("柔和黑");
		jbxSetStyle.addItem("木质感的XP风格");
		jbxSetStyle.addItem("Mac OS风格");
		jbxSetStyle.addItem("纯XP");
		jbxSetStyle.addItem("黑色风格");
		jbxSetStyle.addItem("java朴素风格");
		jbxSetStyle.addItem("黄色风格");
		jbxSetStyle.addItem("金属质感");
		jbxSetStyle.setSelectedIndex(myPiano.chooseIndex);
		pConfirmOrCancel.add(btnConfirm);
		pConfirmOrCancel.add(btnCancel);

		//com.jtattoo.plaf.noire.NoireLookAndFeel  柔和黑
	    /*   com.jtattoo.plaf.smart.SmartLookAndFeel 木质感+xp风格
	       com.jtattoo.plaf.mint.MintLookAndFeel  椭圆按钮+黄色按钮背景
	       com.jtattoo.plaf.mcwin.McWinLookAndFeel 椭圆按钮+绿色按钮背景
	       com.jtattoo.plaf.luna.LunaLookAndFeel  纯XP风格
	       com.jtattoo.plaf.hifi.HiFiLookAndFeel  黑色风格
	       com.jtattoo.plaf.fast.FastLookAndFeel  普通swing风格+蓝色边框
	       com.jtattoo.plaf.bernstein.BernsteinLookAndFeel  黄色风格
	       com.jtattoo.plaf.aluminium.AluminiumLookAndFeel 椭圆按钮+翠绿色按钮背景+金属质感（默认）
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
