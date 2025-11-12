import java.util.*;

public class Krai {
    int dataInt;
    double dataDouble;
    boolean dataBool;
    String dataString;
    char dataChar;
    Krai protocol;
    Krai[] link;
    Krai dataTag;

    Krai() {
        dataInt = 0;
        dataDouble = 0.0;
        dataBool = false;
        dataString = "";
        dataChar = '\0';
        protocol = null;
        link = new Krai[0];
        dataTag = null;
    }

    Krai(int d, Krai[] links, Krai p) {
        dataInt = d;
        protocol = p;
        link = Arrays.copyOf(links, links.length);
        dataDouble=0.0;
        dataBool=false;
        dataChar='\0';
        dataString="";
        dataTag = null;
    }

    Krai(double d, Krai[] links, Krai p) {
        dataDouble = d;
        protocol = p;
        link = Arrays.copyOf(links, links.length);
        dataInt=0;
        dataBool=false;
        dataChar='\0';
        dataString="";
        dataTag = null;
    }

    Krai(boolean b, Krai[] links, Krai p) {
        dataBool = b;
        protocol = p;
        link = Arrays.copyOf(links, links.length);
        dataInt=0;
        dataChar='\0';
        dataDouble=0.0;
        dataString="";
        dataTag = null;
    }

    Krai(String s, Krai[] links, Krai p) {
        dataString = s;
        protocol = p;
        link = Arrays.copyOf(links, links.length);
        dataBool=false;
        dataChar='\0';
        dataInt=0;
        dataDouble=0.0;
        dataTag = null;
    }

    Krai(char c, Krai[] links, Krai p) {
        dataChar = c;
        protocol = p;
        link = Arrays.copyOf(links, links.length);
        dataInt=0;
        dataDouble=0.0;
        dataBool=false;
        dataString="";
        dataTag = null;
    }

    Krai[] getLinks() {
        return Arrays.copyOf(link, link.length);
    }

    Krai getProtocol() {
        return protocol;
    }

    int getDataInt() {
        return dataInt;
    }

    double getDataDouble() {
        return dataDouble;
    }

    boolean getDataBool() {
        return dataBool;
    }

    String getDataString() {
        return dataString;
    }

    char getDataChar() {
        return dataChar;
    }

    Krai getDataTag() {
        return dataTag;
    }

    void linked_list() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data for start:");
        int n = sc.nextInt();

        Krai start_protocol = new Krai();

        System.out.println("Enter number of nodes:");
        int t = sc.nextInt();
        Krai[] links = new Krai[1];
        Krai start = new Krai(n, links, start_protocol);

        Krai nptr = start;
        Krai current = nptr;

        for (int i = 1; i < t; i++) {
            System.out.print("Enter data for node " + (i + 1) + ": ");
            n = sc.nextInt();
            Krai[] newLink = new Krai[1];
            nptr = new Krai(n, newLink, null);
            current.link = Arrays.copyOf(new Krai[]{nptr}, 1);
            current = nptr;
        }

