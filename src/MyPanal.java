
import org.json.JSONObject;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class MyPanal extends JPanel implements ActionListener {

    private static Double findExchangeRateAndConvert(String from, String to, double amount) {
        try {
            String GET_URL="https://api.exchangeratesapi.io/latest?base="+from+"&symbols="+to;
//            https://free.currconv.com/api/v7/convert?q=USD_PHP&compact=ultra&apiKey=497c955913ce146d6b08
            URL url=new URL(GET_URL);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responce=httpURLConnection.getResponseCode();

            if(responce==HttpURLConnection.HTTP_OK){//success

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer responcebuffer=new StringBuffer();

                while ((inputLine=bufferedReader.readLine())!=null){
                    responcebuffer.append(inputLine);
                }

                bufferedReader.close();
                JSONObject obj=new JSONObject(responcebuffer.toString());
                double rates=obj.getJSONObject("rates").getDouble(to);
                return rates*amount;
            }
        } catch (Exception e) {
            System.out.println("hello");
            System.out.println("unable to reach server");
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // if (textField1.getText()!=""){
            try {
                if (textField1.getText()!="") {
                    double amount = Double.parseDouble(textField1.getText());
                    String from = Objects.requireNonNull(upper.getSelectedItem()).toString();
                    String to = Objects.requireNonNull(lower.getSelectedItem()).toString();
                    Double temp=findExchangeRateAndConvert(from,to,amount);
                    textField2.setText(String.valueOf(temp));
                }

            }
            catch (Exception exception){
                System.out.println("NOTHING IN SERACH BOX");
                textField2.setText("0");
            }
       // }
    }

    String countrys[]={"CAD","HKD","ISK","PHP","DKK","EUR","HUF","CZK","AUD","RON","SEK","IDR","INR", "BRL","RUB","HRK",
                         "JPY","THB","CHF","SGD","PLN","BGN","TRY","CNY","NOK","NZD", "ZAR","USD","MXN","ILS","GBP","KRW","MYR"};

    JComboBox upper,lower;
    JTextField textField1;
    JTextPane textField2;
    Timer timer;
    MyPanal(){
        this.setLayout(null);

        upper=new JComboBox(countrys);
        lower=new JComboBox(countrys);
        textField1=new JTextField("0");
        textField2=new JTextPane();
        timer=new Timer(1000,this);

        upper.setBounds(60,100,100,25);
        lower.setBounds(60,200,100,25);
        textField1.setBounds(200,100,150,25);
        textField2.setBounds(200,200,150,25);

        this.add(textField1);
        this.add(textField2);
        this.add(upper);
        this.add(lower);

        timer.start();

    }
}
