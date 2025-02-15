package vn.edu.t3h.employeemanager.utils.map;

import java.util.Map;

public class RequestUtils {
    public static boolean isSearchEmpty(Map<String, String> search) {
        if (search == null || search.isEmpty()) {
            return true; // search null hoặc không có key nào
        }
        // Kiểm tra nếu tất cả các giá trị đều là "" hoặc null
        for (String value : search.values()) {
            if (value != null && !value.trim().isEmpty()) {
                return false; // Có ít nhất 1 giá trị hợp lệ
            }
        }
        return true; // Tất cả giá trị đều rỗng hoặc null
    }
}
