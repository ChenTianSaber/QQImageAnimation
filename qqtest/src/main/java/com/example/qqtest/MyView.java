package com.example.qqtest;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

/**
 * Created by chentian on 23/01/2017.
 */

public class MyView extends View{
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

    List<Point> pointList;
    Canvas canvas;
    //BallThread bt;
    Point currentPoint;
    public MyView(Context Activity) {
        this(Activity,null);

    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        currentPoint = new Point(-100,-100);
        //bt = new BallThread();

        //this.setTranslationY(this.getTranslationY()-100);
    }
    public void initMovables(final List<Point> pointList){
        //bt.start();
        this.pointList = pointList;
//        Point startPoint = new Point(pointList.get(0).getX(), pointList.get(0).getY());
//        Point endPoint = new Point(pointList.get(1).getX(), pointList.get(1).getY());
//        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                currentPoint = (Point) animation.getAnimatedValue();
//                invalidate();
//            }
//        });
//        anim.setInterpolator(new BounceInterpolator());
//        anim.setDuration(2000);
//        anim.start();



        startTanTiao();

//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(animatorSet2).after(anim);
//        animatorSet.setDuration(2000);
//        animatorSet.start();
    }

    private void startTanTiao() {
//        Point startPoint2 = new Point(pointList.get(1).getX(), pointList.get(1).getY());
//        Point endPoint2 = new Point(pointList.get(3).getX(), pointList.get(3).getY());
//        ValueAnimator anim2 = ValueAnimator.ofObject(new PointEvaluator(), startPoint2, endPoint2);
//        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                currentPoint = (Point) animation.getAnimatedValue();
//                invalidate();
//            }
//        });
////        anim2.setDuration(2000);
////        anim2.start();
//
//        Point startPoint3 = new Point(pointList.get(1).getX(), pointList.get(1).getY());
//        Point endPoint3 = new Point(pointList.get(3).getX(), pointList.get(3).getY());
//        ValueAnimator anim3 = ValueAnimator.ofObject(new PointEvaluator(), startPoint3, endPoint3);
//        anim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                currentPoint = (Point) animation.getAnimatedValue();
//                invalidate();
//            }
//        });
//
//        AnimatorSet animatorSet2 = new AnimatorSet();
//        animatorSet2.setDuration(2000);
//        animatorSet2.play(anim2).with(anim3);
//        animatorSet2.start();

        Point startPoint3 = new Point(pointList.get(1).getX(), pointList.get(1).getY());
        int dx = (pointList.get(3).getX()-pointList.get(1).getX())/2+pointList.get(1).getX();
        Point endPoint3 = new Point(dx, pointList.get(1).getY()-600);
        ValueAnimator anim3 = ValueAnimator.ofObject(new PointEvaluator(), startPoint3, endPoint3);
        anim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim3.setInterpolator(new DecelerateInterpolator());
        anim3.setDuration(1000);
//        anim3.start();

        Point startPoint = new Point(dx, pointList.get(1).getY()-600);
        Point endPoint = new Point(pointList.get(3).getX(), pointList.get(3).getY());
        //Point endPoint = new Point(dx, pointList.get(3).getY());
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(3000);

        AnimatorSet animatorSet2 = new AnimatorSet();
        //animatorSet2.setDuration(1500);
        animatorSet2.play(anim).after(anim3);
        animatorSet2.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawBitmap(bitmap, currentPoint.getX(), currentPoint.getY(), null);
    }

}
