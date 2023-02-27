package com.booking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private Set<RoleDTO> roles = new HashSet<>();

    // Getters and setters
}

