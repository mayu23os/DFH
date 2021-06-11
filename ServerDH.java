//Bob 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerDH {
    static int P=23,G=9,Y,a=4,key,k;
	public static void main(String args[]){

		try{

			ServerSocket serverSocket = new ServerSocket(5000);

			System.out.println("\n=============/ BOB /=============");

			System.out.println("Waiting for Alice...");

			//establish connection
			Socket socket = serverSocket.accept();

			System.out.println("Alice Connected...");

			//get incoming message
			DataInputStream din = new DataInputStream(socket.getInputStream());

			k = (int)din.readInt();
			System.out.println("Alice's public message: " + k);
            
			//send public value
			Y=deffie_hellman(G,a,P);

            System.out.println("Sending Public key...");
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            
			dout.writeInt(Y);
			dout.flush();

            key=deffie_hellman(k,a,P);

            System.out.println("Secret key : " + key);

			dout.close();

			//close connection
			serverSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	static int pow(int a,int b,int n)
	{
	    int c=0,f=1;
	    String s=Integer.toBinaryString(b);
	    for(int i=0;i<s.length();i++)
	    {
	        c=2*c;
	        f=(f*f)%n;
	        if(s.charAt(i)=='1')
	        {
	            c=c+1;
	            f=(f*a)%n;
	        }
	    }
        return f;
	}
	static int deffie_hellman(int a,int b,int n)
	{
		return pow(a,b,n);
	}
}
