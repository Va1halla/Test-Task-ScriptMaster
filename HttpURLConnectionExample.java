import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// импорт классов, необходимых для работы с буферизацией и работы с HTTP и URL
import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {
//определение класса HttpURLConnectionExample
        private final String USER_AGENT = "Mozilla/5.0";
//назначение константы USER_AGENT, отвечающаю за модель браузера
        public static void main(String[] args) throws Exception {
//инициализация главного метода c предупреждением, что данный метод может вызвать исключение
                HttpURLConnectionExample http = new HttpURLConnectionExample();
//построение экземпляра класса HttpURLConnectionExample
                System.out.println("Testing 1 - Send Http GET request");
                http.sendGet();
//Вывод сообщения о попытке отправить GET запрос и вызов метода sendGet()                
                System.out.println("\nTesting 2 - Send Http POST request");
                http.sendPost();
//Вывод сообщения о попытке отправить POST запрос и вызов метода sendPost() 
        }

        private void sendGet() throws Exception {
//инициализация метода sendGet с предупреждением, что данный метод может вызвать исключение
                String url = "http://www.google.com/search?q=mkyong";
//назначение строковой переменной url внутри метода sendGet                
                URL obj = new URL(url);
//построение экземпляра класса java.net.URL
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//открытие подключения на основе объекта URL
                con.setRequestMethod("GET");
//передача типа запроса GET
                con.setRequestProperty("User-Agent", USER_AGENT);
//передача типа браузера 
                int responseCode = con.getResponseCode();
//назначение переменной responseCode кода с ответом о статусе запроса
                System.out.println("\nSending 'GET' request to URL : " + url);
//вывод сообщения об отправке запроса Get на указаный url
                System.out.println("Response Code : " + responseCode);
//вывод сообщения о том, какой код вернул запрос GET
                BufferedReader in = new BufferedReader(
//построение экземпляра класса java.io.BufferedReader отвечающего за чтение данных, поступающих в буфер               
                        new InputStreamReader(con.getInputStream()));
//построение экземпляра класа java.io.InputStreamReader который отвечает за буферизацию данных из метода con.getInputStream()                   
                String inputLine;
                StringBuffer response = new StringBuffer();
//cоздание экземпляра класса StringBuffer, отвечающего за построение многострочных значений
                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
// до тех пор, пока значение переменной InputLine не равно null, в конец стрингбуфера response передается значение InputLine
                in.close();
//закрытие экземпляра класса BufferReader

                System.out.println(response.toString());
//вывод полученных данных из GET - запроса

        }
              
        private void sendPost() throws Exception {
//инициализация метода sendPost с предупреждением, что данный метод может вызвать исключение
                String url = "https://selfsolve.apple.com/wcResults.do";
//назначение строковой переменной url внутри метода sendPost 
                URL obj = new URL(url);
//построение экземпляра класса java.net.URL
                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//открытие подключения на основе объекта URL
                con.setRequestMethod("POST");
//передача типа запроса POST
                con.setRequestProperty("User-Agent", USER_AGENT);
//передача типа браузера
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//передача данных о языке получаемой информации
                String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
//назначение переменной urlParameters содержащей дополнительные параметры для запроса                
                con.setDoOutput(true);
//подтверждение отправки POST запроса
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//построение экземпляра клаccа DataOutputStream, предназначенного для записи примитивных типов данных в поток вывода                 
                wr.writeBytes(urlParameters);
//записывает в поток значения из urlParametrs
                wr.flush();
//проталкивает данные в поток вывода и очищает буфер
                wr.close();
//закрытие буфера

                int responseCode = con.getResponseCode();
//назначение переменной responseCode кода с ответом о статусе запроса
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);
//вывод строк о отправке POST запроса к указаному url, о параметрах к запросу и возвращенному response коду
                BufferedReader in = new BufferedReader(
    //построение экземпляра класса java.io.BufferedReader отвечающего за чтение данных, поступающих в буфер               
                        new InputStreamReader(con.getInputStream()));
    //построение экземпляра класа java.io.InputStreamReader который отвечает за буферизацию данных из метода con.getInputStream()                   
                String inputLine;
                StringBuffer response = new StringBuffer();
    //cоздание экземпляра класса StringBuffer, отвечающего за построение многострочных значений
                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
    // до тех пор, пока значение переменной InputLine не равно null, в конец стрингбуфера response передается значение InputLine
                in.close();
    //закрытие экземпляра класса BufferReader
    
                System.out.println(response.toString());
    //вывод полученных данных из GET - запроса

        }

}
