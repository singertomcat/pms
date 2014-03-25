package org.springside.examples.quickstart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

//JPA标识
@Entity
@Table(name = "pms_group")
public class Group extends IdEntity {

	private String code;
	private String name;
	private String description;
	private Group parent;

	// 自关联
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Group getParent() {
		return parent;
	}

	// JSR303 BeanValidator的校验规则
	@NotBlank
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

}
