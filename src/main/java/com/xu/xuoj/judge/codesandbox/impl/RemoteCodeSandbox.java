package com.xu.xuoj.judge.codesandbox.impl;

import com.xu.xuoj.judge.codesandbox.CodeSandbox;
import com.xu.xuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.xu.xuoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 远程代码沙箱(实际调用接口沙箱)
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}

