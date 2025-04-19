package com.example.preg.repository;

import com.example.preg.model.OcuEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OcuService {

    private final JdbcTemplate jdbcTemplate;

    public OcuService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(OcuEntity hotel) {
        String sql = "INSERT INTO ocu_individual (id, name, location) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, hotel.getId(), hotel.getName(), hotel.getLocation());
    }

    public Optional<OcuEntity> retrieveById(String id) {
        String sql = "SELECT * FROM ocu_individual WHERE id = ?";
        try {
            OcuEntity hotel = jdbcTemplate.queryForObject(sql, new Object[]{id}, hotelRowMapper());
            return Optional.ofNullable(hotel);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM ocu_individual WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        if (rows == 0) {
            throw new RuntimeException("Record not found");
        }
    }

    public void update(String id, OcuEntity updated) {
        String sql = "UPDATE ocu_individual SET name = ?, location = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, updated.getName(), updated.getLocation(), id);
        if (rows == 0) {
            throw new RuntimeException("Record not found for update");
        }
    }

    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM ocu_individual WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    private RowMapper<OcuEntity> hotelRowMapper() {
        return (rs, rowNum) -> {
            OcuEntity h = new OcuEntity();
            h.setId(rs.getString("id"));
            h.setName(rs.getString("name"));
            h.setLocation(rs.getString("location"));
            return h;
        };
    }
}

