package InterProcessCommunication;


import java.io.*;
import java.net.*;

    public class ClientPlayer2 {

        // initialize socket and input output streams
        private Socket socket = null;
        private DataInputStream input = null;
        private DataOutputStream out = null;

        // constructor to put ip address and port
        public ClientPlayer2(String address, int port)
        {

            // establish a connection
            try {

                socket = new Socket(address, port);

                System.out.println(" Client Connected to Server ");

                // takes input from terminal
                input = new DataInputStream(System.in);

                // sends output to the socket
                out = new DataOutputStream(
                        socket.getOutputStream());
            }

            catch (UnknownHostException u) {

                System.out.println(u);
            }

            catch (IOException i) {

                System.out.println(i);
            }

            // string to read message from input
            String line = "";
            int countnum=0;
            // keep reading until "End" is input
            //while (!line.equals("End")) {
            while( (countnum != 10 )  ){

                try {
                    System.out.println(" Enter the Message --");

                    line = input.readLine();
                    countnum++;
                    line= line + ":" + countnum;
                    out.writeUTF(line );

                }

                catch (IOException i) {

                    System.out.println(i);
                }
            }

            // close the connection
            try {

                input.close();

                out.close();

                socket.close();
            }

            catch (IOException i) {

                System.out.println(i);
            }
        }

        public static void main(String[] args)
        {

            ClientPlayer2 client
                    = new ClientPlayer2("127.0.0.1", 5000);
        }
    }

