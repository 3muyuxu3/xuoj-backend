package com.xu.xuoj.model.dto.question;

import lombok.Data;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/9 18:14
 * 题目用例
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
