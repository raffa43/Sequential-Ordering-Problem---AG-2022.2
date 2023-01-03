package geneticFunctions;

import java.util.ArrayList;
import java.util.Random;

public class Mutations {
    static Random rd = new Random();
    static int totalMutations;

    public static ArrayList<Chromosome> simpleInversion(ArrayList<Chromosome> chromosomes, double percentage) {

        totalMutations = (int) Math.round(chromosomes.size() * percentage);
        ArrayList<Chromosome> mutations = new ArrayList<>();

        while (mutations.size() < totalMutations) {
            Chromosome mutation = new Chromosome(chromosomes.get(rd.nextInt(chromosomes.size())));
            if (mutation.isValid()) mutations.add(mutation);
        }

        return mutations;
    }

     public static ArrayList<Chromosome> displacement(ArrayList<Chromosome> chromosomes, double percentage){

        totalMutations = (int)Math.round(chromosomes.size()*percentage);
        ArrayList<Chromosome> mutations = new ArrayList<>();

        while (mutations.size() < totalMutations){
            Chromosome parent = chromosomes.get(rd.nextInt(chromosomes.size()));

            int[] parentGenes = parent.getGenes();
            int dim = parent.getDim();

            int displacementLength = (int)Math.round(parentGenes.length/3.0);
            int displacementPos = rd.nextInt(1, (parentGenes.length - displacementLength) - 1);

            int[] genes = new int[parentGenes.length];
            for (int i = displacementPos, j = 0; j < displacementLength; i++, j++) {
                genes[i] = parentGenes[i];
            }


            for (int i = 0; i < genes.length; i++) {
                if (i == 0) genes[i] = i;
                else if (i == dim - 1) genes[i] = i;
                else {
                    if (genes[i] == 0){
                        int aux = 0;
                        int index = 1;
                        do {
                            aux = parentGenes[index++];
                        }while (contains(genes, aux));
                        genes[i] = aux;
                    }
                }
            }
            Chromosome mutation = new Chromosome(genes, parent.getAdjMatrix());
            //if (mutation.isValid())
            mutations.add(mutation);
        }
        return mutations;
    }

    public static ArrayList<Chromosome> scramble(ArrayList<Chromosome> chromosomes, double percentage) {

        totalMutations = (int) Math.round(chromosomes.size() * percentage);
        ArrayList<Chromosome> mutations = new ArrayList<>();

        while (mutations.size() < totalMutations) {
            Chromosome parent1 = chromosomes.get(rd.nextInt(chromosomes.size()));
            int[] parent1Genes = parent1.getGenes();



        }

        return mutations;
    }


    private static boolean contains(int[] nums, int num){
        for (int j : nums) {
            if (j == num) return true;
        }
        return false;
    }
}
