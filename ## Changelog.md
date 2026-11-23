## v1.4
+ Added a config file (config/btwr/nomads_rest.toml) with settings for sleeping, bedrolls and the sleep screen
+ Added a settings screen that opens from Mod Menu on Fabric and the mods list on NeoForge. While connected to another server its server settings are locked, since those are only changed in the server's config file
+ Added crafting bedrolls and beds from mixed wool colors. The result gets the dye color closest to the blend of the wools. The wool used is set in the recipe JSON (nomads_rest:mixed_bedroll and nomads_rest:mixed_bed), so modpacks can swap it
+ Changed the world to stop rendering once the screen is fully black while sleeping
+ Changed players to stay asleep until the time-lapse reaches the wake time, instead of waking up just before it
+ Fixed bedrolls halving fall damage instead of not negating it
+ Fixed sleeping through rain not clearing it
+ Fixed colored bedrolls not dropping anything when broken
+ Fixed beds dropping their loot and making the break noise when broken in creative
+ Fixed beds dropping their loot twice when broken in survival
+ General code cleanup/optimization since the split to MultiLoader template
+ Updated Fabric in the mod to Fabric Loader 0.20.1 & Fabric API 0.116.18
+ Updated NeoForge in the mod to NeoForge 21.1.267

## v1.3.1
+ Fixed bedrolls making a wooden sound when being placed
+ Updated the mod to Fabric API 0.116.15 & Fabric Loader 0.19.3

## v1.3
+ Ported the mod to Neoforge (Multiloader template)
+ Removed bedrolls from the colored blocks creative inventory tab as it didn't make sense for them to be there
+ Updated the mod to Fabric API 0.116.12 & Fabric Loader 0.19.3

## v1.2
+ Added colored bedrolls.
+ Changed the screen to go fully black when the player goes to sleep.
+ Changed the recipe for the vanilla bed to be craftable with all different bedrolls. This is for convenience since there are more bedrolls now, and it would just be annoying to have a matching one to craft a same color bed
+ Updated the mod to Fabric API 0.116.9 & Fabric Loader 0.18.4

## v1.1
+ Added a condition to tick sprinting for the sleeping mechanic from this mod which removes the normal message you get when tick sprinting finishes.
This fixes a bug which removed all tick sprinting messages and only removes it for sleeping in bedrolls and beds now.

## v1.0
+ Initial release