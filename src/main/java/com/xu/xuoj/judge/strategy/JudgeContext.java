package com.xu.xuoj.judge.strategy;

import com.xu.xuoj.model.dto.question.JudgeCase;
import com.xu.xuoj.judge.codesandbox.model.JudgeInfo;
import com.xu.xuoj.model.entity.Question;
import com.xu.xuoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 上下文(用于定义在策略中传递的参数)
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
