package three.stone.netty.apis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.netty.RpcContext;
import three.stone.netty.annotations.Defaulter;
import three.stone.netty.annotations.Name;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class ApiMapper {
    private Logger logger = LogManager.getLogger();

    private Map<String, ApiMethodInfo> map;

    public ApiMapper() {
        map = new HashMap<>();
    }

    public ApiMethodInfo getMethodInfo(String apiName) {
        return map.get(apiName);
    }

    public void addApi(Class<?> apiClazz) {
        Method[] methods = apiClazz.getMethods();
        for (Method method : methods) {
            Name aName = method.getAnnotation(Name.class);
            if (null == aName) {
                throw new RuntimeException("Api must has @Name, " + method.getName());
            }
            String apiName = aName.value();
            if (map.containsKey(apiName)) {
                throw new RuntimeException("Duplicate api name, " + method.getName());
            }

            ApiMethodInfo methodInfo = new ApiMethodInfo();
            methodInfo.setMethod(method);
            methodInfo.setApiName(apiName);
            map.put(apiName, methodInfo);

            Parameter[] parameters = method.getParameters();
            if (parameters.length < 1 ||
                    !parameters[0].getType().isAssignableFrom(RpcContext.class)) {
                throw new RuntimeException("First parameter must be RpcContext, " + method.getName());
            }

            String[] argNames = new String[parameters.length];
            Defaulter[] argDefaulters = new Defaulter[parameters.length];
            for (int i = 1; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                for (Annotation pa : parameter.getAnnotations()) {
                    if (pa instanceof Name) {
                        argNames[i] = ((Name) pa).value();
                    } else if (Defaulter.isDefaultAnnotation(pa)) {
                        argDefaulters[i] = new Defaulter(pa);
                    } else {
                        logger.fatal("unknown annotation for method:{} argument:{} annotation:{}",
                                method.getName(), parameters[i].getName(), pa.getClass().getName());
                    }
                }
                if (argNames[i] == null) {
                    logger.fatal("Api param must have @Name annotation, method:{} argument:{}",
                            method.getName(), parameters[i].getName());
                }
            }
            methodInfo.setArgNames(argNames);
            methodInfo.setArgTypes(method.getGenericParameterTypes());
            methodInfo.setArgDefaulters(argDefaulters);
        }
    }
}
