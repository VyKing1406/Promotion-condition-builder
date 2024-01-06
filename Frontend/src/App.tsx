import "./index.css";
import React, { useState } from "react";
import {
  HomeOutlined,
  PercentageOutlined,
  ShoppingCartOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Layout, Menu, Image, theme } from "antd";
import CreatePromotionPage from "./pages/CreatePromotionPage";
import clsx from "clsx";
import {
  Route,
  RouterProvider,
  Routes,
  createBrowserRouter,
  createRoutesFromChildren,
} from "react-router-dom";
import AppLayout from "./pages/Layout";
import HomePage from "./pages/HomePage";
import GetPromotionPage from "./pages/GetPromotionPage";
import Cart from "./pages/Cart";
import _404 from "./pages/_404";

const { Header, Content, Footer, Sider } = Layout;

type MenuItem = Required<MenuProps>["items"][number];

const getItem = (
  label: React.ReactNode,
  key: React.Key,
  icon?: React.ReactNode,
  children?: MenuItem[]
): MenuItem => {
  return {
    key,
    icon,
    children,
    label,
  } as MenuItem;
};

const router = createBrowserRouter(
  createRoutesFromChildren([
    <Route path="/" element={<AppLayout />}>
      <Route path="home" element={<HomePage />} />,
      <Route path="promotion/view" element={<GetPromotionPage />} />,
      <Route path="promotion/create" element={<CreatePromotionPage />} />,
      <Route
        path="promotion/edit/:promotionId"
        element={<CreatePromotionPage />}
      />
      <Route path="cart" element={<Cart />} />, ,
      <Route path="*" element={<_404 />} />,
    </Route>,
  ])
);

// const router = createBrowserRouter(
//   createRoutesFromChildren([
//     <Route
//       path="home"
//       element={
//         <AppLayout>
//           <HomePage />
//         </AppLayout>
//       }
//     />,
//     <Route
//       path="promotion/view"
//       element={
//         <AppLayout>
//           <GetPromotionPage />
//         </AppLayout>
//       }
//     />,
//     <Route
//       path="promotion/create"
//       element={
//         <AppLayout>
//           <CreatePromotionPage />
//         </AppLayout>
//       }
//     />,
//     <Route
//       path="cart"
//       element={
//         <AppLayout>
//           <Cart />
//         </AppLayout>
//       }
//     />,
//     <Route
//       path="promotion/edit/:promotionId"
//       element={
//         <AppLayout>
//           <CreatePromotionPage />
//         </AppLayout>
//       }
//     />,
//     <Route path="*" element={<_404 />} />,
//   ])
// );

const App = () => {
  return <RouterProvider router={router} />;
};

export default App;
