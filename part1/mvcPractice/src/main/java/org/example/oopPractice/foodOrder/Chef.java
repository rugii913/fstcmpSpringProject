package org.example.oopPractice.foodOrder;

public class Chef {

    public Cook makeCook(MenuItem menuItem) {
        Cook cook = new Cook(menuItem);
        return cook;
    }
}
