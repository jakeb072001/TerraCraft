package terramine.common.network.packet;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import terramine.common.utility.InputHandler;

import java.util.List;

public class UpdateInputPacket {
    private final boolean jump, attack, shift, forwards, backwards, left, right;

    public UpdateInputPacket(List<Boolean> list) {
        this.jump = list.get(0);
        this.attack = list.get(1);
        this.shift = list.get(2);
        this.forwards = list.get(3);
        this.backwards = list.get(4);
        this.left = list.get(5);
        this.right = list.get(6);
    }

    public static UpdateInputPacket read(List<Boolean> list) {
        return new UpdateInputPacket(list);
    }

    public static void write(UpdateInputPacket message, List<Boolean> list) {
        list.add(message.jump);
        list.add(message.attack);
        list.add(message.shift);
        list.add(message.forwards);
        list.add(message.backwards);
        list.add(message.left);
        list.add(message.right);
    }

    public static void onMessage(UpdateInputPacket message, MinecraftServer server, ServerPlayer player) {
        server.execute(() -> {
            if (player != null) {
                InputHandler.update(player, message.jump, message.attack, message.shift, message.forwards, message.backwards, message.left, message.right);
            }
        });
    }
}
