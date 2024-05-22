package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(4);
        list.add(5);
        list.add(10);
        System.out.println(list);


        System.out.println(list.stream().filter(x -> x != 5).collect(Collectors.toList()));
    }
}
