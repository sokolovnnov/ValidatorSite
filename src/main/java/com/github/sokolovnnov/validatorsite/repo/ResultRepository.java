package com.github.sokolovnnov.validatorsite.repo;

import com.github.sokolovnnov.connectivitytest.model.SimpleNode;
import com.github.sokolovnnov.connectivitytest.model.SimpleWay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ResultRepository {

    public abstract List<SimpleWay> getAll();

    protected List<SimpleWay> simpleNodesToSimpleWays(List<SimpleNode> simpleNodes){

        Collection<List<SimpleNode>> simpleNodeList = simpleNodes.stream()
                .collect(Collectors.groupingBy(simpleNode -> simpleNode.getWayOsmId()))
                .values();

        List<SimpleWay> simpleWays = new ArrayList<>();
        for (List<SimpleNode> list : simpleNodeList) {
            SimpleWay simpleWay = new SimpleWay();
            SimpleNode[] nodes = new SimpleNode[list.size()];
            for (SimpleNode simpleNode : list) {
                nodes[simpleNode.getOrderInWay()] = simpleNode;
            }
            simpleWay.setNodes(nodes);
            simpleWay.setOsmId(list.get(0).getWayOsmId());
            simpleWays.add(simpleWay);
        }
        return simpleWays;
    }
}
