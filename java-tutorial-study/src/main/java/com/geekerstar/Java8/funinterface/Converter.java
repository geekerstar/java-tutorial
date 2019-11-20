package com.geekerstar.Java8.funinterface;

/**
 * @author geekerstar
 * date: 2019-08-16 11:24
 * description:
 */
@FunctionalInterface
interface Converter<F,T> {
    T convert(F from);
}
