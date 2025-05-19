import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pokemon {
    private int pokedexNumber;
    private String name;
    private int type1;
    private int type2;
    private int pv; // Points de Vie (HP)
    private int att; // Attack
    private int def; // Defense
    private int vit; // Speed
    private String owner; // New field to track ownership

    // Default constructor (MissingNo)
    public Pokemon() {
        this.pokedexNumber = 0;
        this.name = "MissingNo";
        this.type1 = Type.SANS;
        this.type2 = Type.SANS;
        this.pv = 33;
        this.att = 136;
        this.def = 0;
        this.vit = 29;
        this.owner = null;
        System.out.println("Created Default Pokémon: MissingNo (#0)");
    }

    // Constructor with all attributes
    public Pokemon(int pokedexNumber, String name, int type1, int type2, int pv, int att, int def, int vit) {
        this.pokedexNumber = pokedexNumber;
        this.name = name != null ? name : Type.getEspece(pokedexNumber);
        this.type1 = type1;
        this.type2 = type2;
        this.pv = pv;
        this.att = att;
        this.def = def;
        this.vit = vit;
        this.owner = null;
        System.out.println("Created Pokémon: " + this.name + " (#" + this.pokedexNumber + ") with manual stats.");
    }

    // Constructor that reads from pokedex.csv
    public Pokemon(int pokedexNumber, String name) {
        this.pokedexNumber = pokedexNumber;
        this.name = name != null ? name : Type.getEspece(pokedexNumber);
        this.type1 = Type.SANS;
        this.type2 = Type.SANS;
        this.pv = 0;
        this.att = 0;
        this.def = 0;
        this.vit = 0;
        this.owner = null;

        System.out.println("Attempting to Load Pokémon Data for #" + pokedexNumber + " (" + this.name + ") from pokedex_gen1.csv...");
        try (BufferedReader br = new BufferedReader(new FileReader("..\\pokedex_gen1.csv"))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int num = Integer.parseInt(data[0]);
                if (num == pokedexNumber) {
                    this.type1 = Type.getIndiceType(data[2]);
                    this.type2 = data[3].isEmpty() ? Type.SANS : Type.getIndiceType(data[3]);
                    this.pv = Integer.parseInt(data[4]);
                    this.att = Integer.parseInt(data[5]);
                    this.def = Integer.parseInt(data[6]);
                    this.vit = Integer.parseInt(data[7]);
                    System.out.println("Loaded Pokémon Data: " + this.name + " (#" + pokedexNumber + ")");
                    System.out.println("Types: " + Type.getNomType(this.type1) + (this.type2 != Type.SANS ? "/" + Type.getNomType(this.type2) : ""));
                    System.out.println("Stats: HP=" + this.pv + ", Attack=" + this.att + ", Defense=" + this.def + ", Speed=" + this.vit);
                    break;
                }
            }
            if (this.pv == 0) {
                System.out.println("Warning: Pokémon #" + pokedexNumber + " not found in pokedex_gen1.csv. Using default stats (HP=0).");
            }
        } catch (IOException e) {
            System.err.println("Error reading pokedex_gen1.csv: " + e.getMessage());
        }
    }

    // Getters
    public int getPokedexNumber() { return pokedexNumber; }
    public String getName() { return name; }
    public int getType1() { return type1; }
    public int getType2() { return type2; }
    public int getPv() { return pv; }
    public int getAtt() { return att; }
    public int getDef() { return def; }
    public int getVit() { return vit; }
    public String getOwner() { return owner; }

    // Setters
    public void setPv(int pv) {
        this.pv = pv;
        System.out.println(this.name + " HP Updated to: " + this.pv);
    }

    public void setOwner(String owner) {
        this.owner = owner;
        System.out.println(this.name + " Owner Set to: " + this.owner);
    }

    // Attack method considering type effectiveness
    public void attaque(Pokemon other, JFrame frame) {
        System.out.println("Calculating Attack Damage...");
        // Calculate base damage
        int baseDamage = this.att - other.def;
        if (baseDamage < 1) baseDamage = 1;
        System.out.println("Base Damage (Attack - Defense): " + baseDamage + " (" + this.att + " - " + other.def + ")");

        // Calculate type effectiveness
        double effectiveness1 = Type.getEfficacite(this.type1, other.type1);
        double effectiveness2 = other.type2 != Type.SANS ? Type.getEfficacite(this.type1, other.type2) : Type.NEUTRE;
        double totalEffectiveness = effectiveness1 * effectiveness2;
        System.out.println("Type Effectiveness: " + this.name + " (" + Type.getNomType(this.type1) + ") vs " + other.name + " (" + Type.getNomType(other.type1) + (other.type2 != Type.SANS ? "/" + Type.getNomType(other.type2) : "") + ")");
        System.out.println("Effectiveness Factors: " + effectiveness1 + " * " + effectiveness2 + " = " + totalEffectiveness);

        // Apply effectiveness to damage
        int finalDamage = (int) (baseDamage * totalEffectiveness);
        if (finalDamage < 1 && totalEffectiveness > 0) finalDamage = 1;
        System.out.println("Final Damage: " + finalDamage);

        // Apply damage
        other.setPv(other.getPv() - finalDamage);
        JOptionPane.showMessageDialog(frame, this.name + " attacks " + other.name + " for " + finalDamage + " damage!");
    }

    // Equals method (updated to compare owner as well)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pokemon)) return false;
        Pokemon other = (Pokemon) obj;
        return this.pokedexNumber == other.pokedexNumber &&
                this.name.equals(other.name) &&
                (this.owner == null ? other.owner == null : this.owner.equals(other.owner));
    }

    // toString method
    @Override
    public String toString() {
        return "Pokemon: " + name + " (#" + pokedexNumber + ")\n" +
                "Species: " + Type.getEspece(pokedexNumber) + "\n" +
                "Type: " + Type.getNomType(type1) + (type2 != Type.SANS ? "/" + Type.getNomType(type2) : "") + "\n" +
                "HP: " + pv + ", Att: " + att + ", Def: " + def + ", Speed: " + vit;
    }
}