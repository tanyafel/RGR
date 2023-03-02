package com.example.rgr.JavaModule.Types;

import java.util.Comparator;

public class Dble implements UserType
{
    private double value;

    public Dble() { value = 0;}

    public Dble(double _value) {value = _value;}

    @Override
    public String type_name() {
        return "Double";
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
        return value;
    }

    @Override
    public Object parse_value(String ss) {

        value = Double.parseDouble(ss);
        return this;
    }

    @Override
    public Comparator get_type_Comparator() {
        return this;
    }

    @Override
    public String toString() {return String.valueOf(value);}

    @Override
    public int compare(Object o1, Object o2) {

        if (((Dble)o1).value == ((Dble)o2).value) return 0;
        if (((Dble)o1).value > ((Dble)o2).value) return 1;
        else return -1;
    }
}