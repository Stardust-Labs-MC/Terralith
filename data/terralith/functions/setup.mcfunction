scoreboard objectives add tr.first dummy
scoreboard objectives add tr.disable_maps dummy
scoreboard players set %DISABLE_MAP tr.disable_maps 1
execute unless score %USED tr.first matches 2.. run schedule function terralith:toast 20t
scoreboard players set %USED tr.first 2
schedule function terralith:utility/disable_resetting 2400t