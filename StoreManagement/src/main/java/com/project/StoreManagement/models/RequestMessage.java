package com.project.StoreManagement.models;

import lombok.Data;

import java.util.Date;

@Data
public class RequestMessage<T> {
    Date message;
    T object;
}
