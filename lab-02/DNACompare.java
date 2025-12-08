import java.util.*;

public class DNACompare {

    public static ArrayList<String> DNAToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<>();
        for (int i = 0; i + 2 < dna.length(); i += 3) {
            codons.add(dna.substring(i, i + 3));
        }
        return codons;
    }

    public static String CodonToAminoAcid(String codon) {
        return switch (codon) {
            case "TTT", "TTC" -> "F";
            case "TTA", "TTG", "CTT", "CTC", "CTA", "CTG" -> "L";
            case "ATT", "ATC", "ATA" -> "I";
            case "ATG" -> "M";
            case "GTT", "GTC", "GTA", "GTG" -> "V";
            case "TCT", "TCC", "TCA", "TCG", "AGT", "AGC" -> "S";
            case "CCT", "CCC", "CCA", "CCG" -> "P";
            case "ACT", "ACC", "ACA", "ACG" -> "T";
            case "GCT", "GCC", "GCA", "GCG" -> "A";
            case "TAT", "TAC" -> "Y";
            case "TAA", "TAG", "TGA" -> "*";
            case "CAT", "CAC" -> "H";
            case "CAA", "CAG" -> "Q";
            case "AAT", "AAC" -> "N";
            case "AAA", "AAG" -> "K";
            case "GAT", "GAC" -> "D";
            case "GAA", "GAG" -> "E";
            case "TGT", "TGC" -> "C";
            case "TGG" -> "W";
            case "CGT", "CGC", "CGA", "CGG", "AGA", "AGG" -> "R";
            case "GGT", "GGC", "GGA", "GGG" -> "G";
            default -> "?";
        };
    }

    public static ArrayList<String> dna_to_amino_acid(String dna) {
        ArrayList<String> codons = DNAToCodons(dna);
        ArrayList<String> amino = new ArrayList<>();
        for (String c : codons) amino.add(CodonToAminoAcid(c));
        return amino;
    }

    public static boolean is_match(ArrayList<String> seq1, ArrayList<String> seq2) {
        return seq1.equals(seq2);
    }

    public static void main(String[] args) {
        String DNA1 = "CTGATATTGTATCCGGCCGAA";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAG";

        ArrayList<String> a1 = dna_to_amino_acid(DNA1);
        ArrayList<String> a2 = dna_to_amino_acid(DNA2);
        ArrayList<String> a3 = dna_to_amino_acid(DNA3);

        System.out.println("DNA1 vs DNA2: " + is_match(a1, a2));
        System.out.println("DNA1 vs DNA3: " + is_match(a1, a3));
        System.out.println("DNA2 vs DNA3: " + is_match(a2, a3));
    }
}