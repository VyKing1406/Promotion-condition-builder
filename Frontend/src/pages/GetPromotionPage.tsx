import {
  Badge,
  Button,
  Card,
  Divider,
  Skeleton,
  Space,
  Table,
  Typography,
  message,
} from "antd";
import React, { useEffect, useState } from "react";
import { convertToVND } from "../utils";
import { ColumnsType } from "antd/es/table";
import {
  CartPromotionType,
  DiscountType,
  PercentageDiscountType,
  PromotionType,
  ValueDiscountType,
} from "../constants/promotion";
import { Link, useNavigate } from "react-router-dom";
import { deletePromotionById, getPromotions } from "../services";

const { Title, Text, Paragraph } = Typography;

export interface PromotionInfo {
  promotionId: string;
  name: string;
  promotionType: any;
  description: string;
  discountType: any;
  discountValue: number;
  condition?: any;
  startTime?: any;
  endTime?: any;
}

// const columns: ColumnsType<PromotionInfo> = [
//   {
//     title: "Tên khuyến mãi",
//     dataIndex: "name",
//     key: "name",
//     width: 200,
//     ellipsis: true,
//     sorter: (record1, record2) => {
//       if (record1.name < record2.name) return -1;
//       else if (record1.name > record2.name) return 1;
//       else return 0;
//     },
//   },
//   {
//     title: "Loại khuyến mãi",
//     dataIndex: "discountType",
//     key: "discountType",
//     // width: 200,
//     render: (value) => {
//       if (value === PromotionType.CART) return "Khuyến mãi giỏ hàng";
//       else return "Khuyến mãi sản phẩm";
//     },
//   },
//   {
//     title: "Loại giảm giá",
//     dataIndex: "discountValueType",
//     key: "discountValueType",
//     // width: 200,
//     render: (value) => {
//       if (value === DiscountType.PERCENTAGE) return "Giảm giá theo phần trăm";
//       else return "Giảm giá theo số tiền";
//     },
//     filters: [
//       { text: "Giảm giá theo phần trăm", value: DiscountType.PERCENTAGE },
//       { text: "Giảm giá theo số tiền", value: DiscountType.AMOUNT },
//     ],
//     onFilter: (value, record) => record.discountType === value,
//   },
//   {
//     title: "Giá trị",
//     dataIndex: "discountValue",
//     key: "discountValue",
//     align: "right",
//     // width: 150,
//     render: (value, record) => {
//       if (record.discountType === DiscountType.PERCENTAGE) return `${value}%`;
//       else return `${convertToVND(value)}đ`;
//     },
//   },
//   {
//     title: "Ngày bắt đầu",
//     dataIndex: "startTime",
//     key: "startTime",
//     align: "right",
//     ellipsis: true,
//   },
//   {
//     title: "Ngày kết thúc",
//     dataIndex: "endTime",
//     key: "endTime",
//     align: "right",
//     ellipsis: true,
//   },
//   {
//     title: "",
//     dataIndex: "action",
//     key: "action",
//     align: "center",
//     width: 180,
//     fixed: "right",
//     render: (value, record, index) => {
//       return (
//         <Space size="middle">
//           <Link to={`/promotion/edit/${record.promotionId}`}>
//             <Button size="small">Chỉnh sửa</Button>
//           </Link>
//           <Button
//             type="primary"
//             danger
//             size="small"
//             onClick={() => {
//               deletePromotionById(record.promotionId, record.name).then(
//                 (res) => {
//                   console.log(
//                     "🚀 ~ file: GetPromotionPage.tsx:116 ~ res:",
//                     res
//                   );
//                 }
//               );
//             }}
//           >
//             Gỡ bỏ
//           </Button>
//         </Space>
//       );
//     },
//   },
// ];

const GetPromotionPage = () => {
  const [promotionList, setPromotionList] = useState<Array<PromotionInfo>>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    const fetchApi = async () => {
      const response = await getPromotions();
      setLoading(false);
      setPromotionList(response ? response.data : []);
    };

    fetchApi();
  }, [loading]);

  return (
    <section>
      {contextHolder}
      <Title className="mx-6" level={3}>
        Danh sách khuyến mãi
      </Title>
      <Divider />
      <div className="px-6">
        <Table
          dataSource={promotionList}
          columns={[
            {
              title: "Tên khuyến mãi",
              dataIndex: "name",
              key: "name",
              width: 200,
              ellipsis: true,
              sorter: (record1, record2) => {
                if (record1.name < record2.name) return -1;
                else if (record1.name > record2.name) return 1;
                else return 0;
              },
            },
            {
              title: "Loại khuyến mãi",
              dataIndex: "promotionType",
              key: "promotionType",
              width: 220,
              render: (value) => {
                if (
                  value.promotionTypeName ===
                  CartPromotionType.promotionTypeName
                )
                  return "Khuyến mãi giỏ hàng";
                else return "Khuyến mãi sản phẩm";
              },
            },
            {
              title: "Loại giảm giá",
              dataIndex: "discountType",
              key: "discountType",
              width: 220,
              render: (value) => {
                if (
                  value.discountTypeName ===
                  PercentageDiscountType.discountTypeName
                )
                  return "Giảm giá theo phần trăm";
                else return "Giảm giá theo số tiền";
              },
              filters: [
                {
                  text: "Giảm giá theo phần trăm",
                  value: PercentageDiscountType.discountTypeName,
                },
                {
                  text: "Giảm giá theo số tiền",
                  value: ValueDiscountType.discountTypeName,
                },
              ],
              onFilter: (value, record) => record.discountType === value,
            },
            {
              title: "Giá trị",
              dataIndex: "discountValue",
              key: "discountValue",
              align: "right",
              // width: 150,
              render: (value, record) => {
                if (
                  record.discountType.discountTypeName ===
                  PercentageDiscountType.discountTypeName
                )
                  return `${value}%`;
                else return `${convertToVND(value)}đ`;
              },
            },
            {
              title: "Ngày bắt đầu",
              dataIndex: "startTime",
              key: "startTime",
              align: "right",
              ellipsis: true,
            },
            {
              title: "Ngày kết thúc",
              dataIndex: "endTime",
              key: "endTime",
              align: "right",
              ellipsis: true,
            },
            {
              title: "",
              dataIndex: "action",
              key: "action",
              align: "center",
              width: 180,
              fixed: "right",
              render: (value, record, index) => {
                return (
                  <Space size="middle">
                    <Link to={`/promotion/edit/${record.promotionId}`}>
                      <Button size="small">Chỉnh sửa</Button>
                    </Link>
                    <Button
                      type="primary"
                      danger
                      size="small"
                      onClick={() => {
                        deletePromotionById(
                          record.promotionId,
                          record.name
                        ).then((res) => {
                          console.log(
                            "🚀 ~ file: GetPromotionPage.tsx:247 ~ ).then ~ res:",
                            res
                          );
                          if (res && res.status === 200) {
                            messageApi.open({
                              type: "success",
                              content: "Xoá thành công",
                            });
                            setLoading(true);
                          } else {
                            messageApi.open({
                              type: "error",
                              content: "Something went wrong!",
                            });
                          }
                        });
                      }}
                    >
                      Gỡ bỏ
                    </Button>
                  </Space>
                );
              },
            },
          ]}
          size="small"
          bordered
          loading={loading}
          scroll={{ x: 1200, y: 300 }}
        />
      </div>
    </section>
  );
};

export default GetPromotionPage;
