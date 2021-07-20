package three.stone.effective.dynamic_proxy.same_signature;

import java.lang.reflect.InvocationTargetException;

public class Main {
    private int pendingRp = 0;
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        Constructor<?> constructor = Proxy.getProxyClass(InterfaceA.class.getClassLoader(), new Class[] {InterfaceB.class, InterfaceA.class})
//                .getConstructor(InvocationHandler.class);
//        InvocationHandler h = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Class<?> declaringClass = method.getDeclaringClass();
//                System.out.println(declaringClass + ": " + method.getName());
//                throw new IllegalAccessException();
//            }
//        };
//        InterfaceA b = (InterfaceA)constructor.newInstance(h);
//        b.sameSignature("hello");
        Main main = new Main();
        for (int i = 0; i < 10000; i++) {
            main.test();
        }
    }

    private void test() throws InterruptedException {
        pendingRp++;
        try {
            Thread.sleep(1);
        } finally {
            pendingRp--;
        }
    }
}
