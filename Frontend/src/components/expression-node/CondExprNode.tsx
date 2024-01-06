import { Input, Select, Space } from "antd";
import { useContext, useEffect, useState } from "react";
import { PromotionContext } from "../../contexts/PromotionContext";
import { TreeContext } from "../../contexts/TreeContext";
import { TreeActionType } from "../../constants/treeAction";
import { PropType } from "../../constants/promotion";

const onSearch = (value: string) => {
  // console.log("search:", value);
};

// Filter `option.label` match the user type `input`
const filterOption = (
  input: string,
  option?: { label: string; value: string }
) => (option?.label ?? "").toLowerCase().includes(input.toLowerCase());

const filterPropsOption = (
  input: string,
  option?: { label: string; value: string }
) => {
  return (option?.label ?? "").toLowerCase().includes(input.toLowerCase());
};

export type CondExprNodeType = {
  operator?: string;
  property?: string;
  property_type?: string;
  object?: string;
  value?: any[];
  nodeID: string;
};

const CondExprNode = (props: CondExprNodeType) => {
  const { dispatchTree } = useContext(TreeContext)!;
  const { propOptions, operatorOptionsList, valueOptionsList } =
    useContext(PromotionContext);

  const [property, setProp] = useState(props.property);
  const [propType, setPropType] = useState(props.property_type);

  // Operator states
  const [operatorOptions, setOperatorOptions] = useState<any[]>(
    propType ? operatorOptionsList[propType] : []
  );
  const [operator, setOperator] = useState(props.operator);

  // Value states
  const [valueOptions, setValueOptions] = useState(
    property ? valueOptionsList[property] : []
  );
  const [value, setValue] = useState<string[]>(
    props.value
      ? props.value.map((valEle) => {
          return valEle.value;
        })
      : []
  );

  const onPropChange = (value: string, option: any) => {
    setProp(value);
    dispatchTree({
      type: TreeActionType.MODIFY_PROP,
      payload: {
        key: props.nodeID,
        data: {
          property: value,
          property_type: option.propertyTypeByPropertyTypeId.name,
          // object: option.object,
          label: option.label,
          objectId: option.objectId,
          objectLabel: option.objectLabel,
          objectName: option.objectName,
          propertyId: option.propertyId,
          propertyLabel: option.propertyLabel,
          propertyName: option.propertyName,
          propertyEntity: option.propertyEntity,
          propertyNoneValue: option.propertyNoneValue,
          propertyTypeByPropertyTypeId: option.propertyTypeByPropertyTypeId,
        },
      },
    });
    setPropType(option.propertyTypeByPropertyTypeId.name);
    setOperator(undefined);
    setOperatorOptions(
      operatorOptionsList[option.propertyTypeByPropertyTypeId.name]
    );
    setValueOptions(valueOptionsList[value] ?? []);
  };

  const onOperatorChange = (value: string, option: any) => {
    console.log(
      "🚀 ~ file: CondExprNode.tsx:90 ~ onOperatorChange ~ option:",
      option
    );
    setOperator(value);
    dispatchTree({
      type: TreeActionType.MODIFY_OPERATOR,
      payload: {
        key: props.nodeID,
        data: {
          operator: value,
          //
          label: option.label,
          operatorId: option.operatorIdId,
          operatorLabel: option.operatorLabel,
          operatorName: option.operatorName,
          value: option.value,
        },
      },
    });
  };

  const onValueChange = (value: string[], option?: any) => {
    console.log(
      "🚀 ~ file: CondExprNode.tsx:113 ~ onValueChange ~ value:",
      value
    );
    setValue(value);
    dispatchTree({
      type: TreeActionType.MODIFY_VALUE,
      payload: {
        key: props.nodeID,
        data: {
          value: value,
          option: option,
        },
      },
    });
  };

  return (
    <>
      <div className="flex gap-2">
        {/* Properties selection */}
        <Select
          value={property}
          defaultValue={props.property}
          className="w-72"
          showSearch
          placeholder="Chọn thuộc tính"
          optionFilterProp="children"
          onChange={onPropChange}
          onSearch={onSearch}
          filterOption={filterPropsOption}
          options={propOptions}
        />

        {/* Operator selection */}
        <Select
          disabled={propType === undefined}
          value={operator}
          defaultValue={props.operator}
          className=" w-44"
          // showSearch
          placeholder="Chọn toán tử so sánh"
          optionFilterProp="children"
          onChange={onOperatorChange}
          // onSearch={onSearch}
          // filterOption={filterOption}
          options={operatorOptions}
        />

        {/* Value selection */}
        {propType !== PropType.LIST_STRING &&
        propType !== PropType.LIST_NUMBER ? (
          operator === "82f8efa5-6b0f-4ce9-941c-b468bb8c8425" ? (
            <Space.Compact className="w-72">
              <Input
                className="w-1/2"
                placeholder="Min"
                value={value[0]}
                defaultValue={
                  props.value ? props.value[0].valueName : undefined
                }
                onChange={(e) => {
                  if (value.length !== 2) onValueChange([e.target.value, ""]);
                  else onValueChange([e.target.value, value[1]]);
                }}
              />
              <Input
                className="w-1/2"
                placeholder="Max"
                value={value[1] ?? undefined}
                defaultValue={
                  props.value ? props.value[1].valueName : undefined
                }
                onChange={(e) => {
                  if (value.length !== 2) onValueChange(["", e.target.value]);
                  else onValueChange([value[0], e.target.value]);
                }}
              />
            </Space.Compact>
          ) : (
            <Input
              disabled={operator === undefined}
              className="w-72"
              placeholder="Nhập giá trị"
              value={value[0]}
              defaultValue={props.value ? props.value[0].valueName : undefined}
              onChange={(e) => {
                onValueChange([e.target.value]);
              }}
            />
          )
        ) : (
          <Select
            mode="multiple"
            disabled={operator === undefined}
            defaultValue={undefined}
            value={value}
            className="w-72"
            showSearch
            placeholder="Chọn giá trị"
            optionFilterProp="children"
            onChange={onValueChange}
            onSearch={onSearch}
            filterOption={filterOption}
            options={valueOptions}
          />
        )}
      </div>
    </>
  );
};

export default CondExprNode;
