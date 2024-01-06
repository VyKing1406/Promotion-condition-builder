import { Button, Result } from "antd";
import { useNavigate } from "react-router-dom";

const _404: React.FC = () => {
  const navigate = useNavigate();
  const handleGoHome = () => {
    navigate("/home");
  };
  return (
    <Result
      status="404"
      title="404"
      subTitle="Trang không tồn tại."
      extra={
        <Button type="primary" onClick={handleGoHome}>
          Quay lại trang chủ
        </Button>
      }
    />
  );
};

export default _404;
