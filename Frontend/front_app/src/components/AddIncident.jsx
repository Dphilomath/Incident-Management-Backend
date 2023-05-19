import axios from "axios";
import React, { useState } from "react";
import {
	Form,
	Row,
	FormGroup,
	Label,
	Input,
	Col,
	Button,
	Container,
} from "reactstrap";
import base_url from "../api/Server";

function AddIncident() {
	const [incis, setIncidents] = useState({});

	function handleForm(e) {
		console.log(incis);
		postData(incis);

		e.target.reset();
		e.preventDefault();
	}

	const handlePriority = (e) => {
		// console.log(e.target.value);
		setIncidents({ ...incis, inciPriority: e.target.value });
	};

	const handleStatus = (e) => {
		setIncidents({ ...incis, inciStatus: e.target.value });
	};

	const handleCategory = (e) => {
		setIncidents({ ...incis, inciCategory: e.target.value });
	};

	const handleDept = (e) => {
		setIncidents({ ...incis, userDept: e.target.value })
	}

	const postData = (data)=>{
		axios.post(`${base_url}/incidents`, data).then(
			(response)=>{
				console.log(response);
				console.log("Success Posting Data")
				alert("New Incident Created !!")
			},
			(error)=>{
				alert("OOPS !! Some issue encountered. Please Try again")
				console.log("Something went wrong while Posting To server")
			}
		)
	}

	return (
		<div>
			<h2 className="text-center" style={{ marginTop: "3%" }}>
				Create New Incident
			</h2>

			<div style={{ margin: "4% 20%" }}>
				<Form onSubmit={handleForm}>
					<Row>
						<Col md={6}>
							<FormGroup>
								<Label for="inciId">Incident ID</Label>
								<Input
									id="inciId"
									name="inciId"
									placeholder="Enter Id"
									type="text"
									onChange={(e) => {
										setIncidents({ ...incis, id: e.target.value });
									}}
								/>
							</FormGroup>
						</Col>
						<Col md={6}>
							<FormGroup>
								<Label for="inciName">Name</Label>
								<Input
									id="inciName"
									name="inciName"
									placeholder="Enter Incident Name"
									type="text"
									onChange={(e) => {
										setIncidents({ ...incis, inciName: e.target.value });
									}}
								/>
							</FormGroup>
						</Col>
					</Row>
					<FormGroup>
						<Label for="desc">Name</Label>
						<Input
							id="desc"
							name="desc"
							placeholder="Describe Incident"
							type="textarea"
							onChange={(e) => {
								setIncidents({ ...incis, description: e.target.value });
							}}
						/>
					</FormGroup>

					<FormGroup>
						<Row>
							<Col md={2}>
								<Label>Priority :</Label>
							</Col>

							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio1"
										type="radio"
										value={"Critical"}
										onChange={handlePriority}
									/>{" "}
									<Label check>Critical</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio1"
										type="radio"
										value={"High"}
										onChange={handlePriority}
									/>{" "}
									<Label check>High</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio1"
										type="radio"
										value={"Medium"}
										onChange={handlePriority}
									/>{" "}
									<Label check>Medium</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio1"
										type="radio"
										value={"Low"}
										onChange={handlePriority}
									/>{" "}
									<Label check>Low</Label>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

					<FormGroup>
						<Row>
							<Col md={2}>
								<Label>Category :</Label>
							</Col>

							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio2"
										type="radio"
										value={"Hardware Issues"}
										onChange={handleCategory}
									/>{" "}
									<Label check>Hardware Issues</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio2"
										type="radio"
										value={"Software Issues"}
										onChange={handleCategory}
									/>{" "}
									<Label check>Software Issues</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio2"
										type="radio"
										value={"Accessories Issues"}
										onChange={handleCategory}
									/>{" "}
									<Label check>Accessories Issues</Label>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

					<FormGroup>
						<Row>
							<Col md={2}>
								<Label>Status :</Label>
							</Col>

							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio3"
										type="radio"
										value={"New"}
										onChange={handleStatus}
									/>{" "}
									<Label check>New</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio3"
										type="radio"
										value={"Inprogress"}
										onChange={handleStatus}
									/>{" "}
									<Label check>Inprogress</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio3"
										type="radio"
										value={"Inprogress"}
										onChange={handleStatus}
									/>{" "}
									<Label check>Resolved</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input
										name="radio3"
										type="radio"
										value={"Inprogress"}
										onChange={handleStatus}
									/>{" "}
									<Label check>Rejected</Label>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

					<FormGroup>
						<div>User Details:</div>

						<FormGroup>
							<Label for="userId">User ID</Label>
							<Input
								id="userId"
								name="userId"
								placeholder="Enter User Id"
								type="text"
								onChange={(e) => {
								setIncidents({ ...incis, userId: e.target.value });
							}}
							/>
						</FormGroup>

						<FormGroup>
							<Label for="userName">User Name</Label>
							<Input
								id="userName"
								name="userName"
								placeholder="Enter User Name"
								type="text"
								onChange={(e) => {
								setIncidents({ ...incis, userName: e.target.value });
							}}
							/>
						</FormGroup>

						<FormGroup>
							<Row>
								<Col md={2}>
									<Label>Department :</Label>
								</Col>

								<Col md={2}>
									<FormGroup check>
										<Input name="radio4" type="radio" value={"IT"} onChange={handleDept}/> <Label check>IT</Label>
									</FormGroup>
								</Col>
								<Col md={2}>
									<FormGroup check>
										<Input name="radio4" type="radio" value={"HR"} onChange={handleDept}/> <Label check>HR</Label>
									</FormGroup>
								</Col>
								<Col md={2}>
									<FormGroup check>
										<Input name="radio4" type="radio" value={"Marketing"} onChange={handleDept}/>{" "}
										<Label check>Marketing</Label>
									</FormGroup>
								</Col>
								<Col md={2}>
									<FormGroup check>
										<Input name="radio4" type="radio" value={"Finance"} onChange={handleDept}/>{" "}
										<Label check>Finance</Label>
									</FormGroup>
								</Col>
							</Row>
						</FormGroup>
					</FormGroup>

					<Container
						className="text-center"
						style={{ margin: "5% auto 2% auto" }}
					>
						<button
							type="button reset"
							class="btn btn-dark"
							style={{ marginRight: "30%" }}
						>
							Clear
						</button>
						<button type="button submit" class="btn btn-success">
							Submit
						</button>
					</Container>
				</Form>
			</div>
		</div>
	);
}

export default AddIncident;
