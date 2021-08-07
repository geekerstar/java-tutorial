package com.geekerstar.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;


public class FieldNameSupport {
    public static void main(String[] args) {

        SomeObject object = new SomeObject("one", "two");
        Gson gson = new GsonBuilder().create();
        String s = gson.toJson(object);
        System.out.println(s);
        SomeObject2 object1 = gson.fromJson(s, SomeObject2.class);
        System.out.println(object1);
    }
}

class SomeObject2 {
    private String someField;
    private String someOtherField;

    public SomeObject2(String a, String b) {
        this.someField = a;
        this.someOtherField = b;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "someField='" + someField + '\'' +
                ", someOtherField='" + someOtherField + '\'' +
                '}';
    }
}

class SomeObject {

    @SerializedName(value = "custom_naming", alternate = "a")
    private final String someField;
    private final String someOtherField;

    public SomeObject(String a, String b) {
        this.someField = a;
        this.someOtherField = b;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "someField='" + someField + '\'' +
                ", someOtherField='" + someOtherField + '\'' +
                '}';
    }
}
