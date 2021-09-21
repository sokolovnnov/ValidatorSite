package ru.antisida.validatorsite.repo.inmemory;

import org.springframework.cache.annotation.Cacheable;
import ru.antisida.validatorsite.model.SimpleNode;
import ru.antisida.validatorsite.model.SimpleWay;
import ru.antisida.validatorsite.repo.ResultRepository;

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
