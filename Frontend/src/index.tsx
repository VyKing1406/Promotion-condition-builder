import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { TreeContextProvider } from "./contexts/TreeContext";
import { ConfigProvider } from "antd";
import { PromotionContextProvider } from "./contexts/PromotionContext";
import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromChildren,
  createRoutesFromElements,
} from "react-router-dom";
import AppLayout from "./pages/Layout";
import CreatePromotionPage from "./pages/CreatePromotionPage";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  // <React.StrictMode>
  <ConfigProvider
    theme={{
      components: {
        Tree: {
          titleHeight: 40,
        },
      },
    }}
  >
    <PromotionContextProvider>
      <TreeContextProvider>
        <App />
      </TreeContextProvider>
    </PromotionContextProvider>
  </ConfigProvider>
  // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
