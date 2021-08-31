package three.stone.effective.creat_object;

class SynchronizedCar implements Car{
    @Override
    public void forward() {
        System.out.println("Synchronized Car");
    }
}
