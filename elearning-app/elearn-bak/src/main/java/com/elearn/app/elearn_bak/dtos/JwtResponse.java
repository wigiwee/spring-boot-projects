package com.elearn.app.elearn_bak.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    
        private String token;
    
        private UserDto user;

        // public JwtResponse(String token) {
        //     this.token = token;
        // }
}
