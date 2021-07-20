package three.stone.effective.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {
    private Subject subject;

    public SubjectInvocationHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("start execute method, " + method.getName());
            Object result = method.invoke(subject, args);
            System.out.println("after execute method, " + method.getName());
            return result;
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }

    }
}
