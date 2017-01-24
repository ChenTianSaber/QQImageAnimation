package com.example.qqdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<Msg>();

    private RecyclerView msgRecyclerView;

    private MyRecyclerAdapter adapter;

    MyView myView;
    //BallView myView;

    List<Point> PointList = new ArrayList<Point>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initMsgs(); // 初始化消息数据
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        myView = (MyView) findViewById(R.id.my_view);

        //这里设置表情View与RecyclerView的跟随
        msgRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                myView.setTranslationY(myView.getTranslationY()-dy);
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("你是我天边最美的云彩！", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("让我用心把你留下来！", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("悠悠的唱着最炫的名族风！", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg4 = new Msg("套马的汉子就是我呀！", Msg.TYPE_SENT);
        msgList.add(msg4);
        Msg msg5 = new Msg("后面的歌词到底是什么呀！", Msg.TYPE_RECEIVED);
        msgList.add(msg5);
        Msg msg6 = new Msg("我真的编不下去啦！", Msg.TYPE_SENT);
        msgList.add(msg6);
        Msg msg7 = new Msg("反正也是凑行数的！", Msg.TYPE_RECEIVED);
        msgList.add(msg7);
        Msg msg8 = new Msg("功能才是最重要的！", Msg.TYPE_SENT);
        msgList.add(msg8);
    }

    public void StartAnimation(View view) {
        //点击之后开始渲染动画
        //首先计算坐标
        calThePotion();
        //myView.startJumpThread(PointList);
        myView.startJump(PointList);
    }

    private void calThePotion() {
//        LinearLayout Item1 = (LinearLayout) msgRecyclerView.getChildAt(2);
//        LinearLayout Item2 = (LinearLayout) msgRecyclerView.getChildAt(3);
//        LinearLayout Item3 = (LinearLayout) msgRecyclerView.getChildAt(4);
//
//        TextView Text1 = (TextView) Item1.findViewById(R.id.left_msg);
//        TextView Text2 = (TextView) Item2.findViewById(R.id.right_msg);
//        TextView Text3 = (TextView) Item3.findViewById(R.id.left_msg);
//
//        Log.d("TAG","1"+Text1.getText().toString()+" "
//        +"2"+Text2.getText().toString()+" "
//        +"3"+Text3.getText().toString());

        Point point1 = new Point(300, 600);
        Point point2 = new Point(670, 880);
        Point point3 = new Point(500, 1100);

        PointList.add(point1);
        PointList.add(point2);
        PointList.add(point3);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int x= (int)(displayMetrics.widthPixels);
//        PointList.add(new Point(120 , 50));
//        PointList.add(new Point(x - 140 ,250));
//        PointList.add(new Point(x - 180 ,450));
//        PointList.add(new Point(100 , 650));
//        PointList.add(new Point(x - 140 , 800));
//        PointList.add(new Point(x - 160 , (int)(displayMetrics.heightPixels)+40));
    }
}
