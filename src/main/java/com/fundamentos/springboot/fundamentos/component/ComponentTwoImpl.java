package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImpl implements CompenentDependency{
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente dos");
    }
}
