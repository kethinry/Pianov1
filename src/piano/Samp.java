package piano;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

class Samp extends JFrame{
	MyPiano myPiano;
	String path1="C://Users//giria//Pictures//Saved Pictures//betrayal_by_wlop-d9jj611.jpg";
	String path2="C://Users//giria//Pictures//Saved Pictures//nora_by_wlop-d9i640x.jpg";
	String path3="C://Sunset.jpg";
	String path4="C://Users//giria//Pictures//Saved Pictures//saber_lily_by_wlop-d9e66kl.jpg";
	Image image4 = new ImageIcon(path4).getImage();
	Image image3 = new ImageIcon(path3).getImage();
	Image image2 = new ImageIcon(path2).getImage();
	Image image1 = new ImageIcon(path1).getImage();
	JLabel imgLabel1 = new aLabel(image1);
	JLabel imgLabel2 = new aLabel(image2);
	JLabel imgLabel3 = new aLabel(image3);
	JLabel imgLabel4 = new aLabel(image4);
	public Samp(MyPiano myPiano){
		this.myPiano=myPiano;
		myPiano.f.getLayeredPane().add(imgLabel1, new Integer(Integer.MIN_VALUE));
		myPiano.f.getLayeredPane().add(imgLabel2, new Integer(Integer.MIN_VALUE));
		myPiano.f.getLayeredPane().add(imgLabel3, new Integer(Integer.MIN_VALUE));
		myPiano.f.getLayeredPane().add(imgLabel4, new Integer(Integer.MIN_VALUE));
		imgLabel1.setVisible(false);
		imgLabel2.setVisible(false);
		imgLabel3.setVisible(false);
		imgLabel4.setVisible(false);
		
		Container cp = myPiano.f.getContentPane();
		((JPanel) cp).setOpaque(false); 
		myPiano.f.setResizable(false);
		myPiano.f.setVisible(true);
		imgLabel1.setBounds(0, 0, myPiano.f.getWidth(),myPiano.f.getHeight());
		imgLabel2.setBounds(0, 0, myPiano.f.getWidth(),myPiano.f.getHeight());
		imgLabel3.setBounds(0, 0, myPiano.f.getWidth(),myPiano.f.getHeight());
		imgLabel4.setBounds(0, 0, myPiano.f.getWidth(),myPiano.f.getHeight());
	 }
	//内部类 完成密铺
	class aLabel extends JLabel {
		private Image image;
		public aLabel(Image image){
			this.image = image;
		}
		@Override
		public void paintComponent(Graphics g){
			 super.paintComponent(g);
			 int x = myPiano.f.getWidth();
			 int y = myPiano.f.getHeight();
			 g.drawImage(image, 0, 0, x, y, null);
		 }

	 }
}