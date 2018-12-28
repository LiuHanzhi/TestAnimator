package test.lhz.com.testanimator.animation2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/8/22
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MainActivity extends Activity implements View.OnFocusChangeListener, AdapterView.OnItemClickListener, View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main2);

        ListView listView = findViewById(R.id.listview);

        View view2 = findViewById(R.id.text2);
        View view3 = findViewById(R.id.text3);
        View view4 = findViewById(R.id.text4);

//        view2.setOnFocusChangeListener(this);
//        view3.setOnFocusChangeListener(this);
//        view4.setOnFocusChangeListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);

        List<Map<String,String>> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String,String> map = new HashMap<>();
            map.put("text1","这是左边：" + i);
            map.put("text2","这是右边：" + i);
            data.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this, data,R.layout.animation_main2_list_item, new String[]{"text1","text2"}, new int[]{R.id.list_text1,R.id.list_text2}));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        float sacle;
        if (hasFocus) {
            sacle = 1.2f;
        } else {
            sacle = 1.0f;
        }
        animator(v, sacle);
    }

    private void animator(View view, float scale) {
        view.animate().scaleX(scale).scaleY(scale).setDuration(1000).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView2 = view.findViewById(R.id.list_text2);
        animator(textView2,4.2f);
    }

    @Override
    public void onClick(View v) {
        animator(v,2.2f);
    }

    private class MyAdapter extends SimpleAdapter {
        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }
    }

}
