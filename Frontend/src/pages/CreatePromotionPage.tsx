import { AimOutlined, DownOutlined } from "@ant-design/icons";
import {
  Button,
  Collapse,
  CollapseProps,
  Divider,
  Form,
  Input,
  Radio,
  RadioChangeEvent,
  Tree,
  Typography,
  DatePicker,
  Layout,
  InputNumber,
  message,
  Spin,
} from "antd";
import type { DataNode } from "antd/es/tree";
import React, { useContext, useEffect, useLayoutEffect, useState } from "react";
import ExprNode from "../components/expression-node/ExprNode";
import { TreeContext } from "../contexts/TreeContext";
import { SubmitButton } from "../components/button";
import { convertToVND, convertTreeData } from "../utils";
import {
  createPromotion,
  editPromotionById,
  getPromotionById,
  updatePromotionById,
} from "../services/promotion";
import { TreeActionType } from "../constants/treeAction";
import { useNavigate, useParams } from "react-router-dom";
import {
  CartPromotionType,
  PercentageDiscountType,
  ProductPromotionType,
  ValueDiscountType,
} from "../constants/promotion";
import { PromotionInfo } from "./GetPromotionPage";
import dayjs from "dayjs";

const { Content } = Layout;
const { Title, Paragraph } = Typography;
const { RangePicker } = DatePicker;

const renderNode: (node: any, key: string) => DataNode = (
  node: any,
  key: string
) => {
  if (node.type === "condition") {
    return {
      title: (
        <ExprNode type={node.type} nodeID={key} childProps={{ ...node }} />
      ),
      key: key,
    };
  } else if (node.type === "logic") {
    return {
      title: (
        <ExprNode type={node.type} nodeID={key} childProps={{ ...node }} />
      ),
      key: key,
      children: node.children.map((child: any, index: number) =>
        renderNode(child, `${key}${index}`)
      ),
    };
  } else {
    return {
      title: (
        <ExprNode type={node.type} nodeID={key} childProps={{ ...node }} />
      ),
      key: key,
    };
  }
};

const renderTreeData: (treeData: any) => DataNode[] | undefined = (
  treeData: any
) => {
  const root = treeData.condition;
  return [renderNode(root, "0")];
};

// type PromotionType = {
//   promotionTypeId: string;
//   promotionTypeName: "product" | "cart";
// };

// type PromotionType =
//   | {
//       promotionTypeId: "722928fb-9beb-4bb4-9a2c-4c8bfd81d5dd";
//       promotionTypeName: "cart";
//     }
//   | {
//       promotionTypeId: "3fe103da-337a-4644-8ad2-8ecdb55954de";
//       promotionTypeName: "product";
//     };

// type DiscountType =
//   | {
//       discountTypeId: "df43cc57-8bbc-4e66-8de0-f19c2f14a535";
//       discountTypeName: "percentage";
//     }
//   | {
//       discountTypeId: "931fc54a-fd49-40ef-b08f-de016dc8ce57";
//       discountTypeName: "value";
//     };
type PromotionType = typeof CartPromotionType | typeof ProductPromotionType;
type DiscountType = typeof PercentageDiscountType | typeof ValueDiscountType;

