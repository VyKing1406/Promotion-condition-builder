import React, { useState } from "react";
import {
  HomeOutlined,
  PercentageOutlined,
  ShoppingCartOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Layout, Menu, Image, theme } from "antd";
import clsx from "clsx";
import { Link, Outlet, useLocation, useNavigate } from "react-router-dom";

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

const items: MenuItem[] = [
  getItem(<Link to="/home">Trang chủ</Link>, "/1", <HomeOutlined />),
  getItem("Khuyến mãi", "2", <PercentageOutlined />, [
    getItem(<Link to="/promotion/view">Danh sách khuyến mãi</Link>, "3"),
    getItem(<Link to="/promotion/create">Tạo khuyến mãi</Link>, "4"),
  ]),
  // getItem(
  //   <Link to="/cart">Giả lập giỏ hàng</Link>,
  //   "5",
  //   <ShoppingCartOutlined />
  // ),
];

const AppLayout: React.FC = () => {
  const location = useLocation();
  const path = location.pathname;
  // Xác định key tương ứng với đường dẫn
  const getDefaultSelectedKey = (path: string): string[] => {
    if (path === "/home") {
      return ["1"];
    } else if (path === "/promotion/view") {
      return ["3"];
    } else if (path === "/promotion/create") {
      return ["4"];
    } else if (path === "/cart") {
      return ["5"];
    } else return [];
  };

  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <Layout style={{ minHeight: "100vh" }} hasSider>
      <Sider
        collapsible
        collapsed={collapsed}
        onCollapse={(value) => setCollapsed(value)}
        style={{
          overflow: "auto",
          height: "100vh",
          position: "fixed",
          left: 0,
          top: 0,
          bottom: 0,
        }}
      >
        <div className="w-full my-4 flex justify-center">
          <Image
            preview={false}
            width={40}
            src="https://cdn.haitrieu.com/wp-content/uploads/2021/11/Logo-The-Gioi-Di-Dong-MWG.png"
          />
        </div>
        <Menu
          theme="dark"
          defaultSelectedKeys={getDefaultSelectedKey(path)}
          mode="inline"
          items={items}
        />
      </Sider>
      <Layout
        className={clsx(collapsed ? "ml-[80px]" : "ml-[200px]")}
        style={{
          transition: "all 0.2s, background 0s",
        }}
      >
        <Header style={{ padding: 0, background: colorBgContainer }} />
        <Content className=" bg-white rounded-lg mt-6 mx-4 py-6">
          {/* {props.children} */}
          <Outlet />
        </Content>
        <Footer style={{ textAlign: "center" }}>
          Promotion Team @ MWG
        </Footer>
      </Layout>
    </Layout>
  );
};

export default AppLayout;
