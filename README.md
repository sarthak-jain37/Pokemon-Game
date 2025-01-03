# Pokémon Game

## Description

This project has been inspired by the Pokémon games made by GameFreak. The Pokémon Game is a simple text-based game in which players can choose their favourite starter Pokémon from the Kanto Region and engage in turn-based battles against rival Pokémon. This game is designed to simulate the classic Pokémon battle experience, where each Pokémon has unique moves, advantages, and attributes.

## Features

- Choose from three Pokémon: Bulbasaur, Charmander, or Squirtle.
- Engage in turn-based battles with a Computer Rival.
- Each Pokémon has a different set of moves with different base powers.
- Type advantages determine the effectiveness of moves.
- The battles have an element of skill and luck with critical hits, damage variability and attack accuracy.
- The battle continues until the player’s or rival's Pokémon faints.

## Technologies Used

- **Java**: The game is developed in Java, utilizing its object-oriented features for creating Pokémon and battle mechanics.
- **Maven**: Dependency management for the project.
- **json-simple**: Used for handling JSON files to store Pokémon data.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- An IDE or text editor of your choice (e.g., Visual Studio Code, IntelliJ IDEA).
- Maven installed for managing dependencies.

### Installation

## Installation Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/sarthak-jain37/Pokemon-Game.git
   ```

2. Navigate into the project directory:
   ```bash
   cd Pokemon-Game/src
   ```
3. Use Maven to build the project:
   ```bash
   mvn clean install
   ```
4. Run the game:
   ```bash
   mvn exec:java
   ```

# How to Play

Upon running the game, you will be prompted to choose a Pokémon by entering a number (1 for Bulbasaur, 2 for Charmander, or 3 for Squirtle).
After making your selection, you will face a rival Pokémon in a battle.
Choose your moves and see if you can defeat your rival!

# Future Enhancements

1. Changing the model to a 6 v 6 battle to add more depth.
2. Adding IVs and EVs
3. New Rival AI
