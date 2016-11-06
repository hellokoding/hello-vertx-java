package com.hellokoding.hellovertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HelloVertx extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx
                .createHttpServer()
                .requestHandler(httpServerRequest -> {
                    httpServerRequest.response().end("<h1>Hello, World!</h1>");
                })
                .listen(8080, httpServerAsyncResult -> {
                    if (httpServerAsyncResult.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(httpServerAsyncResult.cause());
                    }
                });
    }
}
