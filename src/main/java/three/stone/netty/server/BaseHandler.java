package three.stone.netty.server;

import three.stone.netty.RpcContext;
import three.stone.netty.apis.BaseHandlerApi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public abstract class BaseHandler implements BaseHandlerApi {
    @Override
    public CompletionStage<Map<String, Object>> echo(RpcContext ctx, String s) {
        return null;
    }

    @Override
    public CompletionStage<Map<String, Object>> showApi(RpcContext ctx, List<String> showDefaultNull, Boolean showDefaultBoolean, Integer showDefaultInteger, Long showDefaultLong, String showDefaultString) {
        return null;
    }
}
