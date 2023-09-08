package io.github.sullis.netty.playground;

import io.netty.buffer.ByteBufUtil;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class NettyLeakExtension
    implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterAllCallback {

    private final NettyLeakListener leakListener = new NettyLeakListener();

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        ByteBufUtil.setLeakListener(leakListener);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        leakListener.assertZeroLeaks();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        leakListener.assertZeroLeaks();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        leakListener.assertZeroLeaks();
    }
}
