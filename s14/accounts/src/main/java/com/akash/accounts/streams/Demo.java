package com.akash.accounts.streams;

import com.akash.accounts.AccountsApplication;
import org.springframework.boot.SpringApplication;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;




public class Demo {
    //************* flatMap() method

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Akash");
        list.add("sanket");
        list.add("sagar");
        //*************creation of stream********
        Stream<String> stream = list.stream();
        //stream.forEach((l)-> System.out.println(l));

        /*Stream<String> stringStream = Stream.of("akash", "ashish", "sagar");
        stringStream.forEach(System.out::println);*/

        /*Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(System.out::println);*/
        //*************creation of stream end********

        //************* map() method
        //stream.map(name-> name.toUpperCase()).forEach(System.out::println);
        //************* map() method end


        Demo.collectStream();


        //*************
        //*************

    }
    public static void flatMapInStreams(){
        String  [] arrayOfWords = {"akash","aher"};

        // we are performing this operation in single line only
        Arrays.stream(arrayOfWords).map(word -> word.split("")).flatMap(Arrays::stream).forEach(System.out::println);

        // detailed code for above operation
            /*
            Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
            Stream<String[]> streamOfLetters = streamOfWords.map(word -> word.split(""));   // we got two stream here Stream<String[]
            Stream<String> streamOfLetterArray = streamOfLetters.flatMap(strm -> Arrays.stream(strm));
            streamOfLetterArray.forEach(System.out::println);
            */


    }


    public static void filterInStream(){
        List<String> list = new ArrayList<>();
        list.add("Supply");
        list.add("Hr");
        list.add("Sales");
        list.add("Marketing");

        list.stream().filter(word-> word.startsWith("S")).forEach(System.out::println);

    }

    public static void limitInSteams(){
        Stream.generate(new Random()::nextInt).limit(10).forEach(System.out::println);
    }

    public static void skipInStream(){
        Stream.iterate(1, n-> n+1).skip(10).limit(20).forEach(System.out::println);
    }

    public static void traverseOnceInStream(){

        try {
            List<String> list = new ArrayList<>();
            list.add("Supply");
            list.add("Hr");
            list.add("Sales");
            list.add("Marketing");

            Stream<String> stream = list.stream();

            stream.forEach(System.out::println);
            stream.forEach(System.out::println);    // we get IllegalStateException when try to traverse stream again
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void reduceInStream(){System.out.println(Stream.iterate(1,n->n+1).limit(20).reduce(0,(a,b)->a+b));
    }

    public static void collectStream(){
        List<String> list = new ArrayList<>();
        list.add("Supply");
        list.add("Hr");
        list.add("Sales");
        list.add("Marketing");

        Stream<String> stream = list.stream();

        List<String> s = stream.filter(word -> word.startsWith("S")).collect(Collectors.toList());

        s.stream().forEach(System.out::println);
    }




}
