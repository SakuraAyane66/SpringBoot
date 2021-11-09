package com.example.demo.common.utils.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableTest {
    public static void main(String[] args) {
        Car c1 = new Car("benqi",1.0,66);
        Car c2 = new Car("baoshike",2.0,88);
        System.out.println(c1.compareTo(c2));
        Mercedes_Benz m1 = new Mercedes_Benz("MCAR",3.0,99);
        System.out.println(m1.getName());
        List<Car> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
//        list.add(m1);
        Collections.sort(list);
        for(Car c:list){
            System.out.println(c.getSpeed());
        }
        list.sort(new SortByPrice());
        for(Car c:list){
            System.out.println(c.getPrice());
        }

    }
}
class Car implements Comparable{
    private String name;
    private Double price;
    private int speed;

    public Car(String name, Double price, int speed) {
        this.name = name;
        this.price = price;
        this.speed = speed;
    }

    public Car(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int compareTo(Object o) {
        Car c1 = (Car)o;
        return this.speed-c1.speed;
    }

}

class Mercedes_Benz extends Car{
    public Mercedes_Benz(String name, Double price, int speed){
        //super(name, price, speed);
    }
}

class SortByPrice implements Comparator<Car>{

    @Override
    public int compare(Car o1, Car o2) {
        System.out.println("o1.price?"+o1.getPrice());
        System.out.println("o2.price?"+o2.getPrice());
        return (int)(o1.getPrice()-o2.getPrice());
    }
}
