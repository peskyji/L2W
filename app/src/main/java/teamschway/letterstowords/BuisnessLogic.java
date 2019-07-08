package teamschway.letterstowords;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class BuisnessLogic{
    public ArrayList <String> giveWords(String s1,InputStream is){
       ArrayList<String> arraylist=new ArrayList<String>();
        int flag=0;
        //TextView tv = (TextView) findViewById(R.id.tvTop);
        //AssetManager assetManager = getAssets();
        try{
            System.out.println("inside try");
            String line="";
            int n=is.read();
            // System.out.println((char)is.read());
            while(n!=-1){
                char ch=(char)n;
                while(ch!='\n')
                {
                    line+=ch;
                    ch=(char)is.read();
                }
                //System.out.println(line);
                if(!(s1.contains("*")||s1.contains("?"))){

                    if(line.length()<=s1.length())
                    {
                        if(CheckPermutation(line,s1))
                        {
                            arraylist.add(line);
                            flag=1;
                        }
                    }
                }
                else if(s1.contains("?")){

                    if(line.length()==s1.length())
                    {
                        if(CheckUnderScore(line,s1))
                        {
                            arraylist.add(line);
                            flag=1;
                        }
                    }
                }

                else{
                    if(line.length()>=s1.length()){

                        if(CheckStar(line,s1))
                        {
                            arraylist.add(line);
                            flag=1;
                        }
                    }
                }

                // System.out.println("at permutation");
                line="";
                n=is.read();
            }
           /* if(flag==0)
            {
                tv.setText("\n"+"!!!!SORRY NOTHING FOUND!!!!");
            }*/
            is.close();
            System.out.println(arraylist);
        }
        catch(Exception e){ arraylist.clear(); arraylist.add("error");}
        return arraylist;
    }

    public boolean CheckPermutation(String dictWord,String userWord)
    {
        char []arrDict=dictWord.toCharArray();
        char []arrUser=userWord.toCharArray();
        int l=arrUser.length,flag=0;
        for(int i=0;i<arrDict.length;i++)
        {
            flag=0;
            for(int j=0;j<l;j++)
            {
                if(arrDict[i]==arrUser[j])
                {
                    char ch;
                    ch=arrUser[j];
                    arrUser[j]=arrUser[l-1];
                    arrUser[l-1]=ch;
                    l-=1;
                    flag=1;
                    break;
                }
            }
            if(flag==0)
                return false;
        }
        return true;
    }

    public boolean CheckUnderScore(String dictWord,String userWord){
        int base=0;
        // System.out.println(dictWord+"\t"+userWord);
        for(int i=0;i<userWord.length();i++){
            if(userWord.charAt(i)=='?' || i==userWord.length()-1){
                if(i==userWord.length()-1)
                    i=userWord.length();
                String tempD=dictWord.substring(base,i);
                String tempU=userWord.substring(base,i);
                if(!tempD.equalsIgnoreCase(tempU))
                    return false;
                //System.out.println("inside us function");
                base=i+1;
            }
        }
        return true;
    }

    public boolean CheckStar(String dictWord,String userWord){
        //int base=0;
        // System.out.println(dictWord+"\t"+userWord);
        if(userWord.startsWith("*") && dictWord.endsWith(userWord.substring(1,userWord.length()))){
            return true;
        }
        else if(userWord.endsWith("*") && dictWord.startsWith(userWord.substring(0,userWord.length()-1))){
            return true;
        }
        else{
            for(int i=0;i<userWord.length();i++) {
                if (userWord.charAt(i) == '*' && dictWord.startsWith(userWord.substring(0, i)) && dictWord.endsWith(userWord.substring(i + 1, userWord.length()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
