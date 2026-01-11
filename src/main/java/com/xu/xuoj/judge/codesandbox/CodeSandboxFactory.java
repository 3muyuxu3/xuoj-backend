package com.xu.xuoj.judge.codesandbox;

import com.xu.xuoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.xu.xuoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.xu.xuoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @author xu
 * @version 1.0
 * @date 2026/1/10
 * 沙箱代码工厂(根据字符串参数创建指定的代码沙箱实例)
 */
public class CodeSandboxFactory {

    /**
     * 创建代码沙箱示例
     *
     * @param type 沙箱类型
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
