package com.github.sokolovnnov.validatorsite.repo.inmemory;

import com.github.sokolovnnov.validatorsite.StorageUtils;
import com.github.sokolovnnov.validatorsite.model.SimpleNode;
import com.github.sokolovnnov.validatorsite.model.SimpleWay;
import org.springframework.cache.annotation.Cacheable;
import com.github.sokolovnnov.validatorsite.repo.ResultRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ResultInMemoryRepository extends ResultRepository {

    private static IsolatedNodes isolatedNodes = new IsolatedNodes();

    @PostConstruct
    public void runAfterStartup() throws IOException, ClassNotFoundException {
        setIsolatedNodes(StorageUtils.deSerializeInMemoryRepository());
    }

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
