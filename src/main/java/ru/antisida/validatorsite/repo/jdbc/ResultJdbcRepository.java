package ru.antisida.validatorsite.repo.jdbc;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.antisida.validatorsite.model.SimpleNode;
import ru.antisida.validatorsite.model.SimpleWay;
import ru.antisida.validatorsite.repo.ResultRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ResultJdbcRepository extends ResultRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<SimpleNode> ROW_MAPPER = BeanPropertyRowMapper.newInstance(SimpleNode.class);

    public ResultJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Cacheable("wayCache")
    public List<SimpleWay> getAll() {

        List<SimpleNode> simpleNodes =  jdbcTemplate.query("SELECT * FROM nodes ORDER BY way_osm_id DESC ", ROW_MAPPER);

        return simpleNodesToSimpleWays(simpleNodes);

    }
}
