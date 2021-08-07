package com.geekerstar.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SerializationExamples {
    public static void main(String[] args) {
        Gson gson = new Gson();


        //collections

        List marks = new ArrayList();

//Serialization
        System.out.println("marks:" + gson.toJson(marks));

//De-serialization
//get the type of the collection.
        Type listType = new TypeToken<List<Integer>>() {}.getType();
//pass the type of collection
        marks = gson.fromJson("[100,90,85]", listType);
        System.out.println("marks:" + marks);


        // 泛型处理
        // create a shape class of type circle.
        Shape<Circle> shape = new Shape<Circle>();

        // Create a Circle object
        Circle circle = new Circle(5.0);

        //assign circle to shape
        shape.setShape(circle);

        // Define a Type shapeType of type circle.
        Type shapeType = new TypeToken<Shape<Circle>>() {}.getType();

        //Serialize the json as ShapeType
        String jsonString = gson.toJson(shape, shapeType);
        System.out.println(jsonString);
        Shape shape1 = gson.fromJson(jsonString, Shape.class);

        System.out.println(shape1.get().getClass());
        System.out.println(shape1.get().toString());
        System.out.println(shape1.getArea());
        Shape shape2 = gson.fromJson(jsonString, shapeType);
        System.out.println(shape2.get().getClass());
        System.out.println(shape2.get().toString());
        System.out.println(shape2.getArea());
    }
}

class Shape <T> {
    public T shape;

    public void setShape(T shape) {
        this.shape = shape;
    }
    public T get() {
        return shape;
    }
    public double getArea() {
        if(shape instanceof Circle) {
            return ((Circle) shape).getArea();
        } else {
            return 0.0;
        }
    }
}

class Circle {
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }
    public String toString() {
        return "Circle";
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea() {
        return (radius*radius*3.14);
    }
}
