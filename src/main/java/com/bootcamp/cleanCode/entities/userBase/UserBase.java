package com.bootcamp.cleanCode.entities.userBase;

import com.bootcamp.cleanCode.entities.Role;

public interface UserBase {
    String getEmail();
    String getPassword();
    String getName();
    Role getRole();
}
