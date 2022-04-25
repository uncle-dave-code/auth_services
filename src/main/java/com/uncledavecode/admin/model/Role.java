package com.uncledavecode.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
    @Enumerated(EnumType.STRING)
	@Column(name="name")
	private EnumRole name;

	public Role(EnumRole name) {
		this.name = name;
	}

	public Role() {
	}
	
}
