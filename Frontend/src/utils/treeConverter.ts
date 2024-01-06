const convertTreeData = (conditionData: any) => {
  console.log(
    "🚀 ~ file: treeConverter.ts:2 ~ convertTreeData ~ condition:",
    conditionData.condition
  );
  return conditionData.condition.children[1];
};

export { convertTreeData };
