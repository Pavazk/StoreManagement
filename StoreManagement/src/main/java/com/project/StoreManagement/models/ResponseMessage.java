package com.project.StoreManagement.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ResponseMessage {
    LocalDate date;
    List<String> message;
    int statusCode;
}
