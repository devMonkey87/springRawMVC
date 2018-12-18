package com.atos.JPA_Artesanal.jpacfg;

public class LandettiDialect extends org.hibernate.dialect.PostgreSQL95Dialect {

	@Override
	public String getCurrentSchemaCommand() {

		return "select current_schema()";
	}

}
