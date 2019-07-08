package teamschway.letterstowords;

import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void FindWords(View V){
        EditText et1=(EditText)findViewById(R.id.et1);
        String s1=String.valueOf(et1.getText());
        if(!s1.isEmpty()){
            Intent intent=new Intent(MainActivity.this,ResultActivity.class);
            intent.putExtra("word",s1);
            startActivity(intent);
        }
    }
}

