# MagicSkyblock Plugin
Mixes non-modded minecraft with mods like Ex Nihilo for a enchanced experience in vanilla minecraft!
# __**Featurelist**__ 
1. **full mana system** `getMana, getMaxMana, setMaxMana, setMana, removeMana, addMana`

2. **darkwater**
    1. darkwater remediy if drunk 4 bottles then can die for darkwater to stop working
    2. uses 8 mana and heals 20 saturation and 2 hunger
    3. adverse effects..
    4. chat scrambling

3. **wands**
    1. **leave extraction:** craft a wand of nature and then you can rightclick on leaves (even from 300 killometer away) and it will drop useful thing
    2. **infnite mana with darkwater:** craft a wand of mana and rightclick it while on darkwater

4. **block transformations** (rightclick + sneak on block + empty hand)
    1. dirt -> grass
    2. grass -> podzol
    3. podzol -> gravel
    4. gravel -> sand
    5. stone -> lava
    6. cobblestone -> dirt
    7. tall grass -> moss
    8. glass -> tinted glass

5. **lava creation**
    1. smelt a iron ingot inside of a furnace and you will get lava bucket (BUCKET DUPE..???)

6. **sneak tree growth**
7. **custom generator**
    1. Rates are very suspicious, check src/main/java/lv/pi/MagicSkyblock/features/cobblegen/CustomCobbleGen.java
    2. _You should probably change them.._
8. **chance of finding seeds in grass by breaking**
9. **grass grows on grass blocks over time, then into tall grass**
10. **throwing eggs spawns in random mobs**

# __**Ideas**__:
These were contributed by the players of the plugin! Make a issue with a idea (if you're not in the discord)
1. **certain soft blocks can either be crushed in item form or in block form by anvil or piston(pick most convenient way of crushing impl)**
2. amethyst related to mana?
3. cursed blocks with darkwater? darkwater interaction with water/lava?
4. TSV dimension where it is always dark (the sun is gone)
5. strobes and monster
6. magic saplings?
7. occasional snowing and powder snow bottles(powder snow bottles implementable due to powder snow cauldron having 3 levels)
8. **flower -> sapling?**
9. **transmuting items(example: beetroot -> carrot -> potato).. could be done by either magicing item frames or items in offhand**
10. **magicing players gives their skull item**
11. "haunting" blocks with dark water bottles to create certain nether blocks: sand -> soul sand; dirt -> soul soil; cobblestone -> netherrack
    1. items could either be haunted by crafting recipe or spilling the dark water on an upwards facing item frame containing the item
12. enchanting rerolls(ability to reroll available enchants list when enchanting an item in the table)
13. **suspicious stew with dark water bottle instead of flower ingredient(dark water effect)**
14.  extension to egg throwing: there is a significantly smaller chance for an unemployed villager to spawn instead of a mob

# Ideas for code clean up
1. You should probably make a class called "Feature" and make it extend every feature and add shit to it yeah ok (for easier organizaztion and more)