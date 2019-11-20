package com.geekerstar.todo;

import com.geekerstar.pojo.Car;
import com.geekerstar.pojo.Person2;

import java.util.Optional;


public class OptionalInAction {

    public static void main(String[] args) {
        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }

    private static String getInsuranceNameByOptional(Person2 person) {

        return Optional.ofNullable(person)
                .flatMap(Person2::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("Unknown");
    }
}
