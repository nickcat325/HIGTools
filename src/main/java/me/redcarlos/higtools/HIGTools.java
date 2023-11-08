package me.redcarlos.higtools;

import me.redcarlos.higtools.commands.Center;
import me.redcarlos.higtools.commands.Coordinates;
import me.redcarlos.higtools.commands.Disconnect;
import me.redcarlos.higtools.modules.borers.*;
import me.redcarlos.higtools.modules.hud.BindsHud;
import me.redcarlos.higtools.modules.hud.GreetingsHud;
import me.redcarlos.higtools.modules.main.*;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.Systems;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.misc.BetterChat;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HIGTools extends MeteorAddon {
    private static final ModMetadata METADATA = FabricLoader.getInstance().getModContainer("higtools").orElseThrow(() -> new RuntimeException("HIGTools mod container not found!")).getMetadata();
    public static final String VERSION = METADATA.getVersion().toString();
    public static final Logger LOG = LoggerFactory.getLogger("HIGTools");
    public static final Category Main = new Category("HIG Tools", Items.NETHERITE_PICKAXE.getDefaultStack());
    public static final Category Borers = new Category(" Borers ", Items.NETHERITE_PICKAXE.getDefaultStack());
    public static final HudGroup Hud = new HudGroup("HIG Tools");

    @Override
    public void onInitialize() {
        LOG.info("Initializing HIGTools %s".formatted(HIGTools.VERSION));

        BetterChat.registerCustomHead("[HIGTools]", new Identifier("higtools", "chat/icon.png"));

        // Commands
        Commands.add(new Center());
        Commands.add(new Coordinates());
        Commands.add(new Disconnect());

        // HUD
        Hud hud = Systems.get(Hud.class);
        hud.register(BindsHud.INFO);
        hud.register(GreetingsHud.INFO);

        // Modules
        Modules modules = Modules.get();

        modules.add(new AfkLogout());
        modules.add(new ArmorNotify());
        modules.add(new AutoCenter());
        modules.add(new AxisViewer());
        modules.add(new DiscordRPC());
        modules.add(new HandManager());
        modules.add(new HighwayBuilderPlus());
        modules.add(new HighwayTools());
        modules.add(new HIGAutoWalk());
        modules.add(new HIGPrefix());
        modules.add(new InvManager());
        modules.add(new ScaffoldPlus());

        modules.add(new AxisBorer());
        modules.add(new NegNegBorer());
        modules.add(new NegPosBorer());
        modules.add(new PosNegBorer());
        modules.add(new PosPosBorer());
        modules.add(new RingRoadBorer());
    }

    @Override
    public String getPackage() {
        return "me.redcarlos.higtools";
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(Main);
        Modules.registerCategory(Borers);
    }
}
