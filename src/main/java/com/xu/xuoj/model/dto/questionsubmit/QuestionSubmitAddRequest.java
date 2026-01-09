package com.xu.xuoj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/9 18:30
 * 创建请求
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}