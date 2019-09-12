package piano;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class TeachMode {
	MyPiano myPiano;
	String musicString; // V0 I1 60q
	String noTrackString; // I1 60q
	String content; // 文本所有内容

	public int getNumOfNote() {
		return myPiano.numOfNote;// 音节数目
	}

	public TeachMode(MyPiano myPiano) throws IOException {

		this.myPiano = myPiano;
		myPiano.currentNum = 0;

		readNewString(myPiano.remember);
		int num = getNumOfNote();
		myPiano.keyCode = new int[num];
		myPiano.character = new int[num];
		myPiano.buttonCode = new int[num];
		myPiano.subArray = new String[num];
		myPiano.subArray = noTrackString.split("  ");
		myPiano.subArrayOnlyNum = new String[num];
		myPiano.durations = new String[num];

		for (int i = 0; i < num; i++) {
			myPiano.subArrayOnlyNum[i] = myPiano.subArray[i].replaceFirst("I\\d+" + " ", "");
			myPiano.subArrayOnlyNum[i] = myPiano.subArrayOnlyNum[i].replaceFirst("[a-zA-Z]", "");
			myPiano.character[i] = Integer.valueOf(myPiano.subArrayOnlyNum[i]);
			KeyProperty key = myPiano.km.findByCharacter(myPiano.character[i]);
			if(key.getIndex()== -1)
				key = myPiano.km.findByRiseCharacter(myPiano.character[i]);
			myPiano.keyCode[i] = key.getKeycode();
			myPiano.buttonCode[i] = key.getIndex();
			myPiano.durations[i] = myPiano.subArray[i].replaceFirst("I\\d+ \\d+", "");
		}
		// 先将第一个音符变成待教模式 之后交给KeyListener

		// 高半个音的话
		if (myPiano.character[0] != 48 && myPiano.character[0] != 50 && myPiano.character[0] != 52
				&& myPiano.character[0] != 53 && myPiano.character[0] != 55 && myPiano.character[0] != 57
				&& myPiano.character[0] != 59 && myPiano.character[0] != 60 && myPiano.character[0] != 62
				&& myPiano.character[0] != 64 && myPiano.character[0] != 65 && myPiano.character[0] != 67
				&& myPiano.character[0] != 69 && myPiano.character[0] != 71 && myPiano.character[0] != 72
				&& myPiano.character[0] != 74 && myPiano.character[0] != 76 && myPiano.character[0] != 77
				&& myPiano.character[0] != 79 && myPiano.character[0] != 81 && myPiano.character[0] != 83
				&& myPiano.character[0] != 84 && myPiano.character[0] != 86 && myPiano.character[0] != 88) {
			myPiano.btn[28].setBackground(Color.YELLOW);

			myPiano.btn[myPiano.buttonCode[0]].setBackground(Color.YELLOW);
		} else {
			myPiano.btn[myPiano.buttonCode[0]].setBackground(Color.YELLOW);
		}
		myPiano.btn[myPiano.trans.transformDurationToButtonCode(myPiano.durations[0])].setBackground(myPiano.myGay);

	}

	public void readOldString(String fileString) {
		content = fileString;
		String[] subString = fileString.split("\r\n");
		myPiano.title = subString[1].replaceFirst("#title:", "");
		myPiano.date = subString[2].replaceFirst("#data:", "");
		myPiano.author = subString[3].replaceFirst("#author:", "");
		musicString = subString[4].replaceFirst("#", "");
		noTrackString = transformToNoTrack(musicString);
		myPiano.numOfNote = Integer.valueOf(subString[6].replaceFirst("#", ""));
	}

	public void readNewString(String fileString) {
		content = fileString;
		String[] subString = fileString.split("\r\n");
		myPiano.title = subString[0]; // 第0行存储题目
		myPiano.author = subString[1]; // 第1行存储作者姓名
		myPiano.date = subString[2]; // 第2行存储日期
		myPiano.numOfNote = Integer.valueOf(subString[8]); // 8行存储第一声部音符个数
		musicString = "";
		for (int i = 10; i < 10 + myPiano.numOfNote * 2; i = i + 2)
			musicString = musicString + "V" + i % 5 + " " + "I" + subString[9] + " " + subString[i]
					+ subString[i + 1].substring(0, 1) + "  "; // V0 I0 60q
		myPiano.numOfNote = myPiano.numOfNote - getOccur(musicString, "-1");
		musicString = musicString.replaceAll("V\\d+ I\\d+ -1[a-zA-Z]  ", "");
		noTrackString = transformToNoTrack(musicString);

	}

	public static int getOccur(String src, String find) {
		int o = 0;
		int index = -1;
		while ((index = src.indexOf(find, index)) > -1) {
			++index;
			++o;
		}
		return o;
	}

	public String transformToNoTrack(String musicString) {
		String noTrackString = musicString.replaceAll("V\\d+" + " ", "");
		return noTrackString;
	}

	public String transformNumToLetter(String numString) {

		String letterString = numString;
		// 替换
		letterString = letterString.replaceAll(" 24", " C2");
		letterString = letterString.replaceAll(" 36", " C3");
		letterString = letterString.replaceAll(" 48", " C4");
		letterString = letterString.replaceAll(" 60", " C5");
		letterString = letterString.replaceAll(" 72", " C6");
		letterString = letterString.replaceAll(" 84", " C7");
		letterString = letterString.replaceAll(" 96", " C8");
		letterString = letterString.replaceAll(" 108", " C9");
		letterString = letterString.replaceAll(" 120", " C10");

		letterString = letterString.replaceAll(" 25", " Db2");
		letterString = letterString.replaceAll(" 37", " Db3");
		letterString = letterString.replaceAll(" 49", " Db4");
		letterString = letterString.replaceAll(" 61", " Db5");
		letterString = letterString.replaceAll(" 73", " Db6");
		letterString = letterString.replaceAll(" 85", " Db7");
		letterString = letterString.replaceAll(" 97", " Db8");
		letterString = letterString.replaceAll(" 109", " Db9");
		letterString = letterString.replaceAll(" 121", " Db10");

		letterString = letterString.replaceAll(" 26", " D2");
		letterString = letterString.replaceAll(" 38", " D3");
		letterString = letterString.replaceAll(" 50", " D4");
		letterString = letterString.replaceAll(" 62", " D5");
		letterString = letterString.replaceAll(" 74", " D6");
		letterString = letterString.replaceAll(" 86", " D7");
		letterString = letterString.replaceAll(" 98", " D8");
		letterString = letterString.replaceAll(" 110", " D9");
		letterString = letterString.replaceAll(" 122", " D10");

		letterString = letterString.replaceAll(" 27", " Eb2");
		letterString = letterString.replaceAll(" 39", " Eb3");
		letterString = letterString.replaceAll(" 51", " Eb4");
		letterString = letterString.replaceAll(" 63", " Eb5");
		letterString = letterString.replaceAll(" 75", " Eb6");
		letterString = letterString.replaceAll(" 87", " Eb7");
		letterString = letterString.replaceAll(" 99", " Eb8");
		letterString = letterString.replaceAll(" 111", " Eb9");
		letterString = letterString.replaceAll(" 123", " Eb10");

		letterString = letterString.replaceAll(" 28", " E2");
		letterString = letterString.replaceAll(" 40", " E3");
		letterString = letterString.replaceAll(" 52", " E4");
		letterString = letterString.replaceAll(" 64", " E5");
		letterString = letterString.replaceAll(" 76", " E6");
		letterString = letterString.replaceAll(" 88", " E7");
		letterString = letterString.replaceAll(" 100", " E8");
		letterString = letterString.replaceAll(" 112", " E9");
		letterString = letterString.replaceAll(" 124", " E10");

		letterString = letterString.replaceAll(" 29", " F2");
		letterString = letterString.replaceAll(" 41", " F3");
		letterString = letterString.replaceAll(" 53", " F4");
		letterString = letterString.replaceAll(" 65", " F5");
		letterString = letterString.replaceAll(" 77", " F6");
		letterString = letterString.replaceAll(" 89", " F7");
		letterString = letterString.replaceAll(" 101", " F8");
		letterString = letterString.replaceAll(" 113", " F9");
		letterString = letterString.replaceAll(" 125", " F10");

		letterString = letterString.replaceAll(" 30", " Gb2");
		letterString = letterString.replaceAll(" 43", " Gb3");
		letterString = letterString.replaceAll(" 54", " Gb4");
		letterString = letterString.replaceAll(" 66", " Gb5");
		letterString = letterString.replaceAll(" 78", " Gb6");
		letterString = letterString.replaceAll(" 90", " Gb7");
		letterString = letterString.replaceAll(" 102", " Gb8");
		letterString = letterString.replaceAll(" 114", " Gb9");
		letterString = letterString.replaceAll(" 126", " Gb10");

		letterString = letterString.replaceAll(" 31", " G2");
		letterString = letterString.replaceAll(" 43", " G3");
		letterString = letterString.replaceAll(" 55", " G4");
		letterString = letterString.replaceAll(" 67", " G5");
		letterString = letterString.replaceAll(" 79", " G6");
		letterString = letterString.replaceAll(" 91", " G7");
		letterString = letterString.replaceAll(" 103", " G8");
		letterString = letterString.replaceAll(" 115", " G9");
		letterString = letterString.replaceAll(" 127", " G10");

		letterString = letterString.replaceAll(" 32", " Ab2");
		letterString = letterString.replaceAll(" 45", " Ab3");
		letterString = letterString.replaceAll(" 56", " Ab4");
		letterString = letterString.replaceAll(" 68", " Ab5");
		letterString = letterString.replaceAll(" 80", " Ab6");
		letterString = letterString.replaceAll(" 92", " Ab7");
		letterString = letterString.replaceAll(" 104", " Ab8");
		letterString = letterString.replaceAll(" 116", " Ab9");

		letterString = letterString.replaceAll(" 33", " A2");
		letterString = letterString.replaceAll(" 45", " A3");
		letterString = letterString.replaceAll(" 57", " A4");
		letterString = letterString.replaceAll(" 69", " A5");
		letterString = letterString.replaceAll(" 81", " A6");
		letterString = letterString.replaceAll(" 93", " A7");
		letterString = letterString.replaceAll(" 105", " A8");
		letterString = letterString.replaceAll(" 117", " A9");

		letterString = letterString.replaceAll(" 34", " Bb2");
		letterString = letterString.replaceAll(" 46", " Bb3");
		letterString = letterString.replaceAll(" 58", " Bb4");
		letterString = letterString.replaceAll(" 70", " Bb5");
		letterString = letterString.replaceAll(" 82", " Bb6");
		letterString = letterString.replaceAll(" 94", " Bb7");
		letterString = letterString.replaceAll(" 106", " Bb8");
		letterString = letterString.replaceAll(" 118", " Bb9");

		letterString = letterString.replaceAll(" 35", " B2");
		letterString = letterString.replaceAll(" 47", " B3");
		letterString = letterString.replaceAll(" 59", " B4");
		letterString = letterString.replaceAll(" 71", " B5");
		letterString = letterString.replaceAll(" 83", " B6");
		letterString = letterString.replaceAll(" 95", " B7");
		letterString = letterString.replaceAll(" 107", " B8");
		letterString = letterString.replaceAll(" 119", " B9");

		letterString = letterString.replaceAll(" 10", " Bb0");
		letterString = letterString.replaceAll(" 11", " B0");
		letterString = letterString.replaceAll(" 12", " C1");
		letterString = letterString.replaceAll(" 13", " Db1");
		letterString = letterString.replaceAll(" 14", " D1");
		letterString = letterString.replaceAll(" 15", " Eb1");
		letterString = letterString.replaceAll(" 16", " E1");
		letterString = letterString.replaceAll(" 17", " F1");
		letterString = letterString.replaceAll(" 18", " Gb1");
		letterString = letterString.replaceAll(" 19", " G1");
		letterString = letterString.replaceAll(" 20", " Ab1");
		letterString = letterString.replaceAll(" 21", " A1");
		letterString = letterString.replaceAll(" 22", " Bb1");
		letterString = letterString.replaceAll(" 23", " B1");
		letterString = letterString.replaceAll(" 0", " C0");
		letterString = letterString.replaceAll(" 1", " Db0");
		letterString = letterString.replaceAll(" 2", " D0");
		letterString = letterString.replaceAll(" 3", " Eb0");
		letterString = letterString.replaceAll(" 4", " E0");
		letterString = letterString.replaceAll(" 5", " F0");
		letterString = letterString.replaceAll(" 6", " Gb0");
		letterString = letterString.replaceAll(" 7", " G0");
		letterString = letterString.replaceAll(" 8", " Ab0");
		letterString = letterString.replaceAll(" 9", " A0");

		return letterString;
	}

}
