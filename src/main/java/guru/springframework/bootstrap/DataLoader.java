package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    final private CategoryRepository categoryRepository;
    final private UnitOfMeasureRepository unitOfMeasureRepository;
    final private RecipeRepository recipeRepository;

    private Map<String, Optional<Category>> categoryMap = new HashMap();
    private Map<String,Optional<UnitOfMeasure>> unitOfMeasureMap = new HashMap<>();

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> loadRecipies() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(chickenTacoLoader());
        recipes.add(perfectGuacamoleLoader());
        return recipes;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        setUpUomMap();
        setUpCategoryMap();
        recipeRepository.saveAll(loadRecipies());
    }


    private void setUpCategoryMap() {

        categoryMap.put("American",categoryRepository.findCategoryByCategoryName("American"));
        categoryMap.put("Italian",categoryRepository.findCategoryByCategoryName("Italian"));
        categoryMap.put("Mexican",categoryRepository.findCategoryByCategoryName("Mexican"));
        categoryMap.put("Fast Food",categoryRepository.findCategoryByCategoryName("Fast Food"));

        categoryMap.values().forEach(oCategory->{
            if(!oCategory.isPresent())
                throw new RuntimeException("Expected Category Not Found");
        });

    }

    private void setUpUomMap() {

        unitOfMeasureMap.put("Tablespoon",unitOfMeasureRepository.findByUom("Tablespoon"));
        unitOfMeasureMap.put("Teaspoon",unitOfMeasureRepository.findByUom("Teaspoon"));
        unitOfMeasureMap.put("Cup",unitOfMeasureRepository.findByUom("Cup"));
        unitOfMeasureMap.put("Pint",unitOfMeasureRepository.findByUom("Pint"));
        unitOfMeasureMap.put("Pinch",unitOfMeasureRepository.findByUom("Pinch"));
        unitOfMeasureMap.put("Ounce",unitOfMeasureRepository.findByUom("Ounce"));
        unitOfMeasureMap.put("Pound",unitOfMeasureRepository.findByUom("Pound"));
        unitOfMeasureMap.put("Dash",unitOfMeasureRepository.findByUom("Dash"));
        unitOfMeasureMap.put("Clove",unitOfMeasureRepository.findByUom("Clove"));
        unitOfMeasureMap.put("Small",unitOfMeasureRepository.findByUom("Small"));
        unitOfMeasureMap.put("Medium",unitOfMeasureRepository.findByUom("Medium"));
        unitOfMeasureMap.put("Large",unitOfMeasureRepository.findByUom("Large"));
        unitOfMeasureMap.put("Bowl",unitOfMeasureRepository.findByUom("Bowl"));
        unitOfMeasureMap.put("Each",unitOfMeasureRepository.findByUom("Each"));

        unitOfMeasureMap.values().forEach(ouom -> {
            if(!ouom.isPresent())
                throw new RuntimeException("Expected UoM not found");
        });

    }
    private Recipe chickenTacoLoader() {

        Recipe chickenTaco = new Recipe();
        chickenTaco.addCategory(categoryMap.get("Mexican").get());
        chickenTaco.setDescription("Spicy Chicken Tacos");
        Notes note = new Notes();
        note.setRecipeNotes("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties. We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n " +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes! \n The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!\n");
//        chickenTaco.setNotes(note);
        chickenTaco.setCookTime(15);
        chickenTaco.setDifficulty(Difficulty.EASY);
        chickenTaco.setDirections("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)\n" +
                "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        chickenTaco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTaco.setPrepTime(20);
        chickenTaco.setServings(5);
        chickenTaco.setSource("simplyrecipes.com");

        Ingredient anchoChilliPowder = new Ingredient("ancho chilli powder", new BigDecimal(2),unitOfMeasureMap.get("Tablespoon").get());
        chickenTaco.addIngredient(anchoChilliPowder);

        Ingredient driedOrgano = new Ingredient("dried oregano", new BigDecimal(1),unitOfMeasureMap.get("Teaspoon").get());
        chickenTaco.addIngredient(driedOrgano);

        Ingredient cumin = new Ingredient("cumin", new BigDecimal(1),unitOfMeasureMap.get("Teaspoon").get());
        chickenTaco.addIngredient(cumin);

        Ingredient sugar = new Ingredient("sugar", new BigDecimal(1),unitOfMeasureMap.get("Teaspoon").get());
        chickenTaco.addIngredient(sugar);

        Ingredient salt = new Ingredient("salt", new BigDecimal("0.5"),unitOfMeasureMap.get("Teaspoon").get());
        chickenTaco.addIngredient(salt);

        Ingredient garlic = new Ingredient("garlic, finely chopped", new BigDecimal(1),unitOfMeasureMap.get("Clove").get());
        chickenTaco.addIngredient(garlic);

        Ingredient orangeZest = new Ingredient("finely grated orange zest", new BigDecimal(1),unitOfMeasureMap.get("Tablespoon").get());
        chickenTaco.addIngredient(orangeZest);

        Ingredient orangeJuice = new Ingredient("fresh-squeezed orange juice", new BigDecimal(3),unitOfMeasureMap.get("Tablespoon").get());
        chickenTaco.addIngredient(orangeJuice);

        Ingredient oliveOil = new Ingredient("olive oil", new BigDecimal(2),unitOfMeasureMap.get("Tablespoon").get());
        chickenTaco.addIngredient(oliveOil);

        Ingredient chickenThighs = new Ingredient("boneless chicken thighs", new BigDecimal("1.25"),unitOfMeasureMap.get("Pound").get());
        chickenTaco.addIngredient(chickenThighs);

        Ingredient cornTortilla = new Ingredient("corn tortillas", new BigDecimal(8),unitOfMeasureMap.get("Small").get());
        chickenTaco.addIngredient(cornTortilla);

        Ingredient babyArugula = new Ingredient("baby arugula (packed)", new BigDecimal(3),unitOfMeasureMap.get("Cup").get());
        chickenTaco.addIngredient(babyArugula);

        Ingredient avacodo = new Ingredient("ripe avacodo, sliced", new BigDecimal(2),unitOfMeasureMap.get("Medium").get());
        chickenTaco.addIngredient(avacodo);

        Ingredient radish = new Ingredient("radish, thinly sliced", new BigDecimal(4),unitOfMeasureMap.get("Each").get());
        chickenTaco.addIngredient(radish);

        Ingredient cherryTomato = new Ingredient("cherryTomato, halved", new BigDecimal("0.5"),unitOfMeasureMap.get("Pint").get());
        chickenTaco.addIngredient(cherryTomato);

        Ingredient redOnion = new Ingredient("red Onion, thinly sliced", new BigDecimal("0.25"),unitOfMeasureMap.get("Each").get());
        chickenTaco.addIngredient(redOnion);

        Ingredient cilantro = new Ingredient("roughly chopped cilantro", new BigDecimal("0.25"),unitOfMeasureMap.get("Each").get());
        chickenTaco.addIngredient(cilantro);

        Ingredient sourCream = new Ingredient("sour Cream thinned", new BigDecimal("0.5"),unitOfMeasureMap.get("Cup").get());
        chickenTaco.addIngredient(sourCream);

        Ingredient milk = new Ingredient("milk for thinning sour cream", new BigDecimal("0.25"),unitOfMeasureMap.get("Cup").get());
        chickenTaco.addIngredient(milk);

        Ingredient lime = new Ingredient("lime, cut into wedges", new BigDecimal(1),unitOfMeasureMap.get("Each").get());
        chickenTaco.addIngredient(lime);
        chickenTaco.setNotes(note);
        return chickenTaco;

    }


    public Recipe perfectGuacamoleLoader() {

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.addCategory(categoryMap.get("Mexican").get());
        perfectGuacamole.setDescription("Perfect Guacamole" );
        String guacNote = "The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade. \n" +
                "Guacamole! Did you know that over 2 billion pounds of avocados are consumed each year in the U.S.? (Google it.) That’s over 7 pounds per person. I’m guessing that most of those avocados go into what has become America’s favorite dip, guacamole.\n" +
                "Where Does Guacamole Come From?\n" +
                "\n" +
                "The word “guacamole”, and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "Ingredients for Easy Guacamole\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "Guacamole Tip: Use Ripe Avocados\n" +
                "\n" +
                "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n" +
                "The Best Way to Cut an Avocado\n" +
                "\n" +
                "To slice open an avocado, cut it in half lengthwise with a sharp chef’s knife and twist apart the sides. One side will have the pit. To remove it, you can do one of two things:\n" +
                "\n" +
                "    Method #1: Gently tap the pit with your chef’s knife so the knife gets wedged into the pit. Twist your knife slightly to dislodge the pit and lift to remove. If you use this method, first protect your hand with a thick kitchen towel before proceeding.\n" +
                "    Method #2: Cut the side with the pit in half again, exposing more of the pit. Use your fingers or a spoon to remove the pit\n" +
                "\n" +
                "Once the pit is removed, just cut the avocado into chunks right inside the peel and use a spoon to scoop them out.\n" +
                "\n" +
                "by Elise Bauer\n" +
                "Save It Jump to Recipe\n" +
                "How to Make Perfect Guacamole\n" +
                "xxxxxyyyyy40201 comments\n" +
                "VideoDipAvocadoGuacamole\n" +
                "The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.\n" +
                "Easy guacamole served in a traditional mexican bowl\n" +
                "Photography Credit: Elise Bauer\n" +
                "\n" +
                "Guacamole! Did you know that over 2 billion pounds of avocados are consumed each year in the U.S.? (Google it.) That’s over 7 pounds per person. I’m guessing that most of those avocados go into what has become America’s favorite dip, guacamole.\n" +
                "Where Does Guacamole Come From?\n" +
                "\n" +
                "The word “guacamole”, and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "Watch our video on how to make guacamole!\n" +
                "\n" +
                "Guacamole\n" +
                "\n" +
                "Ingredients for Easy Guacamole\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "Guacamole Tip: Use Ripe Avocados\n" +
                "\n" +
                "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n" +
                "\n" +
                "Remove the pit from the avocado with a chef knife\n" +
                "The Best Way to Cut an Avocado\n" +
                "\n" +
                "To slice open an avocado, cut it in half lengthwise with a sharp chef’s knife and twist apart the sides. One side will have the pit. To remove it, you can do one of two things:\n" +
                "\n" +
                "    Method #1: Gently tap the pit with your chef’s knife so the knife gets wedged into the pit. Twist your knife slightly to dislodge the pit and lift to remove. If you use this method, first protect your hand with a thick kitchen towel before proceeding.\n" +
                "    Method #2: Cut the side with the pit in half again, exposing more of the pit. Use your fingers or a spoon to remove the pit\n" +
                "\n" +
                "Once the pit is removed, just cut the avocado into chunks right inside the peel and use a spoon to scoop them out.\n" +
                "\n" +
                "    Still curious? Read more about How to Cut and Peel an Avocado\n" +
                "\n" +
                "Homemade guacamole on a chip\n" +
                "Guacamole Variations\n" +
                "\n" +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!\n" +
                "\n" +
                "    Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "    Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "    Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "Other Ways to Use Guacamole\n" +
                "\n" +
                "Guacamole has a role in the kitchen beyond a party dip, of course. It’s great scooped on top of nachos and also makes an excellent topping or side for enchiladas, tacos, grilled salmon, or oven-baked chicken.\n" +
                "\n" +
                "Guacamole is great in foods, as well. Try mixing some guacamole into a tuna sandwich or your next batch of deviled eggs.\n" +
                "How to Store Guacamole\n" +
                "\n" +
                "Guacamole is best eaten right after it’s made. Like apples, avocados start to oxidize and turn brown once they’ve been cut. That said, the acid in the lime juice you add to guacamole can help slow down that process, and if you store the guacamole properly, you can easily make it a few hours ahead if you are preparing for a party.\n" +
                "\n" +
                "The trick to keeping guacamole green is to make sure air doesn’t touch it! Transfer it to a container, cover with plastic wrap, and press down on the plastic wrap to squeeze out any air pockets. Make sure any exposed surface of the guacamole is touching the plastic wrap, not air. This will keep the amount of browning to a minimum.\n" +
                "\n" +
                "You can store the guacamole in the fridge this way for up to three days.\n" +
                "\n" +
                "If you leave the guacamole exposed to air, it will start to brown and discolor. That browning isn’t very appetizing, but the guacamole is still good. You can either scrape off the brown parts and discard, or stir them into the rest of the guacamole.\n";
        String handlingChillies = "Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.\n";
        Notes note = new Notes();
        note.setRecipeNotes(handlingChillies+"\n"+guacNote);
        perfectGuacamole.setNotes(note);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(3);
        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
       perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Ingredient avacado = new Ingredient("ripe avocados", new BigDecimal(2),unitOfMeasureMap.get("Large").get());
        perfectGuacamole.addIngredient(avacado);

        Ingredient salt = new Ingredient("salt, more to tast", new BigDecimal("0.25"),unitOfMeasureMap.get("Teaspoon").get());
        perfectGuacamole.addIngredient(salt);

        Ingredient limeJuice = new Ingredient("fresh lime juice or (lemon can be substituted)", new BigDecimal(1),unitOfMeasureMap.get("Tablespoon").get());
        perfectGuacamole.addIngredient(limeJuice);

        Ingredient redOnion = new Ingredient("minced red onion or thinlysliced green onion", new BigDecimal("0.25"),unitOfMeasureMap.get("Cup").get());
        perfectGuacamole.addIngredient(redOnion);

        Ingredient serranoChillies = new Ingredient("serrano chilies, stems and seeds removed, minced", new BigDecimal(2),unitOfMeasureMap.get("Medium").get());
        perfectGuacamole.addIngredient(serranoChillies);

        Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2),unitOfMeasureMap.get("Tablespoon").get());
        perfectGuacamole.addIngredient(cilantro);

        Ingredient blackPepper = new Ingredient("freshly grated black pepper", new BigDecimal(1),unitOfMeasureMap.get("Dash").get());
        perfectGuacamole.addIngredient(blackPepper);

        Ingredient redTomato = new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal("0.5"),unitOfMeasureMap.get("Large").get());
        perfectGuacamole.addIngredient(redTomato);

        Ingredient radish = new Ingredient("red radishs or jicama, to garnish", new BigDecimal(3),unitOfMeasureMap.get("Large").get());
        perfectGuacamole.addIngredient(radish);

        Ingredient tortillaChips = new Ingredient("Tortilla chips, to serve", new BigDecimal(1),unitOfMeasureMap.get("Bowl").get());
        perfectGuacamole.addIngredient(tortillaChips);

        return perfectGuacamole;
    }

}
