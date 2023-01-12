import React, { useContext } from "react";

import { NavLink } from "react-router-dom";

import { AppContext } from "../Context/AppContext";


 

function NavBar() {

  const { authHandle } = useContext(AppContext);

  const handleLogout = () => {

    authHandle();

  };

  return (

    <nav className="navbar navbar-expand-sm bg-light">

      <ul className="navbar-nav ml-auto">

        <li className="nav-item forhover">

          <NavLink

            to="/ModifyInfo"

            style={({ isActive }) => ({

              color: "#000000",


 

              padding: "10px",

              textDecoration: "none",

            })}

            end

          >

            Update Info

          </NavLink>

        </li>

        <li className="nav-item forhover">

          <NavLink

            to="/Retrieve"

            style={({ isActive }) => ({

              color: "#000000",


 

              padding: "10px",

              textDecoration: "none",

            })}

            end

          >

            Retrieve Info

          </NavLink>

        </li>


 

        <li className="nav-item">

          <button className="forbutton" onClick={handleLogout}>LogOut</button>

        </li>

      </ul>

    </nav>

  );

}


 

export default NavBar;


 