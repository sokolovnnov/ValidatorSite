package com.github.sokolovnnov.repo.inmemory;

import com.github.sokolovnnov.model.SimpleNode;
import com.github.sokolovnnov.model.SimpleWay;
import org.springframework.cache.annotation.Cacheable;
import com.github.sokolovnnov.repo.ResultRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ResultInMemoryRepository extends ResultRepository {

    private static IsolatedNodes isolatedNodes = new IsolatedNodes();

    @Override
    @Cacheable("wayCache")
    public List<SimpleWay> getAll() {

        List<SimpleNode> simpleNodes = isolatedNodes.nodesMap
                .values()
                .stream()
                .flatMap(simpleNodes1 -> simpleNodes1.stream())
                .collect(Collectors.toList());

        return simpleNodesToSimpleWays(simpleNodes);
    }

    public static void setIsolatedNodes(IsolatedNodes isolatedNodes) {
        ResultInMemoryRepository.isolatedNodes = isolatedNodes;
    }
}
