package test.lhz.com.testanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import test.lhz.com.testanimator.dispatchKeyEvent.FocusActivity;
import test.lhz.com.testanimator.player.PlayerActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
                PlayerActivity.launch(MainActivity.this, url, PlayerActivity.MEDIA_TYPE.MP4);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusActivity.launch(MainActivity.this);
            }
        });

        int pro = Runtime.getRuntime().availableProcessors();
        System.out.println("pro:"+pro);


        //TEST v1.1
    }
}
