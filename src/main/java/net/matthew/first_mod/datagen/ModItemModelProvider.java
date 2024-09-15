package net.matthew.first_mod.datagen;

import net.matthew.first_mod.block.ModBlocks;
import net.matthew.first_mod.first_mod;
import net.matthew.first_mod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, first_mod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.DOUGH);
        simpleItem(ModItems.CHEF_HAT);
        simpleItem(ModItems.KNEADED_DOUGH);


        writeJsonFileItem("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + ModItems.SKEWER_ITEM.getId().getPath() + ".json", "first_mod:block/" + ModBlocks.SKEWER.getId().getPath());
        writeJsonFileItem("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + ModItems.EGG_STEAK_SANDWICH.getId().getPath() + ".json", "first_mod:block/" + ModBlocks.EGG_STEAK_SANDWICH_BLOCK.getId().getPath());
        writeJsonFileItem("..\\src\\main\\resources\\assets\\first_mod\\models\\item\\" + ModItems.EGG_SANDWICH.getId().getPath() + ".json", "first_mod:block/" + ModBlocks.EGG_SANDWICH_BLOCK.getId().getPath());

        withExistingParent(ModItems.CAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(first_mod.MODID, "item/" + item.getId().getPath()));
    }

    public static void writeJsonFile(String filePath, String parent) {
        // Creating the JSON structure
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("parent", parent);

        JsonObject displayObject = new JsonObject();

        // thirdperson view
        JsonObject thirdPersonObject = new JsonObject();
        thirdPersonObject.add("rotation", createJsonArray(0, 90, -35));
        thirdPersonObject.add("translation", createJsonArray(0, 1.25, -3.5));
        thirdPersonObject.add("scale", createJsonArray(0.85, 0.85, 0.85));

        // firstperson view
        JsonObject firstPersonObject = new JsonObject();
        firstPersonObject.add("rotation", createJsonArray(0, -135, 25));
        firstPersonObject.add("translation", createJsonArray(0, 4, 2));
        firstPersonObject.add("scale", createJsonArray(1.7, 1.7, 1.7));

        // gui view
        JsonObject guiObject = new JsonObject();
        guiObject.add("rotation", createJsonArray(30, 225, 0));
        guiObject.add("translation", createJsonArray(0, 0, 0));
        guiObject.add("scale", createJsonArray(0.625, 0.625, 0.625));

        // fixed view
        JsonObject fixedObject = new JsonObject();
        fixedObject.add("rotation", createJsonArray(0, 180, 0));
        fixedObject.add("translation", createJsonArray(0, 0, 0));
        fixedObject.add("scale", createJsonArray(0.5, 0.5, 0.5));

        // ground view
        JsonObject groundObject = new JsonObject();
        groundObject.add("rotation", createJsonArray(0, 0, 0));
        groundObject.add("translation", createJsonArray(0, 3, 0));
        groundObject.add("scale", createJsonArray(0.5, 0.5, 0.5));

        // Adding all views to the display object
        displayObject.add("thirdperson", thirdPersonObject);
        displayObject.add("firstperson", firstPersonObject);
        displayObject.add("gui", guiObject);
        displayObject.add("fixed", fixedObject);
        displayObject.add("ground", groundObject);

        // Adding the display object to the root JSON object
        rootObject.add("display", displayObject);

        // Converting the JSON object to a formatted string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(rootObject);

        // Writing the JSON string to a file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
            System.out.println("JSON file created successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJsonFileItem(String filePath, String parent) {
        // Creating the JSON structure
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("parent", parent);

        JsonObject displayObject = new JsonObject();

        // thirdperson view
        JsonObject thirdPersonObject = new JsonObject();
        thirdPersonObject.add("rotation", createJsonArray(0, 56.0, 0));
        thirdPersonObject.add("translation", createJsonArray(0, 11.75, 0));
        thirdPersonObject.add("scale", createJsonArray(0.85, 0.85, 0.85));

        // firstperson view
        JsonObject firstPersonObject = new JsonObject();
        firstPersonObject.add("rotation", createJsonArray(0, 56.0, 0));
        firstPersonObject.add("translation", createJsonArray(0, 11.75, 0));
        firstPersonObject.add("scale", createJsonArray(1.7, 1.7, 1.7));

        // gui view
        JsonObject guiObject = new JsonObject();
        guiObject.add("rotation", createJsonArray(30, 225, 0));
        guiObject.add("translation", createJsonArray(0, 0, 0));
        guiObject.add("scale", createJsonArray(0.625, 0.625, 0.625));

        // fixed view
        JsonObject fixedObject = new JsonObject();
        fixedObject.add("rotation", createJsonArray(0, 180, 0));
        fixedObject.add("translation", createJsonArray(0, 0, 0));
        fixedObject.add("scale", createJsonArray(0.5, 0.5, 0.5));

        // ground view
        JsonObject groundObject = new JsonObject();
        groundObject.add("rotation", createJsonArray(0, 0, 0));
        groundObject.add("translation", createJsonArray(0, 3, 0));
        groundObject.add("scale", createJsonArray(0.5, 0.5, 0.5));

        // Adding all views to the display object
        displayObject.add("thirdperson_righthand", thirdPersonObject);
        displayObject.add("firstperson_righthand", firstPersonObject);
        displayObject.add("gui", guiObject);
        displayObject.add("fixed", fixedObject);
        displayObject.add("ground", groundObject);

        // Adding the display object to the root JSON object
        rootObject.add("display", displayObject);

        // Converting the JSON object to a formatted string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(rootObject);

        // Writing the JSON string to a file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
            System.out.println("JSON file created successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to create a JsonArray from given values
    private static JsonArray createJsonArray(double... values) {
        JsonArray jsonArray = new JsonArray();
        for (double value : values) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

}
