package com.booking.service;

import com.booking.payload.LoginDTO;
import com.booking.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
