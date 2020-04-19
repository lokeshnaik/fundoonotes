package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Labeldto 
{
   @NotBlank(message = "Title is mandatory")
	String name;
}
