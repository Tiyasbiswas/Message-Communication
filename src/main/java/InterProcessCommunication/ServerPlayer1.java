package InterProcessCommunication;

import java.io.*;
import java.net.*;

    public class ServerPlayer1 {

        // initialize socket and input stream
        private Socket socket = null;
        private ServerSocket server = null;
        private DataInputStream in = null;

        // constructor with port
        public ServerPlayer1(int port)
        {
            // starts server and waits for a connection
            try {
                server = new ServerSocket(port);

                System.out.println("Server started");

                System.out.println("Waiting for a client ...");

                socket = server.accept();

                System.out.println("Client accepted");

                // takes input from the client socket
                in = new DataInputStream(
                        new BufferedInputStream(
                                socket.getInputStream()));

                String line = "";
                int countnum=0;


                // reads message from client until "End" is sent
               // while( (!line.equals("End"))  ){
                while( (countnum != 10 )  ){

                    try {

                        line = in.readUTF();
                        countnum++;
                        //line=line +": "+ countnum;

                        System.out.println(line);
                    }

                    catch (IOException i) {

                        System.out.println(i);
                    }
                }

                System.out.println("Closing connection");

                // close connection
                socket.close();

                in.close();
            }

            catch (IOException i) {

                System.out.println(i);
            }
        }

        public static void main(String[] args)
        {

            ServerPlayer1 server = new ServerPlayer1(5000);
        }
    }


