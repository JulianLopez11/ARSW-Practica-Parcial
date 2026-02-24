import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentTestClient {
    public static void main(String[] args) {
        int numConsultas = 50;
        // Pool de hilos 
        ExecutorService executor = Executors.newFixedThreadPool(10); // Pool de hilos 

        for (int i = 0; i < numConsultas; i++) {
            executor.execute(() -> {
                try {
                    URL url = new URL("nombre del servidor");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    //Peticion GET
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    System.out.println("Status: " + responseCode + " en hilo: " + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}