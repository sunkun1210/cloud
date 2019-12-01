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

        String s1 = new String("a");
        s1.intern();
        String s2="a";
        System.out.println(s1==s2);/**  jdk1.6:false  jdk1.7:false  */

        String s3=new String("a")+new String("a"); /**  jdk1.6:false  jdk1.7:true  */
        s3.intern();
        String s4="aa";
        System.out.println(s3==s4);
    }
}
