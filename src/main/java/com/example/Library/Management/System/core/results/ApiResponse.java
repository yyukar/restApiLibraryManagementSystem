package com.example.Library.Management.System.core.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, null, data);
    }

    public static ApiResponse<Void> okMessage(String msg) {
        return new ApiResponse<>(true, msg, null);
    }

    public static ApiResponse<Void> failMessage(String msg) {
        return new ApiResponse<>(false, msg, null);
    }

}
