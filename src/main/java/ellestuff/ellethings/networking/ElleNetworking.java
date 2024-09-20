package ellestuff.ellethings.networking;

import ellestuff.ellethings.ElleThings;
import ellestuff.ellethings.items.RedstoneTransformerItem;
import io.netty.channel.ChannelHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class ElleNetworking {
    public static final Identifier TRANSFORMER_ITEM_PACKET_ID = Identifier.of(ElleThings.MODID, "transformer_item");

    public static void registerEllePackets() {
        ServerPlayNetworking.registerGlobalReceiver(TRANSFORMER_ITEM_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            int power = buf.readByte();

            server.execute(() -> {
                RedstoneTransformerItem.setPower(player.getStackInHand(Hand.MAIN_HAND), power);
            });
        });
    }
}
