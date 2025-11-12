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

    public Krai AL(int n) {
        if (this.protocol != null && this.protocol != this) {
            if (this.protocol.dataString != null && this.protocol.dataString.length() > 0 && this.protocol.dataString.charAt(0) == 'R') {
                this.dataString = this.protocol.dataString;
                return this;
            }
            this.protocol.AL(n);
        }
        int len = 0;
        Krai p = this;
        while (p != null) { len++; p = p.protocol; }
        if (len < 2) { this.dataString = "R:EMPTY;"; return this; }
        Krai[] nodes = new Krai[len];
        p = this;
        for (int i = len - 1; i >= 0; i--) { nodes[i] = p; p = p.protocol; }
        int[] ia = new int[len];
        double[] da = new double[len];
        int[] ba = new int[len];
        for (int i = 0; i < len; i++) { ia[i] = nodes[i].dataInt; da[i] = nodes[i].dataDouble; ba[i] = nodes[i].dataBool ? 1 : 0; }
        String out = "R:";
        int maxOrder = n;
        if (maxOrder > len - 1) maxOrder = len - 1;
        double tol = 1e-3;
        for (int typ = 0; typ < 3; typ++) {
            double[] seq = new double[len];
            if (typ == 0) for (int i = 0; i < len; i++) seq[i] = ia[i];
            else if (typ == 1) for (int i = 0; i < len; i++) seq[i] = da[i];
            else for (int i = 0; i < len; i++) seq[i] = ba[i];
            double mean = 0; for (int i = 0; i < len; i++) mean += seq[i]; mean /= len;
            double energy = 0; for (int i = 0; i < len; i++) energy += (seq[i] - mean) * (seq[i] - mean);
            double base = Math.sqrt(energy / len);
            boolean found = false;
            int foundOrder = 0;
            double[] foundCoeffs = null;
            for (int order = 1; order <= maxOrder && !found; order++) {
                int m = len - order;
                if (m <= 0) break;
                double[][] ATA = new double[order][order];
                double[] ATb = new double[order];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < order; j++) {
                        double xj = seq[order + i - 1 - j];
                        ATb[j] += xj * seq[order + i];
                        for (int k = 0; k < order; k++) ATA[j][k] += xj * seq[order + i - 1 - k];
                    }
                }
                double[][] mat = new double[order][order + 1];
                for (int i = 0; i < order; i++) { for (int j = 0; j < order; j++) mat[i][j] = ATA[i][j]; mat[i][order] = ATb[i]; }
                boolean singular = false;
                for (int col = 0; col < order; col++) {
                    int pivot = col;
                    for (int r2 = col; r2 < order; r2++) if (Math.abs(mat[r2][col]) > Math.abs(mat[pivot][col])) pivot = r2;
                    if (Math.abs(mat[pivot][col]) < 1e-12) { singular = true; break; }
                    if (pivot != col) { double[] tmp = mat[col]; mat[col] = mat[pivot]; mat[pivot] = tmp; }
                    double div = mat[col][col];
                    for (int c2 = col; c2 <= order; c2++) mat[col][c2] /= div;
                    for (int r2 = 0; r2 < order; r2++) if (r2 != col) {
                        double mult = mat[r2][col];
                        for (int c2 = col; c2 <= order; c2++) mat[r2][c2] -= mult * mat[col][c2];
                    }
                }
                if (singular){                    
                   continue;}
                double[] coeffs = new double[order];
                for (int i = 0; i < order; i++) coeffs[i] = mat[i][order];
                double rss = 0;
                for (int i = 0; i < m; i++) {
                    double pred = 0;
                    for (int j = 0; j < order; j++) pred += coeffs[j] * seq[order + i - 1 - j];
                    double res = pred - seq[order + i]; rss += res * res;
                }
                double rms = Math.sqrt(rss / Math.max(1, m));
                double rel = base == 0 ? rms : rms / base;
                if (rel <= tol) { found = true; foundOrder = order; foundCoeffs = coeffs; break; }
            }
            if (found && foundCoeffs != null && foundOrder > 0) {
                out += (typ == 0 ? "I:AR;O=" : (typ == 1 ? "D:AR;O=" : "B:AR;O=")) + foundOrder + ";C=";
                for (int ii = 0; ii < foundCoeffs.length; ii++) { out += Double.toString(foundCoeffs[ii]); if (ii + 1 < foundCoeffs.length) out += ","; }
                out += ";";
            }
            if (!found) {
                int maxPoly = maxOrder;
                for (int deg = 1; deg <= maxPoly && !found; deg++) {
                    int cols = deg + 1;
                    double[][] ATA = new double[cols][cols];
                    double[] ATb = new double[cols];
                    for (int t0 = 0; t0 < len; t0++) {
                        double v = 1;
                        double[] row = new double[cols];
                        for (int c0 = 0; c0 < cols; c0++) { row[c0] = v; v *= t0; }
                        for (int i = 0; i < cols; i++) { for (int j = 0; j < cols; j++) ATA[i][j] += row[i] * row[j]; ATb[i] += row[i] * seq[t0]; }
                    }
                    double[][] mat = new double[cols][cols + 1];
                    for (int i = 0; i < cols; i++) { for (int j = 0; j < cols; j++) mat[i][j] = ATA[i][j]; mat[i][cols] = ATb[i]; }
                    boolean singular = false;
                    for (int col = 0; col < cols; col++) {
                        int pivot = col;
                        for (int r2 = col; r2 < cols; r2++) if (Math.abs(mat[r2][col]) > Math.abs(mat[pivot][col])) pivot = r2;
                        if (Math.abs(mat[pivot][col]) < 1e-12) { singular = true; break; }
                        if (pivot != col) { double[] tmp = mat[col]; mat[col] = mat[pivot]; mat[pivot] = tmp; }
                        double div = mat[col][col];
                        for (int c2 = col; c2 <= cols; c2++) mat[col][c2] /= div;
                        for (int r2 = 0; r2 < cols; r2++) if (r2 != col) {
                            double mult = mat[r2][col];
                            for (int c2 = col; c2 <= cols; c2++) mat[r2][c2] -= mult * mat[col][c2];
                        }
                    }
                    if (singular) continue;
                    double[] coeffs = new double[cols];
                    for (int i = 0; i < cols; i++) coeffs[i] = mat[i][cols];
                    double rss = 0;
                    for (int t0 = 0; t0 < len; t0++) {
                        double v = 1; double pred = 0;
                        for (int c0 = 0; c0 < cols; c0++) { pred += coeffs[c0] * v; v *= t0; }
                        double rres = pred - seq[t0]; rss += rres * rres;
                    }
                    double rms = Math.sqrt(rss / len);
                    double rel = base == 0 ? rms : rms / base;
                    if (rel <= tol) { found = true; out += (typ == 0 ? "I:POLY=" : (typ == 1 ? "D:POLY=" : "B:POLY=")) + deg + ";C="; for (int ii = 0; ii < coeffs.length; ii++) { out += Double.toString(coeffs[ii]); if (ii + 1 < coeffs.length) out += ","; } out += ";"; break; }
                }
            }
            if (!found) {
                boolean oklog = true;
                for (int i = 0; i < len; i++) if (seq[i] <= 0) { oklog = false; break; }
                if (oklog) {
                    double[] lseq = new double[len]; for (int i = 0; i < len; i++) lseq[i] = Math.log(seq[i]);
                    double[][] ATA = new double[2][2]; double[] ATb = new double[2];
                    for (int t0 = 0; t0 < len; t0++) {
                        double x0 = 1; double x1 = t0;
                        ATA[0][0] += x0 * x0; ATA[0][1] += x0 * x1; ATA[1][0] += x1 * x0; ATA[1][1] += x1 * x1;
                        ATb[0] += x0 * lseq[t0]; ATb[1] += x1 * lseq[t0];
                    }
                    double[][] mat = new double[2][3]; for (int i = 0; i < 2; i++) { for (int j = 0; j < 2; j++) mat[i][j] = ATA[i][j]; mat[i][2] = ATb[i]; }
                    boolean singular = false;
                    for (int col = 0; col < 2; col++) {
                        int pivot = col; for (int r2 = col; r2 < 2; r2++) if (Math.abs(mat[r2][col]) > Math.abs(mat[pivot][col])) pivot = r2;
                        if (Math.abs(mat[pivot][col]) < 1e-12) { singular = true; break; }
                        if (pivot != col) { double[] tmp = mat[col]; mat[col] = mat[pivot]; mat[pivot] = tmp; }
                        double div = mat[col][col]; for (int c2 = col; c2 <= 2; c2++) mat[col][c2] /= div;
                        for (int r2 = 0; r2 < 2; r2++) if (r2 != col) { double mult = mat[r2][col]; for (int c2 = col; c2 <= 2; c2++) mat[r2][c2] -= mult * mat[col][c2]; }
                    }
                    if (!singular) {
                        double a0 = mat[0][2]; double a1 = mat[1][2];
                        double rss = 0; for (int t0 = 0; t0 < len; t0++) { double pred = a0 + a1 * t0; double rres = pred - lseq[t0]; rss += rres * rres; }
                        double rms = Math.sqrt(rss / len); double rel = base == 0 ? rms : rms / base;
                        if (rel <= tol) { found = true; out += (typ == 0 ? "I:EXP;O=1;C=" : (typ == 1 ? "D:EXP;O=1;C=" : "B:EXP;O=1;C=")); out += Double.toString(a0) + "," + Double.toString(a1) + ";"; }
                    }
                }
            }
            if (!found) {
                int maxK = Math.max(1, len / 2);
                for (int k = 1; k <= maxK && !found; k++) {
                    double[][] ATA = new double[2][2]; double[] ATb = new double[2];
                    for (int t0 = 0; t0 < len; t0++) {
                        double s = Math.sin(2 * Math.PI * k * t0 / len); double c0 = Math.cos(2 * Math.PI * k * t0 / len);
                        ATA[0][0] += s * s; ATA[0][1] += s * c0; ATA[1][0] += c0 * s; ATA[1][1] += c0 * c0;
                        ATb[0] += s * seq[t0]; ATb[1] += c0 * seq[t0];
                    }
                    double[][] mat = new double[2][3]; for (int i = 0; i < 2; i++) { for (int j = 0; j < 2; j++) mat[i][j] = ATA[i][j]; mat[i][2] = ATb[i]; }
                    boolean singular = false;
                    for (int col = 0; col < 2; col++) {
                        int pivot = col; for (int r2 = col; r2 < 2; r2++) if (Math.abs(mat[r2][col]) > Math.abs(mat[pivot][col])) pivot = r2;
                        if (Math.abs(mat[pivot][col]) < 1e-12) { singular = true; break; }
                        if (pivot != col) { double[] tmp = mat[col]; mat[col] = mat[pivot]; mat[pivot] = tmp; }
                        double div = mat[col][col]; for (int c2 = col; c2 <= 2; c2++) mat[col][c2] /= div;
                        for (int r2 = 0; r2 < 2; r2++) if (r2 != col) { double mult = mat[r2][col]; for (int c2 = col; c2 <= 2; c2++) mat[r2][c2] -= mult * mat[col][c2]; }
                    }
                    if (singular) continue;
                    double A = mat[0][2]; double B = mat[1][2];
                    double rss = 0; for (int t0 = 0; t0 < len; t0++) { double pred = A * Math.sin(2 * Math.PI * k * t0 / len) + B * Math.cos(2 * Math.PI * k * t0 / len); double rres = pred - seq[t0]; rss += rres * rres; }
                    double rms = Math.sqrt(rss / len); double rel = base == 0 ? rms : rms / base;
                    if (rel <= tol) { found = true; out += (typ == 0 ? "I:SIN;F=" : (typ == 1 ? "D:SIN;F=" : "B:SIN;F=")) + k + ";C=" + Double.toString(A) + "," + Double.toString(B) + ";"; break; }
                }
            }
            if (!found) {
                out += (typ == 0 ? "I:RAW:" : (typ == 1 ? "D:RAW:" : "B:RAW:"));
                for (int i = 0; i < len; i++) { out += Double.toString(seq[i]); if (i + 1 < len) out += ","; }
                out += ";";
            }
        }
        int[] charA = new int[len];
        int[] strLen = new int[len];
        for (int i = 0; i < len; i++) { charA[i] = nodes[i].dataChar; strLen[i] = (nodes[i].dataString == null ? 0 : nodes[i].dataString.length()); }
        double[][] feat = new double[len][6];
        for (int i = 0; i < len; i++) {
            feat[i][0] = 1.0;
            feat[i][1] = ia[i];
            feat[i][2] = da[i];
            feat[i][3] = ba[i];
            feat[i][4] = charA[i];
            feat[i][5] = strLen[i];
        }
        String[] names2 = new String[]{"_1","I","D","B","C","S"};
        StringBuilder adv2 = new StringBuilder();
        adv2.append("{\"multivariate\":[");
        boolean firstAdv = true;
        int[] targets2 = new int[]{1,2,3,4,5};
        for (int tix = 0; tix < targets2.length; tix++) {
            int target = targets2[tix];
            java.util.ArrayList<double[]> rows = new java.util.ArrayList<>();
            java.util.ArrayList<Double> ys = new java.util.ArrayList<>();
            java.util.ArrayList<Double> weights = new java.util.ArrayList<>();
            java.util.ArrayList<String> colNames = new java.util.ArrayList<>();
            for (int i = 0; i < len; i++) {
                java.util.ArrayList<Double> r = new java.util.ArrayList<>();
                r.add(1.0);
                double[] fi = differentiableI(feat[i][1]); for (int ii=0; ii<fi.length; ii++) r.add(fi[ii]);
                double[] fd = differentiableD(feat[i][2]); for (int ii=0; ii<fd.length; ii++) r.add(fd[ii]);
                double[] fb = differentiableB((int)feat[i][3]); for (int ii=0; ii<fb.length; ii++) r.add(fb[ii]);
                double[] fc = differentiableC((int)feat[i][4]); for (int ii=0; ii<fc.length; ii++) r.add(fc[ii]);
                double[] fs = differentiableS((int)feat[i][5]); for (int ii=0; ii<fs.length; ii++) r.add(fs[ii]);
                double[] row = new double[r.size()]; for (int j=0;j<r.size();j++) row[j]=r.get(j);
                rows.add(row);
                ys.add(feat[i][target]);
                weights.add(1.0 + 0.5*eprop(nodes[i]));
            }
            int pcols = rows.get(0).length;
            for (int j=0;j<pcols;j++) colNames.add("f"+j);
            double[][] X = new double[len][pcols]; double[] y = new double[len]; double[] w = new double[len];
            for (int i=0;i<len;i++) { double[] r = rows.get(i); for (int j=0;j<pcols;j++) X[i][j]=r[j]; y[i]=ys.get(i); w[i]=weights.get(i); }
            double meanY = 0; for (int i=0;i<len;i++) meanY += y[i]; meanY /= len; double energyY=0; for (int i=0;i<len;i++) energyY += (y[i]-meanY)*(y[i]-meanY);
            int nh = Math.max(3, Math.min(10, pcols/2+1));
            double[][] V = new double[nh][pcols]; double[] W = new double[nh+1];
            java.util.Random rnd = new java.util.Random(1);
            for (int i=0;i<nh;i++) for (int j=0;j<pcols;j++) V[i][j] = (rnd.nextDouble()-0.5)*0.1;
            for (int i=0;i<W.length;i++) W[i] = (rnd.nextDouble()-0.5)*0.1;
            double lr = 0.01; double l2 = 1e-4; double bestRel = Double.MAX_VALUE; int epochs=400;
            for (int ep=0; ep<epochs; ep++) {
                double loss=0; double[][] gV = new double[nh][pcols]; double[] gW = new double[nh+1];
                for (int i=0;i<len;i++) {
                    double[] xi = X[i]; double[] h = new double[nh]; for (int j=0;j<nh;j++) { double s=0; for (int k=0;k<pcols;k++) s += V[j][k]*xi[k]; h[j] = Math.tanh(s); }
                    double pred = W[0]; for (int j=0;j<nh;j++) pred += W[j+1]*h[j]; double err = pred - y[i]; double wt = w[i]; loss += wt*err*err;
                    double dOut = 2*wt*err;
                    gW[0] += dOut;
                    for (int j=0;j<nh;j++) gW[j+1] += dOut * h[j];
                    for (int j=0;j<nh;j++) {
                        double dh = (1 - h[j]*h[j]) * (dOut * W[j+1]);
                        for (int k=0;k<pcols;k++) gV[j][k] += dh * xi[k];
                    }
                }
                for (int j=0;j<nh;j++) for (int k=0;k<pcols;k++) { gV[j][k] = gV[j][k]/len + l2*V[j][k]; V[j][k] -= lr * gV[j][k]; }
                for (int j=0;j<W.length;j++) { gW[j] = gW[j]/len + l2*W[j]; W[j] -= lr * gW[j]; }
                if (ep % 50 == 0) {
                    double rss=0; for (int i=0;i<len;i++) { double[] xi=X[i]; double[] h=new double[nh]; for (int j=0;j<nh;j++) { double s=0; for (int k=0;k<pcols;k++) s+=V[j][k]*xi[k]; h[j]=Math.tanh(s);} double pred=W[0]; for (int j=0;j<nh;j++) pred+=W[j+1]*h[j]; double r=pred-y[i]; rss+=r*r; }
                    double rms = Math.sqrt(rss/len); double base = Math.sqrt(energyY/len); double rel = base==0? rms : rms/base; if (rel < bestRel) bestRel = rel;
                }
            }
            double rss=0; for (int i=0;i<len;i++) { double[] xi=X[i]; double[] h=new double[nh]; for (int j=0;j<nh;j++) { double s=0; for (int k=0;k<pcols;k++) s+=V[j][k]*xi[k]; h[j]=Math.tanh(s);} double pred=W[0]; for (int j=0;j<nh;j++) pred+=W[j+1]*h[j]; double r=pred-y[i]; rss+=r*r; }
            double rms = Math.sqrt(rss/len); double base = Math.sqrt(energyY/len); double rel = base==0? rms : rms/base;
            if (rel <= tol) {
                if (!firstAdv) adv2.append(","); firstAdv = false;
                adv2.append("{"); adv2.append("\"target\":\"").append(names2[target]).append("\""); adv2.append(",\"method\":\"MLP\""); adv2.append(",\"rel\":").append(Double.toString(rel)); adv2.append(",\"preds\":[");
                for (int j=0;j<pcols;j++) { if (j>0) adv2.append(","); adv2.append("\"").append(colNames.get(j)).append("\""); }
                adv2.append("]}");
            }
        }
        adv2.append("]}");
        Krai tag = new Krai(); tag.dataString = adv2.toString(); this.dataTag = tag; this.dataString = out; return this;
    }

    private double[] differentiableI(double v) { return new double[]{v, v*v}; }
    private double[] differentiableD(double v) { return new double[]{v, v*v}; }
    private double[] differentiableB(int b) { return new double[]{b, 1-b}; }
    private double[] differentiableC(int c) { return new double[]{c, Math.sin(c), Math.cos(c)}; }
    private double[] differentiableS(int len) { return new double[]{len, Math.log(len+1)}; }
    private double eprop(Krai node) { if (node == null) return 0.0; return 1.0 + 0.5 * eprop(node.protocol); }

    public Krai protocol_synthesizer(int n) {
        this.AL(n);
        Krai proto = new Krai();
        proto.dataString = this.dataString;
        String s = this.dataString;
        int idx = s.indexOf(";C=");
        if (idx >= 0) {
            int start = idx + 3;
            int end = s.indexOf(";", start);
            if (end < 0) end = s.length();
            String list = s.substring(start, end);
            String[] parts = list.split(",");
            Krai[] childs = new Krai[parts.length];
            for (int i = 0; i < parts.length; i++) {
                double v = 0.0;
                try { v = Double.parseDouble(parts[i]); } catch (Exception ex) { v = 0.0; }
                childs[i] = new Krai(v, new Krai[0], proto);
            }
            proto.link = Arrays.copyOf(childs, childs.length);
        } else {
            proto.link = new Krai[0];
        }
        return proto;
    }

    public Krai protocol_injector_align(int n) {
        this.AL(n);
        if (this.protocol == null) return this;
        Krai prot = this.protocol;
        for (int a = 0; a < this.link.length; a++) {
            prot = this.protocol_diffsim(prot, a);
            if (prot == null) return this;
        }
        this.protocol = prot;
        return prot;
    }

    public Krai predict_and_append(int n) {
        this.AL(n);
        if (this.dataString == null) return this;
        String s = this.dataString;
        int ip = s.indexOf("I:AR");
        if (ip < 0) return this;
        int cidx = s.indexOf(";C=", ip);
        if (cidx < 0) return this;
        int start = cidx + 3;
        int end = s.indexOf(";", start);
        if (end < 0) end = s.length();
        String[] parts = s.substring(start, end).split(",");
        int order = parts.length;
        int oidx = s.indexOf("O=", ip);
        if (oidx >= 0 && oidx < cidx) {
            int ostart = oidx + 2;
            int oend = s.indexOf(";", ostart);
            if (oend < 0 || oend > cidx) oend = cidx;
            try { order = Integer.parseInt(s.substring(ostart, oend)); } catch (Exception ex) { order = parts.length; }
        }
        double[] coeffs = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try { coeffs[i] = Double.parseDouble(parts[i]); } catch (Exception ex) { coeffs[i] = 0.0; }
        }
        int len = 0;
        Krai p = this;
        while (p != null) { len++; p = p.protocol; }
        if (len < 1) return this;
        Krai[] nodes = new Krai[len];
        p = this;
        for (int i = len - 1; i >= 0; i--) { nodes[i] = p; p = p.protocol; }
        int[] ia = new int[len];
        for (int i = 0; i < len; i++) ia[i] = nodes[i].dataInt;
        double pred = 0.0;
        for (int j = 0; j < order && j < coeffs.length; j++) {
            int idxVal = len - 1 - j;
            if (idxVal >= 0) pred += coeffs[j] * ia[idxVal];
        }
        int predicted = (int)Math.round(pred);
        Krai child = new Krai(predicted, new Krai[0], this.protocol);
        Krai[] nl = Arrays.copyOf(this.link, this.link.length + 1);
        nl[nl.length - 1] = child;
        this.link = Arrays.copyOf(nl, nl.length);
        return this;
    }
 

    public static void main(String[] args) {
        Krai demo = new Krai();
        demo.linked_list();
    }
}
