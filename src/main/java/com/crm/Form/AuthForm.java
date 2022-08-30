package com.crm.Form;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthForm {
    String mail;
    String password;
}
