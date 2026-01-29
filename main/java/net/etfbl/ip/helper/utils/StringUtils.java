package net.etfbl.ip.helper.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lx.ds
 */
public class StringUtils {
    
    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }
    public static boolean isNull(Object value){
        return value == null;
    }
    public static boolean isEmpty(String value){
        return value.isEmpty();
    }
    
    public static String format(String format, Object... args) {
        return String.format(format, args);
    }
    
    public static String append(String s, String value, String delimiter) {
        if (s == null || s.isEmpty()) return value;
        if (value == null || value.isEmpty()) return s;
        return s.concat(delimiter).concat(value);
    }
    
    public static String insert(String s, String value, String delimiter) {
        if (s == null || s.isEmpty()) return value;
        if (value == null || value.isEmpty()) return s;
        return value.concat(delimiter).concat(s);
    }
    
    public static String substringFromTo(String s, String from, String to) {
        if (s == null || s.isEmpty() || from == null || from.isEmpty() || to == null || to.isEmpty()) return s;
        int idxFrom = s.indexOf(from);
        if (idxFrom < 0) return "";
        idxFrom = idxFrom + from.length();
        int idxTo = s.indexOf(to, idxFrom);
        return (idxTo < 0 ? s.substring(idxFrom) : s.substring(idxFrom, idxTo));
    }
    public static String substringTo(String s, String value) {
        if (s == null || s.length() == 0 || value == null || value.length() == 0) return s;
        int idx = s.indexOf(value);
        return (idx > -1 ? s.substring(0, idx) : s);
    }
    public static String substringFrom(String s, String value) {
        if (s == null || s.length() == 0 || value == null || value.length() == 0) return s;
        int idx = s.indexOf(value) + 1;
        return (idx < s.length() ? s.substring(idx) : "");
    }
    
    public static String join(String[] items, String delimiter) {
        if (items == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            if (sb.length() > 0) {
                sb.append(delimiter);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    public static String join(List<String> items, String delimiter) {
        if (items == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            if (sb.length() > 0) {
                sb.append(delimiter);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    public static String join(Map<String, String> items, String keyValueDelimiter, String delimiter) {
        if (items == null) return "";
        
        StringBuilder sb = new StringBuilder();
        items.entrySet().stream().forEach((item) -> {
            if (sb.length() > 0) {
                sb.append(delimiter);
            }
            sb.append(item.getKey()).append(keyValueDelimiter).append(item.getValue());
        });
        return sb.toString();
    }
    
    public static List<String> splitToList(String s) {
        return splitToList(s, ",", true);
    }
    public static List<String> splitToList(String s, String delimiter, boolean doTrim) {
        List<String> list = new ArrayList<>();
        
        if (s == null || s.isEmpty()) return list;
        String[] arr = s.split(delimiter);
        for (String v : arr) {
            if (doTrim) v = v.trim();
            if (v.isEmpty()) continue;
            list.add(v);
        }
        
        return list;
    }
    
    public static String trimStart(String value, String chars) {
        return value.replaceAll("^[" + chars + "]+", "");
    }
    public static String trimEnd(String value, String chars) {
        return value.replaceAll("[" + chars + "]+$", "");
    }
    
    public static boolean isNumber(String value) {
        return value.matches("^[0-9]+([.][0-9]+)?$|^$");
    }
    
    public static String toString(Object obj) {
        return toString(obj, null);
    }
    public static String toString(Object obj, String defaultValue) {
        if (obj == null) return defaultValue;
        return obj.toString();
    }
    
    public static boolean equalsIgnoreCase(String value1, String value2){
        if (value1 == null || value2 == null) return false;
        return value1.equalsIgnoreCase(value2);
    }
    public static boolean equals(String value1, String value2){
        if (value1 == null || value2 == null) return false;
        return value1.equals(value2);
    }
    
    public static boolean endsWithIgnoreCase(String value1, String value2){
        if (value1 == null || value2 == null) return false;
        return endsWith(value1.toLowerCase(), value2.toLowerCase());
    }
    public static boolean endsWith(String value1, String value2){
        if (value1 == null || value2 == null) return false;
        return value1.endsWith(value2);
    }
    
    public static boolean startsWithIgnoreCase(String value1, String value2){
        if (value1 == null || value2 == null) return false;
        return startsWith(value1.toLowerCase(), value2.toLowerCase());
    }
    public static boolean startsWith(String value1, String value2){
        if (value1 == null || value2 == null) return false;
        return value1.startsWith(value2);
    }    
    
    public static boolean matches(String s, String regex){
        if (s == null || regex == null) return false;
        return s.matches(regex);
    }
    
    public static boolean contains(String s, String value){
        if (s == null || value == null) return false;
        return s.contains(value);
    }
    
    public static String concat(String s1, String sep, String s2){
        if (s1 == null) return null;
        if (sep == null || s2 == null) return s1;
        return s1.concat(sep).concat(s2);
    }
}