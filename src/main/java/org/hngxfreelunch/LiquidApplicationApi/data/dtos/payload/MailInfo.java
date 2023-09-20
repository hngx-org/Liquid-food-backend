package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MailInfo {
    private String name;
    private String email;
}

