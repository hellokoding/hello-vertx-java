package com.hellokoding.hellovertx;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class HelloVertxTest {
    private Vertx vertx;

    @Before
    public void setUp(TestContext testContext){
        vertx = Vertx.vertx();
        vertx.deployVerticle(HelloVertx.class.getName(), testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void testHelloVertx(TestContext testContext) {
        final Async async = testContext.async();
        vertx.createHttpClient().getNow(8080, "localhost", "/", httpClientResponse -> {
            httpClientResponse.handler(buffer -> {
                testContext.assertTrue(buffer.toString().contains("Hello, World!"));
                async.complete();
            });
        });
    }
}
