OnJoinCommands
==============

Simple way the run command when players join the server.

Installation
------------
Nothing very special here.

- Download the plugin
- Drop it into your `/plugin` folder
- Restart your server

Configuration
-------------
There a single configuration file, which use the YAML syntax :

```
join:
  - say Hello # Each time a player join the server, will run the command /say Hello
  - another command
quit:
```

NB: Same syntax for quit event.

Available aliases :
- `%player%` the player name
- `%world%` the player's world


Build Notes
-----------
- IDE: IntelliJ IDEA
- Compiled with Java JDK8u31

By the way, this plugin is licensed under MIT License.

Feel free the use this plugin for commercial use, please just mention me somewhere in your credits :) (or not).