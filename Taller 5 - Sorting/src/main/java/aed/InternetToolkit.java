package aed;

import java.util.Vector;

public class InternetToolkit {

    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {

        for (int i = 0; i < fragments.length - 1; i++) {

            if (fragments[i].compareTo(fragments[i+1]) > 0) {
                reubicar(fragments, i + 1);
            }

        }
        return fragments;
    }

    private void reubicar(Fragment[] fragments, int i) {
        Fragment elem;
        int j = i-1;

        while (j >= 1 && fragments[i].compareTo(fragments[j-1]) <0) {
            j--;
        }

        elem = fragments[i];
        
        while (j < i) {
            fragments[i] = fragments[i-1];
            i--;
        }

        fragments[j] = elem;
    }


    public Router[] kTopRouters(Router[] routers, int k, int umbral) {

        maxHeap<Router> routHeap = new maxHeap(routers, routers.length);
        Router[] maximos = new Router[k];

        Router rout;
        rout = routHeap.desapilar();

        int j = 0;
        while (j < k && rout.getTrafico() > umbral) {
            maximos[j] = rout;
            if (j < k-1) {rout = routHeap.desapilar();}
            j++;
        }

        return maximos;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        Vector<IPv4Address> ips = new Vector<IPv4Address>(ipv4.length);

        // Armo mi array de ips;
        for (int i = 0; i < ipv4.length; i++){
            ips.add(i, new IPv4Address(ipv4[i]));
        }

        // El ciclo cuesta O(n) pues m = #Casillas está acotada.
        for (int i = 3; i > -1; i--) {

            // Armo el bucket
            Vector<Vector<IPv4Address>> bucket = new Vector<Vector<IPv4Address>>(256);
            for (int j = 0; j < ips.size(); j++) {
                bucket.add(new Vector<IPv4Address>());
            } 

            // Encasillo la ip según su octeto, garantizando estabilidad.
            for (int j = 0; j < ips.size(); j++) {
                bucket.elementAt(ips.elementAt(j).getOctet(i)).add(ips.elementAt(j));
            }

            ips.clear();

            for (int k = 0; k < 256; k++) { 
                for (int j = 0; j < bucket.elementAt(k).size(); j++) {
                    ips.add(bucket.elementAt(k).elementAt(j));
                }
            }

            // Limpio el bucket
            bucket.clear();

        }

        // Vector2Array
        IPv4Address[] res = new IPv4Address[ipv4.length];

        for (int i = 0; i < ipv4.length; i++) {
            res[i] = ips.elementAt(i);
        }

        return res;
    }
}