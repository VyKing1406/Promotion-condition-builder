import { TreeActionType } from "../constants/treeAction";

type TreeAction = {
  type: string;
  payload: {
    key: string;
    // data?: {
    //   object?: string;
    //   property?: string;
    //   property_type?: string;
    //   operator?: string;
    //   value?: any;
    // };
    data?: any;
  };
};

const traverseTree: any = (node: any, key: string, index: number = 1) => {
  if (node.key === key) {
    return node;
  } else {
    const childIndex: number = parseInt(key[index]);
    return traverseTree(node.children[childIndex], key, index + 1);
  }
};

const addCondition = (root: any, key: string) => {
  const node = traverseTree(root, key);
  node.type = "condition";
  return node;
};

const addLogic = (root: any, key: string) => {
  const node = traverseTree(root, key);
  node.type = "logic";
  node.children = [
    {
      type: "default",
      key: `${key}0`,
    },
    {
      type: "default",
      key: `${key}1`,
    },
  ];
  return node;
};

const remove = (root: any, key: string) => {
  const node = traverseTree(root, key);

  //Set this node to default node
  for (let key in node) {
    if (key !== "key") {
      delete node[key];
    }
  }
  node.type = "default";
  // delete node.children;
  // delete node.property;
  // delete node.property_type;
  // delete node.object;
  // delete node.operator;
  // delete node.value;
};

const modifyProp = (root: any, key: string, data: any) => {
  const node = traverseTree(root, key);
  node.property = data.property;
  node.property_type = data.property_type;
  // node.label = data.label;
  node.objectId = data.objectId;
  node.objectLabel = data.objectLabel;
  node.objectName = data.objectName;
  node.propertyId = data.propertyId;
  node.propertyLabel = data.propertyLabel;
  node.propertyName = data.propertyName;
  node.propertyEntity = data.propertyEntity;
  node.propertyNoneValue = data.propertyNoneValue;
  node.propertyTypeByPropertyTypeId = data.propertyTypeByPropertyTypeId;
  delete node.operator;
  delete node.value;
};

const modifyOperator = (root: any, key: string, data: any) => {
  const node = traverseTree(root, key);
  node.operator = data.operator;
  // node.label = data.label;
  node.operatorId = data.operatorIdId;
  node.operatorLabel = data.operatorLabel;
  node.operatorName = data.operatorName;
  // node.value = data.value;
  if (node.type === "condition") delete node.value;
};

const modifyValue = (root: any, key: string, data: any) => {
  console.log("🚀 ~ file: TreeReducer.tsx:96 ~ modifyValue ~ data:", data);
  const node = traverseTree(root, key);
  if (data.option) {
    node.value = data.option.map((element: any) => {
      return {
        value: element.value,
        valueId: element.valueId,
        valueName: element.valueName,
        label: element.label,
      };
    });
  } else {
    console.log(555, data.value);
    node.value = data.value.map((element: any) => {
      return {
        valueName: element,
      };
    });
  }
  console.log(666, node);
};

const TreeReducer = (state: any, action: TreeAction) => {
  switch (action.type) {
    case TreeActionType.ADD_CONDITION:
      addCondition(state.condition, action.payload.key);
      return state;
    case TreeActionType.ADD_LOGIC:
      addLogic(state.condition, action.payload.key);
      return state;
    case TreeActionType.REMOVE:
      remove(state.condition, action.payload.key);
      return state;
    case TreeActionType.MODIFY_PROP:
      modifyProp(state.condition, action.payload.key, action.payload.data);
      return state;
    case TreeActionType.MODIFY_OPERATOR:
      modifyOperator(state.condition, action.payload.key, action.payload.data);
      return state;
    case TreeActionType.MODIFY_VALUE:
      modifyValue(state.condition, action.payload.key, action.payload.data);
      return state;
    case TreeActionType.CLEAR_TREE:
      return {
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
    default:
      return {};
  }
};
export { TreeReducer };
export type { TreeAction };
