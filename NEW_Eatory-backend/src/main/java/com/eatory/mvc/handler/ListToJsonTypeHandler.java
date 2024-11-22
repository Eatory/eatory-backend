package com.eatory.mvc.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListToJsonTypeHandler extends BaseTypeHandler<List<String>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getString(columnIndex));
    }

    private String toJson(List<String> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert list to JSON string", e);
        }
    }

    private List<String> toList(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON string to list", e);
        }
    }
}
