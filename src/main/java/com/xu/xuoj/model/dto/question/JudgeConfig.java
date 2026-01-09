package com.xu.xuoj.model.dto.question;

import lombok.Data;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/9 18:14
 * 题目配置
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}
