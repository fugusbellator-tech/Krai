public class mendicant_bias {

    public static Krai[] buildNetwork() {
        Krai in1 = new Krai(1, new Krai[0], null);
        Krai in2 = new Krai(2, new Krai[0], null);
        Krai in3 = new Krai(3, new Krai[0], null);

        Krai activation = new Krai(3, new Krai[0], null);

        Krai out1 = new Krai(0, new Krai[] { in1, in2, in3 }, activation);
        Krai out2 = new Krai(0, new Krai[] { in3, in2, in1 }, activation);

        return new Krai[] { in1, in2, in3, out1, out2 };
    }

    public static void backpropagate(Krai[] net, int[] desired) {
        int inputsCount = 0;
        for (int i = 0; i < net.length; i++) if (net[i].link.length == 0) inputsCount++;

        for (int oi = inputsCount; oi < net.length; oi++) {
            Krai out = net[oi];
            Krai[] links = out.getLinks();

            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for (int j = 0; j < links.length; j++) {
                if (j > 0) sb.append(' ');
                sb.append(links[j].getDataInt());
                int weight = links.length - j;
                sum += links[j].getDataInt() * weight;
            }

            out.dataString = sb.toString();
            if (out.getProtocol() != null) out.getProtocol().dataTag = out;

            int activated = (sum > out.getProtocol().getDataInt() * 2) ? 1 : 0;
            out.dataInt = activated;

            int dIndex = oi - inputsCount;
            int target = (dIndex < desired.length) ? desired[dIndex] : 0;
            int error = target - out.getDataInt();

            int scale = 1;
            int maxShift = Math.max(1, links.length - 1);

            for (int j = 0; j < links.length; j++) {
                int k = Math.min(maxShift, Math.abs(error) * scale);
                if (k <= 0) continue;
                int newIdx = j;
                if (error > 0) newIdx = Math.max(0, j - k);
                else if (error < 0) newIdx = Math.min(links.length - 1, j + k);
                try { links[j].protocol_ROLARI(newIdx, out); } catch (Exception e) { }
            }
        }
    }

    public static void main(String[] args) {
        Krai[] net = buildNetwork();
        int[] desired = new int[] { 1, 0 };

        System.out.println("Initial network state:");
        for (int i = 0; i < net.length; i++) {
            System.out.println(i + ": dataInt=" + net[i].getDataInt() + " links=" + net[i].link.length + " dataString='" + net[i].getDataString() + "'");
        }

        for (int epoch = 0; epoch < 6; epoch++) {
            backpropagate(net, desired);
            System.out.println("Epoch " + epoch + " results:");
            for (int i = 3; i < net.length; i++) {
                System.out.println(" out" + (i - 3) + " -> dataInt=" + net[i].getDataInt() + " inputs='" + net[i].getDataString() + "'");
            }
        }
    }
}
