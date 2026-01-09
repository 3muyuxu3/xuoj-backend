package com.xu.xuoj.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/9 18:13
 * 创建题目
 */
@Data
public class QuestionAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 判题用例
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置
     */
    private JudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}
