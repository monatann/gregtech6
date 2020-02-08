/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of gregtech6.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with gregtech6. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech6.loaders.a;

import static gregapi6.data.CS.*;

import gregapi6.data.CS.ItemsGT;
import gregapi6.data.MD;
import gregapi6.data.MT;
import gregapi6.data.OP;
import gregapi6.data.TD;
import gregapi6.item.prefixitem.PrefixItem;
import gregapi6.item.prefixitem.PrefixItemProjectile;
import gregapi6.item.prefixitem.PrefixItemRing;
import gregapi6.util.ST;
import gregtech6.entities.projectiles.EntityArrow_Material;
import gregtech6.items.MultiItemBooks;
import gregtech6.items.MultiItemBottles;
import gregtech6.items.MultiItemBumbles;
import gregtech6.items.MultiItemCans;
import gregtech6.items.MultiItemFood;
import gregtech6.items.MultiItemRandomTools;
import gregtech6.items.MultiItemTechnological;

public class Loader_Items implements Runnable {
	@Override
	public void run() {
		PrefixItem tItem;
		
		ItemsGT.TECH = new MultiItemTechnological();
		ItemsGT.TOOLS = new MultiItemRandomTools();
		ItemsGT.CANS = new MultiItemCans();
		ItemsGT.FOOD = new MultiItemFood();
		ItemsGT.BOTTLES = new MultiItemBottles();
		ItemsGT.BOOKS = new MultiItemBooks();
		ItemsGT.BUMBLEBEES = new MultiItemBumbles();
		
		ItemsGT.ALL_MULTI_ITEMS[0] = ItemsGT.TECH;
		ItemsGT.ALL_MULTI_ITEMS[1] = ItemsGT.TOOLS;
		ItemsGT.ALL_MULTI_ITEMS[2] = ItemsGT.CANS;
		ItemsGT.ALL_MULTI_ITEMS[3] = ItemsGT.FOOD;
		ItemsGT.ALL_MULTI_ITEMS[4] = ItemsGT.BOTTLES;
		ItemsGT.ALL_MULTI_ITEMS[5] = ItemsGT.BOOKS;
		ItemsGT.ALL_MULTI_ITEMS[6] = ItemsGT.BUMBLEBEES;
		
		new PrefixItem(MD.GT, "gt6.meta.dust"                        , OP.dust                           );
		new PrefixItem(MD.GT, "gt6.meta.dustSmall"                   , OP.dustSmall                      );
		new PrefixItem(MD.GT, "gt6.meta.dustTiny"                    , OP.dustTiny                       );
		new PrefixItem(MD.GT, "gt6.meta.dustDiv72"                   , OP.dustDiv72                      );
		new PrefixItem(MD.GT, "gt6.meta.dustImpure"                  , OP.dustImpure                     );
		new PrefixItem(MD.GT, "gt6.meta.dustPure"                    , OP.dustPure                       );
		new PrefixItem(MD.GT, "gt6.meta.crushed"                     , OP.crushed                        );
		new PrefixItem(MD.GT, "gt6.meta.crushedTiny"                 , OP.crushedTiny                    );
		new PrefixItem(MD.GT, "gt6.meta.crushedPurified"             , OP.crushedPurified                );
		new PrefixItem(MD.GT, "gt6.meta.crushedPurifiedTiny"         , OP.crushedPurifiedTiny            );
		new PrefixItem(MD.GT, "gt6.meta.crushedCentrifuged"          , OP.crushedCentrifuged             );
		new PrefixItem(MD.GT, "gt6.meta.crushedCentrifugedTiny"      , OP.crushedCentrifugedTiny         );
		
		new PrefixItem(MD.GT, "gt6.meta.gemChipped"                  , OP.gemChipped                     );
		new PrefixItem(MD.GT, "gt6.meta.gemFlawed"                   , OP.gemFlawed                      );
		new PrefixItem(MD.GT, "gt6.meta.gem"                         , OP.gem                            );
		new PrefixItem(MD.GT, "gt6.meta.gemFlawless"                 , OP.gemFlawless                    );
		new PrefixItem(MD.GT, "gt6.meta.gemExquisite"                , OP.gemExquisite                   );
		new PrefixItem(MD.GT, "gt6.meta.gemLegendary"                , OP.gemLegendary                   );
		new PrefixItem(MD.GT, "gt6.meta.boule"                       , OP.bouleGt                        );
		
		new PrefixItem(MD.GT, "gt6.meta.nugget"                      , OP.nugget                         );
		new PrefixItem(MD.GT, "gt6.meta.chunkGt"                     , OP.chunkGt                        );
		new PrefixItem(MD.GT, "gt6.meta.ingot"                       , OP.ingot                          );
		new PrefixItem(MD.GT, "gt6.meta.ingotHot"                    , OP.ingotHot                       );
		new PrefixItem(MD.GT, "gt6.meta.ingotDouble"                 , OP.ingotDouble                    );
		new PrefixItem(MD.GT, "gt6.meta.ingotTriple"                 , OP.ingotTriple                    );
		new PrefixItem(MD.GT, "gt6.meta.ingotQuadruple"              , OP.ingotQuadruple                 );
		new PrefixItem(MD.GT, "gt6.meta.ingotQuintuple"              , OP.ingotQuintuple                 );
		
		new PrefixItem(MD.GT, "gt6.meta.plateGemTiny"                , OP.plateGemTiny                   );
		new PrefixItem(MD.GT, "gt6.meta.plateGem"                    , OP.plateGem                       );
		new PrefixItem(MD.GT, "gt6.meta.plateTiny"                   , OP.plateTiny                      );
		new PrefixItem(MD.GT, "gt6.meta.plate"                       , OP.plate                          );
		new PrefixItem(MD.GT, "gt6.meta.plateDouble"                 , OP.plateDouble                    );
		new PrefixItem(MD.GT, "gt6.meta.plateTriple"                 , OP.plateTriple                    );
		new PrefixItem(MD.GT, "gt6.meta.plateQuadruple"              , OP.plateQuadruple                 );
		new PrefixItem(MD.GT, "gt6.meta.plateQuintuple"              , OP.plateQuintuple                 );
		new PrefixItem(MD.GT, "gt6.meta.plateDense"                  , OP.plateDense                     );
		new PrefixItem(MD.GT, "gt6.meta.plateCurved"                 , OP.plateCurved                    );
		
		new PrefixItem(MD.GT, "gt6.meta.scrapGt"                     , OP.scrapGt                        );
		new PrefixItem(MD.GT, "gt6.meta.rockGt"                      , OP.rockGt                         );
		
		new PrefixItem(MD.GT, "gt6.meta.gearGtSmall"                 , OP.gearGtSmall                    );
		new PrefixItem(MD.GT, "gt6.meta.gearGt"                      , OP.gearGt                         );
		new PrefixItem(MD.GT, "gt6.meta.rotor"                       , OP.rotor                          );
		new PrefixItem(MD.GT, "gt6.meta.stick"                       , OP.stick                          );
		new PrefixItem(MD.GT, "gt6.meta.stickLong"                   , OP.stickLong                      );
		new PrefixItem(MD.GT, "gt6.meta.springSmall"                 , OP.springSmall                    );
		new PrefixItem(MD.GT, "gt6.meta.spring"                      , OP.spring                         );
		new PrefixItem(MD.GT, "gt6.meta.lens"                        , OP.lens                           );
		new PrefixItem(MD.GT, "gt6.meta.round"                       , OP.round                          );
		new PrefixItem(MD.GT, "gt6.meta.bolt"                        , OP.bolt                           );
		new PrefixItem(MD.GT, "gt6.meta.screw"                       , OP.screw                          );
		new PrefixItemRing(MD.GT, "gt6.meta.ring"                    , OP.ring                           );
		new PrefixItem(MD.GT, "gt6.meta.chain"                       , OP.chain                          );
		new PrefixItem(MD.GT, "gt6.meta.foil"                        , OP.foil                           );
		new PrefixItem(MD.GT, "gt6.meta.casingSmall"                 , OP.casingSmall                    );
		new PrefixItem(MD.GT, "gt6.meta.wireFine"                    , OP.wireFine                       );
		new PrefixItem(MD.GT, "gt6.meta.minecartWheels"              , OP.minecartWheels                 );
		new PrefixItem(MD.GT, "gt6.meta.railGt"                      , OP.railGt                         );
		
		new PrefixItem(MD.GT, "gt6.meta.plantGtBerry"                , OP.plantGtBerry                   );
		new PrefixItem(MD.GT, "gt6.meta.plantGtBlossom"              , OP.plantGtBlossom                 );
		new PrefixItem(MD.GT, "gt6.meta.plantGtFiber"                , OP.plantGtFiber                   );
		new PrefixItem(MD.GT, "gt6.meta.plantGtTwig"                 , OP.plantGtTwig                    );
		new PrefixItem(MD.GT, "gt6.meta.plantGtWart"                 , OP.plantGtWart                    );
		
		tItem =
		new PrefixItem(MD.GT, "gt6.meta.chemtube"                    , OP.chemtube                       );
		OP.chemtube.mContainerItem = ST.make(tItem, 1, MT.Empty.mID);
		tItem.mCraftingSound = MD.IC2.mID.toLowerCase() + ":" + "tools.Treetap";
		
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawSword"            , OP.toolHeadRawSword               );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadSword"               , OP.toolHeadSword                  );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawPickaxe"          , OP.toolHeadRawPickaxe             );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadPickaxe"             , OP.toolHeadPickaxe                );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadConstructionPickaxe" , OP.toolHeadConstructionPickaxe    );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadPickaxeGem"          , OP.toolHeadPickaxeGem             );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawShovel"           , OP.toolHeadRawShovel              );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadShovel"              , OP.toolHeadShovel                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawSpade"            , OP.toolHeadRawSpade               );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadSpade"               , OP.toolHeadSpade                  );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawAxe"              , OP.toolHeadRawAxe                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadAxe"                 , OP.toolHeadAxe                    );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawAxeDouble"        , OP.toolHeadRawAxeDouble           );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadAxeDouble"           , OP.toolHeadAxeDouble              );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawHoe"              , OP.toolHeadRawHoe                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadHoe"                 , OP.toolHeadHoe                    );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadHammer"              , OP.toolHeadHammer                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadFile"                , OP.toolHeadFile                   );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawChisel"           , OP.toolHeadRawChisel              );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadChisel"              , OP.toolHeadChisel                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawSaw"              , OP.toolHeadRawSaw                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadSaw"                 , OP.toolHeadSaw                    );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadDrill"               , OP.toolHeadDrill                  );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadChainsaw"            , OP.toolHeadChainsaw               );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadWrench"              , OP.toolHeadWrench                 );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadScrewdriver"         , OP.toolHeadScrewdriver            );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawUniversalSpade"   , OP.toolHeadRawUniversalSpade      );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadUniversalSpade"      , OP.toolHeadUniversalSpade         );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawSense"            , OP.toolHeadRawSense               );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadSense"               , OP.toolHeadSense                  );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawPlow"             , OP.toolHeadRawPlow                );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadPlow"                , OP.toolHeadPlow                   );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadBuzzSaw"             , OP.toolHeadBuzzSaw                );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadRawArrow"            , OP.toolHeadRawArrow               );
		new PrefixItem(MD.GT, "gt6.meta.toolHeadArrow"               , OP.toolHeadArrow                  );
		new PrefixItemProjectile(MD.GT, "gt6.meta.arrowGtWood"       , OP.arrowGtWood        , TD.Projectiles.ARROW          , EntityArrow_Material.class, 1.00F, 6.00F, T, T);
		new PrefixItemProjectile(MD.GT, "gt6.meta.arrowGtPlastic"    , OP.arrowGtPlastic     , TD.Projectiles.ARROW          , EntityArrow_Material.class, 1.50F, 6.00F, T, T);
		new PrefixItemProjectile(MD.GT, "gt6.meta.bulletGtSmall"     , OP.bulletGtSmall      , TD.Projectiles.BULLET_SMALL   , EntityArrow_Material.class, 1.50F, 3.00F, F, F);
		new PrefixItemProjectile(MD.GT, "gt6.meta.bulletGtMedium"    , OP.bulletGtMedium     , TD.Projectiles.BULLET_MEDIUM  , EntityArrow_Material.class, 1.75F, 2.50F, F, F);
		new PrefixItemProjectile(MD.GT, "gt6.meta.bulletGtLarge"     , OP.bulletGtLarge      , TD.Projectiles.BULLET_LARGE   , EntityArrow_Material.class, 2.00F, 2.00F, F, F);
	}
}
