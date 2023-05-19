import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import InciCard from "./InciCard";
import { Jumbotron, Button } from "reactstrap";
import "../css/home.css";
import base_url from "../api/Server";
import axios from "axios";

function Home() {

  const [incidents, setInci] = useState([
    // {
    //   id:"89832",
    //   title:"First Error",
    //   description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ullamcorper malesuada proin libero nunc consequat interdum varius sit amet. Aliquet lectus proin nibh nisl condimentum id venenatis a condimentum. Mollis aliquam ut porttitor leo a diam sollicitudin. Donec ultrices tincidunt arcu non sodales neque sodales ut. Odio euismod lacinia at quis.",
    //   priority:"High",
    //   category:"software",
    //   status:"Done",
    //   uname:"Arif",
    //   uid:"EC903",
    //   udept:"IT"},

    //   {
    //       id:"98057",
	// 				title:"Second Error",
	// 				description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ullamcorper malesuada proin libero nunc consequat interdum varius sit amet. Aliquet lectus proin nibh nisl condimentum id venenatis a condimentum. Mollis aliquam ut porttitor leo a diam sollicitudin. Donec ultrices tincidunt arcu non sodales neque sodales ut. Odio euismod lacinia at quis.",
	// 				priority:"Low",
	// 				category:"Hardware",
	// 				status:"Done",
	// 				uname:"Martis",
	// 				uid:"CS903",
	// 				udept:"IT"
    //   }

  ])

  const getAllIncidents = () => {
	axios.get(`${base_url}/incidents`).then(
		(res) => {
			console.log(res.data);
			setInci(res.data);
		},
		(error) => {
			console.log(error);
		}
	)
  }

  useEffect(()=>{
	getAllIncidents();
  }, [])

	return (
		<div>
			{/* <Navbar /> */}
			<div class="jumbotron text-center">
				<h1 class="display-4">Welcome to Incident Management!</h1>
				<p class="lead">
				Easily report and track incidents in real-time, empowering our team to promptly address and resolve them.
				
				</p>
				<hr class="my-4" />
				<p>
					 Our dedicated platform provides a streamlined and centralized approach to managing incidents, 
					ensuring minimal disruption to our operations and optimal customer satisfaction.
				</p>
				<p class="lead">
					<a class="btn btn-primary btn-lg" href="#" role="button">
						Learn more
					</a>
				</p>
			</div>

			<div class="row">

      {
        incidents.length > 0 ? incidents.map((item) => <InciCard incident={item} />) : "No Incidents"
      }
        
			</div>
		</div>
	);
}

export default Home;
