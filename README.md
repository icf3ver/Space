
# Space

## Purpose

The purpose of this game was to try to have a System of SolarSystems that allow a player to navigate through them and move from one to the other. In addition I wanted the players spaceship to be completely customizable and the map to be generated from a text file.

## The Text File

The text file [a relative link](System) is configurable. To generate your own System it is relatively simple but may change.

| Command | Description |
| ------ | ------ |
| System<number> <name> | Creates a system with the given name and the system number everything following this but before the next system is in that system|
| -Gate <SystemNumber> <x> <y> | Creates a gate. Gates are 2 way portals if one is created there must be a corresponding one in the system it leads too the gate needs too have an x position a y position and needs too store the number of the system it leads too.|
| -Player <name> <x> <y> <None> | Please do not for the time being create new player objects. |
| -Planet <name> <x> <y> <type> <seed> <size> | Creates a planet with a name, a position, a seed, and a size (please do not exceed a size of 21)|
| -Asteroid <type> <x> <y> <type> <seed> <size> | Creates an asteroid with a type (the only type is common for now), a name, a position, a seed, and a size (please do not exceed a size of 21)|
|-Station <name> <x> <y> | Creates a station with a name and a position|

Any other text as well as empty lines may confuse the program.
