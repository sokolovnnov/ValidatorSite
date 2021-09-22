package com.github.sokolovnnov;

import com.github.sokolovnnov.repo.inmemory.IsolatedNodes;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        StorageUtils.downloadFromFTP();

        IsolatedNodes isolatedNodes
                = StorageUtils.deSerializeInMemoryRepository();
        isolatedNodes.nodesMap.values().stream().flatMap(simpleNodes1 -> simpleNodes1.stream()).forEach(System.out::println);
    }
}
