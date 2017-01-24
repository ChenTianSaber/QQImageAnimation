package com.example.qqdemo;

import android.animation.TypeEvaluator;

/**
 * Created by chentian on 23/01/2017.
 */

public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        int x = (int) (startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX()));
        int y = (int) (startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY()));
        Point point = new Point(x, y);
        return point;
    }

}
