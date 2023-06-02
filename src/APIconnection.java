
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class APIconnection { 
        /*
        	List of currency codes for conversion:
        	    USD: United States Dollar
    			EUR: Euro
    			GBP: British Pound
    			JPY: Japanese Yen
    			CAD: Canadian Dollar
    			AUD: Australian Dollar
    			CHF: Swiss Franc
    			CNY: Chinese Yuan
    			INR: Indian Rupee
    			MXN: Mexican Peso
    		
    		**MUST BE A STRING**     
         */
    
    //method to do the conversion
    public static String convert(String sourceCurrency, String targetCurrency, double amount) {
    	String apiKey = "9189d01f18714a06bd93c2b96b931bdf";

        try {
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            
            //format the result with this
            DecimalFormat df = new DecimalFormat("#.00");

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            JSONObject rates = new JSONObject(response.toString()).getJSONObject("rates");
            double sourceRate = rates.getDouble(sourceCurrency);
            double targetRate = rates.getDouble(targetCurrency);

            double convertedAmount = (amount / sourceRate) * targetRate;
            return amount + " " + sourceCurrency + " = " + df.format(convertedAmount) + " " + targetCurrency;
        } catch (Exception e) {
            return e.getMessage();
        }	
    	
    }

    public static String historical(String sourceCurrency, String targetCurrency, double amount, String date) {
        String apiKey = "9189d01f18714a06bd93c2b96b931bdf";

        try {
            URL url = new URL("https://openexchangerates.org/api/historical/" + date + ".json?app_id=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            //format the result with this
            DecimalFormat df = new DecimalFormat("#.00");

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            JSONObject rates = new JSONObject(response.toString()).getJSONObject("rates");
            double sourceRate = rates.getDouble(sourceCurrency);
            double targetRate = rates.getDouble(targetCurrency);

            double convertedAmount = (amount / sourceRate) * targetRate;
            return amount + " " + sourceCurrency + " = " + df.format(convertedAmount) + " " + targetCurrency;
        } catch (Exception e) {
            return e.getMessage();

        }
    }
}
