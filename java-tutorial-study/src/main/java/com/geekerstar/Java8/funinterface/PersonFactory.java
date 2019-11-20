package com.geekerstar.Java8.funinterface;

/**
 * @author geekerstar
 * date: 2019-08-16 11:47
 * description:
 */
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
