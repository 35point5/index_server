package index;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class IndexManager {
    Map<String, Map<String, Index>> indexes;
    private static IndexManager instance = new IndexManager();

    private IndexManager() {
        indexes = new HashMap<>();
    }

    public static IndexManager getInstance() {
        return instance;
    }

    List<Edge> readGraph(String graphName) {
        File f = new File("./dataset/" + graphName + "/graph.txt");
        try {
            Scanner s = new Scanner(f);
            List<Edge> l = new LinkedList<Edge>();
//            int maxLine = 100;
            while (true) {
                try {
                    int u, v;
                    double p;
                    u = s.nextInt();
                    v = s.nextInt();
                    p = s.nextDouble();
                    Edge e = new Edge(Integer.toString(u), Integer.toString(v), p);
                    l.add(e);
//                    --maxLine;
//                    if (maxLine == 0) break;
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            return l;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public int Add(String graphName, String indexName) {
        if (!indexes.containsKey(graphName)) {
            indexes.put(graphName, new HashMap<>());
        }
        indexes.get(graphName).put(indexName, new Index());
        List<Edge> edges = readGraph(graphName);
        if (edges != null) {
            indexes.get(graphName).get(indexName).Init(edges);
            return 1;
        } else {
            return 0;
        }
    }

    public List<IndexEntry> QueryIndex(String graphName, String indexName, String node) {
        return indexes.get(graphName).get(indexName).getIndex(node);
    }

    public Object Operator(String graphName, String indexName, String opType, Map<String, Object> params) {
        if (opType.equals("shortest_path")) {
            return indexes.get(graphName).get(indexName).QueryDis(params.get("v1_id").toString(), params.get("v2_id").toString());
        } else if (opType.equals("reliability")) {
            return indexes.get(graphName).get(indexName).QueryProb(params.get("v1_id").toString(), params.get("v2_id").toString());
        }
        return null;
    }

    public int Delete(String graphName, String indexName){
        if (indexes.containsKey(graphName) && indexes.get(graphName).containsKey(indexName)){
            indexes.get(graphName).remove(indexName);
            return 1;
        }
        else {
            return 0;
        }
    }
}
