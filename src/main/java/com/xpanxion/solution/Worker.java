package com.xpanxion.solution;

import java.util.Map;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Person;
import com.xpanxion.java.assignments.model.Product;

public class Worker {
    public void ex1 () {
        var productArray = DataAccess.getProducts();
        var newProductArray = productArray.stream().map(p -> {
            String department;
            if (p.getDepartmentId() == 1) {
                department = "Electronics";
            }
            else if (p.getDepartmentId() == 2) {
                department = "Food";
            }
            else {
                department = "";
            }
            return new Product(p.getId(), p.getDepartmentId(), department, p.getName(), p.getPrice(), p.getSku());
        }).toList();
        for (Product product : newProductArray) {
            System.out.println(product);
        }
    }

    public void ex2 () {
        var productArray = DataAccess.getProducts();
        var newProductArray = productArray.stream().map(p -> new Product(p.getId(), p.getDepartmentId(), "N/A", p.getName(), p.getPrice(), p.getSku())).toList();
        for (Product product : newProductArray) {
            System.out.println(product);
        }
    }

    public void ex3() {
        var productArray = DataAccess.getProducts();
        var newProductArray = productArray.stream().filter(p -> p.getDepartmentId() == 1 && p.getPrice() >= 10).toList();
        for (Product product : newProductArray) {
            System.out.println(product);
        }
    }

    public void ex4() {
        var productArray = DataAccess.getProducts();
        var foodSum = productArray.stream().filter(p -> p.getDepartmentId() == 2).map(Product::getPrice).reduce((float) 0, Float::sum);

        System.out.printf("$%.2f%n", foodSum);
    }

    public void ex5() {
        var personArray = DataAccess.getPeople();
        var newPersonArray = personArray.stream().filter(p -> p.getId() <= 3).map(p -> new Person(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getSsn().substring(7))).toList();
        for (Person person : newPersonArray) {
            System.out.println(person);
        }
    }
}
