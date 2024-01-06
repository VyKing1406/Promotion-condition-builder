import { Select } from "antd";
import { useContext, useState } from "react";
import { TreeContext } from "../../contexts/TreeContext";
import { TreeActionType } from "../../constants/treeAction";

// const onChange = (value: string) => {
//   console.log(`selected ${value}`);
// };

// const onSearch = (value: string) => {
//   console.log("search:", value);
// };

// Filter `option.label` match the user type `input`
const filterOption = (
  input: string,
  option?: { label: string; value: string }
) => (option?.label ?? "").toLowerCase().includes(input.toLowerCase());

export type LogicExprNodeType = {
  operator?: string;
  value?: string;
  children?: Object[];
  nodeID: string;
};

const options = [
  { value: "&&", label: "AND" },
  {
    value: "||",
    label: "OR",
  },
];

const LogicExprNode = (props: LogicExprNodeType) => {
  const { dispatchTree } = useContext(TreeContext)!;
  const [operator, setOperator] = useState<string | undefined>(undefined);
  const onOperatorChange = (value: string) => {
    setOperator(value);
    dispatchTree({
      type: TreeActionType.MODIFY_OPERATOR,
      payload: {
        key: props.nodeID,
        data: {
          operator: value,
        },
      },
    });
  };
  return (
    <>
      <Select
        className="w-40"
        placeholder="Chọn toán tử logic"
        options={options}
        defaultValue={props.operator}
        value={operator}
        onChange={onOperatorChange}
      />
    </>
  );
};

export default LogicExprNode;
