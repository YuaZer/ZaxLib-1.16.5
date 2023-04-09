package io.github.yuazer.zaxlib.Utils;

import com.pixelmonmod.pixelmon.api.battles.BattleType;
import com.pixelmonmod.pixelmon.api.battles.attack.AttackRegistry;
import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import com.pixelmonmod.pixelmon.api.pokemon.Nature;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelmonmod.pixelmon.battles.api.rules.BattleRules;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.TrainerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import com.pixelmonmod.pixelmon.entities.npcs.NPCEntity;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static io.github.yuazer.zaxlib.Utils.NMSUtils.bkToNmsWorld;

public class PokeUtils {
    private static PokeUtils utils;

    public static PokeUtils getUtils() {
        return utils;
    }

    public static LivingEntity getLivingEntity(int entityid) {
        LivingEntity le = null;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof LivingEntity && entity.getEntityId() == entityid) {
                    le = (LivingEntity) entity;
                }
            }
        }
        return le;
    }

    public static void setNPCTrainerInFile_NBT(NPCTrainer trainer, File file) throws IOException {
        CompoundNBT nbt = new CompoundNBT();
        trainer.func_213281_b(nbt);
        CompressedStreamTools.func_74795_b(nbt, file);
    }

    public static NPCTrainer getNPCTrainerInFile_NBT(File file) throws IOException {
        NPCTrainer npcTrainer = new NPCTrainer(NMSUtils.bkToNmsWorld(Bukkit.getWorld("world")));
        CompoundNBT nbt = CompressedStreamTools.func_74797_a(file);
        npcTrainer.func_70037_a(nbt);
        return npcTrainer;
    }

    //发起宝可梦与NPC的单打对战
    public static void PlayerBattleNPCTrainer(Player player, NPCTrainer npcTrainer,int num) {
        BattleParticipant[] bp =
                {
                        new PlayerParticipant(PlayerUtil.getServerPlayerEntity(player),
                                StorageProxy.getParty(player.getUniqueId()).getAndSendOutFirstAblePokemon(PlayerUtil.getServerPlayerEntity(player)))
                };
        BattleParticipant[] tp = {new TrainerParticipant(npcTrainer,num)};
        BattleRegistry.startBattle(tp, bp, npcTrainer.battleRules);
    }

    //将宝可梦存为NBT文件
    public static void setPokemonInFile_NBT(Pokemon pokemon, File file) throws IOException {
        CompoundNBT nbt = new CompoundNBT();
        pokemon.setUUID(UUID.randomUUID());
        pokemon.writeToNBT(nbt);
        CompressedStreamTools.func_74795_b(nbt, file);
    }

    //从文件中的NBT获取宝可梦
    public static Pokemon getPokemonInFile_NBT(File file) throws IOException {
        Pokemon pokemon = PokemonFactory.create(CompressedStreamTools.func_74797_a(file));
        return pokemon;
    }

    //发起玩家和指定List<Pokemon>的单打对战(NPCTrainer)
    public static void battlePokemon(Player player, List<Pokemon> pokemons) {
        NPCTrainer npcTrainer = new NPCTrainer(NMSUtils.bkToNmsWorld(player.getWorld()));
        for (int i = 0; i <= pokemons.size() - 1; i++) {
            npcTrainer.getPokemonStorage().set(i, pokemons.get(i));
        }
        BattleParticipant[] bp =
                {
                        new PlayerParticipant(PlayerUtil.getServerPlayerEntity(player),
                                StorageProxy.getParty(player.getUniqueId()).getAndSendOutFirstAblePokemon(PlayerUtil.getServerPlayerEntity(player)))
                };
        BattleParticipant[] tp = {new TrainerParticipant(npcTrainer, 1)};
        //tp.startBattle(bp.bc);
        BattleRegistry.startBattle(tp, bp, new BattleRules());
    }

    public static ServerPlayerEntity getServerPlayerEntity(Player player) {
        UUID playerUUID = player.getUniqueId();
        return playerUUID == null ? null : ServerLifecycleHooks.getCurrentServer().func_184103_al().func_177451_a(playerUUID);
    }

    //发起玩家和指定精灵的单打对战(Wild)
    public static void battlePokemon_Wild(Player player, File file) {
        try {
            Pokemon pokemon = getPokemonInFile_NBT(file);
            pokemon.setUUID(UUID.randomUUID());
//            BattleParticipant[] bp = new PlayerParticipant[]{new PlayerParticipant(PlayerUtil.getServerPlayerEntity(player), pokemon.getOrSpawnPixelmon
//                    (bkToNmsWorld(player.getWorld()), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))};
            BattleParticipant[] bp = {
                    new PlayerParticipant(PlayerUtil.getServerPlayerEntity(player),
                            StorageProxy.getParty(player.getUniqueId()).getAndSendOutFirstAblePokemon(PlayerUtil.getServerPlayerEntity(player)))};
            BattleParticipant[] tp = {new WildPixelmonParticipant(pokemon.getOrSpawnPixelmon(bkToNmsWorld(player.getWorld()), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()))};
            BattleRegistry.startBattle(tp, bp, new BattleRules());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LivingEntity getPokemonLivingEntity(Pokemon pokemon) {
        org.bukkit.entity.LivingEntity le = null;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof org.bukkit.entity.LivingEntity && entity.getEntityId() == pokemon.getEntityID()) {
                    le = (LivingEntity) entity;
                }
            }
        }
        return le;
    }

    public Attack getMoveCN(String moveName) {
        for (ImmutableAttack attack : AttackRegistry.getAllAttacks()) {
            if (attack.getLocalizedName().equalsIgnoreCase(moveName)) {
                return attack.ofMutable();
            }
        }
        return AttackRegistry.ACID.get().ofMutable();
    }

    public Nature getNatureCN(String natureName) {
        for (Nature nature : Nature.values()) {
            if (nature.getLocalizedName().equalsIgnoreCase(natureName)) {
                return nature;
            }
        }
        return Nature.CALM;
    }
}
