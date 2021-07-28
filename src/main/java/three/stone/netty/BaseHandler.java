package three.stone.netty;

import com.google.common.collect.ImmutableMap;
import three.stone.base.Maps;
import three.stone.netty.apis.ApiMapper;
import three.stone.netty.apis.BaseHandlerApi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public abstract class BaseHandler implements BaseHandlerApi {
    private ApiMapper mapper;

    private void prepareApiMethods() {
        mapper = new ApiMapper();
        mapper.addApi(this.getClass());
    }

    @Override
    public CompletionStage<Map<String, Object>> echo(RpcContext ctx, String s) {
        return CompletableFuture.completedFuture(ImmutableMap.of("result", s));
    }

    @Override
    public CompletionStage<Map<String, Object>> showApi(RpcContext ctx,
                                                        List<String> showDefaultNull,
                                                        Boolean showDefaultBoolean,
                                                        Integer showDefaultInteger,
                                                        Long showDefaultLong,
                                                        String showDefaultString) {
        Map<String, Object> result = Maps.construct(
                "default_null", showDefaultBoolean,
                "default_boolean", showDefaultBoolean,
                "default_integer", showDefaultInteger,
                "default_long", showDefaultLong,
                "default_string", showDefaultString
        );
        return CompletableFuture.completedFuture(result);
    }
}
