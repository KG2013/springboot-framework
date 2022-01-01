package com.codingapi.springboot.permission.jdbc;

import com.codingapi.springboot.framework.event.DomainEventContext;
import com.codingapi.springboot.permission.event.JdbcPreparedStatementSqlCreatedEvent;
import com.codingapi.springboot.permission.analyzer.SQL;

import java.sql.*;

/**
 * @author lorne
 * @since 1.0.0
 */
public class ConnectionProxy extends BaseConnection {

    private final Connection delegate;

    public ConnectionProxy(Connection delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return new StatementProxy(delegate.createStatement());
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return new StatementProxy(delegate.createStatement(resultSetType, resultSetConcurrency));
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new StatementProxy(delegate.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        SQL executeSql = new SQL(sql);
        DomainEventContext.getInstance().push(new JdbcPreparedStatementSqlCreatedEvent(executeSql));
        return new PreparedStatementProxy(delegate.prepareStatement(executeSql.getSql()),executeSql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        SQL executeSql = new SQL(sql);
        DomainEventContext.getInstance().push(new JdbcPreparedStatementSqlCreatedEvent(executeSql));
        return new PreparedStatementProxy(delegate.prepareStatement(executeSql.getSql(), resultSetType, resultSetConcurrency),executeSql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        SQL executeSql = new SQL(sql);
        DomainEventContext.getInstance().push(new JdbcPreparedStatementSqlCreatedEvent(executeSql));
        return new PreparedStatementProxy(delegate.prepareStatement(executeSql.getSql(), resultSetType, resultSetConcurrency, resultSetHoldability),executeSql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        SQL executeSql = new SQL(sql);
        DomainEventContext.getInstance().push(new JdbcPreparedStatementSqlCreatedEvent(executeSql));
        return new PreparedStatementProxy(delegate.prepareStatement(executeSql.getSql(), autoGeneratedKeys),executeSql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        SQL executeSql = new SQL(sql);
        DomainEventContext.getInstance().push(new JdbcPreparedStatementSqlCreatedEvent(executeSql));
        return new PreparedStatementProxy(delegate.prepareStatement(executeSql.getSql(), columnIndexes),executeSql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        SQL executeSql = new SQL(sql);
        DomainEventContext.getInstance().push(new JdbcPreparedStatementSqlCreatedEvent(executeSql));
        return new PreparedStatementProxy(delegate.prepareStatement(executeSql.getSql(), columnNames),executeSql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        SQL executeSql = new SQL(sql);
        return new CallableStatementProxy(delegate.prepareCall(executeSql.getSql()),executeSql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        SQL executeSql = new SQL(sql);
        return new CallableStatementProxy(delegate.prepareCall(executeSql.getSql(), resultSetType, resultSetConcurrency),executeSql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        SQL executeSql = new SQL(sql);
        return new CallableStatementProxy(delegate.prepareCall(executeSql.getSql(), resultSetType, resultSetConcurrency, resultSetHoldability),executeSql);
    }


}
