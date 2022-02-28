import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MrHoGradeCalculator {
    static final double s_a_cutoff = 0.9;//skill A level
    final static double s_b_cutoff = 0.8; //skill B level
    final static double s_c_cutoff = 0.7; //skill C level
    final static double i_a_cutoff = 0.8; //insight A level
    final static double i_b_cutoff = 0.6;//insight B level
    final static double i_c_cutoff = 0.4; //insight C level

    public static void main(String[] args) throws Exception {
        ArrayList<Double> scores = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter fout = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            scores.add(Double.parseDouble(st.nextToken()));
        }
        double improvement = Double.MIN_VALUE;
        for (int i = 0; i < scores.size(); i++) {
            for (int j = i; j < scores.size(); j++) {
                improvement = Math.max(improvement, scores.get(j) - scores.get(i));
            }
        }
        double score = scores.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        fout.println(improvement + " improvement addon");
        double insight = (score + improvement) / (N * 10);
        fout.println("enter skills score");
        st = new StringTokenizer(br.readLine());
        double skills = Double.parseDouble(st.nextToken());
        fout.println("insight score: " + insight);
        fout.println(letterGrade(skills, insight));
        fout.close();
    }

    public static char letterGrade(double skills, double insight) throws Exception {
        char cgrade = ' ';
        if (skills < s_c_cutoff) {
            cgrade = 'F';
        } else if (skills >= s_c_cutoff && skills < s_b_cutoff) {
            if (insight >= (i_c_cutoff - i_a_cutoff) / (s_b_cutoff - s_c_cutoff) * (skills - s_c_cutoff) + i_a_cutoff) {
                cgrade = 'B';
            } else {
                cgrade = 'C';
            }
        } else if (skills >= s_b_cutoff && skills < s_a_cutoff) {
            if (insight >= (i_b_cutoff - i_a_cutoff) / (s_a_cutoff - s_b_cutoff) * (skills - s_b_cutoff) + i_a_cutoff) {
                cgrade = 'A';
            } else if (insight >= i_c_cutoff) {
                cgrade = 'B';
            } else {
                cgrade = 'C';
            }
        } else if (skills >= s_a_cutoff && skills <= 1) {
            if (insight >= i_b_cutoff) {
                cgrade = 'A';
            } else if (insight >= i_c_cutoff) {
                cgrade = 'B';
            } else {
                cgrade = 'C';
            }
        } else {
            throw new Exception("Some input error");
        }
        return cgrade;
    }
}
