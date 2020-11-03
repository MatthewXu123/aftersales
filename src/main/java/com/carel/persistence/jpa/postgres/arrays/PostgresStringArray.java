package com.carel.persistence.jpa.postgres.arrays;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class PostgresStringArray implements UserType {
protected static final int  SQLTYPE = java.sql.Types.ARRAY;

@Override
public Object nullSafeGet(final ResultSet rs, final String[] names, SharedSessionContractImplementor arg2, final Object owner) throws HibernateException, SQLException {
    Array array = rs.getArray(names[0]);
	if (array==null) return new String[0];

	
    String[] javaArray = (String[]) array.getArray();
    return javaArray;
}

@Override
public void nullSafeSet(final PreparedStatement statement, final Object object, final int i, SharedSessionContractImplementor arg2) throws HibernateException, SQLException {
    Connection connection = statement.getConnection();

    String[] castObject = (String[]) object;
    Array array = connection.createArrayOf("text", castObject);

    statement.setArray(i, array);
}

@Override
public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
    return cached;
}

@Override
public Object deepCopy(final Object o) throws HibernateException {
    return o == null ? null : ((String[]) o).clone();
}

@Override
public Serializable disassemble(final Object o) throws HibernateException {
    return (Serializable) o;
}

@Override
public boolean equals(final Object x, final Object y) throws HibernateException {
	
	boolean xEmptyOrNull = (x == null) || (x instanceof String[] &&  ((String[]) x).length==0);
	boolean yEmptyOrNull = (y == null) || (y instanceof String[] &&  ((String[]) y).length==0);
	
	boolean deepEquals = Arrays.deepEquals(new Object[]{x}, new Object[]{y});
	
    return xEmptyOrNull ? yEmptyOrNull : deepEquals;
}

@Override
public int hashCode(final Object o) throws HibernateException {
    return o == null ? 0 : o.hashCode();
}

@Override
public boolean isMutable() {
    return true;
}

@Override
public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
    return original;
}

@Override
public Class<String[]> returnedClass() {
    return String[].class;
}

@Override
public int[] sqlTypes() {
    return new int[] { SQLTYPE };
}


}