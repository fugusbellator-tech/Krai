class Intel{
 Krai[] create1(boolean cylcic,Krai... iu){//iu for intelligence unit, nominally, this create1 is/shud be used for creating the very base of a krai intelligence structure.period.
   for(int i=0;i<iu.length;i++){
     if(i>0){
       iu[i].protocol=iu[i-1].protocol;
     }
     if(cyclic=true){
       iu[iu.length-1].protocol=iu[0];}     
   }
   return iu;
   }
 }
