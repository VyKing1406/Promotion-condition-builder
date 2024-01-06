import { green } from "@ant-design/colors";
import { Button, ButtonProps, ConfigProvider } from "antd";
import React from "react";

const SubmitButton = (props: ButtonProps) => {
  return (
    <ConfigProvider
      theme={{
        token: {
          colorPrimary: green[6],
          colorPrimaryActive: green[7],
          colorPrimaryBorder: green[3],
          colorPrimaryBorderHover: green[5],
        },
      }}
    >
      <Button {...props}>{props.children}</Button>
    </ConfigProvider>
  );
};

export { SubmitButton };
