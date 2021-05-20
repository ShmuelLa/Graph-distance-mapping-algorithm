public class AllDistances {

    public static final int inf = 1000000;
    private int[] _vw;
    private int[][] _ew;
    private int[][] _matrix;
    private int _min_i;
    private int _max_i;
    private int _min_j;
    private int _max_j;
    private int _v_count;

    public AllDistances(int[] vertices_weights, int[][] edges_weights) {
        _vw = vertices_weights;
        _ew = edges_weights;
        _v_count = _vw.length;
        _matrix = new int[_v_count][_v_count];
        for (int i = 0; i < _v_count; i++) _matrix[i][i] = _vw[i];
        for (int i = 0; i < _v_count; i++) {
            for (int j = i + 1; j < _v_count; j++) {
                _matrix[i][j] = _ew[i][j] + _matrix[i][i] + _matrix[j][j];
                if (_matrix[i][j] > inf) _matrix[i][j] = inf;
            }
        }
        for (int k = 0; k < _v_count; k++) {
            for (int i = 0; i < _v_count; i++) {
                for (int j = i + 1; j < _v_count; j++) {
                    _min_i = i;
                    _max_i = k;
                    _min_j = k;
                    _max_j = j;
                    if (j < k) {
                        _min_j = j;
                        _max_j = k;
                    }
                    if (k < i) {
                        _min_i = k;
                        _max_i = i;
                    }
                    if (_matrix[i][j] > _matrix[_min_i][_max_i] + _matrix[_min_j][_max_j] - _matrix[k][k]) {
                        _matrix[i][j] = _matrix[_min_i][_max_i] + _matrix[_min_j][_max_j] - _matrix[k][k];
                    }
                }
            }
        }
        for (int i = 1; i < _v_count; i++) {
            for (int j = 0; j < i; j++) {
                _matrix[i][j] = _matrix[j][i];
            }
        }
    }

    public int distance(int u, int v) {
        return _matrix[u - 1][v - 1];
    }

    public String path(int u, int v) {
        String result = String.valueOf(v);
        if (u == v) return result;
        int distance = _matrix[u - 1][v - 1];
        if (distance == inf) return "";
        int tmp = v - 1;
        for (int i = 0; tmp != (u-1); i++) {
            if (_ew[tmp][i] < inf) {
                if (distance - _ew[tmp][i] - _matrix[tmp][tmp] == _matrix[u - 1][i]) {
                    distance = _matrix[u - 1][i];
                    result = String.valueOf(i + 1) + "-" + result;
                    tmp = i;
                    i = -1;
                }
            }
        }
        return result;
    }

    public int[][] distance_matrix() {
        return _matrix;
    }
}
