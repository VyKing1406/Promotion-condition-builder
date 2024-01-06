const convertToVND = (value: number | undefined) =>
  `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ",");

export { convertToVND };
