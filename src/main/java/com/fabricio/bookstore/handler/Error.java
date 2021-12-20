package com.fabricio.bookstore.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Error {

    private Long timestamp;
    private Integer status;
    private String error;

}
