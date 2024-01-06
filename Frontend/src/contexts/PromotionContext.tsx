import axios, { AxiosError } from "axios";
import React, { createContext, useEffect, useState } from "react";
import { CONDITION_DATA_API } from "../api/conditionData";
import { PropType } from "../constants/promotion";
import { getConditionData } from "../services/promotion";

interface PromotionContextType {
  conditionData: Object;
  propOptions: Array<Object>;
  operatorOptions: Array<Object>;
}

const convertProps = (conditionData: any) => {
  return conditionData.objectDtos.map((element: any) => {
    const label = element.label;
    //
    const objectId = element.id;
    const objectLabel = element.label;
    const objectName = element.name;
    const options = element.propertiesByObjectId.map((propObj: any) => {
      const label = propObj.label;
      const value = propObj.id;
      const type = propObj.propertyTypeByPropertyTypeId.name;
      //
      const propertyId = propObj.id;
      const propertyLabel = propObj.label;
      const propertyName = propObj.name;
      const propertyEntity = propObj.propertyEntity;
      const propertyNoneValue = propObj.propertyNoneValue;
      const propertyTypeByPropertyTypeId = propObj.propertyTypeByPropertyTypeId;
      return {
        label,
        value,
        type,
        propertyId,
        propertyLabel,
        propertyName,
        propertyEntity,
        propertyNoneValue,
        propertyTypeByPropertyTypeId,
        objectId,
        objectLabel,
        objectName,
      };
    });
    return { label, options };
  });
};

const convertOperator = (conditionData: any) => {
  const operatorOptionsList: any = {};
  conditionData.propertyType.forEach((element: any) => {
    // Operator
    const type = element.name;
    //
    const typeId = element.id;
    const typeName = element.name;
    const options = element.propertyTypeOperatorsByPropertyTypeId.map(
      (op: any) => {
        const label = op.operatorByOperatorId.label;
        const value = op.operatorByOperatorId.id;
        //
        const operatorId = op.operatorByOperatorId.id;
        const operatorLabel = op.operatorByOperatorId.label;
        const operatorName = op.operatorByOperatorId.name;
        return { label, value, operatorId, operatorLabel, operatorName };
      }
    );
    operatorOptionsList[type] = options;
  });

  return operatorOptionsList;
};

const convertValue = (conditionData: any) => {
  const valueOptionsList: any = {};
  conditionData.objectDtos.forEach((element: any) => {
    element.propertiesByObjectId.forEach((property: any) => {
      // if (
      //   property.type &&
      //   (property.type.value === PropType.LIST_STRING ||
      //     property.type.value === PropType.LIST_NUMBER)
      // )
      if (!property.propertyNoneValue) {
        valueOptionsList[property.id] = property.propertyValuesByPropertyId.map(
          (value: any) => {
            return {
              label: value.name,
              value: value.id,
              //
              valueId: value.id,
              valueName: value.name,
            };
          }
        );
      }
    });
  });

  return { ...valueOptionsList };
};

const PromotionContext = createContext<any>(undefined);
// const PromotionContext = createContext<PromotionContextType | undefined>(
//   undefined
// );

const PromotionContextProvider: React.FC<React.PropsWithChildren<{}>> = ({
  children,
}) => {
  const [propOptions, setPropOptions] = useState([]);
  const [operatorOptionsList, setOperatorOptionsList] = useState([]);
  const [valueOptionsList, setValueOptionsList] = useState([]);
  useEffect(() => {
    const fetchApi = async () => {
      try {
        const conditionData = await getConditionData();
        if (conditionData) {
          setPropOptions(convertProps(conditionData));
          setOperatorOptionsList(convertOperator(conditionData));
          setValueOptionsList(convertValue(conditionData));
        }
      } catch (err) {
        console.log(
          "🚀 ~ file: PromotionContext.tsx:198 ~ fetchApi ~ err:",
          err
        );
      }
    };

    fetchApi();
  }, []);

  return (
    <PromotionContext.Provider
      value={{
        propOptions,
        operatorOptionsList,
        valueOptionsList,
      }}
    >
      {children}
    </PromotionContext.Provider>
  );
};

export { PromotionContext, PromotionContextProvider };
