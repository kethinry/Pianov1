package piano;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Music {
	WuXianPu wuXianPu;
	String title;// 乐谱的标题
	String author;// 乐谱的作者
	String date;// 乐谱的创作时间
	int part;// 总伴奏数
	int standard;// 标准音高1=？
	int pace;// 乐谱每分钟多少拍
	int instrument[];// 乐谱的播放乐器
	int noteCount[];//表示每个声部有多少个音符
	long startTime;// 乐谱的起始时间
	Note note[][];// 每个主旋律音符
	
	public int maxLength;
	MyPiano myPiano;
	public PlayModeThread[][] playModeThreads;
	EndThread endThread;
	
	boolean isPlayed[][];
    
    PlayPanel playpanel;
    
	public Music(MyPiano myPiano,int part, int maxLength) {//创建一首乐曲
		this.myPiano=myPiano;
		this.maxLength=maxLength;
		this.part=part;
		note = new Note[part][maxLength+10];
	}
	public Music(MyPiano myPiano) {
		this.myPiano=myPiano;
		
	}

	public boolean getMusicFromFile() {// 从文件中获得乐谱
		// Music music = new Music(myPiano);
		// 标准文件存储格式
		// title
		// author
		// date
		// part
		// standard
		// pace
		// startTime
		// maxLength
		// noteCount[0]
		// instrument[0]
		// character
		// duration

		// noteCount[1]
		// instrument[1]
		// character
		// duration
		//
		JFileChooser chooser = new JFileChooser(".");
		int ret = chooser.showOpenDialog(null);

		if (ret == JFileChooser.APPROVE_OPTION) {// 判断用户选择的是确定、取消等按钮
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(chooser.getSelectedFile().getPath());
				FileReader fr = new FileReader(chooser.getSelectedFile().getPath());
				BufferedReader br = new BufferedReader(fr);
				String temp = "";
				title = br.readLine();
				author = br.readLine();
				date = br.readLine();
				part = Integer.parseInt(br.readLine());
				standard = Integer.parseInt(br.readLine());
				pace = Integer.parseInt(br.readLine());
				startTime = Integer.parseInt(br.readLine());
				maxLength = Integer.parseInt(br.readLine());
				note = new Note[part][maxLength];// 按照最大长度初始化数组
				instrument = new int[part];
				noteCount = new int[part];
				for (int i = 0; i < part; i++) {// 每一种伴奏分别录入
					noteCount[i] = Integer.parseInt(br.readLine());// 读取这一段伴奏共有多少音符
					instrument[i] = Integer.parseInt(br.readLine());// 读取这一段伴奏乐器
					note[i][0] = new Note(myPiano, this, i, instrument[i], 1);
					note[i][0].character = Integer.parseInt(br.readLine());
					note[i][0].durationString = br.readLine();
					if (note[i][0].durationString.length() >= 2) {// 这个音符带浮点,那么isFudian应该设为true，并截取前面的第一个字母作为实际音长
						note[i][0].isFudian = true;
						note[i][0].durationString = note[i][0].durationString.substring(0, 1);
					}
					note[i][0].durationInt = myPiano.transformDurationToTime(note[i][0].durationString, pace,
							note[i][0].isFudian);
					note[i][0].beginTime = 2000;
					int j = 1;
					while (!(temp = br.readLine()).equals("#")) {
						note[i][j] = new Note(myPiano, this, i, instrument[i], j + 1);
						note[i][j].character = Integer.parseInt(temp);
						note[i][j].durationString = br.readLine();
						if (note[i][j].durationString.length() == 2) {
							note[i][j].durationString = note[i][j].durationString.substring(0, 1);
							note[i][j].isFudian = true;
						}
						note[i][j].durationInt = myPiano.transformDurationToTime(note[i][j].durationString, pace,
								note[i][j].isFudian);
						note[i][j].beginTime = note[i][j - 1].beginTime + note[i][j - 1].durationInt;
						j++;
					}
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					fin.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			isPlayed = new boolean[part][maxLength];
			for (int i = 0; i < part; i++) {
				
				for (int j = 0; j < noteCount[i]; j++) {
					isPlayed[i][j] = false;
				}
			}
			return true;
		}
		else{
			return false;
		}

	}

	public void saveAsFile() {// 把乐谱保存成文件
				// 标准文件存储格式
				// title
				// author
				// date
				// part
				// standard
				// pace
				// startTime
				// maxLength
				// noteCount[0]
				// instrument[0]
				// character
				// duration
				
				// noteCount[1]
				// instrument[1]
				// character
				// duration
				//
		JFileChooser chooser = new JFileChooser(".");
		Writer writer = null;
		int ret = chooser.showSaveDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {// 判断用户选择的是确定、取消等按钮
			try {
				writer = new FileWriter(chooser.getSelectedFile().getPath());
				writer.write(title + "\r\n");
				writer.write(author + "\r\n");
				writer.write(date + "\r\n");
				writer.write(part + "\r\n");
				writer.write(standard + "\r\n");
				writer.write(pace + "\r\n");
				writer.write(startTime + "\r\n");
				writer.write(maxLength + "\r\n");
				for(int i=0;i<part;i++){
					writer.write(noteCount[i] + "\r\n");
					writer.write(instrument[i] + "\r\n");
					for (int j = 0; j < noteCount[i]; j++) {
						writer.write(note[i][j].character + "\r\n");
						if(note[i][j].isFudian)writer.write(note[i][j].durationString + "f\r\n");
						else writer.write(note[i][j].durationString + "\r\n");
					}
					writer.write("#\r\n");
				}
			
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (writer != null)
					try {
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		}
	}

	public void addNote(Note note,int index) {// 增加一个的音符
		this.note[index][noteCount[index]] = note;
		noteCount[index]++;
	}

	public void play() {// 播放这个乐谱对象
		playModeThreads = new PlayModeThread[part][maxLength];
		createPlayThread();
		startPlayThread();
		playpanel = new PlayPanel(this);
		myPiano.f.add(playpanel);
	}

	public void createPlayThread() {//创建播放线程
		for (int i = 0; i < part; i++) {
			for (int j = 0; j < noteCount[i]; j++) {
				playModeThreads[i][j] = new PlayModeThread(myPiano,note[i][j]);//原先使用的是一维数组的playModeThread
			}
		}
		endThread=new EndThread(this);
	}

	public void startPlayThread() {
		for (int i = 0; i < part; i++) {
			for (int j = 0; j < noteCount[i]; j++) {
				playModeThreads[i][j].start();
			}
		}
		
		endThread.start();
	}

	public void pause() {// 播放暂停
		for (int i = 0; i < part; i++) {
			for (int j = 0; j <noteCount[i]; j++) {
				playModeThreads[i][j].interrupt();;
			}
		}
		endThread.stop();
		myPiano.refresh();
		
		int[] stopLocation = new int[part];
		for (int i = 0; i < part; i++) {
			stopLocation[i] = 0;
			for (int j = 0; j < noteCount[i]; j++) {
				if (note[i][j].character!=-1 && !isPlayed[i][j]) {
					stopLocation[i] = j;
					break;
				}
			}
		}
		
	
		for (int i = 0; i < part; i++) {
			long time=note[i][stopLocation[i]].beginTime;
			for (int j = 0; j < noteCount[i]; j++) {
				note[i][j].beginTime = note[i][j].beginTime - time;
			}
		}

	}

	public void wake() { // 播放继续
		playModeThreads = new PlayModeThread[part][maxLength];
		createPlayThread();
		startPlayThread();
	}

	public void stop() {// 播放停止
		for (int i = 0; i < part; i++) {
			for (int j = 0; j <noteCount[i]; j++) {
				playModeThreads[i][j].stop();
			}
		}
		playpanel.remove(playpanel);
		playpanel.setVisible(false);
		endThread.interrupt();
		myPiano.isPlaying=false;
		myPiano.refresh();
		myPiano.lblWuXianPu.x_note = myPiano.lblWuXianPu.left_bound;
	}
}
