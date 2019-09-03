package piano;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.*;

import piano.Samp.aLabel;

import java.awt.event.*;

import java.awt.Container;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.event.*;

class SkinFrame extends JFrame {
	MyPiano myPiano;
	
	int flag = 0;// 0 无背景 1：图片1 2：图片2

	public SkinFrame(MyPiano myPiano) {
		// this.myPiano=myPiano;
		JFrame f = new JFrame("Skin");
		f.setVisible(true);
		f.setSize(500, 800);
		String path1 = ".//image//背景1.jpg";
		String path2 = ".//image//背景2.jpg";
		String path3 = ".//image//背景3.jpg";
		String path4 = ".//image//背景4.jpg";
		Image image1 = new ImageIcon(path1).getImage();
		Image image2 = new ImageIcon(path2).getImage();
		Image image3 = new ImageIcon(path3).getImage();
		Image image4 = new ImageIcon(path4).getImage();
		JLabel imgLabelbig1 = new aLabel1(image1);
		JLabel imgLabelbig2 = new aLabel1(image2);
		JLabel imgLabelbig3 = new aLabel1(image3);
		JLabel imgLabelbig4 = new aLabel1(image4);
		JLabel imgLabelsmall1 = new aLabel2(image1);
		JLabel imgLabelsmall2 = new aLabel2(image2);
		JLabel imgLabelsmall3 = new aLabel2(image3);
		JLabel imgLabelsmall4 = new aLabel2(image4);
		JButton btn = new JButton("确认");
		f.add(imgLabelbig1);
		f.add(imgLabelbig2);
		f.add(imgLabelbig3);
		f.add(imgLabelbig4);
		f.add(btn);
		imgLabelbig1.setVisible(true);
		imgLabelbig2.setVisible(false);
		imgLabelbig3.setVisible(false);
		imgLabelbig4.setVisible(false);
		f.add(imgLabelsmall1);
		f.add(imgLabelsmall2);
		f.add(imgLabelsmall3);
		f.add(imgLabelsmall4);
		f.getLayeredPane().add(imgLabelsmall1, new Integer(Integer.MAX_VALUE));
		f.getLayeredPane().add(imgLabelsmall4, new Integer(Integer.MAX_VALUE));
		Container cp = f.getContentPane();
		((JPanel) cp).setOpaque(false);
		// f.setResizable(true);
		imgLabelbig1.setBounds(0, 0, f.getWidth(), f.getHeight());
		imgLabelbig2.setBounds(0, 0, f.getWidth(), f.getHeight());
		imgLabelbig3.setBounds(0, 0, f.getWidth(), f.getHeight());
		imgLabelbig4.setBounds(0, 0, f.getWidth(), f.getHeight());

		// 前面两个参数是xy坐标
		imgLabelsmall1.setBounds(200, 500, f.getWidth(), f.getHeight());
		imgLabelsmall2.setBounds(200, 300, f.getWidth(), f.getHeight());
		imgLabelsmall3.setBounds(0, 300, f.getWidth(), f.getHeight());
		imgLabelsmall4.setBounds(0, 500, f.getWidth(), f.getHeight());
		btn.setBounds(150, 250, 80, 30);
		imgLabelsmall1.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// imgLabel1.setVisible(false);
			}

			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}

			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}

			public void mousePressed(MouseEvent e) {

				imgLabelbig1.setVisible(true);
				imgLabelbig3.setVisible(false);
				imgLabelbig2.setVisible(false);
				imgLabelbig4.setVisible(false);
				flag = 1;
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		imgLabelsmall2.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}

			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}

			public void mousePressed(MouseEvent e) {
				imgLabelbig1.setVisible(false);
				imgLabelbig3.setVisible(false);
				imgLabelbig2.setVisible(true);
				imgLabelbig4.setVisible(false);
				flag = 2;
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		imgLabelsmall3.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}

			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}

			public void mousePressed(MouseEvent e) {

				imgLabelbig1.setVisible(false);
				imgLabelbig3.setVisible(true);
				imgLabelbig2.setVisible(false);
				imgLabelbig4.setVisible(false);
				flag = 3;
			}

			public void mouseReleased(MouseEvent e) {

			}
		});
		imgLabelsmall4.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}

			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}

			public void mousePressed(MouseEvent e) {
				imgLabelbig1.setVisible(false);
				imgLabelbig3.setVisible(false);
				imgLabelbig2.setVisible(false);
				imgLabelbig4.setVisible(true);
				flag = 4;

			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    Samp what = new Samp(myPiano);
		    myPiano.background.setVisible(false);
				if (flag == 4) {
					((JPanel)myPiano.f.getContentPane()).setOpaque(false);
					ImageIcon img = new ImageIcon("image/背景4.jpg"); 
					myPiano.background = new JLabel(img);
					myPiano.background.setBounds(0, 0, myPiano.f.getWidth(), myPiano.f.getHeight());
					myPiano.f.getLayeredPane().add(myPiano.background, new Integer(Integer.MIN_VALUE));
					setVisible(false);
				}
				if (flag == 1) {
					((JPanel)myPiano.f.getContentPane()).setOpaque(false);
					ImageIcon img = new ImageIcon("image/背景1.jpg"); 
					myPiano.background = new JLabel(img);
					myPiano.background.setBounds(0, 0, myPiano.f.getWidth(), myPiano.f.getHeight());
					myPiano.f.getLayeredPane().add(myPiano.background, new Integer(Integer.MIN_VALUE));
					setVisible(false);
				}
				if (flag == 2) {
					((JPanel)myPiano.f.getContentPane()).setOpaque(false);
					ImageIcon img = new ImageIcon("image/背景2.jpg");
					myPiano.background = new JLabel(img);
					myPiano.background.setBounds(0, 0, myPiano.f.getWidth(), myPiano.f.getHeight());
					myPiano.f.getLayeredPane().add(myPiano.background, new Integer(Integer.MIN_VALUE));
					setVisible(false);
				}
				if (flag == 3) {
					((JPanel)myPiano.f.getContentPane()).setOpaque(false);
					ImageIcon img = new ImageIcon("image/背景3.jpg"); 
					myPiano.background = new JLabel(img);
					myPiano.background.setBounds(0, 0, myPiano.f.getWidth(), myPiano.f.getHeight());
					myPiano.f.getLayeredPane().add(myPiano.background, new Integer(Integer.MIN_VALUE));
					setVisible(false);
				}
				OutputStreamWriter writer = null;
				switch(flag){
				case 1:try {
						writer=new FileWriter(".//data//skin");
						String temp="";
						writer.write("1");
				        
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
					}break;
				case 2:try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//skin");
						String temp="";
						writer.write("2");
				        
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
					}break;
				case 3:try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//skin");
						String temp="";
						writer.write("3");
				        
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
					}break;
				case 4:try {
						//fout=new FileOutputStream(".//data//style");
						writer=new FileWriter(".//data//skin");
						String temp="";
						writer.write("4");
				        
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
					}break;
				default:break;
				}
				f.dispose();
			}
		});
	}

	// 此处的xy设置图片大小
	class aLabel1 extends JLabel {// 主框大小
		private Image image;

		public aLabel1(Image image) {
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x = 300;
			int y = 225;
			g.drawImage(image, 30, 0, x, y, null);
		}

	}

	class aLabel2 extends JLabel {// 副框大小
		private Image image;

		public aLabel2(Image image) {
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x = 150;
			int y = 112;
			g.drawImage(image, 30, 0, x, y, null);
		}

	}
}
