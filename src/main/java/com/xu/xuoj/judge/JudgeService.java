package com.xu.xuoj.judge;

import com.xu.xuoj.model.entity.QuestionSubmit;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
