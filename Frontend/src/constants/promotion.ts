export enum PromotionType {
  PRODUCT = "product",
  CART = "cart",
}

export enum DiscountType {
  PERCENTAGE = "percentage",
  AMOUNT = "amount",
}

export enum PropType {
  LIST_STRING = "list string",
  LIST_NUMBER = "list number",
}

export const CartPromotionType = {
  promotionTypeId: "722928fb-9beb-4bb4-9a2c-4c8bfd81d5dd",
  promotionTypeName: "cart",
};

export const ProductPromotionType = {
  promotionTypeId: "3fe103da-337a-4644-8ad2-8ecdb55954de",
  promotionTypeName: "product",
};

export const PercentageDiscountType = {
  discountTypeId: "df43cc57-8bbc-4e66-8de0-f19c2f14a535",
  discountTypeName: "percentage",
};

export const ValueDiscountType = {
  discountTypeId: "931fc54a-fd49-40ef-b08f-de016dc8ce57",
  discountTypeName: "value",
};
