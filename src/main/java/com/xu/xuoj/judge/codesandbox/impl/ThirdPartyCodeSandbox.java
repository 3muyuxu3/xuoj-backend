package com.xu.xuoj.judge.codesandbox.impl;

import com.xu.xuoj.judge.codesandbox.CodeSandbox;
import com.xu.xuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.xu.xuoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 第三方代码沙箱
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