        System.out.println("\nLinked Krai chain formed:");
        Krai temp = start;
        int idx = 1;
        while (temp != null) {
            System.out.println("Node " + idx + " → dataInt: " + temp.getDataInt() +
                               " | protocol: " + (temp.getProtocol() == null ? "null" : "root"));
            if (temp.link.length == 0 || temp.link[0] == null) break;
            temp = temp.link[0];
            idx++;
        }
    }

    Krai protocol_equals(Krai protocol) {
        if (this.dataInt == protocol.dataInt) {
            return protocol;
        }
        return null;
    }

    Krai protocol_ROLARI(int a, Krai protocol) {
        if(protocol==null){
            return null;
        }
        if (a >= protocol.link.length || a < 0) {
            System.out.println("Invalid index given ");
            return protocol;    
        } else {
            Krai temp = new Krai();
            temp.dataInt=protocol.link[a].dataInt;
            temp.dataDouble=protocol.link[a].dataDouble;
            temp.dataString=protocol.link[a].dataString;
            temp.dataBool=protocol.link[a].dataBool;
            temp.dataChar=protocol.link[a].dataChar;
            temp.link=Arrays.copyOf(protocol.link[a].link,protocol.link[a].link.length);
            protocol.link[a].dataInt = this.dataInt;
            protocol.link[a].dataDouble = this.dataDouble;
            protocol.link[a].dataBool = this.dataBool;
            protocol.link[a].dataChar = this.dataChar;
            protocol.link[a].dataString = this.dataString;
            protocol.link[a].link = Arrays.copyOf(this.link, this.link.length);
            this.dataInt = temp.dataInt;
            this.dataDouble = temp.dataDouble;
            this.dataBool = temp.dataBool;
            this.dataChar = temp.dataChar;
            this.dataString = temp.dataString;
            this.link = Arrays.copyOf(temp.link, temp.link.length);           
            return protocol;
        }
    }

    Krai protocol_diffsim(Krai protocol, int a) {
        if (a >= this.link.length || a < 0) {
            return protocol;
        }
        if (protocol == null) {
            return null;
        } else {
            int j = Integer.MAX_VALUE;
            int p = -1;
            for (int i = 0; i < protocol.link.length; i++) {
                if (Math.abs(protocol.link[i].dataInt - this.link[a].dataInt) < j) {
                    j = Math.abs(protocol.link[i].dataInt - this.link[a].dataInt);
                    p = i;
                }
            }
            protocol = protocol.protocol_ROLARI(p, protocol);
            return protocol;
        }
    }

    double helperInt(Krai node, double... setVal) {
        if (node == null) return 0.0;
        if (setVal.length > 0) {
            node.dataInt = (int) Math.round(setVal[0]);
        }
        return (double) node.dataInt;
    }

    double helperDouble(Krai node, double... setVal) {
        if (node == null) return 0.0;
        if (setVal.length > 0) {
            node.dataDouble = setVal[0];
        }
        return node.dataDouble;
    }

    double helperBool(Krai node, double... setVal) {
        if (node == null) return 0.0;
        if (setVal.length > 0) {
            node.dataBool = setVal[0] >= 0.5;
        }
        return node.dataBool ? 1.0 : 0.0;
    }

    double helperString(Krai node, double... setVal) {
        if (node == null) return 0.0;
        if (setVal.length > 0) {
            int v = (int) Math.round(setVal[0]);
            int c = (v % 95) + 32;
            node.dataString = String.valueOf((char) c);
        }
        if (node.dataString == null || node.dataString.length() == 0) return 0.0;
        int sum = 0;
        for (int i = 0; i < node.dataString.length(); i++) sum += (int) node.dataString.charAt(i);
        return ((double) sum) / (double) node.dataString.length();
    }

    double helperChar(Krai node, double... setVal) {
        if (node == null) return 0.0;
        if (setVal.length > 0) {
            int v = (int) Math.round(setVal[0]);
            node.dataChar = (char) Math.max(0, Math.min(65535, v));
        }
        return (double) node.dataChar;
    }

    void eprop(double[] grads, Krai[] ancestry, int fieldIndex) {
        if (grads == null || ancestry == null) return;
        for (int i = 0; i < grads.length && i < ancestry.length; i++) {
            Krai a = ancestry[i];
            if (a == null) continue;
            double g = grads[i];
            if (fieldIndex == 0) {
                double cur = helperInt(a);
                helperInt(a, cur - g);
            } else if (fieldIndex == 1) {
                double cur = helperDouble(a);
                helperDouble(a, cur - g);
            } else if (fieldIndex == 2) {
                double cur = helperBool(a);
                helperBool(a, cur - g);
            } else if (fieldIndex == 3) {
                double cur = helperString(a);
                helperString(a, cur - g);
            } else if (fieldIndex == 4) {
                double cur = helperChar(a);
                helperChar(a, cur - g);
            }
        }
    }

    public void Al(int n) {
        if (n <= 0) return;
        Krai[] ancestry = new Krai[n];
        int m = 0;
        Krai p = this.protocol;
        while (p != null && m < n) {
            ancestry[m++] = p;
            p = p.protocol;
        }
        if (m == 0) return;
        int iters = Math.max(10, n * 10);
        for (int iter = 0; iter < iters; iter++) {
            for (int field = 0; field < 5; field++) {
                double[] vals = new double[m];
                for (int i = 0; i < m; i++) {
                    if (field == 0) vals[i] = helperInt(ancestry[i]);
                    else if (field == 1) vals[i] = helperDouble(ancestry[i]);
                    else if (field == 2) vals[i] = helperBool(ancestry[i]);
                    else if (field == 3) vals[i] = helperString(ancestry[i]);
                    else vals[i] = helperChar(ancestry[i]);
                }
                double mean = 0.0;
                for (int i = 0; i < m; i++) mean += vals[i];
                mean /= (double) m;
                double target = 0.0;
                if (field == 0) target = helperInt(this);
                else if (field == 1) target = helperDouble(this);
                else if (field == 2) target = helperBool(this);
                else if (field == 3) target = helperString(this);
                else target = helperChar(this);
                double err = mean - target;
                double[] grads = new double[m];
                for (int i = 0; i < m; i++) grads[i] = (err) / (double) m * (1.0 / (1.0 + iter * 0.01));
                eprop(grads, ancestry, field);
            }
        }
    }

    public void autocomp(int n) {
        Al(n);
        Krai[] ancestry = new Krai[n];
        int m = 0;
        Krai p = this.protocol;
        while (p != null && m < n) {
            ancestry[m++] = p;
            p = p.protocol;
        }
        if (m == 0) return;
        double sumInt = 0.0;
        double sumDouble = 0.0;
        double sumBool = 0.0;
        double sumString = 0.0;
        double sumChar = 0.0;
        for (int i = 0; i < m; i++) {
            sumInt += helperInt(ancestry[i]);
            sumDouble += helperDouble(ancestry[i]);
            sumBool += helperBool(ancestry[i]);
            sumString += helperString(ancestry[i]);
            sumChar += helperChar(ancestry[i]);
        }
        double avgInt = sumInt / (double) m;
        double avgDouble = sumDouble / (double) m;
        double avgBool = sumBool / (double) m;
        double avgString = sumString / (double) m;
        double avgChar = sumChar / (double) m;
        if (this.dataString == null || this.dataString.length() == 0) helperString(this, avgString);
        if (this.dataChar == '\0') helperChar(this, avgChar);
        if (this.dataInt == 0) helperInt(this, avgInt);
        if (this.dataDouble == 0.0) helperDouble(this, avgDouble);
        if (!this.dataBool) helperBool(this, avgBool);
    }

    public void GenKrai() {
        int len = this.link.length;
        if (len == 0) {
            Krai nk = new Krai();
            nk.dataInt = this.dataInt;
            nk.dataDouble = this.dataDouble;
            nk.dataBool = this.dataBool;
            nk.dataString = this.dataString;
            nk.dataChar = this.dataChar;
            nk.protocol = this.protocol;
            this.link = Arrays.copyOf(this.link, 1);
            this.link[0] = nk;
            return;
        }
        int m = Math.max(1, len);
        double sumDi = 0.0;
        double sumDd = 0.0;
        double sumDb = 0.0;
        double sumDs = 0.0;
        double sumDc = 0.0;
        for (int i = 1; i < len; i++) {
            sumDi += helperInt(this.link[i]) - helperInt(this.link[i - 1]);
            sumDd += helperDouble(this.link[i]) - helperDouble(this.link[i - 1]);
            sumDb += helperBool(this.link[i]) - helperBool(this.link[i - 1]);
            sumDs += helperString(this.link[i]) - helperString(this.link[i - 1]);
            sumDc += helperChar(this.link[i]) - helperChar(this.link[i - 1]);
        }
        double di = len > 1 ? sumDi / (double) (len - 1) : helperInt(this.link[len - 1]) - helperInt(this);
        double dd = len > 1 ? sumDd / (double) (len - 1) : helperDouble(this.link[len - 1]) - helperDouble(this);
        double db = len > 1 ? sumDb / (double) (len - 1) : helperBool(this.link[len - 1]) - helperBool(this);
        double ds = len > 1 ? sumDs / (double) (len - 1) : helperString(this.link[len - 1]) - helperString(this);
        double dc = len > 1 ? sumDc / (double) (len - 1) : helperChar(this.link[len - 1]) - helperChar(this);
        Krai last = this.link[len - 1];
        Krai nk = new Krai();
        helperInt(nk, helperInt(last) + di);
        helperDouble(nk, helperDouble(last) + dd);
        helperBool(nk, helperBool(last) + db);
        helperString(nk, helperString(last) + ds);
        helperChar(nk, helperChar(last) + dc);
        nk.protocol = this.protocol;
        this.link = Arrays.copyOf(this.link, this.link.length + 1);
        this.link[this.link.length - 1] = nk;
    }

    public static void main(String[] args) {
        Krai demo = new Krai();
        demo.linked_list();
    }
}
