package com.xu.xuoj.judge;

import com.xu.xuoj.judge.strategy.DefaultJudgeStrategy;
import com.xu.xuoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.xu.xuoj.judge.strategy.JudgeContext;
import com.xu.xuoj.judge.strategy.JudgeStrategy;
import com.xu.xuoj.judge.codesandbox.model.JudgeInfo;
import com.xu.xuoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 判题管理
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
