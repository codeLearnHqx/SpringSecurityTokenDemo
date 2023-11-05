package com.hqx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Create by hqx
 * @Date 2023/11/2 16:52
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRspVO implements Serializable {
    private static final long serialVersionUID = -2404206202106235753L;

    private String token;

}
