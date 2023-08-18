package aed;

class Funciones {
    int cuadrado(int x) {
        int res;
        res = x*x;
        return res;
    }

    double distancia(double x, double y) {
        double res;
        res = Math.sqrt(x*x + y*y);
        return res;
    }

    boolean esPar(int n) {        
        return ((n % 2) == 0);
    }

    boolean esBisiesto(int n) {
        boolean res;
        res = ((n % 4 == 0) && (n % 100 != 0)) || (n % 400 == 0);
        return res;
    }

    int factorialIterativo(int n) {
        int res;
        res = 1;
        for (int i = 2; i <= n; i++) {
            res = res * i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        int res;
        if (n == 0 || n == 1) {
            res = 1;
        }
        else {
            res = n * factorialRecursivo(n-1);
        }
        return res;
    }

    boolean esPrimo(int n) {
        boolean res;
        res = true;
        if (n > 1) {
        for (int i = 2; i < n; i++) {
            res = res && (n % i != 0);
            }
        }
        else {
            res = false;
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        int res;
        res = 0;
        for (int n: numeros) {
            res = res + n;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res;
        res = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscado) {
                res = i;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res;
        res = false;
        for (int n: numeros) {
            res = res || esPrimo(n);
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res;
        res = true;
        if (numeros.length > 0) {
        for (int n: numeros) {
            res = res && esPar(n);
            }
        }
        else {
            res = false;
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean res;
        res = true;
        if (s1.length() <= s2.length() ) {
            for (int i = 0; i < s1.length(); i++) {
                res = res && s1.charAt(i) == s2.charAt(i);
            }
        }
        else {
            res = false;
            }
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res;
        res = esPrefijo(invertir(s1), invertir(s2));
        return res;
    }

    String invertir(String palabra){
        String res;
        res = "";
        for (int i = (palabra.length()-1) ; i >= 0; i--) {
            res = res + palabra.charAt(i);
        }
        return res;
    }
}
