package com.demo.hypertest;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//This class reads bean info from application.properties and store it in object
@Component
public class BeansToPatch {

    @Resource
    private Environment env;

    public List<ClassEntry> getBeansToPatch() {
        List<ClassEntry> classConfigurations = new ArrayList<>();

        int i = 0;
        while (true) {
            String className = env.getProperty("bean[" + i + "].name");

            // If className is null, it means there are no more classes configured
            if (className == null)
                break;

            ClassEntry classEntry = new ClassEntry();
            classEntry.setName(className);

            List<MethodEntry> methods = new ArrayList<>();
            int j = 0;
            while (true) {
                String methodName = env.getProperty("bean[" + i + "].methods[" + j + "].name");

                // If methodName is null, it means there are no more methods configured for this class
                if (methodName == null)
                    break;

                String patchedValue = env.getProperty("bean[" + i + "].methods[" + j + "].patchedValue");
                MethodEntry methodEntry = new MethodEntry();
                methodEntry.setName(methodName);
                methodEntry.setPatchedValue(patchedValue);

                methods.add(methodEntry);
                j++;
            }

            classEntry.setMethods(methods);
            classConfigurations.add(classEntry);
            i++;
        }

        return classConfigurations;
    }

    public static class ClassEntry {
        private String name;
        private List<MethodEntry> methods;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<MethodEntry> getMethods() {
            return methods;
        }

        public void setMethods(List<MethodEntry> methods) {
            this.methods = methods;
        }
    }

    public static class MethodEntry {
        private String name;
        private String patchedValue;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatchedValue() {
            return patchedValue;
        }

        public void setPatchedValue(String patchedValue) {
            this.patchedValue = patchedValue;
        }
    }
}
