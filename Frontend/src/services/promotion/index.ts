import axios from "axios";
import { CONDITION_DATA_API, PROMOTION_API } from "../../api";
import { ResponseAPI } from "../type";
import { PromotionInfo } from "../../pages/GetPromotionPage";

export const getConditionData = async () => {
  try {
    const response = await axios.get(CONDITION_DATA_API, {
      headers: {
        "ngrok-skip-browser-warning": "69420",
      },
    });
    return response.data;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:9 ~ getConditionData ~ error:", error);
  }
};

export const createPromotion = async (conditionStmt: any) => {
  try {
    const response = await axios.post(
      `${PROMOTION_API}/promotions`,
      {
        ...conditionStmt,
      },
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
        },
      }
    );
    return response;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:25 ~ createPromotion ~ error:", error);
  }
};

export const getPromotions = async () => {
  try {
    const response = await axios.get(`${PROMOTION_API}/promotions`, {
      headers: {
        "ngrok-skip-browser-warning": "69420",
      },
    });
    return response;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:34 ~ getPromotion ~ error:", error);
  }
};
export const getPromotionById = async (promotionId: string) => {
  try {
    const response = await axios.get(
      `${PROMOTION_API}/promotions/${promotionId}`,
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
        },
      }
    );
    return response;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:34 ~ getPromotion ~ error:", error);
  }
};

export const editPromotionById = async (
  promotionId: string,
  conditionStmt: any
) => {
  try {
    const response = await axios.patch(
      `${PROMOTION_API}/promotions/${promotionId}`,
      {
        ...conditionStmt,
      },
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
        },
      }
    );
    return response;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:66 ~ editPromotionById ~ error:", error);
  }
};

export const deletePromotionById = async (
  promotionId: string,
  promotionName: string
) => {
  try {
    const response = await axios.delete(
      `${PROMOTION_API}/promotions/${promotionId}/${promotionName}`,
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
        },
      }
    );
    return response;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:85 ~ error:", error);
  }
};

export const updatePromotionById = async (
  promotionId: string,
  conditionStmt: any
) => {
  try {
    const response = await axios.put(
      `${PROMOTION_API}/promotions/${promotionId}`,
      { ...conditionStmt },
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
        },
      }
    );
    return response;
  } catch (error) {
    console.log("🚀 ~ file: index.ts:85 ~ error:", error);
  }
};
