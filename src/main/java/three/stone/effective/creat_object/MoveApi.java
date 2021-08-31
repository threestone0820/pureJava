package three.stone.effective.creat_object;

public interface MoveApi {
    static Car synchronizedCar() {
        return new SynchronizedCar();
    }
}
