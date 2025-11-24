class Intel{
  private static int __depth = 0;
  Krai[] create1(boolean cyclic,Krai... iu){
    for(int i=0;i<iu.length;i++){
      if(i>0){
        iu[i].protocol=iu[i-1].protocol.link[0];
      }
      if(cyclic==true){
        iu[iu.length-1].protocol.link[0]=iu[0];
      }
    }
    return iu;
  }
  void self_arrange(Krai[] link){
    int n=link.length;
    for (int i = 0; i < n - 2; i++){
      double d = dissimilarity(link[i], link[i+1]);
      for(int j=i;j<n;j++){
        if(d > dissimilarity(link[j], link[i+1])){
          link[i+1]=link[i+1].protocol_ROLARI(0,link[j].protocol);
        }
      }
    }
  }
    double dissimilarity(Krai a,Krai b){
      // Minimal dissimilarity metric using integer payloads and string length differences
      if(a==null || b==null) return Double.MAX_VALUE;
      double diff = Math.abs(a.dataInt - b.dataInt);
      if(a.dataString != null && b.dataString != null){
        diff += Math.abs(a.dataString.length() - b.dataString.length());
      }
      return diff;
    }
  }
