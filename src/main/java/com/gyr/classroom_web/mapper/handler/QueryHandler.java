package com.gyr.classroom_web.mapper.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyr.classroom_web.bean.Query;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHandler extends BaseTypeHandler<Query> {
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Query query, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, objectMapper.writeValueAsString(query));
    }

    @SneakyThrows
    @Override
    public Query getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return objectMapper.readValue(resultSet.getString(s), Query.class);
    }

    @SneakyThrows
    @Override
    public Query getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return objectMapper.readValue(resultSet.getString(i), Query.class);
    }

    @SneakyThrows
    @Override
    public Query getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return objectMapper.readValue(callableStatement.getString(i), Query.class);
    }
}
