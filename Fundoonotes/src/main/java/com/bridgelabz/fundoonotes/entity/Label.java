package com.bridgelabz.fundoonotes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Label_Information")

public class Label 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;
	@NotBlank(message= "Name is mandatory")
	private String name;
	
	
	@ManyToMany(cascade = {CascadeType.ALL,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name = "Label_to_note", joinColumns = { @JoinColumn(name = "label_id") }, inverseJoinColumns ={@JoinColumn(name = "note_id") })
   @JsonIgnore
	private List<Notes> list;
}
