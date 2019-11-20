package com.geekerstar.todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args) {
//        createStreamFromCollection().forEach(System.out::println);
//        createStreamFromValues().forEach(System.out::println);
//        createStreamFromArays().forEach(System.out::println);

//        Stream<String> streamFromFile = createStreamFromFile();
//        System.out.println(streamFromFile);

//        createStreamFromIterator().forEach(System.out::println);
//        createStreamFromGenerate().forEach(System.out::println);
        createaObjStreamFromGenerate().forEach(System.out::println);

    }

    /**
     * Generate the stream object from collection.
     * @return
     */
    private static Stream<String> createStreamFromCollection(){
        List<String> list = Arrays.asList("hello","hi","geekerstar","jikewenku");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues(){
        return Stream.of("hello","alex","haha","ggg");
    }

    private static Stream<String> createStreamFromArays(){
        String[] strings = {"haha","sd","dfdse","4fd"};
        return Arrays.stream(strings);
    }

    private static Stream<String> createStreamFromFile(){
        Path path = Paths.get("/work/ideaprojects/java8_study/src/main/java/com/geekerstar/java8/LambdaExpression.java");
        try(Stream<String> streamFromFile = Files.lines(path)) {
            streamFromFile.forEach(System.out::println);
            return streamFromFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Integer> createStreamFromIterator(){

        Stream<Integer> stream = Stream.iterate(0,n -> n+2).limit(10);

        return stream;
    }

    private static Stream<Double> createStreamFromGenerate(){
        return Stream.generate(Math::random).limit(10);
    }

    private static Stream<Obj> createaObjStreamFromGenerate(){
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    static class ObjSupplier implements Supplier<Obj>{

        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index,"Name -> "+ index);
        }
    }

    static class Obj{
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
