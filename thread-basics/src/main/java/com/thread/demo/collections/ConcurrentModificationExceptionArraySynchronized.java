package com.thread.demo.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcurrentModificationExceptionArraySynchronized {

    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory();

        /**
         * note that displaySoldProducts() method will display only those products that populate within 1s, then the all products
         * that will be added later. the displaySoldProducts() method will not be able to print them, and this is reasonable.
         */
        new Thread(inventory::populateSoldProducts).start();
        Thread.sleep(1000);
        new Thread(inventory::displaySoldProducts).start();
    }

    public static class Inventory{
        private List<Product> soldProducts =  Collections.synchronizedList(new ArrayList<Product>());

        public void populateSoldProducts(){
            for(int i = 0; i < 100; i++){
                Product product = new Product(i, "product_" + i);
                soldProducts.add(product);
                System.out.println("add product:" + product);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void displaySoldProducts(){
            synchronized(soldProducts) {
                for (Product product : soldProducts) {
                    System.out.println("print:" + product);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static class Product{
        private Integer productId;
        private String productName;

        public Product(Integer productId, String productName) {
            this.productId = productId;
            this.productName = productName;
        }

        @Override
        public String toString() {
            return "Product{" + "productId='" + productId + '\'' + ", productName='" + productName + '\'' + '}';
        }
    }
}

