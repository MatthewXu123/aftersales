
package org.hibernate.cfg;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL10Dialect;

/**
 * Description:
 * @author Matthew Xu
 * @date Aug 25, 2020
 */
public class CustomPostgreSQLDialect extends PostgreSQL10Dialect{

	public CustomPostgreSQLDialect() {
		super();
		this.registerColumnType(Types.ARRAY, "text[]");
		//this.registerHibernateType(Types.ARRAY, "stringArray");
	}
}
