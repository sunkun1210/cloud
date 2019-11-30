package com.imooc.example.proxy.classloader;

public class ClassLoaderChecker {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader m = new MyClassLoader("????", "myClassLoader");
        Class c = m.loadClass("com.imooc.example.proxy.UserDomain");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        //System.out.println(c.getClassLoader().getParent().getParent().getParent());
        c.newInstance();
    }
}
