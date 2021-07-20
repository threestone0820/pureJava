package three.stone.effective.dynamic_proxy;

public class RealSubject implements Subject{
    @Override
    public String getName(String city) {
        return city;
    }

}
