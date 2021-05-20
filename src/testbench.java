import java.util.Arrays;

public class testbench {

    public static void main(String[] args) {
        int[] vw = new int[]{2,4,3,6};
        int[][] ew = new int[][]{
                {0,18,5,AllDistances.inf},
                {18,0,2,3},
                {5,2,0,AllDistances.inf},
                {AllDistances.inf,3,AllDistances.inf,0}};
        AllDistances dist = new AllDistances(vw, ew);
        System.out.println(dist.distance(1,4) + "\n");
        int[][] tmp = dist.distance_matrix();
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(Arrays.toString(tmp[i]));
        }
        System.out.println("\n" + dist.path(1,4));
    }
}
