package de.berlin.home.kang.webrest.model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * 
 * entity class of order
 * 
 * @author xinhua
 *
 */
@Entity(name = "auftrag")
@Table(name = "auftrag",schema = "webrest")
@JsonbPropertyOrder(value = {"number", "name" })
@NamedQuery(name = Order.FIND_ALL, query = "SELECT b FROM auftrag b")
public class Order implements Serializable{

	/**
	 * make it possible to serialize/deserialize it in camunda engine 
	 * so we can see the content of this entity
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the name or Named Query
	 */
	public static final String FIND_ALL = "Order.findAll";
	
	/**
	 * as primary key in table
	 */
	@Id
	@Column(name = "number", unique = true)
	private Long number;

	/**
	 * the column of 'name' , it can not be null
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/*
	 * empty constructor
	 */
	public Order() {
	}

	/**
	 * constructor 
	 * @param number
	 * @param name
	 */
	public Order(final Long number, final String name) {
		super();
		this.number = number;
		this.name = name;
	}

	/**
	 *  getter and setter here:
	 */

	/**
	 * get number
	 * @return this.number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * set number
	 * @param number
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * get name
	 * @return this.name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
