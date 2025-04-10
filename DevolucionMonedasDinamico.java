public class DevolucionMonedasDinamico {

    public static void main(String[] args) {
        int[] monedas = {1, 2, 5, 10, 20, 50, 100, 200}; // céntimos
        int cantidad = 568; // 5,68 €

        int[] resultado = devolverCambio(monedas, cantidad);

        if (resultado != null) {
            imprimeCambio(monedas, resultado);
        }
    }

    public static int[] devolverCambio(int[] monedas, int cantidad) {
        int[] dp = new int[cantidad + 1];
        int[] ultMoneda = new int[cantidad + 1];

        // Inicialización
        for (int i = 1; i <= cantidad; i++) {
            dp[i] = Integer.MAX_VALUE;
            ultMoneda[i] = -1;
        }

        // Programación dinámica
        for (int i = 1; i <= cantidad; i++) {
            for (int j = 0; j < monedas.length; j++) {
                if (monedas[j] <= i && dp[i - monedas[j]] != Integer.MAX_VALUE) {
                    if (dp[i - monedas[j]] + 1 < dp[i]) {
                        dp[i] = dp[i - monedas[j]] + 1;
                        ultMoneda[i] = j;
                    }
                }
            }
        }

        if (dp[cantidad] == Integer.MAX_VALUE) {
            System.out.println("No hay solución");
            return null;
        }

        // Reconstrucción de solución
        int[] resultado = new int[monedas.length];
        int k = cantidad;
        while (k > 0) {
            int idx = ultMoneda[k];
            resultado[idx]++;
            k -= monedas[idx];
        }

        return resultado;
    }

    public static void imprimeCambio(int[] monedas, int[] cantidades) {
        for (int i = 0; i < monedas.length; i++) {
            if (cantidades[i] > 0) {
                System.out.println(cantidades[i] + " monedas de " + (monedas[i] / 100.0) + " €");
            }
        }
    }
}
