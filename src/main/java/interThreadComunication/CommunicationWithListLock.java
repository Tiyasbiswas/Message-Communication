package interThreadComunication;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CommunicationWithListLock<msg>{

    private final ReadWriteLock readWriteLock
            = new ReentrantReadWriteLock ();
    private final Lock writeLock
            = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();
    private final List<msg> list = new ArrayList<> ();

    // setElement function set i.e., write the element to the thread
    public void setElement(msg m)
    {
        // acquire the thread for writing
        writeLock.lock();
        try {
            list.add(m);
            System.out.println(
                    "Element by thread "
                            + Thread.currentThread().getName()
                            + " is added");
        }
        finally {
            // To unlock the acquired write thread
            writeLock.unlock();
        }
    }

    // getElement function prints i.e., read the element from the thread
    public msg getElement(int i)
    {
        // acquire the thread for reading
        readLock.lock();
        try {
            System.out.println(
                    "Elements by thread "+ Thread.currentThread().getName() + " is printed");
            return list.get(i);
        }
        finally {
            // To unlock the acquired read thread
            readLock.unlock();
        }
    }
    public static void main(String[] args)
    {
        String  message     = "";
        Scanner scan        =new Scanner(System.in);
      CommunicationWithListLock<String> listlockObj = new CommunicationWithListLock<> ();
        for (int i=0;i<10;i++) {
            System.out.println ( "Enter the Message by sender" );
            message = scan.nextLine ();
            message=message+":"+i;
            listlockObj.setElement ( message);
        }
        // retrieving data from string list array in for loop
        for (int i=0;i < listlockObj.list.size ();i++)
        {
            System.out.print ("Message Recieved  From Sender  "+listlockObj.list.get (i));
            System.out.println();
        }


    }
}
