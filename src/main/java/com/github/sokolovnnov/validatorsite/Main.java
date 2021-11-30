package com.github.sokolovnnov.validatorsite;

import com.github.sokolovnnov.validatorsite.repo.inmemory.IsolatedNodeStorage;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        StorageUtils.downloadFromFTP();

//        IsolatedNodeStorage isolatedNodeStorage
//                = StorageUtils.deSerializeInMemoryRepository();
//        isolatedNodeStorage.simpleNodes.stream().forEach(System.out::println);
        System.out.println(System.getenv("FTP_USER"));
        System.out.println(Integer.parseInt(System.getenv("FTP_PORT")));
    }
}