const GeneralInfo = (props: PromotionInfo) => {
  const [promotionType, setPromotionType] = useState<PromotionType>({
    promotionTypeId: "3fe103da-337a-4644-8ad2-8ecdb55954de",
    promotionTypeName: "product",
  });
  const [discountType, setDiscountType] = useState<DiscountType>(
    props.discountType
  );

  return (
    <section className="px-8 w-[800px]">
      <Form.Item
        label="Tên khuyến mãi"
        name="name"
        rules={[{ required: true, message: "Vui lòng nhập tên khuyến mãi!" }]}
        initialValue={props.name}
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="Loại khuyến mãi"
        name="promotionType"
        rules={[{ required: true, message: "Vui lòng chọn loại khuyến mãi" }]}
        initialValue={
          props.promotionType &&
          props.promotionType.promotionTypeName ==
            ProductPromotionType.promotionTypeName
            ? ProductPromotionType
            : CartPromotionType
        }
      >
        <Radio.Group
          onChange={(e: RadioChangeEvent) => setPromotionType(e.target.value)}
        >
          <Radio value={CartPromotionType}>{"Khuyến mãi sản phẩm"}</Radio>
          <Radio value={ProductPromotionType}>{"Khuyến mãi giỏ hàng"}</Radio>
        </Radio.Group>
      </Form.Item>
      <Form.Item
        label="Mô tả"
        name="description"
        initialValue={props.description}
      >
        <Input.TextArea />
      </Form.Item>
      <Form.Item
        label="Loại giá trị khuyến mãi"
        name="discountType"
        rules={[
          { required: true, message: "Vui lòng chọn loại giá trị khuyến mãi" },
        ]}
        initialValue={
          props.discountType &&
          props.discountType.discountTypeName ===
            ValueDiscountType.discountTypeName
            ? ValueDiscountType
            : PercentageDiscountType
        }
      >
        <Radio.Group
          onChange={(e: RadioChangeEvent) => setDiscountType(e.target.value)}
        >
          <Radio value={PercentageDiscountType}>
            {"Giảm giá theo phần trăm (%)"}
          </Radio>
          <Radio value={ValueDiscountType}>
            {"Giảm giá theo số tiền (VND)"}
          </Radio>
        </Radio.Group>
      </Form.Item>
      <Form.Item
        label="Giá trị khuyến mãi"
        name="discountValue"
        rules={[
          { required: true, message: "Vui lòng nhập giá trị khuyến mãi!" },
        ]}
        initialValue={props.discountValue}
      >
        {discountType &&
        discountType.discountTypeName === ValueDiscountType.discountTypeName ? (
          <InputNumber addonAfter="VND" min={0} formatter={convertToVND} />
        ) : (
          <InputNumber addonAfter="%" min={0} max={100} />
        )}
      </Form.Item>

      {/* {discountType === ValueDiscountType ? (
        <Form.Item
          label="Giá trị khuyến mãi"
          name="discountValue"
          rules={[
            { required: true, message: "Vui lòng nhập giá trị khuyến mãi!" },
          ]}
          initialValue={props.discountValue}
        >
          <InputNumber addonAfter="VND" min={0} formatter={convertToVND} />
        </Form.Item>
      ) : (
        <Form.Item
          label="Giá trị khuyến mãi"
          name="discountValue"
          rules={[
            { required: true, message: "Vui lòng nhập giá trị khuyến mãi!" },
          ]}
          initialValue={props.discountValue}
        >
          <InputNumber addonAfter="%" min={0} max={100} />
        </Form.Item>
      )} */}

      <Form.Item
        label="Thời gian"
        name="time"
        rules={[{ required: true, message: "Vui lòng chọn khoảng thời gian!" }]}
        initialValue={[
          props.startTime ? dayjs(props.startTime) : undefined,
          props.endTime ? dayjs(props.endTime) : undefined,
        ]}
      >
        <RangePicker showTime />
      </Form.Item>
    </section>
  );
};

const ConditionRule = (props: { treeData: any }) => {
  return (
    <Content className="px-8">
      <article className="flex">
        <Paragraph className="flex items-center  after:content-['*'] after:text-[#ff4d4f] after:ms-1 after:font-mark">
          {`Chương trình khuyến mãi sẽ được tạo dựa trên (các) điều kiện dưới đây`}
        </Paragraph>
      </article>
      <Tree
        showLine
        switcherIcon={<DownOutlined />}
        defaultExpandAll
        treeData={renderTreeData(props.treeData)}
      />
    </Content>
  );
};

