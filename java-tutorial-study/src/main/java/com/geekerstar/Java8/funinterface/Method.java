package com.geekerstar.Java8.funinterface;

/**
 * @author geekerstar
 * date: 2019-08-16 11:40
 * description:
 */
public class Method {


    public static void main(String[] args) {
        class Something{
            String startsWith(String s){
                return String.valueOf(s.charAt(0));
            }
        }

        Something something = new Something();
        Converter<String,String> converter = something::startsWith;
        String converted = converter.convert("java");
        System.out.println(converted);
    }



}
