package com.fundamentos.springboot.fundamentos.bean;

import com.fundamentos.springboot.fundamentos.FundamentosApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDepedencyImpl implements MyBeanWithDependency {

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDepedencyImpl.class);
    private MyOperation myOperation;

    public MyBeanWithDepedencyImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {

        LOGGER.info("Hemos ingresado al método printWithDependency");
        int numero = 1;
        LOGGER.debug("El número enviado como parametro a la dependecia es "+numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación con dependencia");
    }
}
