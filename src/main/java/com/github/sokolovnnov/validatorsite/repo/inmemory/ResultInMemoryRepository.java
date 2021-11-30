package com.github.sokolovnnov.validatorsite.repo.inmemory;

import com.github.sokolovnnov.connectivitytest.repository.inMemory.IsolatedNodeStorage;
import com.github.sokolovnnov.validatorsite.StorageUtils;
import com.github.sokolovnnov.connectivitytest.model.SimpleNode;
import com.github.sokolovnnov.connectivitytest.model.SimpleWay;
import org.springframework.cache.annotation.Cacheable;
import com.github.sokolovnnov.validatorsite.repo.ResultRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Repository
public class ResultInMemoryRepository extends ResultRepository {

    private IsolatedNodeStorage isolatedNodeStorage = new IsolatedNodeStorage();

    @PostConstruct
    public void runAfterStartup() throws IOException, ClassNotFoundException {
        setIsolatedNodes(StorageUtils.deSerializeInMemoryRepository());
//        setIsolatedNodesList(StorageUtils.fromJson());
    }

    @Override
    @Cacheable("wayCache")
    public List<SimpleWay> getAll() {

      /*  List<SimpleNode> simpleNodes = isolatedNodeStorage.nodesMap
                .values()
                .stream()
                .flatMap(simpleNodes1 -> simpleNodes1.stream())
                .collect(Collectors.toList());*/

        return simpleNodesToSimpleWays(isolatedNodeStorage.simpleNodes);
    }

    private void setIsolatedNodes(IsolatedNodeStorage isolatedNodeStorage) {
        this.isolatedNodeStorage = isolatedNodeStorage;
    }

    private void setIsolatedNodesList(List<SimpleNode> simpleNodes) {
        this.isolatedNodeStorage.simpleNodes = simpleNodes;
    }
}
