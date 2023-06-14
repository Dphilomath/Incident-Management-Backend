import React from "react";
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

function AddUser() {
	const handleForm = () => {};

	return (
		<div>
			<h2 className="text-center" style={{ marginTop: "3%" }}>
				Create User
			</h2>

			<div style={{ margin: "4% 20%" }}>
				<Form onSubmit={handleForm}>
					<FormGroup>
						<Row>
							<Col md={6}>
								<FormGroup>
									<Label for="userId">User ID</Label>
									<Input
										id="userId"
										name="userId"
										placeholder="System Generated"
										type="text"
										required
										readOnly
									/>
								</FormGroup>
							</Col>
							<Col md={6}>
								<FormGroup>
									<Label for="userName">User Name</Label>
									<Input
										id="userName"
										name="userName"
										placeholder="Enter User Name"
										type="text"
									/>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

					<FormGroup>
						<Row>
							<Col md={6}>
								<FormGroup>
									<Label for="usermail">Email Id:</Label>
									<Input
										id="userMail"
										name="userMail"
										placeholder="Enter e-mail"
										type="email"
									/>
								</FormGroup>
							</Col>
							<Col md={6}>
								<FormGroup>
									<Label for="userTel">Mobile No.</Label>
									<Input
										id="userTel"
										name="userTel"
										placeholder="Enter User Name"
										type="tel"
									/>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

                    <FormGroup>
						<Row>
							<Col md={6}>
								<FormGroup>
									<Label for="userDesg">Designation:</Label>
									<Input
										id="userDesg"
										name="userDesg"
										placeholder="Enter Designation"
										type="text"
									/>
								</FormGroup>
							</Col>
							<Col md={6}>
								<FormGroup>
									<Label for="userGrade">Grade</Label>
									<Input
										id="userGrade"
										name="userGrade"
										placeholder="Enter Grade"
										type="number"
									/>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

					<FormGroup>
						<Row>
							<Col md={2}>
								<Label>Department :</Label>
							</Col>

							<Col md={2}>
								<FormGroup check>
									<Input name="radio4" type="radio" value={"IT"} />{" "}
									<Label check>IT</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input name="radio4" type="radio" value={"HR"} />{" "}
									<Label check>HR</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input name="radio4" type="radio" value={"Marketing"} />{" "}
									<Label check>Marketing</Label>
								</FormGroup>
							</Col>
							<Col md={2}>
								<FormGroup check>
									<Input name="radio4" type="radio" value={"Finance"} />{" "}
									<Label check>Finance</Label>
								</FormGroup>
							</Col>
						</Row>
					</FormGroup>

					<Container
						className="text-center"
						style={{ margin: "5% auto 2% auto" }}
					>
						<button
							type="button submit"
							class="btn btn-success"
							style={{ width: "15%" }}
						>
							Done
						</button>
					</Container>
				</Form>
			</div>
		</div>
	);
}

export default AddUser;
