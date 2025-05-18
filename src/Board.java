import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private static final int SIZE = 9;
    private static final int TOTAL_POKEMON_PER_PLAYER = 21;
    private Pokemon[][] grid;
    private List<Pokemon> player1Pokemons;
    private List<Pokemon> player2Pokemons;
    private Pokemon player1Mewtwo;
    private Pokemon player2Mewtwo;
    private JFrame frame;
    private JLabel[][] boardLabels;
    private boolean player1Turn;
    private Pokemon selectedPokemon;
    private int selectedRow, selectedCol;

    public Board() {
        grid = new Pokemon[SIZE][SIZE];
        player1Pokemons = new ArrayList<>();
        player2Pokemons = new ArrayList<>();
        player1Turn = true;
        selectedPokemon = null;
        System.out.println("Board Created: 9x9 grid initialized.");
    }

    public void initializeBoard() {
        System.out.println("Initializing Board with " + TOTAL_POKEMON_PER_PLAYER + " Pokémon per player...");

        // Player 1: Rows 0-2
        System.out.println("Player 1: Placing Pokémon in rows 0-2...");
        // Row 0: [39, 44, 45, 90, 106, 90, 45, 44, 39]
        placePokemon(4, 0, 0, player1Pokemons);  // Salamèche (39)
        placePokemon(7, 0, 1, player1Pokemons);  // Carapuce (44)
        placePokemon(1, 0, 2, player1Pokemons);  // Bulbizarre (45)
        placePokemon(149, 0, 3, player1Pokemons); // Dragonite (91, using as 90)
        placePokemon(150, 0, 4, player1Pokemons); // Mewtwo (106)
        placePokemon(149, 0, 5, player1Pokemons); // Dragonite (91, using as 90)
        placePokemon(1, 0, 6, player1Pokemons);  // Bulbizarre (45)
        placePokemon(7, 0, 7, player1Pokemons);  // Carapuce (44)
        placePokemon(4, 0, 8, player1Pokemons);  // Salamèche (39)

        // Row 1: [58, 59, 60, 91, 90, 91, 60, 59, 58]
        placePokemon(5, 1, 0, player1Pokemons);  // Reptincel (58)
        placePokemon(8, 1, 1, player1Pokemons);  // Carabaffe (59)
        placePokemon(2, 1, 2, player1Pokemons);  // Herbizarre (60)
        placePokemon(148, 1, 3, player1Pokemons); // Dracolosse (91)
        placePokemon(145, 1, 4, player1Pokemons); // Électhor (90)
        placePokemon(148, 1, 5, player1Pokemons); // Dracolosse (91)
        placePokemon(2, 1, 6, player1Pokemons);  // Herbizarre (60)
        placePokemon(8, 1, 7, player1Pokemons);  // Carabaffe (59)
        placePokemon(5, 1, 8, player1Pokemons);  // Reptincel (58)

        // Row 2: [0, 0, 0, 80, 79, 78, 0, 0, 0]
        placePokemon(3, 2, 3, player1Pokemons);  // Florizarre (80)
        placePokemon(9, 2, 4, player1Pokemons);  // Tortank (79)
        placePokemon(6, 2, 5, player1Pokemons);  // Dracaufeu (78)

        player1Mewtwo = grid[0][4]; // Mewtwo at (0,4)

        // Player 2: Rows 6-8
        System.out.println("Player 2: Placing Pokémon in rows 6-8...");
        // Row 6: [0, 0, 0, 78, 79, 80, 0, 0, 0]
        placePokemon(6, 6, 3, player2Pokemons);  // Dracaufeu (78)
        placePokemon(9, 6, 4, player2Pokemons);  // Tortank (79)
        placePokemon(3, 6, 5, player2Pokemons);  // Florizarre (80)

        // Row 7: [58, 59, 60, 91, 90, 91, 60, 59, 58]
        placePokemon(5, 7, 0, player2Pokemons);  // Reptincel (58)
        placePokemon(8, 7, 1, player2Pokemons);  // Carabaffe (59)
        placePokemon(2, 7, 2, player2Pokemons);  // Herbizarre (60)
        placePokemon(148, 7, 3, player2Pokemons); // Dracolosse (91)
        placePokemon(145, 7, 4, player2Pokemons); // Électhor (90)
        placePokemon(148, 7, 5, player2Pokemons); // Dracolosse (91)
        placePokemon(2, 7, 6, player2Pokemons);  // Herbizarre (60)
        placePokemon(8, 7, 7, player2Pokemons);  // Carabaffe (59)
        placePokemon(5, 7, 8, player2Pokemons);  // Reptincel (58)

        // Row 8: [39, 44, 45, 90, 106, 90, 45, 44, 39]
        placePokemon(4, 8, 0, player2Pokemons);  // Salamèche (39)
        placePokemon(7, 8, 1, player2Pokemons);  // Carapuce (44)
        placePokemon(1, 8, 2, player2Pokemons);  // Bulbizarre (45)
        placePokemon(149, 8, 3, player2Pokemons); // Dragonite (91, using as 90)
        placePokemon(150, 8, 4, player2Pokemons); // Mewtwo (106)
        placePokemon(149, 8, 5, player2Pokemons); // Dragonite (91, using as 90)
        placePokemon(1, 8, 6, player2Pokemons);  // Bulbizarre (45)
        placePokemon(7, 8, 7, player2Pokemons);  // Carapuce (44)
        placePokemon(4, 8, 8, player2Pokemons);  // Salamèche (39)

        player2Mewtwo = grid[8][4]; // Mewtwo at (8,4)

        System.out.println("Board Initialized Successfully:");
        System.out.println("Player 1 Pokémon: " + player1Pokemons.size() + " placed - " + player1Pokemons);
        System.out.println("Player 2 Pokémon: " + player2Pokemons.size() + " placed - " + player2Pokemons);

        setupGUI();
    }

    private void placePokemon(int pokedexNum, int row, int col, List<Pokemon> pokemons) {
        Pokemon pokemon = new Pokemon(pokedexNum, null);
        grid[row][col] = pokemon;
        pokemons.add(pokemon);
        System.out.println("Placed " + pokemon.getName() + " (#" + pokemon.getPokedexNumber() + ") at (" + row + "," + col + ")");
        System.out.println("Stats: HP=" + pokemon.getPv() + ", Attack=" + pokemon.getAtt() + ", Defense=" + pokemon.getDef() + ", Speed=" + pokemon.getVit());
    }

    private void placePokemons(Scanner scanner, List<Pokemon> pokemons, int startRow, int endRow, String player) {
        int placed = 0;
        while (placed < TOTAL_POKEMON_PER_PLAYER) {
            System.out.println(player + ": Enter Pokémon #" + (placed + 1) + " by specifying Pokedex number, name, row, and column (e.g., '150 Mewtwo 0 0'). Enter 'done' to finish early, or 'fill' to auto-fill remaining slots.");
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                if (placed < TOTAL_POKEMON_PER_PLAYER) {
                    System.out.println("Warning: Only " + placed + " Pokémon placed. Auto-filling remaining " + (TOTAL_POKEMON_PER_PLAYER - placed) + " slots with defaults.");
                    // Removed auto-fill logic since we're using precise placement
                }
                break;
            } else if (input.equalsIgnoreCase("fill")) {
                // Removed auto-fill logic
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 4) {
                System.out.println("Invalid input. Format: pokedexNumber name row col");
                continue;
            }
            try {
                int pokedexNum = Integer.parseInt(parts[0]);
                String name = parts[1];
                int row = Integer.parseInt(parts[2]);
                int col = Integer.parseInt(parts[3]);
                if (row < startRow || row > endRow || col < 0 || col >= SIZE) {
                    System.out.println("Invalid position. Row must be between " + startRow + " and " + endRow + ", col between 0 and " + (SIZE - 1));
                    continue;
                }
                if (grid[row][col] != null) {
                    System.out.println("Position (" + row + "," + col + ") is already occupied!");
                    continue;
                }
                Pokemon pokemon = new Pokemon(pokedexNum, name);
                grid[row][col] = pokemon;
                pokemons.add(pokemon);
                System.out.println("Placed " + pokemon.getName() + " (#" + pokemon.getPokedexNumber() + ") at (" + row + "," + col + ") for " + player);
                System.out.println("Stats: HP=" + pokemon.getPv() + ", Attack=" + pokemon.getAtt() + ", Defense=" + pokemon.getDef() + ", Speed=" + pokemon.getVit());
                placed++;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format.");
            }
        }
    }

    private void setupGUI() {
        frame = new JFrame("Pokémon Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(SIZE, SIZE));
        boardLabels = new JLabel[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final int row = i, col = j;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleClick(row, col);
                    }
                });
                boardLabels[i][j] = label;
                frame.add(label);
            }
        }

        frame.setSize(SIZE * 60, SIZE * 60);
        frame.setVisible(true);
        System.out.println("GUI Setup Complete: Window opened with 9x9 grid.");
        updateBoard();
    }

    private void updateBoard() {
        System.out.println("Updating Board Display...");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JLabel label = boardLabels[i][j];
                if (grid[i][j] != null) {
                    try {
                        ImageIcon icon = new ImageIcon("..\\images\\" + grid[i][j].getPokedexNumber() + ".png");
                        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        label.setIcon(new ImageIcon(img));
                        label.setText("<html><center>" + grid[i][j].getName() + "<br><font color='red'>" + grid[i][j].getPv() + "</font></center></html>");
                        label.setBackground(selectedPokemon == grid[i][j] ? Color.YELLOW : ((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE));
                        System.out.println("Image Loaded for " + grid[i][j].getName() + " at (" + i + "," + j + "): " + grid[i][j].getPokedexNumber() + ".png");
                    } catch (Exception e) {
                        label.setText("<html><center>" + grid[i][j].getName().charAt(0) + "<br><font color='red'>" + grid[i][j].getPv() + "</font></center></html>");
                        label.setIcon(null);
                        label.setBackground(selectedPokemon == grid[i][j] ? Color.YELLOW : ((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE));
                        System.out.println("Failed to Load Image for " + grid[i][j].getName() + " at (" + i + "," + j + "): Using initial '" + grid[i][j].getName().charAt(0) + "'. Error: " + e.getMessage());
                    }
                } else {
                    label.setText("");
                    label.setIcon(null);
                    label.setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                }
            }
        }
        frame.setTitle("Pokémon Chess - " + (player1Turn ? "Player 1" : "Player 2") + "'s Turn");
        System.out.println("Board Updated: Current Turn - " + (player1Turn ? "Player 1" : "Player 2"));
    }

    private void handleClick(int row, int col) {
        System.out.println("Click Detected at (" + row + "," + col + ")");
        if (selectedPokemon == null) {
            Pokemon clickedPokemon = grid[row][col];
            if (clickedPokemon != null && ((player1Turn && player1Pokemons.contains(clickedPokemon)) || (!player1Turn && player2Pokemons.contains(clickedPokemon)))) {
                selectedPokemon = clickedPokemon;
                selectedRow = row;
                selectedCol = col;
                System.out.println("Selected Pokémon: " + selectedPokemon.getName() + " at (" + row + "," + col + ") by " + (player1Turn ? "Player 1" : "Player 2"));
                updateBoard();
                JOptionPane.showMessageDialog(frame, "Selected " + selectedPokemon.getName() + "\nChoose an action: Move or Attack.");
            } else {
                System.out.println("Invalid Selection: No Pokémon at (" + row + "," + col + ") or not your Pokémon.");
            }
        } else {
            System.out.println("Action Selection for " + selectedPokemon.getName() + " at (" + selectedRow + "," + selectedCol + ")");
            Object[] options = {"Move", "Attack", "Cancel"};
            int choice = JOptionPane.showOptionDialog(frame, "Choose an action for " + selectedPokemon.getName(), "Action",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            System.out.println("Action Chosen: " + (choice == 0 ? "Move" : choice == 1 ? "Attack" : "Cancel"));

            if (choice == 0) { // Move
                if (Math.abs(row - selectedRow) <= 1 && Math.abs(col - selectedCol) <= 1 && grid[row][col] == null) {
                    grid[row][col] = selectedPokemon;
                    grid[selectedRow][selectedCol] = null;
                    System.out.println(selectedPokemon.getName() + " moved from (" + selectedRow + "," + selectedCol + ") to (" + row + "," + col + ").");
                    endTurn();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid move! Can only move one space, and target must be empty.");
                    System.out.println("Invalid Move Attempt: From (" + selectedRow + "," + selectedCol + ") to (" + row + "," + col + ").");
                    selectedPokemon = null;
                    updateBoard();
                }
            } else if (choice == 1) { // Attack
                if (Math.abs(row - selectedRow) <= 1 && Math.abs(col - selectedCol) <= 1 && grid[row][col] != null) {
                    Pokemon target = grid[row][col];
                    List<Pokemon> opponentPokemons = player1Turn ? player2Pokemons : player1Pokemons;
                    if (opponentPokemons.contains(target)) {
                        System.out.println("Attack Initiated: " + selectedPokemon.getName() + " attacks " + target.getName());
                        System.out.println("Before Attack - Attacker HP: " + selectedPokemon.getPv() + ", Defender HP: " + target.getPv());
                        selectedPokemon.attaque(target, frame);
                        System.out.println("After Attack - Attacker HP: " + selectedPokemon.getPv() + ", Defender HP: " + target.getPv());
                        if (target.getPv() <= 0) {
                            JOptionPane.showMessageDialog(frame, target.getName() + " was defeated!");
                            System.out.println(target.getName() + " defeated at (" + row + "," + col + ").");
                            grid[row][col] = null;
                            opponentPokemons.remove(target);
                        }
                        endTurn();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Cannot attack your own Pokémon!");
                        System.out.println("Invalid Attack: Cannot attack own Pokémon at (" + row + "," + col + ").");
                        selectedPokemon = null;
                        updateBoard();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid target! Must be an adjacent enemy Pokémon.");
                    System.out.println("Invalid Attack Target: Must be adjacent enemy at (" + row + "," + col + ").");
                    selectedPokemon = null;
                    updateBoard();
                }
            } else { // Cancel
                System.out.println("Action Cancelled for " + selectedPokemon.getName());
                selectedPokemon = null;
                updateBoard();
            }
        }
    }

    private void endTurn() {
        if (isGameOver()) {
            String winner = getWinner();
            String gameOverMessage = "Game Over!\n" + winner + " wins!\n";
            if (player1Mewtwo.getPv() <= 0) {
                gameOverMessage += "Player 1's Mewtwo was defeated!";
            } else {
                gameOverMessage += "Player 2's Mewtwo was defeated!";
            }
            JOptionPane.showMessageDialog(frame, gameOverMessage);
            System.out.println("Game Over: " + winner + " wins!");
            System.out.println("Final Board State:");
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(grid[i][j] != null ? grid[i][j].getName() + " " : "- ");
                }
                System.out.println();
            }
            frame.dispose();
            System.exit(0);
        }
        player1Turn = !player1Turn;
        selectedPokemon = null;
        System.out.println("Turn Ended: Now " + (player1Turn ? "Player 1" : "Player 2") + "'s Turn");
        updateBoard();
    }

    public boolean isGameOver() {
        boolean gameOver = player1Mewtwo.getPv() <= 0 || player2Mewtwo.getPv() <= 0;
        if (gameOver) {
            System.out.println("Game Over Condition Met:");
            System.out.println("Player 1 Mewtwo HP: " + player1Mewtwo.getPv());
            System.out.println("Player 2 Mewtwo HP: " + player2Mewtwo.getPv());
        }
        return gameOver;
    }

    public String getWinner() {
        if (player1Mewtwo.getPv() <= 0) return "Player 2";
        if (player2Mewtwo.getPv() <= 0) return "Player 1";
        return null;
    }

    public List<Pokemon> getPlayer1Pokemons() { return player1Pokemons; }
    public List<Pokemon> getPlayer2Pokemons() { return player2Pokemons; }
}