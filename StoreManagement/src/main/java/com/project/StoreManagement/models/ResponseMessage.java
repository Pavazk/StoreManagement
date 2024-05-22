package com.project.StoreManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ResponseMessage {
    String date;
    String message;
    String statusCode;
}
