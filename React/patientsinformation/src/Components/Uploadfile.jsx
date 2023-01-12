import React, { useState } from "react";

import axios from "axios";


 

import NavBar from "./NavBar";

function UploadFiles() {

  const styling = {

    width: "500px",

    top: "50%",

    transform: "translate(-50%,-50%)",

    left: "50%",

    position: "fixed",

  };

  const [selectedFile, setSelectedFile] = useState("");


 

  const handleClick = async (e) => {

    e.preventDefault();

    const formData = new FormData();

    formData.append("file", selectedFile, selectedFile.name);

    axios

      .post("http://localhost:8081/upload", formData)

      .then((res) => {

        console.log(res.data);

        if(res.data==="File is uploaded Successfully AND INDUCTION IS SUCCESSFUL")

        alert("File Uploaded Successfully" );

        else

        alert("Details' format is not correct! Date Of Birth should be MM/DD/YYYY, Contact Number should be valid 10 digits")

      })

      .catch((err) => {

        console.log("error");

        alert("Please Upload File in Excel Format Only" );

      });

  };

  const changeHandler = (e) => {

    setSelectedFile(e.target.files[0]);

  };

  console.log(selectedFile);


 

  return (

    <>

      <NavBar></NavBar>

      <div>

        <div style={styling}>

          <p className="h3">Upload File Here</p>

          <form

            action="/action_page.php"

            style={{ marginTop: "20px", display: "flex", alignItems: "center" }}

          >

            <input

              className="form-control"

              type="file"

              id="formFile"

              name="file"

              onChange={(e) => changeHandler(e)}

            />

            <input

              type="submit"

              className="btn btn-primary"

              value="Upload"

              onClick={handleClick}

              style={{ margin: "20px" }}

            />

          </form>

        </div>

      </div>

    </>

  );

}


 

export default UploadFiles;


 