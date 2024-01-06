import { Divider, Typography } from "antd";
import React from "react";

const { Title } = Typography;

const HomePage = () => {
  return (
    <section>
      <Title className="mx-6" level={3}>
        Trang chủ
      </Title>
      <Divider />
    </section>
  );
};

export default HomePage;
