package com.xpanxion.solution;

import java.util.*;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.*;

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
        System.out.println(newProductArray);
    }

    public void ex2 () {
        var productArray = DataAccess.getProducts();
        var newProductArray = productArray.stream().map(p -> new Product(p.getId(), p.getDepartmentId(), "N/A", p.getName(), p.getPrice(), p.getSku())).toList();
        System.out.println(newProductArray);
    }

    public void ex3() {
        var productArray = DataAccess.getProducts();
        var newProductArray = productArray.stream().filter(p -> p.getDepartmentId() == 1 && p.getPrice() >= 10).toList();
        System.out.println(newProductArray);
    }

    public void ex4() {
        var productArray = DataAccess.getProducts();
        var foodSum = productArray.stream().filter(p -> p.getDepartmentId() == 2).map(Product::getPrice).reduce((float) 0, Float::sum);

        System.out.printf("$%.2f%n", foodSum);
    }

    public void ex5() {
        var personArray = DataAccess.getPeople();
        var newPersonArray = personArray.stream().filter(p -> p.getId() <= 3).map(p -> new Person(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getSsn().substring(7))).toList();
        System.out.println(newPersonArray);
    }

    public void ex6() {
        var catsArray = DataAccess.getCats();
        catsArray.sort(Cat::compareTo);
        System.out.println(catsArray);
    }

    public void ex7() {
        var words = DataAccess.getWords();
        StringTokenizer tokenizer = new StringTokenizer(words);
        var wordsDict = new HashMap<String, Integer>();
        while (tokenizer.hasMoreTokens()) {
            var word = tokenizer.nextToken();
            if (wordsDict.containsKey(word)) {
                wordsDict.put(word, wordsDict.get(word)+1);
            }
            else {
                wordsDict.put(word, 1);
            }
        }
        var keys = new ArrayList<>(wordsDict.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            System.out.println(key + " = " + wordsDict.get(key));
        }
    }

    public void ex8() {
        var personList = DataAccess.getPeople();
        personList.forEach(u -> {u.setLastName("null"); u.setSsn("null");});
        System.out.println(personList);
    }

    public void ex9() {
        var productList = DataAccess.getProducts();
        productList.forEach(u -> {
            if (u.getDepartmentId() == 1) {
                u.setPrice(u.getPrice()+2);
            }
        });
        System.out.printf("$%.2f%n", productList.stream().filter(p -> p.getDepartmentId() == 1).map(Product::getPrice).reduce((float) 0, Float::sum));
    }

    public void ex10() {
        var personList = DataAccess.getPeople();
        var catList = DataAccess.getCats();

        var peopleCats = new ArrayList<>();

        for (Person person : personList) {
            var personsCat = catList.stream().filter(c -> c.getId() == person.getId()).toList();
            peopleCats.add(new PersonCat(person.getId(), person.getFirstName(), personsCat));
        }
        System.out.println(peopleCats);
    }
}
