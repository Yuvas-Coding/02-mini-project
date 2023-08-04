package com.vtalent.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Eligibilety_Details")
public class EligibiletyDetails {
	
	@Id
	@Column(name = "Eligibilety_Id")
	private Integer eligId	;	
	
	@Column(name = "Name")
	private String name;	
	
	@Column(name = "Mobile_Number")
	private Long mobile;
	
	@Column(name = "Email_Id")
	private String email;	
	
	@Column(name = "Gender")
    private Character gender;	
	
	@Column(name = "SSN")
	private Long ssn;
	
	@Column(name = "Plan_Name")
	private String planName;
	
	@Column(name = "Plan_Status")
	private String planStatus;
	
	@Column(name = "Plan_Start_Date")
	private LocalDate planStartDate;
	
	@Column(name = "Plan_End_Date")
	private LocalDate planEndDate;
	
	@Column(name = "Created_Date")
	private LocalDate createDate;
	
	@Column(name = "Updated_Date")
	private LocalDate updateDate;	
	
	@Column(name = "Created_By")
	private String createdBy;	
	
	@Column(name = "Updated_By")
	private String updatedBy;

}
