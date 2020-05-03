package com.bridgelabz.fundoonotes.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter

@Table(name="user_inform")
@AllArgsConstructor
@NoArgsConstructor


public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long userId;
	
	@NotBlank(message = "FirstName is mandatory")
	private String firstName;
	@NotBlank(message = "lastName is mandatory")
	private String lastName;
	@Email
	private String email;
	@NotBlank(message ="Password is mandatory")
	private String password;
	@Column(length=10)
	private String phoneNumber;
	
	
	
	private LocalDateTime createdDate;
	private boolean isVerified;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")	
	private List<Notes> notes;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Label> label;
	
	 @JsonIgnore
	  @ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
		@JoinTable(name = "notes_collaborator", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "note_id") })
			private List<Notes> collaboratornotes;
	
	
	
	
}