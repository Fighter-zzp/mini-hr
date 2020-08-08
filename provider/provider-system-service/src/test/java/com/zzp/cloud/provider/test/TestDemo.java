package com.zzp.cloud.provider.test;

public class TestDemo {
    public static void main(String[] args) {
        var uf = "https://zzp-test.oss-cn-shenzhen.aliyuncs.com/face/12691daf-e506-4049-beb3-470209672adf.jpg";
        var s = uf.substring((uf.lastIndexOf("/") + 1));
        System.out.println(s);
    }
}
