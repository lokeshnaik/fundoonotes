package com.bridgelabz.fundoonotes.entity;

import java.time.LocalDateTime;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="note_information")
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,scope = Notes.class)

public class Notes 
{
	@NotBlank(message = "Title is mandatory")	
    private String title;
    @NotBlank(message ="Description is mandantory")
     private String description;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;   
    private boolean isArchieved;
    private boolean isPinned;
    private boolean isTrashed;
    private LocalDateTime dataAndTimeCreated;
    private LocalDateTime upDateAndTime;
    @ManyToMany(cascade = {CascadeType.ALL,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name = "Label_to_note", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns ={@JoinColumn(name = "label_id") })
		private List<Label> label;
   
}
