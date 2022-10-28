package com.musictest;

import com.musictest.model.Datasource;
//connection을 main함수에서 조정
public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        datasource.close();
    }
}