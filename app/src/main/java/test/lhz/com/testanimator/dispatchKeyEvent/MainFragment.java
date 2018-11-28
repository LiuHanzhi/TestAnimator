package test.lhz.com.testanimator.dispatchKeyEvent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/22
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class MainFragment extends Fragment implements View.OnFocusChangeListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_main_dispatch,null);
        return inflater.inflate(R.layout.fragment_main_dispatch2, null);
//        return inflater.inflate(R.layout.fragment_main_dispatch2_item_item, null);
    }

    private MyItemView layout1;

    private MyItemView layout2;

    private MyItemView layout3;

    private MyItemView layout4;

    private LinearLayout main;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layout1 = getView().findViewById(R.id.layout1);
        layout2 = getView().findViewById(R.id.layout2);
        layout3 = getView().findViewById(R.id.layout3);
        layout4 = getView().findViewById(R.id.layout4);
        main = getView().findViewById(R.id.main);

        for (int i = 0; i < main.getChildCount(); i++) {
            main.getChildAt(i).setOnFocusChangeListener(this);
        }

        Log.e("lhz","onActivityCreated");
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout1.requestFocus();
            }
        },1000);

//        getView().findViewById(R.id.item_item).requestFocus();

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        MyItemView myItemView = (MyItemView) v;
        myItemView.setFocusStyle(hasFocus);
    }
}
