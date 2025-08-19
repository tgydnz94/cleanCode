package com.bootcamp.cleanCode.core.utilities.exceptions;

import java.util.Map;

import com.bootcamp.cleanCode.core.utilities.ProblemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> validationErrors;    
}
