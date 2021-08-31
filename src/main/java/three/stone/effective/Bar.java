package three.stone.effective;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import three.stone.effective.creat_object.Car;
import three.stone.effective.creat_object.MoveApi;

public class Bar extends Foo<Bar>{
    @Override
    Bar deepCopy() {
        return new Bar();
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer();
        Car car = MoveApi.synchronizedCar();
        car.forward();
    }
}
