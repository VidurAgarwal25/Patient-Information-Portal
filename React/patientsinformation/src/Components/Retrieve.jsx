import axios from 'axios';

import React from 'react'

import { useState } from 'react'

import NavBar from './NavBar'

import { UserRow } from "./UserRow";


 

import { useNavigate } from "react-router-dom";


 

import { Table } from "react-bootstrap";

function Retrieve() {

  const navigate=useNavigate();

  const [data, setData] = useState("");

  const [details, setDetails] = useState([])

  const handleSubmit = (data) => {

    axios

      .get(`http://localhost:8081/retireve/${data}`)

      .then((res) => {

        console.log(res.data);

        if (res.data.length === 0)

          alert("Patient Not Found!!")

        setDetails(res.data);

      })

      .catch((err) => {

        console.log("error");

       

        alert("Patient Not Found")

      });


 

  }

  const navigateToUpdate=(t)=>{

    // console.log(t.dob);

    navigate("/ModifyInfo",{state:{id:t.id,patientname:t.patientname,address:t.address,dob:t.dob,phn:t.phn,email:t.email}});

    // console.log("working")

   

  }

  console.log(details);

  return (

    <div>

      <NavBar />


 

      <div className="t1">

        <div className="t2">

          <h1 className="t3">Retrieve Patient Information</h1>

          <label className="t4">

            {/* Patient Name */}

            <input

              type="text"

              value={data}

              onChange={(e) => {

                setData(e.target.value);

              }}

              placeholder="Patient Name"

              className="t5"

            />

          </label>

          <button onClick={() => handleSubmit(data)} className="t6">Submit</button>

        </div>

      </div>

      <>

       

        <Table striped bordered hover>

          <thead>

            <tr>

              <td>ID</td>

              <td>Patient NAME</td>

              <td>Address </td>

              <td>Contact</td>

              <td>Email id</td>

              <td>Dob</td>

              <td>Drug ID</td>

              <td>Drug Name</td>

              <td>Status</td>

              <td>Edit</td>

            </tr>

          </thead>

          <tbody>

            {details.map((item, i) => (

              <UserRow

                name={item.patientname}

                address={item.address}

                phone={item.phn}

                emailid={item.email}

                Dob={item.dob}

                id={item.id}

                drugid={item.drug.drugid}

                drugname={item.drug.drugname}

                status={item.status}

               edit={<button onClick={()=>navigateToUpdate(item)}>Edit</button>}

              />


 

            ))}


 

          </tbody>


 

        </Table>


 

      </>


 

    </div>


 

  );


 

}




 

export default Retrieve;


 