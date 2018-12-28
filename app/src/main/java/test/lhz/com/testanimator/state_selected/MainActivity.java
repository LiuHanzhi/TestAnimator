package test.lhz.com.testanimator.state_selected;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import test.lhz.com.testanimator.R;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {


    private View main1;

    private View main2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_state_selected);

        main1 = findViewById(R.id.main1);
        main2 = findViewById(R.id.main2);

        main1.setOnFocusChangeListener(this);
        main2.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        v.setSelected(hasFocus);
    }
}
