package test.lhz.com.testanimator.spannable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import test.lhz.com.testanimator.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spannable);


        TextView textView = findViewById(R.id.search_key);
        String text = "501234567895";
        String hightlight = "5";
        SpannableString spannableString = getSpannableString(text, hightlight);
        textView.setText(spannableString);
    }


    private SpannableString getSpannableString(String result, String highlight) {
        SpannableString spannableString = new SpannableString(result);
        if (TextUtils.isEmpty(highlight)) {
            return spannableString;
        }
        try {
            int fromIndex = 0;
            while (true) {
                int index = result.indexOf(highlight, fromIndex);
                if (index >= 0) {
                    spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), index, index + highlight.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    fromIndex = index + highlight.length();
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannableString;
    }
}
