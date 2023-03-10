import React, { useContext } from "react";

import { Navigate } from "react-router-dom";

import { AppContext } from "../Context/AppContext";



export default function PrivateRoute({ children }) {

  const { isAuth } = useContext(AppContext);

  if (isAuth === false) {

    return <Navigate to="/"></Navigate>;

  }

  return children;

}