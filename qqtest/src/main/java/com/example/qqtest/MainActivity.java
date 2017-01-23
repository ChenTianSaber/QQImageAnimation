package com.example.qqtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<Msg>();

    private RecyclerView msgRecyclerView;

    private MyRecyclerAdapter adapter;

    //存储item的坐标
    List<Point> PointList = new ArrayList<>();
    MyView myView;

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
        //myView.setVisibility(View.VISIBLE);

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
        //这里执行掉落表情
        calThePotion();
        //myView.setAlpha(1);
        myView.initMovables(PointList);
    }

    private void calThePotion() {
        //这个用来计算表情应该掉落位置的坐标
        //首先拿到左边第一个，右边第一个和左边第二个TextView
        LinearLayout leftItem1, leftItem2,rightItem1;
        LinearLayout leftTop1,leftTop2,rightTop1;
        for(int i=1;;i++){
            View view = msgRecyclerView.getChildAt(i);
            leftItem1 = (LinearLayout) view.findViewById(R.id.left_layout);
            leftTop1 = (LinearLayout) view.findViewById(R.id.layout_top);
            TextView left = (TextView) leftItem1.findViewById(R.id.left_msg);
            if(!left.getText().toString().equals("")){
                View view2 = msgRecyclerView.getChildAt(i+1);
                rightItem1 = (LinearLayout) view2.findViewById(R.id.right_layout);
                rightTop1 = (LinearLayout) view2.findViewById(R.id.layout_top);
                View view3 = msgRecyclerView.getChildAt(i+2);
                leftItem2 = (LinearLayout) view3.findViewById(R.id.left_layout);
                leftTop2 = (LinearLayout) view3.findViewById(R.id.layout_top);
                Log.d("TAG",left.getText().toString());
                break;
            }
        }

        //拿到三个聊天框之后计算它们的中点坐标
        int leftX1 = (int) (leftItem1.getWidth()/2+leftItem1.getX());
        int leftX2 = (int) (leftItem2.getWidth()/2+leftItem2.getX());
        int rightX1 = (int) (rightItem1.getWidth()/2+rightItem1.getX());

        int dx20 = MyUtils.dip2px(getApplicationContext(),20);
        int leftY1 = leftTop1.getTop()+leftItem1.getTop()+dx20;
        int leftY2 = leftTop2.getTop()+leftItem2.getTop()+dx20;
        int rightY1 = rightTop1.getTop()+rightItem1.getTop()+dx20;
        Log.d("TAG", leftY2+" "+rightY1);
        //把坐标存进去
        Point first = new Point(leftX1,-100);
        Point left1Point = new Point(leftX1,leftY1);
        Point left2Point = new Point(leftX2,leftY2);
        Point right1Point = new Point(rightX1,rightY1);

        PointList.add(first);
        PointList.add(left1Point);
        PointList.add(left2Point);
        PointList.add(right1Point);
    }
}