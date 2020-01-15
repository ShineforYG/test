package com.shine.easy_coding;

public class FatherDemo {
    private int age;
    private FatherDemo(){

    }

    public FatherDemo(int age){
        this.age = age;
    }
}

class SonDemo extends FatherDemo{
    public SonDemo(){
        super(13);
    }

    public SonDemo(int a){
        super(13);

    }
}


 class FuctionDemo{
     public static void main(String[] args) {

     }

     public static double get(){return 1.0;}
//     public static float get(){return 2.0;}
 }