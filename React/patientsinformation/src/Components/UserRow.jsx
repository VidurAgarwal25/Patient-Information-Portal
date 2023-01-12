import React from "react";

// import style from "../App.css";



const UserRow = ({ name, address, phone, emailid, Dob, id, drugid, drugname, status,edit }) => {

    return (

        <>

            <tr>

                <td>{id}</td>

                <td>{name}</td>

                <td>{address}</td>

                <td>{phone}</td>

                <td>{emailid}</td>

                <td>{Dob}</td>

                <td>{drugid}</td>

                <td>{drugname}</td>

                <td>{status}</td>

                <td>{edit}</td>

            </tr>

        </>

    );

};

export { UserRow };