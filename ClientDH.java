//Alice 
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket; 

class ClientDH {
    static int P=23,G=9,X,b=3,key,k;
	public static void main(String args[]){

	try{
			
			Socket socket = new Socket("localhost", 5000);

			System.out.println("\n=============/ ALICE /=============");

			System.out.println("Connected with Bob...");

            X=deffie_hellman(G,b,P);

			//sending the message
			System.out.println("Sending Public key...");
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeInt(X);
			dout.flush();

            //get public key
            DataInputStream din = new DataInputStream(socket.getInputStream());
			k = (int)din.readInt();

			System.out.println("Bob's public message : " + k);
            key=deffie_hellman(k,b,P);
            System.out.println("Secret key : " + key);

			//close connection
			dout.close();
			socket.close();
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