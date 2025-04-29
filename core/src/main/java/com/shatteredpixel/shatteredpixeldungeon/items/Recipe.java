/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.recipe.AlchemyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.bossloot.SandBossLoot;
import com.shatteredpixel.shatteredpixeldungeon.items.recipe.ExoticRecipe;
import com.shatteredpixel.shatteredpixeldungeon.items.recipe.ScienceRecipe;
import com.shatteredpixel.shatteredpixeldungeon.items.recipe.StarRecipe;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.FireDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.GrassDust;
import com.shatteredpixel.shatteredpixeldungeon.items.star.dust.WaterDust;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Blandfruit;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MeatPie;
import com.shatteredpixel.shatteredpixeldungeon.items.food.StewedMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.AlchemyGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.FireGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.GrassGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WaterGem;
import com.shatteredpixel.shatteredpixeldungeon.items.star.gem.WeaponGem;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.AlchemicalCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.BlizzardBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.CausticBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.InfernalBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.ShockingBrew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfArcaneArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfDragonsBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfIcyTouch;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfToxicEssence;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Pickaxe;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Alchemize;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.AquaBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.ArcaneCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.BeaconOfReturning;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.CurseInfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.FeatherFall;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.MagicalInfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.MagicalPorter;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.PhaseShift;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.ReclaimTrap;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.Recycle;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.SummonElemental;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.TelekineticGrab;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.WildEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public abstract class Recipe {
	
	public abstract boolean testIngredients(ArrayList<Item> ingredients);
	
	public abstract int cost(ArrayList<Item> ingredients);
	
	public abstract Item brew(ArrayList<Item> ingredients);
	
	public abstract Item sampleOutput(ArrayList<Item> ingredients);
	
	//subclass for the common situation of a recipe with static inputs and outputs
	public static abstract class SimpleRecipe extends Recipe {
		
		//*** These elements must be filled in by subclasses
		protected Class<?extends Item>[] inputs; //each class should be unique
		protected int[] inQuantity;
		
		protected int cost;
		
		protected Class<?extends Item> output;
		protected int outQuantity;
		//***
		
		//gets a simple list of items based on inputs
		public ArrayList<Item> getIngredients() {
			ArrayList<Item> result = new ArrayList<>();
			for (int i = 0; i < inputs.length; i++) {
				Item ingredient = Reflection.newInstance(inputs[i]);
				ingredient.quantity(inQuantity[i]);
				result.add(ingredient);
			}
			return result;
		}
		
		@Override
		public final boolean testIngredients(ArrayList<Item> ingredients) {
			
			int[] needed = inQuantity.clone();
			
			for (Item ingredient : ingredients){
				if (!ingredient.isIdentified()) return false;
				for (int i = 0; i < inputs.length; i++){
					if (ingredient.getClass() == inputs[i]){
						needed[i] -= ingredient.quantity();
						break;
					}
				}
			}
			
			for (int i : needed){
				if (i > 0){
					return false;
				}
			}
			
			return true;
		}
		
		public final int cost(ArrayList<Item> ingredients){
			return cost;
		}
		
		@Override
		public final Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			int[] needed = inQuantity.clone();
			//TODO 部分物品在炼金完成后不消耗
			for (Item ingredient : ingredients){
				for (int i = 0; i < inputs.length; i++) {
					/*
					if(ingredient instanceof MeleeWeapon){
						if(((MeleeWeapon) ingredient).tier>=3){

						}
						GLog.i(Messages.get(SandBossLoot.class, "loot"));

					}else
					 */
					if (ingredient.getClass() == inputs[i] && needed[i] > 0) {
						if (needed[i] <= ingredient.quantity()) {
							ingredient.quantity(ingredient.quantity() - needed[i]);
							needed[i] = 0;
						} else {
							needed[i] -= ingredient.quantity();
							ingredient.quantity(0);
						}
					}
				}
			}
			
			//sample output and real output are identical in this case.
			return sampleOutput(null);
		}
		
		//ingredients are ignored, as output doesn't vary
		public final Item sampleOutput(ArrayList<Item> ingredients){
			try {
				Item result = Reflection.newInstance(output);
				//TODO 通过判断result所属来判定是否鉴定这个物品

				if(result instanceof AlchemyWeapon){
					result.identify();
				}

				if(outQuantity==0) {
					result.quantity(1);
				}else {
					result.quantity(outQuantity);
				}
				return result;
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException( e );
				return null;
			}
		}
	}
	
	
	//*******
	// Static members
	//*******

	private static Recipe[] variableRecipes = new Recipe[]{
			new LiquidMetal.Recipe()
	};
	
	private static Recipe[] oneIngredientRecipes = new Recipe[]{
		new Scroll.ScrollToStone(),
		new ExoticPotion.PotionToExotic(),
		new ExoticScroll.ScrollToExotic(),
		new ArcaneResin.Recipe(),
		new Alchemize.Recipe(),
		new StewedMeat.oneMeat(),
		new AlchemyGem.Recipe(),
		//碎晶转换为消耗品
		new FireGem.RedGemtoPotion(),
		new FireGem.RedGemtoScroll(),
		new GrassGem.GreenGemtoPotion(),
		new GrassGem.GreenGemtoScroll(),
		new WaterGem.BlueGemtoPotion(),
		new WaterGem.BlueGemtoScroll(),
		new FireDust.RedDusttoPotion(),
		new FireDust.RedDusttoScroll(),
		new GrassDust.GreenDusttoPotion(),
		new GrassDust.GreenDusttoScroll(),
		new WaterDust.BlueDusttoPotion(),
		new WaterDust.BlueDusttoScroll(),

	};
	
	private static Recipe[] twoIngredientRecipes = new Recipe[]{
		new Blandfruit.CookFruit(),
		new Bomb.EnhanceBomb(),
		new AlchemicalCatalyst.Recipe(),
		new ArcaneCatalyst.Recipe(),
		new ElixirOfArcaneArmor.Recipe(),
		new ElixirOfAquaticRejuvenation.Recipe(),
		new ElixirOfDragonsBlood.Recipe(),
		new ElixirOfIcyTouch.Recipe(),
		new ElixirOfMight.Recipe(),
		new ElixirOfHoneyedHealing.Recipe(),
		new ElixirOfToxicEssence.Recipe(),
		new BlizzardBrew.Recipe(),
		new InfernalBrew.Recipe(),
		new ShockingBrew.Recipe(),
		new CausticBrew.Recipe(),
		new AquaBlast.Recipe(),
		new BeaconOfReturning.Recipe(),
		new CurseInfusion.Recipe(),
		new FeatherFall.Recipe(),
		new MagicalInfusion.Recipe(),
		new MagicalPorter.Recipe(),
		new PhaseShift.Recipe(),
		new ReclaimTrap.Recipe(),
		new Recycle.Recipe(),
		new WildEnergy.Recipe(),
		new TelekineticGrab.Recipe(),
		new SummonElemental.Recipe(),
		new StewedMeat.twoMeat(),

		//炼金武器
		new AlchemyWeapon.BleedToothRecipe(),
		//骨镖
		new AlchemyWeapon.ToothNailRecipe(),
		//沧澜
		new AlchemyWeapon.BlueRecipe(),
		//骨牙杖
		new AlchemyWeapon.WandToothRecipe(),


		//合成星尘
		new GrassGem.GrassDustRecipe(),
		new FireGem.FireDustRecipe(),
		new WaterGem.WaterDustRecipe(),
		new WeaponGem.WeaponDustRecipe(),

		//科技树
		new ScienceRecipe.PotionLevelRecipe(),


	};
	
	private static Recipe[] threeIngredientRecipes = new Recipe[]{
		new Potion.SeedToPotion(),
		new StewedMeat.threeMeat(),
		new MeatPie.Recipe(),
		//炼金武器
		new AlchemyWeapon.ChainRecipe(),
		new AlchemyWeapon.DeadBookRecipe(),
		new AlchemyWeapon.BoneSwordRecipe(),
		new AlchemyWeapon.ShellHammerRecipe(),
		new AlchemyWeapon.GemGloveRecipe(),
		//合成升级
		new WeaponGem.WeaponGemToUpdateRecipe(),
		//雕刻骨头
		//new AlchemyWeapon.BurinRecipe(),
		//灵魂结晶
		new StarRecipe.WhiteGemRecipe(),
		//规则结晶
		new StarRecipe.RuleGemRecipe(),
		//星尘附加
		new ExoticRecipe.TearExoticRecipe(),
		new ExoticRecipe.PowerExoticRecipe(),
		new ExoticRecipe.FrostExoticRecipe(),

		new StarRecipe.ColorGemRecipe(),
		new AlchemyWeapon.EleoveRecipe(),

	};


	private static Recipe[] fourIngredientRecipes = new Recipe[]{

			new AlchemyWeapon.FireGloveRecipe(),
			new ExoticRecipe.FlyMindExoticRecipe(),
	};


	
	public static ArrayList<Recipe> findRecipes(ArrayList<Item> ingredients){

		ArrayList<Recipe> result = new ArrayList<>();

		for (Recipe recipe : variableRecipes){
			if (recipe.testIngredients(ingredients)){
				result.add(recipe);
			}
		}

		if (ingredients.size() == 1){
			for (Recipe recipe : oneIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					result.add(recipe);
				}
			}
			
		} else if (ingredients.size() == 2){
			for (Recipe recipe : twoIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					result.add(recipe);
				}
			}
			
		} else if (ingredients.size() == 3){
			for (Recipe recipe : threeIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					result.add(recipe);
				}
			}
		}

		else if (ingredients.size() == 4){
			for (Recipe recipe : fourIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					result.add(recipe);
				}
			}
		}


		
		return result;
	}
	
	public static boolean usableInRecipe(Item item){
		//return !item.cursed && (!(item instanceof EquipableItem) || item instanceof MissileWeapon);
		if(item instanceof  EquipableItem){
			return item.isIdentified()
				&& !item.cursed
				&& (item instanceof MissileWeapon
					|| item instanceof MeleeWeapon
					|| item instanceof Pickaxe)
				&& !item.isEquipped( hero );
		} else  if (item instanceof Wand){
			return  item.isIdentified()&&!item.cursed;
		} else {
			return !item.cursed;
		}
	}
}