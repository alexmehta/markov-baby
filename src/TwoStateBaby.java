public class TwoStateBaby {
    static final String[] stateNames = {"awake", "sleeping"};
    static final int AWAKE = 0;
    static final int SLEEPING = 1;
    double[][] transition = {{0.3, 0.7}, {0.1, 0.9}};
    private int currentState;

    public TwoStateBaby(int currentState) {
        this.currentState = currentState;
    }

    public static void main(String[] args) {
        TwoStateBaby twoStateBaby = new TwoStateBaby(0);
        twoStateBaby.displayBabyStates(100);
    }

    public void displayBabyStates(int n) {
        for (int i = 0; i < n; i++) {
            transitionToNextState();
            System.out.println(i
                    + " state:\t" + (currentState == AWAKE ? "Awake" : "Sleeping"));
        }
    }

    public int mostLikelyNextState() {
        return transition[currentState][AWAKE] > transition[currentState][SLEEPING] ? AWAKE : SLEEPING;
    }

    public void transitionToNextState() {
        this.currentState = getNextState();
    }

    public int getNextState() {
        return Math.random() < transition[currentState][0] ? AWAKE : SLEEPING;
    }
}
