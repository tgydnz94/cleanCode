package com.bootcamp.cleanCode.business.concretes.requests.roleRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
}
