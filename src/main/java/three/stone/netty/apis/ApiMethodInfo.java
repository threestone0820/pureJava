package three.stone.netty.apis;

import three.stone.netty.annotations.Defaulter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ApiMethodInfo {
    private Method method;
    private String apiName;
    private String[] argNames;
    private Type[] argTypes;
    private Defaulter[] argDefaulters;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String[] getArgNames() {
        return argNames;
    }

    public void setArgNames(String[] argNames) {
        this.argNames = argNames;
    }

    public Type[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(Type[] argTypes) {
        this.argTypes = argTypes;
    }

    public Defaulter[] getArgDefaulters() {
        return argDefaulters;
    }

    public void setArgDefaulters(Defaulter[] argDefaulters) {
        this.argDefaulters = argDefaulters;
    }
}