const CreatePromotionPage: React.FC = () => {
  const { promotionId } = useParams();
  //
  // const [timeId, setTimeId] = useState();
  // const [andId, setAndId] = useState();
  //

  const { treeData, setRerender, dispatchTree } = useContext(TreeContext)!;
  const [form] = Form.useForm();
  const [loading, setLoading] = useState<boolean>(true);
  const [loadingSubmit, setLoadingSubmit] = useState<boolean>(false);
  const [messageApi, contextHolder] = message.useMessage();

  const navigate = useNavigate();

  const onFormFinish = async (values: any) => {
    setLoadingSubmit(true);
    //
    treeData.promotionId = promotionId;
    //
    const conditionStmt = { ...treeData };
    Object.keys(values).forEach((key: any) => {
      if (key !== "time") {
        treeData[key] = values[key];
        conditionStmt[key] = values[key];
      } else {
        if (values[key] && values[key].length === 2) {
          // Get value for TIME PROPERTY
          const timeRange = values[key].map((value: any) => {
            return { valueName: value.format() };
          });
          conditionStmt["startTime"] = timeRange[0].valueName;
          conditionStmt["endTime"] = timeRange[1].valueName;

          const timeNode = {
            // id: timeId,
            type: "condition",
            propertyName: "current",
            property_type: "date",
            objectId: "",
            objectName: "time",
            object: "time",
            operator: "82f8efa5-6b0f-4ce9-941c-b468bb8c8425",
            operatorName: "between",

            propertyNoneValue: true,
            value: timeRange,
          };

          const conditionRoot = treeData.condition;

          // Merge TIME NODE with CONDITION ROOT
          conditionStmt.condition = {
            // id: andId,
            type: "logic",
            operator: "&&",
            children: [timeNode, conditionRoot],
          };
        }
      }
    });

    try {
      console.log(111, conditionStmt);
      if (!promotionId) {
        const response = await createPromotion(conditionStmt);

        if (response && response.status === 200) {
          messageApi.open({
            type: "success",
            content: "Lưu thành công",
          });
        }
        navigate("/promotion/view");
      } else {
        const response = await updatePromotionById(promotionId, conditionStmt);

        if (response && response.status === 200) {
          messageApi.open({
            type: "success",
            content: "Lưu thành công",
          });
        }
        navigate("/promotion/view");
      }
    } catch (error: any) {
      messageApi.open({
        type: "error",
        content: error.message,
      });
      console.log(
        "🚀 ~ file: CreatePromotionPage.tsx:174 ~ onFormFinish ~ error:",
        error
      );
    }
    setLoadingSubmit(false);
  };

  const items: CollapseProps["items"] = [
    {
      key: "1",
      label: (
        <>
          <Title level={5}>Thông tin chung</Title>
          <Divider className="mt-6 mb-0" />
        </>
      ),
      children: <GeneralInfo {...treeData} />,
    },
    {
      key: "2",
      label: (
        <>
          <Title level={5}>Điều kiện khuyến mãi</Title>
          <Divider className="mt-6 mb-0" />
        </>
      ),
      children: <ConditionRule treeData={treeData} />,
    },
  ];

  useEffect(() => {
    const fetchData = async () => {
      if (promotionId) {
        const response = await getPromotionById(promotionId);
        if (response && response.status === 200) {
          for (var key in response.data) {
            if (key === "condition") {
              //
              // setAndId(response.data.condition.id);
              // setTimeId(response.data.condition.children[0].id);
              treeData[key] = convertTreeData(response?.data);
            } else {
              treeData[key] = response?.data[key];
            }
          }
        }

        setRerender((value) => !value);
        // treeData.condition = convertTreeData(conditionData).condition;
      }
      setLoading(false);
    };
    fetchData();
    return () => {
      dispatchTree({
        type: TreeActionType.CLEAR_TREE,
        payload: {
          key: "0",
        },
      });
    };
  }, []);

  if (loading)
    return (
      <section className="flex justify-center items-center">
        <Spin />
      </section>
    );

  return (
    <section>
      {contextHolder}
      <Title className="mx-6" level={3}>
        Tạo chương trình khuyến mãi
      </Title>
      <Divider />
      <Form layout="vertical" form={form} onFinish={onFormFinish}>
        <div className="px-12">
          <Collapse
            defaultActiveKey={["1", "2"]}
            ghost
            items={items}
            size="large"
          />
        </div>
        <div className="flex justify-center mt-12">
          <Form.Item>
            <SubmitButton
              type="primary"
              htmlType="submit"
              loading={loadingSubmit}
            >
              Lưu
            </SubmitButton>
          </Form.Item>
        </div>
      </Form>
    </section>
  );
};

export default CreatePromotionPage;
