package com.storywatpad.backend.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.type.StringType;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.BOOLEAN, "boolean");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIMESTAMP, "datetime");

        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
    }

    // ✅ KHÔNG CẦN @Override nếu bị lỗi – vẫn được công nhận bởi Hibernate 5

    public boolean supportsIdentityColumns() {
        return true;
    }

    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    public String getIdentityColumnString() {
        return "integer";
    }

    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    public boolean supportsLimit() {
        return true;
    }

    protected String getLimitString(String query, boolean hasOffset) {
        return query + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    public boolean supportsTemporaryTables() {
        return true;
    }

    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    public boolean supportsUnionAll() {
        return true;
    }

    public boolean hasAlterTable() {
        return false;
    }

    public boolean dropConstraints() {
        return false;
    }

    public String getAddColumnString() {
        return "add column";
    }

    public String getForUpdateString() {
        return "";
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    public String getDropForeignKeyString() {
        return "";
    }

    public String getAddForeignKeyConstraintString(String cn, String[] fk, String t, String[] pk, boolean rpk) {
        return "";
    }

    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return "";
    }

    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    public boolean supportsCascadeDelete() {
        return false;
    }
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

}
