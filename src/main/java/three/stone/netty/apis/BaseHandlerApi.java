package three.stone.netty.apis;

import three.stone.netty.RpcContext;
import three.stone.netty.annotations.DefaultBoolean;
import three.stone.netty.annotations.DefaultInteger;
import three.stone.netty.annotations.DefaultLong;
import three.stone.netty.annotations.DefaultNull;
import three.stone.netty.annotations.DefaultString;
import three.stone.netty.annotations.Name;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public interface BaseHandlerApi {

    @Name("echo")
    CompletionStage<Map<String, Object>> echo(
            RpcContext ctx,
            @Name("s") String s);

    @Name("show_api")
    CompletionStage<Map<String, Object>> showApi(
            RpcContext ctx,
            @Name("show_default_null") @DefaultNull List<String> showDefaultNull,
            @Name("show_default_boolean") @DefaultBoolean(false) Boolean showDefaultBoolean,
            @Name("show_default_integer") @DefaultInteger(10) Integer showDefaultInteger,
            @Name("show_default_long") @DefaultLong(20L) Long showDefaultLong,
            @Name("show_default_string") @DefaultString("echo") String showDefaultString);
}
