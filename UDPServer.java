import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.io.IOException;

public class UDPServer {
/**
* @param args
*/
    public static void main(String[] args) {
    DatagramSocket aSocket = null;
    try {
    aSocket = new DatagramSocket(6789);//cria-se o servidor e atribui-se uma porta (faz-se o bind) ao mesmo

    byte[] buffer = new byte[13];

    while (true){
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(request);//recebendo o que é transmitido por outros processos

        //Processando o conteúdo da mensagem
        String data = request.getData().toString();
        System.out.println(data);
        String [] datas = data.split(" ");
        int first = Integer.parseInt(datas[0]); 
        char op = datas[1].toString().charAt(0);
        int second = Integer.parseInt(datas[2]); 
        int calc = 0;
        if (op == '+') {
            calc = first + second;
        } else if (op == '*') {
            calc = first * second;
        } else if (op == '/') {
            calc = first / second;
        } else {
        }

        String replyData = String.valueOf(calc); //conteúdo da reposta da mensagem

        System.out.println(request.getAddress());//endereço do processo transmissor
        System.out.println(request.getPort());//endereço da porta do processo transmissor
        DatagramPacket reply = new DatagramPacket(replyData.getBytes(), request.getLength(), request.getAddress(), request.getPort());//construindo-se a mensagem de resposta
        aSocket.send(reply);//enviando-se para o processo cujo endereço e porta estão indicados no objeto request
    }

    } catch (SocketException e){
        e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            if(aSocket!=null){
                aSocket.close();
            }
        } //fechando o try
    }//fechando a main
}//fechando a classe