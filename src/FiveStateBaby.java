import java.util.Arrays;

public class FiveStateBaby {
    static final int SLEEPING = 0;
    static final int EATING = 1;
    static final int CRYING = 2;
    static final int PLAYING = 3;
    static final int FUSSING = 4;
    static double[][] transitions = {
            {0.2, 0.4, 0.1, 0.15, 0.15},
            {0.4, 0.1, 0.0, 0.3, 0.2},
            {0.0, 0.2, 0.1, 0.2, 0.5},
            {0.1, 0.2, 0.1, 0.3, 0.3},
            {0.1, 0.1, 0.3, 0.4, 0.1}
    };
    private int currentState;

    public FiveStateBaby(int currentState) {
        this.currentState = currentState;
    }

    public static void main(String[] args) {
        FiveStateBaby fiveStateBaby = new FiveStateBaby(0);
        fiveStateBaby.train(new int[]{0,3,1,2,3,2,1,2,3,2,0,4,4,4,4,4,4,4,4,4,1,1,1,1,0,0,0,0});
        fiveStateBaby.train("342342342342342342342342342342342342342342342");
        fiveStateBaby.displayBabyStates(100);

    }

    public void displayBabyStates(int n) {
        for (int i = 0; i < n; i++) {
            transitionToNextState();
            System.out.println(i + 1
                    + " state:\t" + currentState);
        }
    }

    private void transitionToNextState() {
        this.currentState = predictState();
    }

    public int predictState() {
        double p = Math.random();
        double totalProb = transitions[currentState][SLEEPING];
        if (p < totalProb) return SLEEPING;
        totalProb += transitions[currentState][EATING];
        if (p < totalProb) return EATING;
        totalProb += transitions[currentState][EATING];
        if (p < totalProb) return CRYING;
        totalProb += transitions[currentState][CRYING];
        if (p < totalProb)
            return PLAYING;
        return FUSSING;
    }
    public void clear(){
        transitions = new double[5][5];
    }
    public void train(String sequence){
        char[] arr = sequence.toCharArray();
        int[] trainingdata = new int[arr.length];
        for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
            char c = arr[i];
            trainingdata[i]= Integer.parseInt(String.valueOf(c));
        }
        train(trainingdata);

    }
    int[][] counts = new int[5][5];
    public  void train(int[] stateSequence){
        for (int i = 1; i < stateSequence.length; i++) {
            counts[stateSequence[i-1]][stateSequence[i]]++;
        }
        for (int i = 0; i < counts.length; i++) {
            int sum = 0;
            for (int j = 0; j < counts.length; j++) {
                sum += counts[i][j];
            }
            for (int j = 0; j < counts.length; j++) {
                transitions[i][j] = counts[i][j]/(double)sum;
            }
        }
        System.out.println(Arrays.deepToString(transitions));
    }

}
