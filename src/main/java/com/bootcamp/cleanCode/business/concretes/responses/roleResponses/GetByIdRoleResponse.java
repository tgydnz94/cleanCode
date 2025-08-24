package com.bootcamp.cleanCode.business.concretes.responses.roleResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRoleResponse {
    private int id;
    private String name;
}
