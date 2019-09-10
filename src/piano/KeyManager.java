package piano;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyManager {
    int type;
    private ArrayList<KeyProperty> index2keys = new ArrayList<KeyProperty>();
    private HashMap<Integer,KeyProperty> code2keys = new HashMap<Integer, KeyProperty>();
    private HashMap<Integer,KeyProperty> character2keys = new HashMap<Integer, KeyProperty>();

    public KeyManager(int type){
        this.type = type;
        initial();
    }

    public void initial() {
        try {
            FileReader fr = new FileReader(".//data//keyproperty.txt");
            BufferedReader bf = new BufferedReader(fr);
            String fileString;
            int i=0;
            for (;(fileString = bf.readLine()) != null;i++) {
                KeyProperty KeyNow = new KeyProperty(i,fileString);
                int code = KeyNow.getKeycode();
                int character = KeyNow.getCharacter();
                index2keys.add(KeyNow);
                code2keys.put(code,KeyNow);
                if(i>0&&i<8||i>14&&i<22||i>28&&i<36||i>41&&i<49){
                    character2keys.put(character,KeyNow);
                }

            }
            bf.close();
            fr.close();
            //KeyProperty KeyError = new KeyProperty(i,"-2,-2,-2,-2");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyProperty findByCode(int code){
        if(code2keys.containsKey(code))
            return code2keys.get(code);
        System.out.println("No exsist key with input keycode!");
        KeyProperty errorkey = new KeyProperty(-1,"-1,-1,-1,-1,n");
        return errorkey;

    }
    public KeyProperty findByIndex(int index){
        if(index<=index2keys.size())
            return index2keys.get(index);
        System.out.println("No exsist key with input index!");
        KeyProperty errorkey = new KeyProperty(-1,"-1,-1,-1,-1,n");
        return errorkey;
    }
    public KeyProperty findByCharacter(int character){
        if(character2keys.containsKey(character))
        return character2keys.get(character);
        System.out.println("No exsist key with input character!");
        KeyProperty errorkey = new KeyProperty(-1,"-1,-1,-1,-1,n");
        return errorkey;
    }
    //KeyProperty key = keyManager.findByCode(123);
}
