import React, { useContext, useState } from "react";

import { useNavigate } from "react-router-dom";


 

import { AppContext } from "../Context/AppContext";

function Login() {

  const navigate = useNavigate();

  const { isAuth, authHandle } = useContext(AppContext);

  const [login, setLogin] = useState({

    username: "",

    password: "",

  });

  const handleChange = (e) => {

    const { value, name } = e.target;

    setLogin({ ...login, [name]: value });

  };

  const handleLogin = async (e, login) => {

    //navigate("/Uploadfile");

    /*

   

    */

    // e.preventDefault();

    // axios

    //   .post("localhost:8080/user/login", {

    //     username: login.username,

    //     password: login.password,

    //   })

    //   .then((Response) => console.log(Response))

    e.preventDefault();

    let user_data = {

      username: login.username,

      password: login.password,

    };


 

    var user_data1 = JSON.stringify(user_data);


 

    let res = await fetch("http://localhost:8080/user/login", {

      method: "POST",

      body: user_data1,

      headers: {

        "Content-Type": "application/json",

      },

    });


 

    let data = await res.json();

    // console.log(data);

    if (data.error) {

      //console.log("Invalid Credentials");

      alert("Invalid Credentials");

    } else {

      //   navigate("/Uploadfile");

      authHandle();

      //console.log("You are logging in");

    }

  };

  if (isAuth) {

    navigate("/Uploadfile");

  }

  return (

    <div className="logbegin">

    <div className="log-form">

      <h2 data-testid="login-link">Login</h2>

      <form className="formLogin" data-testid="login-form">

        <label htmlFor="uname">

          <b>Username</b>

        </label>

        <input

          type="email"

          name="username"

          value={login.username}

          className="inputLogin"

          onChange={(e) => handleChange(e)}

          data-testid="email-input"

        />

        <label htmlFor="pass">

          <b>Password</b>

        </label>

        <input

          type="password"

          name="password"

          className="inputLogin"

          data-testid="password-input"

          value={login.password}

          onChange={(e) => handleChange(e)}

        />

        <input

          type="submit"

          data-testid="form-submit"

          onClick={(e) => handleLogin(e, login)}

          className="buttonLogin"

          value={"Login"}

        ></input>

        <a className="forgot" href="google.com">

          Forgot Username?

        </a>

        {/* <Link to="/Uploadfile">UploadFiles</Link> */}

      </form>

    </div>

    </div>

  );

}


 

export default Login;


 