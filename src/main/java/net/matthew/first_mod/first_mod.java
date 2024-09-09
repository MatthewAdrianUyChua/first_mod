package net.matthew.first_mod;

import com.mojang.logging.LogUtils;
import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.block.entity.ModBlockEntities;
import net.matthew.first_mod.block.entity.ModEntities;
import net.matthew.first_mod.block.entity.client.entity.CatRenderer;
import net.matthew.first_mod.block.renderer.Display_Item_Block_Renderer;
import net.matthew.first_mod.block.renderer.Display_Item_Block_Renderer_Plus1;
import net.matthew.first_mod.block.renderer.Display_Item_Block_Renderer_Plus4;
import net.matthew.first_mod.item.ModCreativeModeTabs;
import net.matthew.first_mod.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(first_mod.MODID)
public class first_mod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "first_mod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public first_mod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.CAT.get(), CatRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.DISPLAY_ITEM_BLOCK_ENTITY.get(), Display_Item_Block_Renderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.DISPLAY_ITEM_BLOCK_ENTITY_PLUS1.get(), Display_Item_Block_Renderer_Plus1::new);
            event.registerBlockEntityRenderer(ModBlockEntities.DISPLAY_ITEM_BLOCK_ENTITY_PLUS4.get(), Display_Item_Block_Renderer_Plus4::new);
        }
    }
}
