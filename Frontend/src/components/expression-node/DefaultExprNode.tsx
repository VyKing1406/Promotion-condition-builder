import { Button, ButtonProps } from "antd";
import { useContext } from "react";
import { TreeActionType } from "../../constants/treeAction";
import { TreeContext } from "../../contexts/TreeContext";

export type DefaultExprNodeType = {
  nodeID: string;
};

const DefaultExprNode = (props: DefaultExprNodeType) => {
  const commonButtonProps: ButtonProps = {
    type: "primary",
    style: {
      width: 196,
    },
  };

  const { dispatchTree, setRerender } = useContext(TreeContext)!;
  const handleAddCondition = () => {
    dispatchTree({
      type: TreeActionType.ADD_CONDITION,
      payload: {
        key: props.nodeID,
      },
    });
    setRerender((value) => !value);
  };

  const handleAddLogic = () => {
    dispatchTree({
      type: TreeActionType.ADD_LOGIC,
      payload: {
        key: props.nodeID,
      },
    });
    setRerender((value) => !value);
  };

  return (
    <div className="flex justify-end gap-2">
      <Button {...commonButtonProps} onClick={handleAddLogic}>
        Thêm toán tử Logic
      </Button>
      <Button {...commonButtonProps} onClick={handleAddCondition}>
        Thêm câu điều kiện
      </Button>
    </div>
  );
};

export default DefaultExprNode;
