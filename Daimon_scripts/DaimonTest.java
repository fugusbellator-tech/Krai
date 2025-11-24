public class DaimonTest{
    public static void main(String[] args){
        // Demo: parse ARC-AGI-2 JSONL with offensive_bias parser
        try{
            java.nio.file.Path demo = java.nio.file.Paths.get("demo_train.jsonl");
            String sample = "{\"train\":[{\"input\":{\"grid\": [\"r g b\", \"g . r\"]}, \"output\": {\"grid\": [\"r g b\", \"g . r\"]}}]}\n";
            java.nio.file.Files.writeString(demo, sample);
            java.util.List<offensive_bias.GridExample> ex = offensive_bias.parseArcAgi2Train(demo.toString(), java.util.Map.of("r", 1, "g", 2, "b", 3, ".", 0));
            System.out.println("Demo loader parsed examples count: " + ex.size());
            if(ex.size() > 0){
                offensive_bias.GridExample g = ex.get(0);
                System.out.println("Input Grid Tokens:");
                for(int i=0;i<g.inputGrid.length;i++){
                    for(int j=0;j<g.inputGrid[i].length;j++) System.out.print(g.inputGrid[i][j] + " ");
                    System.out.println();
                }
                if(g.inputInts != null){
                    System.out.println("Input Grid Ints:");
                    for(int i=0;i<g.inputInts.length;i++){
                        for(int j=0;j<g.inputInts[i].length;j++) System.out.print(g.inputInts[i][j] + " ");
                        System.out.println();
                    }
                }
            }
        } catch(Exception e){ e.printStackTrace(); }

        daimon_pyrtanis d = new daimon_pyrtanis();
        Krai t = d.thought("Turn on the living-room light at 8 pm, please.");
        Krai[] top = t.getLinks();
        System.out.println("Thought protocol: " + t.getDataString());
        System.out.println("TOP LENGTH: " + top.length);
        System.out.println("POS_NP: " + d.POS_NP.getDataString());
        Krai tokens = top[0];
        Krai parse = top[1];
        System.out.println("TOKENS:");
        Krai[] tk = tokens.getLinks();
        for(int i=0;i<tk.length;i++){
            String p = tk[i].getProtocol()==null?"null":tk[i].getProtocol().getDataString();
            System.out.println(i+": " + tk[i].getDataString() + " -> pos=" + p);
        }
        System.out.println("PARSE:");
        Krai[] ps = parse.getLinks();
        System.out.println("SUBJ: " + ps[0].getDataString() + " pos=" + ps[0].getProtocol().getDataString());
        System.out.println("VERB: " + ps[1].getDataString() + " pos=" + ps[1].getProtocol().getDataString());
        System.out.println("OBJ: " + ps[2].getDataString() + " pos=" + ps[2].getProtocol().getDataString());
        System.out.println("INTENT: " + top[2].getDataString());
        Krai[] sls = top[3].getLinks();
        System.out.println("SLOTS:");
        for(int i=0;i<sls.length;i++){
            System.out.println(sls[i].getDataString() + " -> " + (sls[i].getDataTag()==null?"":sls[i].getDataTag().getDataString()));
        }
        System.out.println("CONFIDENCE: " + top[4].getDataDouble());
    }
}