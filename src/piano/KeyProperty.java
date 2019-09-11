package piano;

public class KeyProperty {
    private int index;//btn数组的顺序，比如1是1
    private int keycode;//ASCII
    private int character;//do re mi fa...
    private int risecharacter;
    private int whitecode;
    private int blackcode;
    private String newcharacter;
    private String newRiseCharacter;
    public KeyProperty(int index,String fileString){
        String[] subString = fileString.split("\\,");
        this.index=index;
        this.keycode = Integer.valueOf(subString[0]);
        this.character=Integer.valueOf(subString[1]);
        this.risecharacter = Integer.valueOf(subString[2]);
        this.whitecode=Integer.valueOf(subString[3]);
        this.blackcode=Integer.valueOf(subString[4]);
        this.newcharacter=String.valueOf(subString[5]);
        this.newRiseCharacter=String.valueOf(subString[6]);

        //System.out.println("index"+this.index+"keycode"+this.keycode+"character"+this.character+"whitecode"+this.whitecode+"blackcode"+this.blackcode+"newcharacter"+this.newcharacter);
    }

    public int getKeycode() {
        return keycode;
    }

    public int getCharacter() {
        return character;
    }

    public int getRisecharacter() {
        return risecharacter;
    }

    public int getIndex(){
        return index;
    }

    public int getWhitecode(){
        return whitecode;
    }

    public int getBlackcode() { return blackcode; }

    public String getNewcharacter(){
        return newcharacter;
    }

    public String getNewRiseCharacter(){ return newRiseCharacter; }
}
