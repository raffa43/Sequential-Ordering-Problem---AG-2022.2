package geneticFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Selections {
    static Random rd = new Random();
    public static ArrayList<Chromosome> tournament(ArrayList<Chromosome> chromosomes, int popSize){

        ArrayList<Chromosome> selections = new ArrayList<>();

        while(selections.size() < popSize){
            ArrayList<Chromosome> tournament = new ArrayList<>();

            for (int k = 0; k < 10; k++) {
                tournament.add(chromosomes.get(rd.nextInt(chromosomes.size())));
            }
            int winnerPos = 0;
            double winnerFitness = Double.MIN_VALUE;

            for (int k = 0; k < tournament.size(); k++) {
                if (tournament.get(k).getFitness() > winnerFitness){
                    winnerPos = k;
                    winnerFitness = tournament.get(k).getFitness();
                }
            }
            selections.add(tournament.get(winnerPos));
        }
        return selections;
    }

    public static ArrayList<Chromosome> roulette(ArrayList<Chromosome> chromosomes, int popSize){

        ArrayList<Chromosome> selections = new ArrayList<>();
        double fitnesssum = 0;

        for (int i = 0; i < chromosomes.size(); i++) {
            fitnesssum += chromosomes.get(i).getFitness();
        }

        while (selections.size() < popSize) {

            double iSum = 0;
            int j = 0;
            double alpha = rd.nextDouble(fitnesssum);

            do {
                iSum += chromosomes.get(j).getFitness();
                j += 1;
            } while (iSum < alpha && j < chromosomes.size()-1);
            selections.add(chromosomes.get(j));
        }
        return selections;
    }


    public static ArrayList<Chromosome> rank(ArrayList<Chromosome> chromosomes, int popSize){
        Collections.sort(chromosomes);
        ArrayList<Chromosome> selections = new ArrayList<>();

        double sum = 1.0/(chromosomes.size()-2.001);

        while (selections.size() < popSize){
            for (int i = 0; i < chromosomes.size(); i++) {
                double alpha = rd.nextDouble(sum);
                double probability = (double)i/(chromosomes.size()*(chromosomes.size()-1));

                if (probability <= alpha){
                    selections.add(chromosomes.get(i));
                    break;
                }
            }
        }
        return selections;
    }

    public static ArrayList<Chromosome> StochasticUniversalSampling(ArrayList<Chromosome> chromosomes, int popSize){
        ArrayList<Chromosome> selections = new ArrayList<>();

        double fitnessSum = 0;
        for (int i = 0; i < chromosomes.size(); i++) {
            fitnessSum += chromosomes.get(i).getFitness();
        }
        while (selections.size() < popSize){
                double meanFitness = (double)(1 / chromosomes.size()) * fitnessSum;
                double alpha = rd.nextDouble(0, 1);
                double sum = chromosomes.get(0).getFitness();
                double delta = alpha * meanFitness;
                int i = 0;
                do {
                    if (delta < sum) {
                        selections.add(chromosomes.get(i));
                    }else {
                        i++;
                        sum += chromosomes.get(i).getFitness();
                    }
                }while (i <chromosomes.size());
            }
        return selections;
    }
}
