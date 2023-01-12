import React from "react";

import { Route, Routes } from "react-router-dom";

import Login from "../Components/Login";

import ModifyInfo from "../Components/ModifyInfo";

import Retrieve from "../Components/Retrieve";

import UploadFiles from "../Components/Uploadfile";

import PrivateRoute from "./PrivateRoute";


 

export default function AllRoutes() {

  return (

    <Routes>

      <Route path="/" element={<Login />}></Route>

      <Route

        path="/Uploadfile"

        element={

          <PrivateRoute>

            <UploadFiles />

          </PrivateRoute>

         

        }

      ></Route>

      <Route

        path="/ModifyInfo"

        element={

          <PrivateRoute>

            <ModifyInfo />

          </PrivateRoute>

         

        }

      ></Route>

      <Route

        path="/Retrieve"

        element={

          <PrivateRoute>

            <Retrieve />

          </PrivateRoute>

         

        }

      ></Route>

    </Routes>

  );

}


 