package com.example.qqdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

/**
 * Created by chentian on 23/01/2017.
 */

public class MyView extends View{
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
    Context mContext;

    Point currentPoint = new Point(-100,-100);

    List<Point> PointList;

    public MyView(Context Activity) {
        this(Activity,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, currentPoint.getX(), currentPoint.getY(), null);
    }

    public void startJump(final List<Point> PointList) {
        this.PointList = PointList;
        Animator anim = FirstDown(PointList.get(0).getX(),-100,PointList.get(0).getY());

        AnimatorSet set1 = LuoChaJump(PointList.get(0).getX(),PointList.get(0).getY(),
                PointList.get(1).getX(),PointList.get(1).getY());

        AnimatorSet set2 = PinTiao(PointList.get(1).getX(),PointList.get(1).getY(),
                PointList.get(1).getX()+50,PointList.get(1).getY(),100);

        AnimatorSet set3 = PinTiao(PointList.get(1).getX()+50,PointList.get(1).getY(),
                PointList.get(1).getX()+100,PointList.get(1).getY(),50);

        AnimatorSet set4 = LuoChaJump(PointList.get(1).getX()+100,PointList.get(1).getY(),
                PointList.get(2).getX(),PointList.get(2).getY());

        AnimatorSet set5 = PinTiao(PointList.get(2).getX(),PointList.get(2).getY(),
                PointList.get(2).getX()-50,PointList.get(2).getY(),100);

        AnimatorSet set6 = PinTiao(PointList.get(2).getX()-50,PointList.get(2).getY(),
                PointList.get(2).getX()-100,PointList.get(2).getY(),50);

        AnimatorSet set7 = FinishDown(PointList.get(2).getX()-100,PointList.get(2).getY());

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(anim,set1,set2,set3,set4,set5,set6,set7);
        set.start();
    }

    public Animator FirstDown(float x1,float y1,float y2){
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x1, y2);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(2500);
        return anim;
    }

    public AnimatorSet LuoChaJump(float x1,float y1,float x2,float y2){
        float dx = (x2-x1)/3+x1;
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(dx, y1-500);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(1000);

        Point startPoint2 = new Point(dx, y1-500);
        Point endPoint2 = new Point(x2, y2);
        ValueAnimator anim2 = ValueAnimator.ofObject(new PointEvaluator(), startPoint2, endPoint2);
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim2.setInterpolator(new AccelerateInterpolator());
        anim2.setDuration(1500);

        AnimatorSet set = new AnimatorSet();
        set.play(anim2).after(anim);
        return set;

    }

    public AnimatorSet PinTiao(float x1,float y1,float x2,float y2,int dy){
        float dx = (x2-x1)/2+x1;
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(dx, y1-dy);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(300);

        Point startPoint2 = new Point(dx, y1-dy);
        Point endPoint2 = new Point(x2, y2);
        ValueAnimator anim2 = ValueAnimator.ofObject(new PointEvaluator(), startPoint2, endPoint2);
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim2.setInterpolator(new AccelerateInterpolator());
        anim2.setDuration(250);

        AnimatorSet set = new AnimatorSet();
        set.play(anim2).after(anim);
        return set;
    }

    public AnimatorSet FinishDown(float x1,float y1){
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x1, y1-100);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(250);

        Point startPoint2 = new Point(x1, y1-100);
        Point endPoint2 = new Point(x1, MyUtils.getScreenHeight(mContext)+100);
        ValueAnimator anim2 = ValueAnimator.ofObject(new PointEvaluator(), startPoint2, endPoint2);
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim2.setInterpolator(new AccelerateInterpolator());
        anim2.setDuration(1500);

        AnimatorSet set = new AnimatorSet();
        set.play(anim2).after(anim);
        return set;
    }
}
