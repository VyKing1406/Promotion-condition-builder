import { DeleteOutlined } from "@ant-design/icons";
import { Button } from "antd";
import { useContext } from "react";
import { TreeActionType } from "../../constants/treeAction";
import { TreeContext } from "../../contexts/TreeContext";
import CondExprNode from "./CondExprNode";
import DefaultExprNode from "./DefaultExprNode";
import LogicExprNode from "./LogicExprNode";

type ExprNodeType = {
  // childProps?: CondExprNodeType | LogicExprNodeType | DefaultExprNodeType;
  childProps?: any;
  type: "condition" | "logic" | "default";
  nodeID: string;
};

const ExprNode = (props: ExprNodeType) => {
  const { dispatchTree, setRerender } = useContext(TreeContext)!;
  const handleRemove = () => {
    dispatchTree({
      type: TreeActionType.REMOVE,
      payload: {
        key: props.nodeID,
      },
    });
    setRerender((value) => !value);
  };
  return (
    <div className="flex justify-between">
      {props.type === "condition" && (
        <CondExprNode nodeID={props.nodeID} {...props.childProps} />
      )}
      {props.type === "logic" && (
        <LogicExprNode nodeID={props.nodeID} {...props.childProps} />
      )}
      {props.type === "default" && <DefaultExprNode nodeID={props.nodeID} />}
      {props.type !== "default" && (
        <Button
          icon={<DeleteOutlined />}
          type="primary"
          danger
          onClick={handleRemove}
        />
      )}
    </div>
  );
};

export default ExprNode;
