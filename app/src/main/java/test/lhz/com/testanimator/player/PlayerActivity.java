package test.lhz.com.testanimator.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import test.lhz.com.testanimator.R;

/**
 * @author hanzhi <hanzhi@staff.weibo.com>
 * @version 2018/6/12
 * @copyright copyright(2011) weibo.com all rights reserved
 */
public class PlayerActivity extends Activity {

    public static void launch(Context context, String url, MEDIA_TYPE type) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("type", type.getValue());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
    }

    /**
     * 视频格式
     */
    public enum MEDIA_TYPE {

        MP4(1),

        DASH(2);

        private int value;

        MEDIA_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static MEDIA_TYPE getType(int value) {
            switch (value) {
                case 2:
                    return DASH;
            }
            return MP4;
        }
    }


}
