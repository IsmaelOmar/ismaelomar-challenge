package com.mixam.ismaelomarchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormData {

    private String name;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String orderNumber;
    private String message;
}
