package com.example.rgr.JavaModule;

import com.example.rgr.JavaModule.Types.Dble;
import com.example.rgr.JavaModule.Types.CartVector2D;
import com.example.rgr.JavaModule.Types.UserType;

import java.util.ArrayList;
import java.util.Arrays;

public class UserFactory {

    public static ArrayList<String> get_type_name_list()
    {
        ArrayList<String> type_list = new ArrayList<>(Arrays.asList("Double", "Vector2D"));
        return type_list;
    }

    public static UserType get_builder_by_name(String type_name)
    {
        switch (type_name)
        {
            case "Double":
                return new Dble();
            case "Vector2D":
                return new CartVector2D();
            default:
                return null;
        }
    }
}
