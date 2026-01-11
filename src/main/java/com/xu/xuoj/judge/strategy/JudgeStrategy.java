package com.xu.xuoj.judge.strategy;

import com.xu.xuoj.judge.codesandbox.model.JudgeInfo;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
