package piano;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jfugue.*;


public class Music {
	Playing playing;
	String title;// ���׵ı���
	String author;// ���׵�����
	String date;// ���׵Ĵ���ʱ��
	int part;// �ܰ�����
	int standard;// ��׼����1=��
	int pace;// ����ÿ���Ӷ�����
	int instrument[];// ���׵Ĳ�������
	int noteCount[];// ��ʾÿ�������ж��ٸ�����
	long startTime;// ���׵���ʼʱ��
	Note note[][];// ÿ������������
	public int maxLength;
	MyPiano myPiano;
	public PlayModeThread[] playModeThreads;
	//EndThread endThread;

	boolean isPlayed[][];

	PlayPanel playpanel;

	public Music(MyPiano myPiano, int part, int maxLength) {// ����һ������
		this.myPiano = myPiano;
		this.maxLength = maxLength;
		this.part = part;
		note = new Note[part][maxLength + 10];
	}

	public Music(MyPiano myPiano,Playing playing) {
		this.myPiano = myPiano;
		this.playing = playing;
	}

	public Music(MyPiano myPiano) {
		this.myPiano = myPiano;
	}
	
	public Pattern getMusicFromFile() {// ���ļ��л������
		// Music music = new Music(myPiano);
		// ��׼�ļ��洢��ʽ
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
		Pattern musicPattern = null;
		if (ret == JFileChooser.APPROVE_OPTION) {// �ж��û�ѡ�����ȷ����ȡ���Ȱ�ť
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
				note = new Note[part][maxLength];// ������󳤶ȳ�ʼ������
				instrument = new int[part];
				noteCount = new int[part];
				musicPattern = new Pattern("T["+pace+"]");/////���=========
				for (int i = 0; i < part; i++) {// ÿһ�ְ���ֱ�¼��
					noteCount[i] = Integer.parseInt(br.readLine());// ��ȡ��һ�ΰ��๲�ж�������
					instrument[i] = Integer.parseInt(br.readLine());// ��ȡ��һ�ΰ�������
					Pattern pattern = new Pattern("V"+i+" I"+instrument[i]+" ");/////���=========
					
					//pattern.setInstrument(instrument[i]);// ��ȡ��һ�ΰ�������/////���=========
					note[i][0] = new Note(myPiano, this, i, instrument[i], 1);
					note[i][0].character = Integer.parseInt(br.readLine());
					note[i][0].durationString = br.readLine();
					if (note[i][0].durationString.length() >= 2) {// �������������,��ôisFudianӦ����Ϊtrue������ȡǰ��ĵ�һ����ĸ��Ϊʵ������
						note[i][0].isFudian = true;
						note[i][0].durationString = note[i][0].durationString.substring(0, 1);
					}
					note[i][0].durationInt = myPiano.trans.transformDurationToTime(note[i][0].durationString, pace,
							note[i][0].isFudian);
					note[i][0].beginTime = 0;
					int j = 1;
					while (!(temp = br.readLine()).equals("#")) {
						
						note[i][j] = new Note(myPiano, this, i, instrument[i], j + 1);
						note[i][j].character = Integer.parseInt(temp);
						note[i][j].durationString = br.readLine();
						
						pattern.add(myPiano.trans.transiformCharacterToNewCharacter(note[i][j].character)+myPiano.f2dot(note[i][j].durationString));
						if (note[i][j].durationString.length() == 2) {
							note[i][j].durationString = note[i][j].durationString.substring(0, 1);
							note[i][j].isFudian = true;
						}
						note[i][j].durationInt = myPiano.trans.transformDurationToTime(note[i][j].durationString, pace,
								note[i][j].isFudian);
						note[i][j].beginTime = note[i][j - 1].beginTime + note[i][j - 1].durationInt;
						j++;
					}
					musicPattern.add(pattern);
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
			
		} 
		return musicPattern;
	}

	public void saveAsFile() {// �����ױ�����ļ�
		// ��׼�ļ��洢��ʽ
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
		if (ret == JFileChooser.APPROVE_OPTION) {// �ж��û�ѡ�����ȷ����ȡ���Ȱ�ť
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
				for (int i = 0; i < part; i++) {
					writer.write(noteCount[i] + "\r\n");
					writer.write(instrument[i] + "\r\n");
					for (int j = 0; j < noteCount[i]; j++) {
						writer.write(note[i][j].character + "\r\n");
						if (note[i][j].isFudian)
							writer.write(note[i][j].durationString + ".\r\n");
						else
							writer.write(note[i][j].durationString + "\r\n");
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

	public void addNote(Note note, int index) {// ����һ��������
		this.note[index][noteCount[index]] = note;
		noteCount[index]++;
	}

	public void play() {// ����������׶���
		//System.out.println("������");
		createPlayThread();
		startPlayThread();
	}

	public void createPlayThread() {// ���������߳�
		playModeThreads = new PlayModeThread[part];
		for (int i = 0; i < part; i++) {
			playModeThreads[i] = new PlayModeThread(myPiano, this,i);// ԭ��ʹ�õ���һά�����playModeThread
		}
		//endThread = new EndThread(playing);
	}

	public void startPlayThread() {
		for (int i = 0; i < part; i++) {
			playModeThreads[i].start();
		}
		//endThread.start();
	}

	public void pause() {// ������ͣ
		
		for (int i = 0; i < part; i++) {
			for (int j = 0; j < noteCount[i]; j++) {
				playModeThreads[i].interrupt();
			}
		}
		//endThread.stop();
		myPiano.refresh();

		int[] stopLocation = new int[part];
		for (int i = 0; i < part; i++) {
			stopLocation[i] = 0;
			for (int j = 0; j < noteCount[i]; j++) {
				if (note[i][j].character != -1 && !isPlayed[i][j]) {
					stopLocation[i] = j;
					break;
				}
			}
		}

		for (int i = 0; i < part; i++) {
			long time = note[i][stopLocation[i]].beginTime;
			for (int j = 0; j < noteCount[i]; j++) {
				note[i][j].beginTime = note[i][j].beginTime - time;
			}
		}

	}

	public void wake() { // ���ż���
		playModeThreads = new PlayModeThread[part];
		createPlayThread();
		startPlayThread();
	}

	public void stop() {// ����ֹͣ
		for (int i = 0; i < part; i++) {
			for (int j = 0; j < noteCount[i]; j++) {
				playModeThreads[i].stop();
			}
		}
		//endThread.interrupt();
		myPiano.lblWuXianPu.x_note = myPiano.lblWuXianPu.left_bound;
	}
}
