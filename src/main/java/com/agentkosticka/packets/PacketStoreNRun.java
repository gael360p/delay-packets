package com.agentkosticka;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PacketDelayMod implements ModInitializer {

    public static final String MOD_ID = "packetdelay";
    public static final Logger LOGGER = LoggerFactory.getLogger("packetdelay");

    // Store intercepted client->server packets
    private static List<Packet<?>> interceptedC2SPackets = new ArrayList<>();

    // Store intercepted server->client packets
    private static List<Packet<?>> interceptedS2CPackets = new ArrayList<>();

    @Override
    public void onInitialize() {
        LOGGER.info("PacketDelayMod initialized for Minecraft 1.21.4 Fabric!");

        // Example: you can register a tick event or command to release packets here
        // This is where you'd hook your logic to intercept and release packets
        // For brevity, that logic is omitted here — add as needed
    }

    // Call this to send all intercepted client->server packets
    public static void releaseC2SPackets(MinecraftClient client) {
        if (interceptedC2SPackets.isEmpty()) {
            return;
        }
        for (Packet<?> packet : interceptedC2SPackets) {
            sendC2SPacket(packet, client);
        }
        interceptedC2SPackets = new ArrayList<>();
    }

    public static void sendC2SPacket(Packet<?> packet, MinecraftClient client){
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if(networkHandler == null) {
            sendMessage("Error sending C2S packet: networkHandler is null");
            return;
        }
        try {
            networkHandler.sendPacket(packet);
        } catch (Exception e) {
            sendMessage("Exception sending C2S packet: " + e);
        }
    }

    // Call this to send all intercepted server->client packets
    public static void releaseS2CPackets(MinecraftClient client){
        if(interceptedS2CPackets.isEmpty()){
            return;
        }
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if (networkHandler == null){
            return;
        }
        for (Packet<?> packet: interceptedS2CPackets) {
            sendS2CPacket(packet, client);
        }
        interceptedS2CPackets = new ArrayList<>();
    }

    public static void sendS2CPacket(Packet<?> packet, MinecraftClient client) {
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if(networkHandler == null) {
            sendMessage("Error sending S2C packet: networkHandler is null");
            return;
        }

        // Handle packets by their types as needed
        // This is based on Minecraft 1.21.4 packet handlers
        try {
            if(packet instanceof GameJoinS2CPacket){
                networkHandler.onGameJoin((GameJoinS2CPacket) packet);
            } else if(packet instanceof EntitySpawnS2CPacket){
                networkHandler.onEntitySpawn((EntitySpawnS2CPacket) packet);
            } else if(packet instanceof ExperienceOrbSpawnS2CPacket){
                networkHandler.onExperienceOrbSpawn((ExperienceOrbSpawnS2CPacket) packet);
            } else if(packet instanceof EntityVelocityUpdateS2CPacket){
                networkHandler.onEntityVelocityUpdate((EntityVelocityUpdateS2CPacket) packet);
            } else if(packet instanceof EntityTrackerUpdateS2CPacket){
                networkHandler.onEntityTrackerUpdate((EntityTrackerUpdateS2CPacket) packet);
            } else if(packet instanceof PlayerSpawnS2CPacket){
                networkHandler.onPlayerSpawn((PlayerSpawnS2CPacket) packet);
            } else if(packet instanceof EntityPositionS2CPacket){
                networkHandler.onEntityPosition((EntityPositionS2CPacket) packet);
            } else if(packet instanceof UpdateSelectedSlotS2CPacket){
                networkHandler.onUpdateSelectedSlot((UpdateSelectedSlotS2CPacket) packet);
            } else if(packet instanceof EntityS2CPacket){
                networkHandler.onEntity((EntityS2CPacket) packet);
            } else if(packet instanceof EntitySetHeadYawS2CPacket){
                networkHandler.onEntitySetHeadYaw((EntitySetHeadYawS2CPacket) packet);
            } else if(packet instanceof EntitiesDestroyS2CPacket){
                networkHandler.onEntitiesDestroy((EntitiesDestroyS2CPacket) packet);
            } else if(packet instanceof PlayerPositionLookS2CPacket){
                networkHandler.onPlayerPositionLook((PlayerPositionLookS2CPacket) packet);
            } else if(packet instanceof ChunkDeltaUpdateS2CPacket){
                networkHandler.onChunkDeltaUpdate((ChunkDeltaUpdateS2CPacket) packet);
            } else if(packet instanceof ChunkDataS2CPacket){
                networkHandler.onChunkData((ChunkDataS2CPacket) packet);
            } else if(packet instanceof ChunkBiomeDataS2CPacket){
                networkHandler.onChunkBiomeData((ChunkBiomeDataS2CPacket) packet);
            } else if(packet instanceof UnloadChunkS2CPacket){
                networkHandler.onUnloadChunk((UnloadChunkS2CPacket) packet);
            } else if(packet instanceof BlockUpdateS2CPacket){
                networkHandler.onBlockUpdate((BlockUpdateS2CPacket) packet);
            } else if(packet instanceof DisconnectS2CPacket){
                networkHandler.onDisconnect((DisconnectS2CPacket) packet);
            } else if(packet instanceof ItemPickupAnimationS2CPacket){
                networkHandler.onItemPickupAnimation((ItemPickupAnimationS2CPacket) packet);
            } else if(packet instanceof GameMessageS2CPacket){
                networkHandler.onGameMessage((GameMessageS2CPacket) packet);
            } else if(packet instanceof ChatMessageS2CPacket){
                networkHandler.onChatMessage((ChatMessageS2CPacket) packet);
            } else if(packet instanceof ProfilelessChatMessageS2CPacket){
                networkHandler.onProfilelessChatMessage((ProfilelessChatMessageS2CPacket) packet);
            } else if(packet instanceof RemoveMessageS2CPacket){
                networkHandler.onRemoveMessage((RemoveMessageS2CPacket) packet);
            } else if(packet instanceof EntityAnimationS2CPacket){
                networkHandler.onEntityAnimation((EntityAnimationS2CPacket) packet);
            } else if(packet instanceof DamageTiltS2CPacket){
                networkHandler.onDamageTilt((DamageTiltS2CPacket) packet);
            } else if(packet instanceof WorldTimeUpdateS2CPacket){
                networkHandler.onWorldTimeUpdate((WorldTimeUpdateS2CPacket) packet);
            } else if(packet instanceof PlayerSpawnPositionS2CPacket){
                networkHandler.onPlayerSpawnPosition((PlayerSpawnPositionS2CPacket) packet);
            } else if(packet instanceof EntityPassengersSetS2CPacket){
                networkHandler.onEntityPassengersSet((EntityPassengersSetS2CPacket) packet);
            } else if(packet instanceof EntityAttachS2CPacket){
                networkHandler.onEntityAttach((EntityAttachS2CPacket) packet);
            } else if(packet instanceof EntityStatusS2CPacket){
                networkHandler.onEntityStatus((EntityStatusS2CPacket) packet);
            } else if(packet instanceof EntityDamageS2CPacket){
                networkHandler.onEntityDamage((EntityDamageS2CPacket) packet);
            } else if(packet instanceof HealthUpdateS2CPacket){
                networkHandler.onHealthUpdate((HealthUpdateS2CPacket) packet);
            } else if(packet instanceof ExperienceBarUpdateS2CPacket){
                networkHandler.onExperienceBarUpdate((ExperienceBarUpdateS2CPacket) packet);
            } else if(packet instanceof PlayerRespawnS2CPacket){
                networkHandler.onPlayerRespawn((PlayerRespawnS2CPacket) packet);
            } else if(packet instanceof ExplosionS2CPacket){
                networkHandler.onExplosion((ExplosionS2CPacket) packet);
            } else if(packet instanceof OpenHorseScreenS2CPacket){
                networkHandler.onOpenHorseScreen((OpenHorseScreenS2CPacket) packet);
            } else if(packet instanceof OpenScreenS2CPacket){
                networkHandler.onOpenScreen((OpenScreenS2CPacket) packet);
            } else if(packet instanceof ScreenHandlerSlotUpdateS2CPacket){
                networkHandler.onScreenHandlerSlotUpdate((ScreenHandlerSlotUpdateS2CPacket) packet);
            } else if(packet instanceof InventoryS2CPacket){
                networkHandler.onInventory((InventoryS2CPacket) packet);
            } else if(packet instanceof SignEditorOpenS2CPacket){
                networkHandler.onSignEditorOpen((SignEditorOpenS2CPacket) packet);
            } else if(packet instanceof BlockEntityUpdateS2CPacket){
                networkHandler.onBlockEntityUpdate((BlockEntityUpdateS2CPacket) packet);
            } else if(packet instanceof ScreenHandlerPropertyUpdateS2CPacket){
                networkHandler.onScreenHandlerPropertyUpdate((ScreenHandlerPropertyUpdateS2CPacket) packet);
            } else if(packet instanceof EntityEquipmentUpdateS2CPacket){
                networkHandler.onEntityEquipmentUpdate((EntityEquipmentUpdateS2CPacket) packet);
            } else if(packet instanceof CloseScreenS2CPacket){
                networkHandler.onCloseScreen((CloseScreenS2CPacket) packet);
            } else if(packet instanceof BlockEventS2CPacket){
                networkHandler.onBlockEvent((BlockEventS2CPacket) packet);
            } else if(packet instanceof BlockBreakingProgressS2CPacket){
                networkHandler.onBlockBreakingProgress((BlockBreakingProgressS2CPacket) packet);
            } else if(packet instanceof GameStateChangeS2CPacket){
                networkHandler.onGameStateChange((GameStateChangeS2CPacket) packet);
            } else if(packet instanceof MapUpdateS2CPacket){
                networkHandler.onMapUpdate((MapUpdateS2CPacket) packet);
            } else if(packet instanceof WorldEventS2CPacket){
                networkHandler.onWorldEvent((WorldEventS2CPacket) packet);
            } else if(packet instanceof AdvancementUpdateS2CPacket){
                networkHandler.onAdvancements((AdvancementUpdateS2CPacket) packet);
            } else if(packet instanceof SelectAdvancementTabS2CPacket){
                networkHandler.onSelectAdvancementTab((SelectAdvancementTabS2CPacket) packet);
            } else if(packet instanceof CommandTreeS2CPacket){
                networkHandler.onCommandTree((CommandTreeS2CPacket) packet);
            } else if(packet instanceof StopSoundS2CPacket){
                networkHandler.onStopSound((StopSoundS2CPacket) packet);
            } else if(packet instanceof CommandSuggestionsS2CPacket){
                networkHandler.onCommandSuggestions((CommandSuggestionsS2CPacket) packet);
            } else if(packet instanceof SynchronizeRecipesS2CPacket){
                networkHandler.onSynchronizeRecipes((SynchronizeRecipesS2CPacket) packet);
            } else if(packet instanceof UnlockRecipesS2CPacket){
                networkHandler.onUnlockRecipes((UnlockRecipesS2CPacket) packet);
            } else if(packet instanceof LookAtS2CPacket){
                networkHandler.onLookAt((LookAtS2CPacket) packet);
            } else if(packet instanceof TagQueryResponseS2CPacket){
                networkHandler.onTagQueryResponse((TagQueryResponseS2CPacket) packet);
            } else if(packet instanceof NbtQueryResponseS2CPacket){
                networkHandler.onNbtQueryResponse((NbtQueryResponseS2CPacket) packet);
            } else if(packet instanceof StructureTemplateUpdateS2CPacket){
                networkHandler.onStructureTemplateUpdate((StructureTemplateUpdateS2CPacket) packet);
            } else if(packet instanceof SelectTradeS2CPacket){
                networkHandler.onSelectTrade((SelectTradeS2CPacket) packet);
            } else if(packet instanceof MerchantOffersS2CPacket){
                networkHandler.onMerchantOffers((MerchantOffersS2CPacket) packet);
            } else if(packet instanceof ExperienceUpdateS2CPacket){
                networkHandler.onExperienceUpdate((ExperienceUpdateS2CPacket) packet);
            } else if(packet instanceof EntitySoundEffectS2CPacket){
                networkHandler.onEntitySoundEffect((EntitySoundEffectS2CPacket) packet);
            } else if(packet instanceof SoundEffectS2CPacket){
                networkHandler.onSoundEffect((SoundEffectS2CPacket) packet);
            } else if(packet instanceof ParticleS2CPacket){
                networkHandler.onParticle((ParticleS2CPacket) packet);
            } else if(packet instanceof GameEventS2CPacket){
                networkHandler.onGameEvent((GameEventS2CPacket) packet);
            } else if(packet instanceof SynchronizeTagsS2CPacket){
                networkHandler.onSynchronizeTags((SynchronizeTagsS2CPacket) packet);
            } else if(packet instanceof SynchronizeCommandsS2CPacket){
                networkHandler.onSynchronizeCommands((SynchronizeCommandsS2CPacket) packet);
            } else if(packet instanceof ChangeDifficultyS2CPacket){
                networkHandler.onChangeDifficulty((ChangeDifficultyS2CPacket) packet);
            } else if(packet instanceof PlayerListS2CPacket){
                networkHandler.onPlayerList((PlayerListS2CPacket) packet);
            } else if(packet instanceof ScoreboardObjectiveUpdateS2CPacket){
                networkHandler.onScoreboardObjectiveUpdate((ScoreboardObjectiveUpdateS2CPacket) packet);
            } else if(packet instanceof ScoreboardScoreUpdateS2CPacket){
                networkHandler.onScoreboardScoreUpdate((ScoreboardScoreUpdateS2CPacket) packet);
            } else if(packet instanceof ScoreboardDisplayS2CPacket){
                networkHandler.onScoreboardDisplay((ScoreboardDisplayS2CPacket) packet);
            } else if(packet instanceof TeamS2CPacket){
                networkHandler.onTeam((TeamS2CPacket) packet);
            } else if(packet instanceof UpdateRecipesS2CPacket){
                networkHandler.onUpdateRecipes((UpdateRecipesS2CPacket) packet);
            } else if(packet instanceof UpdateTagsS2CPacket){
                networkHandler.onUpdateTags((UpdateTagsS2CPacket) packet);
            } else if(packet instanceof PrepareCraftingGridS2CPacket){
                networkHandler.onPrepareCraftingGrid((PrepareCraftingGridS2CPacket) packet);
            } else if(packet instanceof CraftingResultS2CPacket){
                networkHandler.onCraftingResult((CraftingResultS2CPacket) packet);
            } else if(packet instanceof TradeListS2CPacket){
                networkHandler.onTradeList((TradeListS2CPacket) packet);
            } else if(packet instanceof PlayerAbilitiesS2CPacket){
                networkHandler.onPlayerAbilities((PlayerAbilitiesS2CPacket) packet);
            } else if(packet instanceof PlayerLookAtS2CPacket){
                networkHandler.onPlayerLookAt((PlayerLookAtS2CPacket) packet);
            } else if(packet instanceof SynchronizePlayerPositionS2CPacket){
                networkHandler.onSynchronizePlayerPosition((SynchronizePlayerPositionS2CPacket) packet);
            } else if(packet instanceof VehicleMoveS2CPacket){
                networkHandler.onVehicleMove((VehicleMoveS2CPacket) packet);
            } else if(packet instanceof OpenBookS2CPacket){
                networkHandler.onOpenBook((OpenBookS2CPacket) packet);
            } else if(packet instanceof OpenWrittenBookS2CPacket){
                networkHandler.onOpenWrittenBook((OpenWrittenBookS2CPacket) packet);
            } else if(packet instanceof OpenCommandBlockS2CPacket){
                networkHandler.onOpenCommandBlock((OpenCommandBlockS2CPacket) packet);
            } else if(packet instanceof OpenMinecartCommandBlockS2CPacket){
                networkHandler.onOpenMinecartCommandBlock((OpenMinecartCommandBlockS2CPacket) packet);
            } else if(packet instanceof OpenJigsawBlockS2CPacket){
                networkHandler.onOpenJigsawBlock((OpenJigsawBlockS2CPacket) packet);
            } else if(packet instanceof CustomPayloadS2CPacket){
                networkHandler.onCustomPayload((CustomPayloadS2CPacket) packet);
            } else if(packet instanceof DisconnectS2CPacket){
                networkHandler.onDisconnect((DisconnectS2CPacket) packet);
            } else {
                sendMessage("Unhandled S2C packet: " + packet.getClass().getSimpleName());
            }
        } catch (Exception e){
            sendMessage("Exception while handling S2C packet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void sendMessage(String message){
        MinecraftClient client = MinecraftClient.getInstance();
        if(client != null && client.player != null){
            client.player.sendMessage(new net.minecraft.network.message.TextMessage(message), false);
        }
        LOGGER.info(message);
    }

    // Add your intercept logic here to catch packets and add to intercepted lists as needed
    // For example, listen to packet events and add them to these lists instead of sending immediately
}

