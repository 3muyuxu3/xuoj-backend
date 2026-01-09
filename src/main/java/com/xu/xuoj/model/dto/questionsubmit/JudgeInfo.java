package com.xu.xuoj.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/9 18:29
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间（KB）
     */
    private Long time;
}
