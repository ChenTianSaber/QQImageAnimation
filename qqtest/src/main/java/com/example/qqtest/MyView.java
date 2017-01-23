package com.example.qqtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by chentian on 23/01/2017.
 */

public class MyView extends View{
    float currentX= 100f;//表情实时运动坐标
    float currentY= -100f;

    float count = 0.0001f;//下落的加速度
    float origal = 0.0001f;
    //float count;

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

    //List<Point> pointList;
    Canvas canvas;

    Context mContext;

    public MyView(Context Activity) {
        this(Activity,null);

    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        //count = MyUtils.px2dip(mContext,16);
    }
    public void initMovables(final List<Point> pointList){

        Log.d("TAG", pointList.get(1).getX()+" "+pointList.get(2).getX()+" "+pointList.get(3).getX()+"");
        new Thread(new Runnable() {
            @Override
            public void run() {
                currentX=pointList.get(0).getX();
                //currentY=pointList.get(0).getY();
                down(pointList.get(1).getY());
                up(pointList.get(1).getY()-200);
                down(pointList.get(1).getY());
                up(pointList.get(1).getY()-100);
                down(pointList.get(1).getY());
                up(pointList.get(1).getY()-50);
                down(pointList.get(1).getY());

                JumpToRight(pointList.get(3).getY(),200);
                JumpToRight(pointList.get(3).getY(),100);
                JumpToRight(pointList.get(3).getY(),50);

                JumpToLeft(pointList.get(2).getY(),200);
                JumpToLeft(pointList.get(2).getY(),100);
                JumpToLeft(pointList.get(2).getY(),50);

                up(currentY-100);
                down(MyUtils.getScreenHeight(mContext)+100);

                currentX= 100f;//表情实时运动坐标
                currentY= -100f;

                Log.d("TAG", "线程结束！");
            }
        }).start();
    }

    public void down(float y){
        while (currentY<y){
            float b = 0.1f+count;
            currentY += b;
            count+=0.0001;
            //offsetTopAndBottom((int) b);
            postInvalidate();
        }
        count = origal;
    }

    public void up(float y){
        while (y<currentY){
            float b = 0.1f+count;
            currentY-=b;
            count+=0.0001;
            postInvalidate();
        }
        count = origal;
    }

    public void JumpToRight(float y,int jump){
//        float avaX = (x-currentX)/2+currentX;
//        float dy = (y-currentY);
        float y2 = currentY-jump;
        while (currentY>y2){
            float b = 0.1f;
            currentX+=b;

            float b2 = 0.1f+count;
            currentY-=b2;
            count+=0.0001;

            postInvalidate();
        }
        count = origal;
        while (currentY<y){
            float b = 0.1f;
            currentX+=b;

            float b2 = 0.1f+count;
            currentY += b2;
            count+=0.0001;
            postInvalidate();
        }
        count = origal;
    }

    public void JumpToLeft(float y,int jump){
//        float avaX = (x-currentX)/2+currentX;
//        float dy = (y-currentY);
        float y2 = currentY-jump;
        while (currentY>y2){
            float b = 0.1f;
            currentX-=b;

            float b2 = 0.1f+count;
            currentY-=b2;
            count+=0.0001;

            postInvalidate();
        }
        count = origal;
        while (currentY<y){
            float b = 0.1f;
            currentX-=b;

            float b2 = 0.1f+count;
            currentY += b2;
            count+=0.0001;
            postInvalidate();
        }
        count = origal;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawBitmap(bitmap, currentX, currentY, null);
    }
}
