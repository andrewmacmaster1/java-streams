package com.xpanxion.solution;

import java.util.Map;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;
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
}
