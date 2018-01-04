package test.kai.tan.com.testpopwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow mPopWindow;
    private GestureDetector gestureDetector;
    private int downX, downY;
    private View mMenuTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMenuTv = findViewById(R.id.menu);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                downX = (int) e.getX();
                downY = (int) e.getY();

                Toast.makeText(MainActivity.this, "onLongPress:" + downX + "-" + downY, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showPopupWindow() {

        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popuplayout, null);
//        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置各个控件的点击响应
        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView) contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        //显示PopupWindow
        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
//        mPopWindow.showAtLocation(rootview, Gravity.TOP, 0, 0);
        mPopWindow.showAsDropDown(mMenuTv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pop_computer: {
                Toast.makeText(this, "clicked computer", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_financial: {
                Toast.makeText(this, "clicked financial", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_manage: {
                Toast.makeText(this, "clicked manage", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
        }
    }
}
