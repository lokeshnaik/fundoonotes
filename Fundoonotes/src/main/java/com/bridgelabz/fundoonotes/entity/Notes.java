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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="note_inform")
@AllArgsConstructor
@NoArgsConstructor

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
   
   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)

	@JoinColumn(name = "userId")	

	private List<Notes> notes;
   
}
