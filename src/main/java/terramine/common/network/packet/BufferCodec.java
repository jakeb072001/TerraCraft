package terramine.common.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public interface BufferCodec {
    // todo: probably not correct. look into it
    StreamCodec<ByteBuf, FriendlyByteBuf> FRIENDLYBYTEBUF = new StreamCodec<>() {

        @Override
        public void encode(ByteBuf byteBuf, FriendlyByteBuf friendlyByteBuf) {
            byteBuf.readBytes(friendlyByteBuf);
        }

        @Override
        public @NotNull FriendlyByteBuf decode(ByteBuf byteBuf) {
            return new FriendlyByteBuf(Unpooled.buffer()).readBytes(byteBuf);
        }
    };
}
