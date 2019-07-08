package teamschway.letterstowords;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String s1 = intent.getStringExtra("word");
        TextView tv = (TextView) findViewById(R.id.tvTop);
        TextView tv1= (TextView) findViewById(R.id.tvBottom);
        TextView tv2= (TextView) findViewById(R.id.tvMiddle);
        ArrayList<String> arraylist = new ArrayList<String>();

        AssetManager assetManager = getAssets();
        try {
            System.out.println("inside try");
            InputStream is = assetManager.open("english2.txt");
            System.out.println("file loaded successfully");
            arraylist=new BuisnessLogic().giveWords(s1,is);

            if(arraylist.isEmpty())
            {
                tv2.setVisibility(View.VISIBLE);
                //tv2.setText("NO WORD FOUND");
            }
            else if(arraylist.get(0).toString().equals("error")){
                tv.setText("sorry some error occur");
            }
            else{
                tv.setText("Words for '" + s1 + "'");
                tv1.setText("word(s) found : "+arraylist.size());
            }
        }
        catch(Exception e){ tv.setText("\nSystem Currently not Working\nPlease Try Again Later");}

        RecyclerView rv1 = (RecyclerView) findViewById(R.id.rv1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new GridLayoutManager(this, 3));
        RvAdapter adapter=new RvAdapter(arraylist);
        rv1.setAdapter(adapter);
    }
}
