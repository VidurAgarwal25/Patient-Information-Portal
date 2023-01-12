import axios from "axios";

import React from "react";

import { useState } from "react";

import NavBar from "./NavBar";

import {useLocation} from 'react-router-dom';


 

export default function ModifyInfo() {

  const location = useLocation();

  // console.log(location.state.patientname);

  const [data, setData] = useState("");

  const [modify, setModify] = useState({

    address: location.state===null?"":location.state.address,

    email: location.state===null?"":location.state.email,

    phn: location.state===null?"":location.state.phn,

    dob: location.state===null?"":location.state.dob,

  });

  const handleModify = (e) => {

    setData(e.target.value);

  };

//   console.log(data);

  const handleChange = (e) => {

    const { value, name } = e.target;

    setModify({ ...modify, [name]: value });

  };

  const handleSubmit = async(data) => {

   

    axios

      .put(`http://localhost:8081/update/${location.state===null?data:location.state.id}`,{

       

        address: modify.address,


 

      email: modify.email,


 

      phn: modify.phn,


 

      dob: modify.dob,

      })

      .then((res) => {

        console.log(res.data);

        if(res.data === "Patient Not Found"){

            alert("Patient Not available");

        }

        else if(res.data==="Invalid Date Of Birth"){

          alert("Invalid Date Of Birth");

        }

        else if(res.data==="Invalid Email Address"){

          alert("Invalid Email Address");

        }

        else if(res.data==="Invalid Phone Number"){

          alert("Invalid Phone Number");

        }

        else{

            alert("Successfully Updated Patient Details")

        }

      })

      .catch((err) => {

        console.log("error");

        alert("Patient Not Found")

      });


 

    // const datanew = await res.json();

    // console.log(datanew);

    // console.log(state);

  };


 

  return (

    <div>

        <NavBar/>

        <div className="formbegin">

      <div className="formmodify">

        <h1 className="formmodifyheader">Enter Details to Modify:</h1>

      <label className="formmodifylabel">

      Patient Id

      <input type="text" value={location.state===null?data:location.state.id} onChange={handleModify} className="formmodifyinput" placeholder={location.state===null?"id":location.state.id}></input>

      </label>

      <label className="formmodifylabel" >

      Address

      <input

        type="text"

        name="address"

        value={modify.address}

        onChange={handleChange} className="formmodifyinput"

        placeholder={location.state===null?"address":location.state.address}

      ></input>

      </label >

      <label className="formmodifylabel" >

      Email

      <input

        type="text"

        name="email"

        value={modify.email}

        onChange={handleChange} className="formmodifyinput"

        placeholder={location.state===null?"email":location.state.email}

      ></input>

      </label>


 

      <label className="formmodifylabel" >

      Contact

      <input

        type="text"

        name="phn"

        value={modify.phn}

        onChange={handleChange} className="formmodifyinput"

        placeholder={location.state===null?"phone":location.state.phn}

      ></input>

      </label>


 

      <label className="formmodifylabel">

      Date of Birth [MM/DD/YYYY]

      <input

        type="text"

        name="dob"

        value={modify.dob}

        onChange={handleChange} className="formmodifyinput"

        placeholder={location.state===null?"dob":location.state.dob}

      ></input>

      </label>


 

      <button className="formmodifybutton" onClick={()=>handleSubmit(data)}>Modify</button>

    </div>

  </div>

    </div>

  );

}


 