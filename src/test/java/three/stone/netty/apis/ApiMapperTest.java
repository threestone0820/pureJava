package three.stone.netty.apis;

import org.junit.Before;
import org.junit.Test;
import three.stone.netty.RpcContext;

public class ApiMapperTest {
    private ApiMapper mapper;

    @Before
    public void setup() {
        mapper = new ApiMapper();
        mapper.addApi(BaseHandlerApi.class);
    }

    @Test
    public void testMapper() {
        RpcContext context = new RpcContext();
        ApiMethodInfo showApiMethodInfo = mapper.getMethodInfo("show_api");
        ApiMethodInfo echoMethodInfo = mapper.getMethodInfo("echo");
    }

}