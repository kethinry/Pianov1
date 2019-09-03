package piano;


import java.awt.Graphics;
import java.lang.*;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class WuXianPu extends JLabel {
	MyPiano myPiano;
	JLabel[] lblNote ;
	JLabel[] label_note;
	ImageIcon img ; 
	ImageIcon img1 ;
	int count=0 ;
	int count_tmp=0;
	String scoreString;// 分离得到的歌谱的字符串数组
	String[] notes;
	String[] bitString;// 节奏数组
	String[] keyString;// 音高数组
	int key[];// 转化为int型的音高数组
	// 空白五线谱的参数
	int x_score;
	int y_score;
	int height_score;
	int width_score;
	int left_bound;
	int right_bound;
	int high_zero_y;
	int high_five_y;
	int low_zero_y;
	int low_five_y;
	int d;
	int d_x;
	// 音符的参数
	int x_note=0;
	int[] y_note;
	int height_note;
	int width_note;
	// 音符的种类
	int type;
	boolean judge;
	
	//xhf写的
	long currentTime,lastTime;
	// 分离和弦
	private void split_notes(String args) {
		notes = scoreString.split("\\+");
	}

	private String getBit(String note) {
		String bit = note.substring(2, 3);
		return bit;
	}

	private String getKey(String note) {
		String key = note.substring(0, 2);
		return key;
	}

    private int  getYNote(int key) {
        int ynote = 0;
        
        switch (key) {
        // 出现在高音
        case 60:
            ynote = high_zero_y +2*d;
            break;
        case 61: 
        	ynote = high_zero_y +2*d;
            break;
        case 62:
            ynote = high_zero_y + d;
            break;
        case 63:
            ynote = high_zero_y + d;
            break;
        case 64:
            ynote = high_zero_y ;
            break;
        case 65:
            ynote = high_zero_y -  d;
            break;
        case 66:
            ynote = high_zero_y -  d;
            break;
        case 67:
            ynote = high_zero_y - 2 * d;
            break;
        case 68:
            ynote = high_zero_y - 2 * d;
            break;
        case 69:
            ynote = high_zero_y - 3 * d;
            break;
        case 70:
            ynote = high_zero_y - 4 * d;
            break;
        case 71:
            ynote = high_zero_y - 4 * d;
            break;
        case 72:
            ynote = high_zero_y - 5 * d;
            break;
        case 73:
            ynote = high_zero_y - 5 * d;
            break;
        case 74:
            ynote = high_zero_y - 6 * d;
            break;
        case 75:
            ynote = high_zero_y - 6 * d;
            break;
        case 76:
            ynote = high_zero_y - 7 * d;
            break;
        case 77:
            ynote = high_zero_y - 8 * d;
            break;
        case 78:
            ynote = high_zero_y - 8 * d;
            break;
        case 79:
            ynote = high_zero_y - 9 * d;
            break;
        case 80:
            ynote = high_zero_y - 9 * d;
            break;
        case 81:case 82:
            ynote = high_zero_y - 10 * d;
            break;
        case 83:
            ynote = high_zero_y - 11 * d;
            break;
         // 出现在低音
        case 41:
            ynote = low_zero_y+d;
            break;
        case 42:
            ynote = low_zero_y+d;
            break;
        case 43:
            ynote = low_zero_y ;
            break;
        case 44:
            ynote = low_zero_y ;
            break;
        case 45:
            ynote = low_zero_y -  d;
            break;
        case 46:
            ynote = low_zero_y -  d;
            break;
        case 47:
            ynote = low_zero_y - 2* d;
            break;
        case 48:
            ynote = low_zero_y - 3 * d;
            break;
        case 49:
            ynote = low_zero_y - 3 * d;
            break;
        case 50:
            ynote = low_zero_y - 4 * d;
            
            break;
        case 51:
            ynote = low_zero_y - 4 * d;
            
            break;
        case 52:
            ynote = low_zero_y - 5 * d;
            break;
        case 53:
            ynote = low_zero_y - 6 * d;
            break;
        case 54:
            ynote = low_zero_y - 6 * d;
            break;
        case 55:
            ynote = low_zero_y - 7 * d;
            break;
        case 56:
            ynote = low_zero_y - 7 * d;
            break;
        case 57:
            ynote = low_zero_y - 8 * d;
            break;
        case 58:
            ynote = low_zero_y - 8 * d;
            break;
        case 59:
            ynote = low_zero_y - 9 * d;
            break;
        default:
        	ynote = 200;
            break;
        }
        return ynote;
    }
    
	// 将返回的转换成需要的最终形式
	private void StringSplit() {
		// 判断有无和弦

		if (judge) {// 有和弦
			split_notes(scoreString);
			for (int i = 0; i < notes.length; i++) {
				
				keyString[i] = getKey(notes[i]);
				bitString[i] = getBit(notes[i]);
				key[i] = Integer.parseInt(keyString[i]);
				y_note[i]=getYNote(key[i]);

				
				count_tmp++;
			}
			
		} else {
			keyString[0] = getKey(scoreString);
			bitString[0] = getBit(scoreString);
			key[0] = Integer.parseInt(keyString[0]);
			y_note[0]=getYNote(key[0]);

		}

	}
	public void addNote(String str) {
		scoreString = str;
		judge = str.contains("+");
		StringSplit();
		width_note=30;
		height_note=50;
		currentTime=System.currentTimeMillis();
		if(currentTime-lastTime>=30){//两个音符的时间差距很大，不是和弦，应该往后移动
			if (x_note >= right_bound)/* 原来是2 **/ {
				this.getGraphics().drawImage(img.getImage(), 0, 0,580,135, img.getImageObserver());
				x_note = left_bound;
			}
			else{
				x_note += d_x;
			}
		}
		lastTime = currentTime;
		
		if (judge) {		
			
			for (int i = 0; i < count_tmp; i++) {

				if(bitString[i].equals("h")){
					img1 = new ImageIcon("image/note1.png");
				}
				else if(bitString[i].equals("w")){
					img1 = new ImageIcon("image/note2.png");
					width_note=30*17/44;
					height_note=50*13/44;
					
				}
				else if(bitString[i].equals("q")){
					img1 = new ImageIcon("image/note3.png");
				}
				else if(bitString[i].equals("s")){
					img1 = new ImageIcon("image/note4.png");
				}
				else {
					img1 = new ImageIcon("image/note4.png");
				}
				if(Integer.parseInt(keyString[i])>=0)this.getGraphics().drawImage(img1.getImage(), x_note, y_note[i] - height_note * 22/ 45, height_note, width_note, img1.getImageObserver());
			}
			
		}
		else {
			if(bitString[0].equals("h")){
				img1 = new ImageIcon("image/note1.png");
			}
			else if(bitString[0].equals("w")){
				img1 = new ImageIcon("image/note2.png");
				width_note=30*17/44;
				height_note=50*13/44;
			}
			else if(bitString[0].equals("q")){
				img1 = new ImageIcon("image/note3.png");
			}
			else if(bitString[0].equals("s")){
				img1 = new ImageIcon("image/note4.png");
			}
			else {
				img1 = new ImageIcon("image/note4.png");
			}
			this.getGraphics().drawImage(img1.getImage(), x_note, y_note[0] - height_note * 22/ 45, height_note, width_note, img1.getImageObserver());
		}
		
			
		count++;
	}
	
	public WuXianPu(MyPiano myPiano) {
		lastTime=System.currentTimeMillis();
		this.myPiano=myPiano;
		this.setBounds(0,0,580,135);
		this.setVisible(true);
		this.setOpaque(true);
		myPiano.pLyrics.add(this);
		img = new ImageIcon("image/blank.jpg");
		//添加图片
		ImageIcon lblBackgroundimg = new ImageIcon("image/blank.jpg");
		Image blankimg=lblBackgroundimg.getImage();
		blankimg = blankimg.getScaledInstance(580, 135, Image.SCALE_DEFAULT);
		lblBackgroundimg.setImage(blankimg);
		this.setIcon(lblBackgroundimg);
		
		notes = new String[20];
		keyString = new String[20];
		bitString = new String[20];
		key = new int[20];
		y_note = new int[20];
		
		lblNote = new JLabel[100];
		for(int i = 0;i<100;i++)lblNote[i] = new JLabel();
		
		width_score=580;
		height_score=100;
		left_bound=width_score*157/1410;
		right_bound=width_score*1250/1410;
		
		low_zero_y=height_score*341/364+31;
		low_five_y=height_score*241/364+20;
		high_five_y=height_score*35/364+2;
		high_zero_y=height_score*143/364+11;
		
		
		width_note=30;
		height_note=50;
		d_x = width_note;
		x_note = left_bound;
		d = (high_zero_y -high_five_y )/9;

		
	}

	
}