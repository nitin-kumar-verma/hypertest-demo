package com.demo.hypertest;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class HyperTest {

    @Autowired
    private Environment environment;

    @Autowired
    private BeansToPatch beans;

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if(environment.containsProperty("HTMODE") && environment.getProperty("HTMODE").equals("REPLAY")) {
                    Class<?> beanClass = bean.getClass();

                    for (BeansToPatch.ClassEntry patchedBean : beans.getBeansToPatch()) {

                        if (beanClass.getName().contentEquals(patchedBean.getName())) {


                            for (BeansToPatch.MethodEntry methodEntry : patchedBean.getMethods())

                                try {
                                    //Replace bean with patched bean
                                    bean = new ByteBuddy()
                                            .subclass(bean.getClass())
                                            .method(ElementMatchers.named(methodEntry.getName()))
                                            .intercept(FixedValue.value(methodEntry.getPatchedValue()))
                                            .make()
                                            .load(bean.getClass().getClassLoader())
                                            .getLoaded()
                                            .newInstance();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                        }
                    }
                }
                return bean;
            }

        };
    }
}


