# URPSS-1.2.0
This is the gold league update, version control

Please do understand that some files are weird
If you want to download files,
copy the java files and create them in the right packages
This is mostly functions as an archive to older versions of Ultimate Rock Paper Scissor Shoot.
I have earlier versions of this, but private since it has way too much bugs.
I'm pretty sure this version has just as many, but I didn't spot any yet.
Read the about in the game to know a little more about how to play.
It was mainly made for me and to share with my friends.

I made this public because this is the version where the first beginner's league is completed.
This should have been version 1.0.0
Idk why I have a long list of what I did.
Just disregard if you aren't me reading this.

I was considering archiving this, but I thought I might make more updates
idk. 

Game.java is a test run, don't run it.

Graph of code:
-
src

*gameRunner*

    -3files
  
*Move*

    -2files
  
*playersettings*
 
    -3files
  
  * Names of all moves:
 * "Scissors", "attack", 1, 0
 * "Protect", "protect", 0, 3
 * "PowerUp", "status", 0, 4
 * "Sparky", "status", 0, 2
 * "Knife", "attack", 1, 7
 * "Blessing", "status", 0, 6
 * "Rest", "status", 0, 8
 * "Recover", "status", 0, 1
 * "Gun", "hybrid", 1, 3
 * "R&S", "hybrid", 1, 4 (Reload and Shoot)
 * "ThunderPunch", "hybrid", 2, 2
 * "GarbageCan", "status", 0, 9
 * "DScissors", "attack", 2, 0
 * "Boom", "attack", 3, 0
 * "Healing", "status", 0, 10
 * "WideGuard", "protect", 0, 11
 
  ALPHA DETAILS:
 * alpha 0.0.1 created UPRSS, patched module info
 * alpha 0.1.0 created class player
 * alpha 0.1.1 patch player setting package info
 * alpha 0.1.2 class player updated correct toString
 * alpha 0.2.0 created class Move
 * alpha 0.2.1 class Move print()
 * alpha 0.2.2 class Move getEffect
 * alpha 0.2.3 class player add printMove
 * alpha 0.2.4 class player patch null pointer
 * alpha 0.3.0 new player method useMove
 * alpha 0.3.1 useMove patched
 * alpha 0.4.0 split useMove to attacked and useMove
 * alpha 0.4.1 powerUp bug, solved by nerfing
 * alpha 0.4.2 changed useMove return to int from void
 * alpha 0.5.0 created three classic new moves
 * alpha 0.5.1 patched new moves
 * alpha 0.5.2 new get functions
 * alpha 0.6.0 new package gamerRunner, new class game
 * alpha 0.6.1 new newGame method in Player
 * alpha 0.6.2 tested two AI fighting
 * alpha 0.6.3 patched oddities
 * alpha 0.7.0 getMoves method for player
 * alpha 0.8.0 tryLevel function in move
 * alpha 0.8.1 new getEffects 
 * alpha 0.8.2 new Money concept
 * alpha 0.9.0 successful testing of Game function, set for deployment
 * alpha 1.0.0 deployed to beta testing
 * BETA DETAILS:
 * beta 0.0.1 new FullGame class
 * beta 0.1.0 created new Scanner
 * beta 0.1.1 patched imports
 * beta 0.1.2 copied getInput functions from other program
 * beta 0.2.0 new player, setup new game
 * beta 0.2.1 new main menu
 * beta 0.3.0 check stats option coded
 * beta 0.3.1 about page made
 * beta 0.3.2 credits page made
 * beta 0.3.3 about page debugged
 * beta 0.4.0 new save function
 * beta 0.4.1 patched save function
 * beta 0.4.2 fixed get save
 * beta 0.5.0 new Moves, quick fight made
 * beta 0.5.1 patched fighting loop
 * beta 0.5.2 edited various functions
 * beta 0.5.3 moved fighting to new function
 * beta 0.6.0 various aesthetics update
 * beta 0.6.1 aesthetics patch, better logo
 * beta 0.6.2 update getInput function, with yes and no
 * beta 0.6.3 tested some games in Game
 * beta 0.7.0 shop created, released
 * beta 0.7.1 shop patched random bugs
 * beta 0.7.2 fixed shop bugs created by previous patch
 * beta 0.7.3 added new player utility functions
 * beta 0.8.0 move menu created
 * beta 0.8.1 move menu patch
 * beta 0.8.2 finding name list for AI names
 * beta 0.8.3s updated move cost
 * beta 0.9.0 ranking match created
 * beta 0.9.1 ranking match patch
 * beta 0.9.2 new story mode
 * beta 0.9.3 story mode patch
 * beta 0.9.4 version log made
 * beta 0.9.5 more aesthetics updates
 * beta 0.9.6 updating story mode
 * beta 0.9.7 story mode patch
 * beta 0.9.8 xp level up patch
 * beta 0.9.9 testing for deployments
 * beta 1.0.0 release to version 1.0.0
 * RELEASE:
 * 1.0.0 silver league story development
 * 1.0.1 fixed minor aesthetics bug
 * 1.0.2 fixed move damage based on level bug
 * 1.0.3 fixed move list copy bug
 * 1.0.3.1 lol 1.0.2 bug fix created bug, new aglo with calc damage
 * 1.0.3.2 reworking of Move class
 * 1.0.4 finally added new moves - big update
 * 1.0.5 reworked shop glitch, safety the input
 * 1.0.6 finished silver league captain
 * 1.0.7 finished silver league
 * 1.0.8 fixed shop bug
 * 1.0.9 fixed move menu bug
 * 1.1.0 release testing to version 1.1.0
 * Second Release:
 * 1.1.1 new badge display.
 * 1.1.2 new moves
 * 1.1.3 gold league 30% complete
 * 1.1.4 new function to check if moves viable
 * 1.1.5 new sell function, tested, works on the first try!
 * 1.1.6 gold league development 70%
 * 1.1.7 new moves
 * 1.1.8 Big debug session
 * 1.1.9 finished gold league
 * 1.2.0 testing to new version
 * @version 1.2.0
