import java.util.*;
import java.io.*;

public class KMP {
  public static void main(String[] args) throws Exception{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    for (String W = in.readLine(); W != null; W = in.readLine()) {
      String S = in.readLine();
      KMP myKMP = new KMP(W);
      List<Integer> indices = myKMP.search(S);
      for (int i = 0; i < indices.size(); i++) {
        System.out.printf("%s%d", (i != 0 ? " " : ""), indices.get(i));
      }
      System.out.println();
    }
  }

  int[] T;
  char[] W;

  public KMP(String Wrd) {
    if (Wrd.length() == 0) throw new IllegalArgumentException();
    W = Wrd.toCharArray();
    T = new int[W.length+1];
    T[0] = -1;
    T[1] = 0;
    int cnd = 0;
    for (int pos = 2; pos < T.length; pos++) {
      if (W[pos-1] == W[cnd]) {
        T[pos] = ++cnd;
      } else if (cnd > 0) {
        cnd = T[cnd];
        T[pos] = 0;
        pos--;
      } else {
        T[pos] = 0;
      }
    }
  }

  public List<Integer> search(String Str) {
    List<Integer> indices = new ArrayList<>();
    char[] S = Str.toCharArray();
    int m = 0;
    int i = 0;
    while (m+i < S.length) {
      if (W[i] == S[m+i]) {
        i++;
        if (i == W.length) {
          indices.add(m);
          m = m + i - T[i];
          i = T[i];
        }
      } else if (T[i] > -1) {
        m = m + i - T[i];
        i = T[i];
      } else {
        m++;
        i = 0;
      }
    }
    return indices;
  }
}
