package vn.edu.t3h.employeemanager.utils;

public class StringUtils {
    public static boolean isBlank(String str){
        return str == null || str.trim().isEmpty();
    }

    public static Long toLong(String str){
        if(str == null || str.trim().isEmpty()){
            return null;
        }
        try {
            return Long.parseLong(str.trim());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }
}
