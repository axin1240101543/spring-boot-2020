package com.darren.center.springboot.collection;

import java.util.Set;
import java.util.TreeSet;

/**
 * 自然排序
 */
public class Customer implements Comparable{

    private String name;

    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int result;
        result = (name == null) ? 0 : name.hashCode();
        result = 29 * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Customer)){
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.name.equals(other.getName()) && this.age == other.getAge()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {
        Customer other = (Customer) o;

        //先按name属性排序
        if (this.name.compareTo(other.getName()) > 0){
            return 1;
        }
        if (this.name.compareTo(other.getName()) < 0){
            return -1;
        }

        //再按照age属性排序
        if(this.age > other.getAge()){
            return 1;
        }
        if(this.age < other.getAge()){
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Set<Customer> set = new TreeSet<>();
        Customer c1 = new Customer("Tom", 2);
        Customer c2 = new Customer("Tom", 1);
        Customer c3 = new Customer("Tom", 5);
        Customer c4 = new Customer("Tom", 3);
        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(c4);
        set.forEach(a -> {
            System.out.println(a.getName() + " " + a.getAge());
        });
    }
}
