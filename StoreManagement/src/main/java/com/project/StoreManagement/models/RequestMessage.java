package com.project.StoreManagement.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class RequestMessage<T> {
    Date message;
    T object;
}
