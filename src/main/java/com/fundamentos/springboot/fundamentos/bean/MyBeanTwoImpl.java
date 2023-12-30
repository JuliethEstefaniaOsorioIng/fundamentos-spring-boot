package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanTwoImpl implements MyBean{
    @Override
    public void print() {
        System.out.println("Hola desde mi implementación dos propia del bean");
    }
}
