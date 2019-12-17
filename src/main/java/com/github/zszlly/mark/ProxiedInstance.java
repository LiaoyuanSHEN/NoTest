package com.github.zszlly.mark;

import com.github.zszlly.recorder.proxy.NoTestInstanceProxy;

public interface ProxiedInstance {

    int getInstanceId();

    NoTestInstanceProxy getNoTestInstanceProxyOriginalInstance();

}
