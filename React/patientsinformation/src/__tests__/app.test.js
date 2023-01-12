import React from "react";

import {

  render,

  fireEvent,

  screen,

  cleanup,

  act,

} from "@testing-library/react";

import "@testing-library/jest-dom";

import App from "../App.js";



import { MemoryRouter } from "react-router-dom";

import AppContextProvider, {

 

} from "../Context/AppContext.jsx";

import fetchMock from "jest-fetch-mock";




beforeAll(() => {

  fetchMock.enableMocks();

});




//To check whether email and password are having email and password only as attribute type

describe("Login page works correctly", () => {

  test("render email and password input", async () => {

    fetchMock.dontMockOnce().dontMockOnce();

    const {getByTestId} = render(

      <MemoryRouter initialEntries={["/"]}>

        <AppContextProvider>

          <App />

        </AppContextProvider>

      </MemoryRouter>

    );



    const inputEl = screen.getByTestId("email-input");

    expect(inputEl).toBeInTheDocument();

    const password = screen.getByTestId("password-input");

    expect(password).toBeInTheDocument();

    expect(inputEl).toHaveAttribute("type", "email");

    expect(password).toHaveAttribute("type", "password");

  });

//to test username and password along with button are included in the page

  test("pass valid email to test email input field", async () => {

    fetchMock.dontMockOnce();

    const { getByTestId} = render(

      <MemoryRouter initialEntries={["/"]}>

        <AppContextProvider>

          <App />

        </AppContextProvider>

      </MemoryRouter>

    );



    const inputEl = screen.getByTestId("email-input");

    const password = screen.getByTestId("password-input");

    const submit = getByTestId("form-submit");



    expect(submit).toBeInTheDocument();



    fireEvent.change(inputEl, {

      target: {

        value: "pavan@gmail.com",

      },

    });



    fireEvent.change(password, {

      target: {

        value: "Pavanmuppala@543",

      },

    });



    expect(inputEl.value).toBe("pavan@gmail.com");

    expect(password.value).toBe("Pavanmuppala@543");

    await act(() => {

      fireEvent.click(submit);

    });

   

  });

});



//if we have not login take us back to the login page from uploadfile, For security purpose

describe("Private Routes", () => {

  test("Private Route component redirects user to login when visiting /Uploadfile page", async () => {

    const { getByTestId} = render(

      <MemoryRouter initialEntries={["/Uploadfile"]}>

        <AppContextProvider>

          <App />

        </AppContextProvider>

      </MemoryRouter>

    );



    const form = getByTestId("login-form");

    expect(form).toBeInTheDocument();

 

  });



  test("Private Route component redirects user to login when visiting /Retrieve page", async () => {

    const { getByTestId, } = render(

      <MemoryRouter initialEntries={["/Retrieve"]}>

        <AppContextProvider>

          <App />

        </AppContextProvider>

      </MemoryRouter>

    );



    const form = getByTestId("login-form");

    expect(form).toBeInTheDocument();

   

  });

  test("Private Route component redirects user to login when visiting /ModifyInfo page", async () => {

    const { getByTestId } = render(

      <MemoryRouter initialEntries={["/ModifyInfo"]}>

        <AppContextProvider>

          <App />

        </AppContextProvider>

      </MemoryRouter>

    );



    const form = getByTestId("login-form");

    expect(form).toBeInTheDocument();

   

  });

});