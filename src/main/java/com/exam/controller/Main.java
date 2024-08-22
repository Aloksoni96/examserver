//package com.exam.controller;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//public class Main {
//
//    public static void main(String[] args){
//
////        Predicate<Integer> predicate = (x->x>10000);
////        //predicate.test(200);
////        System.out.println(predicate.test(200));
//
//        Predicate<Integer> isEven = (x->x%2==0);
//        Predicate<String> startWithLetterV = x-> x.toLowerCase().charAt(0)=='v';
//        Predicate<String> endWithLetterL =x->x.toLowerCase().charAt(x.length()-1)=='l';
//        Predicate<String> and = startWithLetterV.and(endWithLetterL);
//        System.out.println(and.test("vlol"));
//
////        List<Integer> list = Arrays.asList(2 ,4,5,6,7,8);
////        for (int i : list){
////            if (isEven.test(i)){
////                System.out.println(i);
////            }
////        }
//
//        Function<String , Integer> function = x->x.length();
//        System.out.println(function.apply("AlokSoni"));
//
//        Function<String ,String> function1 = x->x.substring(0,3);
//        System.out.println(function1.apply("AlokSoni"));
//
//        Function<List<Student> ,List<Student>> list = li-> {
//            List<Student> result = new ArrayList<>();
//            for (Student s : li) {
//                if (function1.apply(s.getName()).equalsIgnoreCase("vip")) {
//
//                   result.add(s);
//
//                }
//
//
//            }
//            return result;
//        };
//
//        Student s1 = new Student(1,"Alok");
//        Student s2 = new Student(3,"Saurabh");
//        Student s3 = new Student(4,"Vipul");
//
//        List<Student> student = Arrays.asList(s1 ,s2 ,s3);
//        List<Student> apply = list.apply(student);
//        System.out.println(apply);
//
//
//    }
//
//}
//
// class Student{
//
//    int id;
//    String name;
//
//
//    public Student(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//     public int getId() {
//         return id;
//     }
//
//     public void setId(int id) {
//         this.id = id;
//     }
//
//     public String getName() {
//         return name;
//     }
//
//     public void setName(String name) {
//         this.name = name;
//     }
//
//     @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
//
//
