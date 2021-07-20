package three.stone.effective;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

public class Bar extends Foo<Bar>{
    @Override
    Bar deepCopy() {
        return new Bar();
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer();
    }
}
