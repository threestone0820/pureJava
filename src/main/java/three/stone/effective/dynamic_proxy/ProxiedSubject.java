package three.stone.effective.dynamic_proxy;

import java.lang.reflect.Proxy;

public class ProxiedSubject {
    public static void main(String[] args) throws CloneNotSupportedException {
        Subject subject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new SubjectInvocationHandler(new RealSubject()));
        System.out.println(subject.getName("Beijing"));
    }
}
