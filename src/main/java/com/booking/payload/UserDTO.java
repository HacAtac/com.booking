package com.booking.payload;

import com.booking.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Jordan Hackworth on 2023-02-03
 * Reason: Doing this so that I can remove the password from the user object when it is returned to the client
 * TODO: May need to remove the role array aswell.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String name;
    private Set<Role> roles;
}
