package ru.antisida.validatorsite.repo.inmemory;

import ru.antisida.validatorsite.model.SimpleNode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IsolatedNodes implements Serializable {

    public final Map<Integer, List<SimpleNode>> nodesMap = new ConcurrentHashMap<>();

}
