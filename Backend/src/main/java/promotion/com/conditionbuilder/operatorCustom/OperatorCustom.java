package promotion.com.conditionbuilder.operatorCustom;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OperatorCustom {
  public static boolean starts_with(String value, List<String> character){
    return !value.isEmpty() && value.startsWith(character.get(0));
  }

  public static boolean not_starts_with(String value, List<String> character){
    return !value.isEmpty() && !value.startsWith(character.get(0));
  }

  public static boolean ends_with(String value, List<String> character){
    return !value.isEmpty() && value.endsWith(character.get(0));
  }

  public static boolean not_ends_with(String value, List<String> character){
    return !value.isEmpty() && !value.endsWith(character.get(0));
  }

  public static boolean contain(String value, List<String> string){
    return !value.isEmpty() && value.contains(string.get(0));
  }

  public static boolean not_contain(String value, List<String> string){
    return !value.isEmpty() && !value.contains(string.get(0));
  }

  public static boolean is_empty(String value){
    return value.isEmpty();
  }

  public static boolean is_not_empty(String value){
    return !value.isEmpty();
  }

  public static boolean between(Integer value, List<Integer> list) {
    return value != null && value >= list.get(0) && value <= list.get(1);
  }

  public static boolean not_between(Integer value, List<Integer> list) {
    return value != null && value <= list.get(0) && value >= list.get(1);
  }

  public static OffsetDateTime parseDateTime(String value){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    return OffsetDateTime.parse(value, formatter);
  }

  public static boolean between(OffsetDateTime value, List<String> list) {
    return value != null && !(value.isBefore(parseDateTime(list.get(0))) || value.isAfter(parseDateTime(list.get(1))));
  }

  public static boolean not_between(OffsetDateTime value, List<String> list) {
    return value != null && (value.isBefore(parseDateTime(list.get(0))) || value.isAfter(parseDateTime(list.get(1))));
  }

  public static boolean is(Integer value, List<Integer> values) {
    return value != null && value.equals(values.get(0));
  }

  public static boolean is(String value, List<String> values) {
    return !value.isEmpty() && value.equals(values.get(0));
  }

  public static boolean is_not(Integer value, List<Integer> values) {
    return value != null && !value.equals(values.get(0));
  }

  public static boolean is_not(String value, List<String> values) {
    return !value.isEmpty() && !value.equals(values.get(0));
  }

  public static boolean is_less_than(Integer value, List<Integer> value2){
    return value != null && value < value2.get(0);
  }

  public static boolean is_equal_to_or_less_than (Integer value, List<Integer> value2){
    return value != null && value <= value2.get(0);
  }

  public static boolean is_greater_than(Integer value, List<Integer> values) {
    return value != null && value > values.get(0);
  }

  public static boolean is_equal_to_or_greater_than(Integer value, List<Integer> value2){
    return value != null && value >= value2.get(0);
  }

  public static boolean is_listed(String value, List<String> list){
    return !value.isEmpty() && list.contains(value);
  }

  public static boolean is_listed(List<String> value, List<String> list){
    return !value.isEmpty() && list.stream().anyMatch(value::contains);
  }

  public static boolean is_not_listed(String value, List<String> list){
    return !value.isEmpty() && !list.contains(value);
  }
}
