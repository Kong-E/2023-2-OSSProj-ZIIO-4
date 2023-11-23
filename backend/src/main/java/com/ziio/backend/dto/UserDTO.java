package com.ziio.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info {
        private String email;
        private String name;
    }
}