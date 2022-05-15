import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class UDPClient {
    public static void main(String[] args){
    //args fornece o contéudo da mensagem e o hostname do servidor
    DatagramSocket aSocket = null;
    try
    {
        aSocket = new DatagramSocket();
        String aux = "2 * 2";
        byte[] m = aux.getBytes();//pegando o conteudo da mensagem do args
        
        System.out.println(m.length);
        InetAddress aHost = InetAddress.getByName(args[1]);//pegando o hostname do servidor

        System.out.println(aHost.getHostName());
        int serverPort = 6789;//porta do servidor
        DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);//construindo um pacote do tipo datagrama para ser enviado ao servidor
        aSocket.send(request);//enviando-o ao servidor
        byte[] buffer = new byte[13];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(reply); //recebendo o pacote de resposta do servidor
        System.out.println("Reply: "+new String(reply.getData())); //imprimindo a resposta
    } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            if(aSocket!=null){
                aSocket.close();
            }
        }//fechando o try
    }//fechando a main
}//fechando a classe