package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pe_permission")
@Getter
@Setter
@NoArgsConstructor
public class Permission implements Serializable {
	private static final long serialVersionUID = -4990810027542971546L;
	/**
	 * 主键
	 */
	@Id
	private String id;
	private String name;
	private String code;
	private String description;
}