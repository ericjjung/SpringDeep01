package com.spring.springdeep01.dto.formCheck;


import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;



public interface WholeInqury {
    String getTitle();
    String getUsername();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
}
