package piano;

import javax.swing.*;
import java.awt.*;

public class SettingKey {
    MyPiano myPiano;
    public SettingKey(MyPiano myPiano){
        this.myPiano = myPiano;
    }
    
    public void settingwhitekey(int whitecode){
        if (whitecode>=0&&whitecode<52) {
            if (whitecode == 0)
                myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/0.jpg"));
            else if (whitecode == 1)
                myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/7.jpg"));
            switch ((whitecode - 2) % 7) {
                case 0:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/1.jpg"));break;
                case 1:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/2.jpg"));break;
                case 2:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/3.jpg"));break;
                case 3:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/4.jpg"));break;
                case 4:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/5.jpg"));break;
                case 5:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/6.jpg"));break;
                case 6:
                    myPiano.btnPianoWhite[whitecode].setIcon(new ImageIcon("image/7.jpg"));break;
                default:
                    myPiano.btnPianoWhite[whitecode].setIcon(null);break;
            }
        }
    }
    public void setkeycolor(int btni,int mode){
        switch(mode) {
            case 1:
                if (!myPiano.isColorful) {// white-black
                    myPiano.btn[btni].setBackground(Color.BLACK);
                    if (btni == 41) //41:shift
                        myPiano.btn[btni].setBackground(Color.BLACK);
                    if (btni == 53) //53,57:shift
                        myPiano.btn[57].setBackground(Color.BLACK);
                } else {// colorful
                    if (myPiano.colorNum >= 6)
                        myPiano.colorNum = 0;
                    if (btni != -1)
                        myPiano.btn[btni].setBackground(myPiano.myColor[myPiano.colorNum]);
                    if (btni == 41)
                        myPiano.btn[52].setBackground(myPiano.myColor[myPiano.colorNum]);
                    if (btni == 53)
                        myPiano.btn[57].setBackground(myPiano.myColor[myPiano.colorNum]);
                    myPiano.colorNum++;
                }break;
            case 2:
                myPiano.btn[btni].setBackground(Color.GREEN);
                if (btni == 41)
                    myPiano.btn[52].setBackground(Color.GREEN);
                if (btni == 53)
                    myPiano.btn[57].setBackground(Color.GREEN);
                break;
            case 3:
                myPiano.btn[btni].setBackground(Color.RED);
            case 4:
                myPiano.btn[btni].setBackground(myPiano.myPink);
                if (btni == 41)
                    myPiano.btn[52].setBackground(myPiano.myPink);
                if (btni == 53)
                    myPiano.btn[57].setBackground(myPiano.myPink);
            case 5:
                myPiano.btn[btni].setBackground(Color.WHITE);
                if (btni == 41)
                    myPiano.btn[52].setBackground(Color.WHITE);
                if (btni == 53)
                    myPiano.btn[57].setBackground(Color.WHITE);
                break;
        }
    }
}
