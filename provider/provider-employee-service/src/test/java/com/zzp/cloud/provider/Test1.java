package com.zzp.cloud.provider;

public class Test1 {
    public static void main(String[] args) {
        for (var i=0;i<8;i++){
            var k = ++i;
            while (k<7){
                System.out.println(i);
                break;
            }
        }
    }
}
