<!-- @format -->

# Welcome to Summoner's Terminal!

### This is a small-scale, text based strategy game inspired by League of Legends.

To start playing, simply fork the repo and run "Main.java".
Rules and instructions will be provided in the terminal!

Good luck, and have fun! 😄

---

### [ Rules ]:

    Your aim is to destroy the enemy nexus 🔻, while protecting your own. 💎
    To attack a nexus, a champion must first break through the enemies minions.
    Minions spawn in waves at the start of each combat sequence.
    A combat sequence consists of 5 actions. Each combat action cost 1 action point.
    Certain actions, such as going to base or purchasing items, cost more action points.
    A minion wave consists of 2 melee minions, and 3 caster minions.
    Every three waves, a stronger canon minion will be added to the wave.
    Minions award gold when killed, which can be used to purchase items.
    Attempting to buy an item will send you back to base, even if you don't have enough gold!

---

TODO:

- Refactor actions:

  - Count down from at total of 5 actions points, instead of counting up.
  - Display how many action points are spent per action.

- Player behaviour:

  - Add display of stats between each action (Refactor out 's')
  - Add 'passive' mana regen between actions.
  - Attempting to purchase item without gold should fail without sending you you to base.

- Add more ability options.

  - Each champion should have 2 abilities: one basic ability, and one ultimate.
  - Each champion

- Add level-up system.

  - Minions should award exp when killed.
  - Champion should level up after 'x' amount of exp.
  - Level up multiplier should kick in for each concecutive level.
  - Max level should be 18.

- NPC behaviour.

  - Overload minion.takeDamage() so that it can take an index (displayed when hit).
  - Add enemy champion behaviour.
  - Truncate gold value to use 🪙 instead of text.

- Friendly NPC:

  - Add player nexus.
  - (Optional) - Add friendly minions.

- Add Towers.
  - Tower should attack player if they attempt to hit it.
  - (Optional) - Tower should attack minions if possible (even when player hits tower).
- Add more dynamic items effects.
  - Crit chance.
  - Reduced healing.
  - Increased health/mana regen.
