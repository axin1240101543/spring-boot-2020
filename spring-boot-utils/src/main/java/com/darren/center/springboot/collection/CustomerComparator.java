package com.darren.center.springboot.collection;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 客户化排序
 */
public class CustomerComparator implements Comparator<Customer>{

    @Override
    public int compare(Customer o1, Customer o2) {
        if (o1.getName().compareTo(o2.getName()) > 0){
            return -1;
        }
        if (o1.getName().compareTo(o2.getName()) < 0){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Set<Customer> set = new TreeSet<>(new CustomerComparator());
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
