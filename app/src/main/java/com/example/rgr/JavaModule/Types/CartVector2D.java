package com.example.rgr.JavaModule.Types;

import java.util.Comparator;

public class CartVector2D implements UserType
{
    private int x;
    private int y;
    private double vectorLength;

    public CartVector2D() {
        x = 0;
        y = 0;
        vectorLength = Math.sqrt(x * x + y * y);
    }

    public CartVector2D(int _axis_x, int _axis_y)
    {
        x = _axis_x;
        y = _axis_y;
        vectorLength = Math.sqrt(x * x + y * y);
    }

    @Override
    public String type_name() {
        return "Vector2D";
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object clone() {
        return this;
    }

    @Override
    public Object read_value() {
        return new Object[]{x,y};
    }

    @Override
    public Object parse_value(String ss) {

        String ss1;
        ss1 = ss.substring(1, ss.length()-1);
        String[] point2d = ss1.split(";");

        x = Integer.parseInt(point2d[0]);
        y = Integer.parseInt(point2d[1]);
        return this;
    }

    @Override
    public Comparator get_type_Comparator() {
        return this;
    }

    @Override
    public String toString() { return "(" + x + ";" + y + ")"; }
//
//    public double getDistance(int _x, int _y)
//    {
//        return Math.abs(Math.sqrt(Math.pow((x - _x),2)-Math.pow(y - _y,2)));
//    }

    @Override
    public int compare(Object o1, Object o2) {

        if (((CartVector2D)o1).vectorLength ==((CartVector2D)o2).vectorLength) {
            return 0;
        }
        if (((CartVector2D)o1).vectorLength > ((CartVector2D)o2).vectorLength) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
