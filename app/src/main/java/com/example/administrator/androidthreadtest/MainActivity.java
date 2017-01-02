package com.example.administrator.androidthreadtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int UPDATE_TEXT=1;
    private static final int UPDATE_TEXT1=2;
    private TextView text;
    private Button change;
    private Button button;
    private Handler handler=new Handler(){
         public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    text.setText("Nice to meet you");
                    break;
                case UPDATE_TEXT1:
                    text.setText("Nice to meet you too");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text =(TextView)findViewById(R.id.textView);
        change =(Button)findViewById(R.id.button);;
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg =new Message();
                        msg.what = UPDATE_TEXT;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChangeTask().execute();
            }
        });

    }

    class ChangeTask extends AsyncTask<Void,String,Void>{

        @Override
        protected Void doInBackground(Void... params) {
         String s="Nice to meet you too";
            publishProgress(s);

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values){
            text.setText(values[0]);
        }
    }
}
