import java.util.*;

public class Krai {
    int dataInt;
    double dataDouble;
    boolean dataBool;
    String dataString;
    char dataChar;
    Krai protocol;
    Krai[] link;

    Krai() {
        dataInt = 0;
        dataDouble = 0.0;
        dataBool = false;
        dataString = "";
        dataChar = '\0';
        protocol = null;
        link = new Krai[0];
    }

    Krai(int d, Krai[] links, Krai p) {
        dataInt = d;
        protocol = p;
        setLinks(links);
    }

    Krai(double d, Krai[] links, Krai p) {
        dataDouble = d;
        protocol = p;
        setLinks(links);
    }

    Krai(boolean b, Krai[] links, Krai p) {
        dataBool = b;
        protocol = p;
        setLinks(links);
    }

    Krai(String s, Krai[] links, Krai p) {
        dataString = s;
        protocol = p;
        setLinks(links);
    }

    Krai(char c, Krai[] links, Krai p) {
        dataChar = c;
        protocol = p;
        setLinks(links);
    }

    public void setLinks(Krai[] links) {
        if (links == null) {
            this.link = new Krai[0];
            return;
        }
        this.link = Arrays.copyOf(links, links.length);
        for (Krai k : links) {
            if (k == null) continue;
            boolean found = false;
            if (k.link != null) {
                for (Krai kk : k.link) {
                    if (kk == this) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                if (k.link == null) {
                    k.link = new Krai[]{this};
                } else {
                    Krai[] newArr = Arrays.copyOf(k.link, k.link.length + 1);
                    newArr[newArr.length - 1] = this;
                    k.link = newArr;
                }
            }
        }
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
            current.setLinks(new Krai[]{nptr});
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
            Krai target = protocol.link[a];
            if (target == null) return protocol;
            Krai temp = new Krai();
            temp.dataInt = target.dataInt;
            temp.dataDouble = target.dataDouble;
            temp.dataString = target.dataString;
            temp.dataBool = target.dataBool;
            temp.dataChar = target.dataChar;
            temp.link = Arrays.copyOf(target.link, target.link == null ? 0 : target.link.length);
            target.dataInt = this.dataInt;
            target.dataDouble = this.dataDouble;
            target.dataBool = this.dataBool;
            target.dataChar = this.dataChar;
            target.dataString = this.dataString;
            target.link = Arrays.copyOf(this.link, this.link == null ? 0 : this.link.length);
            this.dataInt = temp.dataInt;
            this.dataDouble = temp.dataDouble;
            this.dataBool = temp.dataBool;
            this.dataChar = temp.dataChar;
            this.dataString = temp.dataString;
            this.link = Arrays.copyOf(temp.link, temp.link == null ? 0 : temp.link.length);
            if (a >= 0 && a < protocol.link.length) protocol.link[a] = this;
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

    public static void main(String[] args) {
        Krai demo = new Krai();
        demo.linked_list();
    }
}
