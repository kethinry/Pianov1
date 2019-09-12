package piano;

public class Transform {

    public int transformInstrument(int selectIndex) {
        return selectIndex * 8;
    }

    public String transiformCharacterToNewCharacter(int character) {
        if (character<0)return "-1";
        String s = "";
        int x,y;
        x = character%12;
        y = character/12;
        switch (x) {
            case 0:
                s = "C";
                break;
            case 1:
                s = "Db";
                break;
            case 2:
                s = "D";
                break;
            case 3:
                s = "Eb";
                break;
            case 4:
                s = "E";
                break;
            case 5:
                s = "F";
                break;
            case 6:
                s = "Gb";
                break;
            case 7:
                s = "G";
                break;
            case 8:
                s = "Ab";
                break;
            case 9:
                s = "A";
                break;
            case 10:
                s = "Bb";
                break;
            case 11:
                s = "B";
                break;
            default:
                s = "";
                break;
        }
        return s+y;
    }

    public String transformDuration(int selectIndex) {
        switch (selectIndex) {
            case 0:
                return "w";
            case 1:
                return "h";
            case 2:
                return "q";
            case 3:
                return "i";
            case 4:
                return "s";
            case 5:
                return "t";
            case 6:
                return "x";
            case 7:
                return "o";
            default:
                return "q";
        }
    }

    public long transformDurationToTime(String durations,int pace,boolean isFudian) {

        pace=60000/pace;
        if(isFudian)
            pace*=1.5;
        switch (durations) {
            case "w":
                return 4*pace;
            case "h":
                return 2*pace;
            case "q":
                return pace;
            case "i":
                return pace/2;
            case "s":
                return pace/4;
            case "t":
                return pace/8;
            case "x":
                return pace/16;
            case "o":
                return pace/32;
            default:
                return 500;
        }
    }

    public int transformDurationToButtonCode(String durations) {
        switch (durations) {
            case "w":
                return 25;
            case "h":
                return 26;
            case "q":
                return 27;
            case "i":
                return 38;
            case "s":
                return 39;
            case "t":
                return 49;
            case "x":
                return 50;
            case "o":
                return 51;
            default:
                return 27;
        }
    }
}
