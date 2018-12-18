package com.atos.JPA_Artesanal.auth.JWT_Filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMixin {
	@JsonCreator
	public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {
	}
}
