package hw3.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        List[] l = new List[M];
        for (int i = 0; i < M; i++) {
            l[i] = new ArrayList<Oomage>();

        }
        for (int i = 0; i < oomages.size(); i++){
            l[(oomages.get(i).hashCode() & 0x7FFFFFFF ) % M].add(oomages.get(i));

        }
        for (int i = 0; i < M; i++){
            if (l[i].size() >= oomages.size() / 2.5 || l[i].size() <= oomages.size() / 50){
                return false;
            }
        }
        return true;
    }
}
