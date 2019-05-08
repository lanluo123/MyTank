package com.TA;


import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
     static Properties prop=new Properties();
     static {
         try {
             prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     public static Object getKey(String name){
         return  prop.getProperty(name);
     }
}
