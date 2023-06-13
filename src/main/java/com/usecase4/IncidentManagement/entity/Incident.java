package com.usecase4.IncidentManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incident_details")
@ToString
public class Incident {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "incident_id")
	private Integer incidentId;

	@NotNull
	private String incidentName;

	@NotNull
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Enums.Priority incidentPriority;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Enums.Status incidentStatus;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Enums.Category incidentCategory;

	@NotNull
	@JsonBackReference(value = "user-incident")
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
}