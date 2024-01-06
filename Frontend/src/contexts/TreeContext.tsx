import React, { createContext, useReducer, useState } from "react";
import { TreeAction, TreeReducer } from "./TreeReducer";

interface TreeContextType {
  treeData: any;
  // setTreeData: React.Dispatch<React.SetStateAction<any>>;
  dispatchTree: React.Dispatch<TreeAction>;
  // rerender: boolean;
  setRerender: React.Dispatch<React.SetStateAction<boolean>>;
}

const TreeContext = createContext<TreeContextType | undefined>(undefined);

const TreeContextProvider: React.FC<React.PropsWithChildren<{}>> = ({
  children,
}) => {
  const initTreeData = {
    name: null,
    description: null,
    discountType: null,
    discountValueType: null,
    discountValue: null,
    condition: {
      type: "default",
      key: "0",
    },
  };

  const [treeData, dispatchTree] = useReducer(TreeReducer, initTreeData);
  const [rerender, setRerender] = useState<boolean>(false);
  return (
    <TreeContext.Provider value={{ treeData, dispatchTree, setRerender }}>
      {children}
    </TreeContext.Provider>
  );
};

export { TreeContext, TreeContextProvider };
