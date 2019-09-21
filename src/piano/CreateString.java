package piano;

public class CreateString {
    MyPiano myPiano;
    public CreateString(MyPiano myPiano){
        this.myPiano = myPiano;
    }
    public String getString(int keyCode,boolean isControl) {
        String note = "",wxpNote="";
        KeyProperty key = myPiano.km.findByCode(keyCode);
        int character ;
        if(isControl)
            character = key.getRisecharacter();
        else
            character = key.getCharacter();
        if (character == -1)
            return note;
        wxpNote=String.valueOf(character) + 'q';
        myPiano.lblWuXianPu.addNote(wxpNote);
        note = "V" + String.valueOf(myPiano.getChannel(myPiano.trans.transformInstrument(myPiano.jbxSetInstrument.getSelectedIndex()))) + " " + myPiano.instrument + " " + wxpNote + " ";
        myPiano.numOfNote++;
        //System.out.println("note="+note);

        return note;
    }
    public String getStreamString(boolean isPress,int keyCode,int channel,boolean isControl) {
        String note = "",wxpNote="";
        KeyProperty key = myPiano.km.findByCode(keyCode);
        String newcharacter ;
        int character ;
        if(isControl){
            newcharacter = key.getNewRiseCharacter();
            character = key.getRisecharacter();
        }
        else{
            newcharacter = key.getNewcharacter();
            character = key.getCharacter();
        }
        //System.out.println("newcharacter="+newcharacter);
        if (newcharacter.equals("n"))
            return "";
		/*if (isUpperLetter())
			character++;*/
        wxpNote=String.valueOf(character) + 'q';
        //lblWuXianPu.addNote(wxpNote);
        if(isPress)note = "V" + String.valueOf(channel) + " " + myPiano.instrument + " " + newcharacter + "o- ";
        else {
            note = "V" + String.valueOf(channel) + " " + myPiano.instrument + " " + newcharacter + "-o ";
            myPiano.lblWuXianPu.addNote(wxpNote);
        }

        //myPiano.numOfNote++;
        //System.out.println("note="+note);

        return note;
    }

}
