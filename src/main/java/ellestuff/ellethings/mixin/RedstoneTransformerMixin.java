package ellestuff.ellethings.mixin;

import ellestuff.ellethings.blocks.ElleBlocks;
import ellestuff.ellethings.items.RedstoneTransformerItem;
import ellestuff.ellethings.networking.ElleNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class RedstoneTransformerMixin {

    @Shadow public abstract ItemStack getMainHandStack();
    @Shadow @Final public PlayerEntity player;

    @Inject(method = "scrollInHotbar", at = @At("HEAD"), cancellable = true)
    private void hotbarInject(double scrollAmount, CallbackInfo info) {
        if (this.getMainHandStack().isOf(ElleBlocks.TRANSFORMER_ITEM) && this.player.isSneaking()) {
            ItemStack stack = this.getMainHandStack();

            int i = (int)Math.signum(scrollAmount);

            RedstoneTransformerItem.setPower(stack, RedstoneTransformerItem.getPower(stack)+i);

            PacketByteBuf buffer = PacketByteBufs.create();

            buffer.writeByte(RedstoneTransformerItem.getPower(stack));

            ClientPlayNetworking.send(ElleNetworking.TRANSFORMER_ITEM_PACKET_ID, buffer);

            info.cancel();
        }
    }
}
