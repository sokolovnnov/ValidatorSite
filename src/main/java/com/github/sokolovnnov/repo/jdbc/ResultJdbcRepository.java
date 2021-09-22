package com.github.sokolovnnov.repo.jdbc;

import com.github.sokolovnnov.model.SimpleNode;
import com.github.sokolovnnov.model.SimpleWay;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.github.sokolovnnov.repo.ResultRepository;

import java.util.List;

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
