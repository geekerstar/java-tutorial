package com.geekerstar.Java8.funinterface;

/**
 * @author geekerstar
 * date: 2019-08-16 11:49
 * description:
 */
public class PersonTest {
    public static void main(String[] args) {
        //我们通过 Person::new 来创建一个Person类构造函数的引用。Java编译器会自动地选择合适的构造函数来匹配PersonFactory.create函数的签名，并选择正确的构造函数形式。
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter","parker");
        System.out.println(person);

    }
}
