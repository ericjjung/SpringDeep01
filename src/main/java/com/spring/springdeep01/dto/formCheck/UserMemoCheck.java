package com.spring.springdeep01.dto.formCheck;

import java.time.LocalDateTime;

public interface UserMemoCheck {
    Long getId();
    String getTitle();
    String getUsername();
    String getContent();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
}
