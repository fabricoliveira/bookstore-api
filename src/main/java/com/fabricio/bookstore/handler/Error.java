package com.fabricio.bookstore.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Error {

    private Long timestamp;
    private Integer status;
    private String error;

}
