package index;

public class IndexEntry { //索引项
    public String v; //到哪个点
    public int dis; //距离
    public double prob; //概率
    IndexEntry(String V,int D,double P){
        v=V; dis=D; prob=P;
    }
}
