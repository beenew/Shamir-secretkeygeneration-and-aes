import shamir.*;
import encryption.*;
import Qrgenerator.*;
import generate.*;

public class MAIN {

	public static void main(String[] args) {
//		576056
		int ffshare=0;
		int cdshare=0;
		String ffshareenc=null;
		String cdshareenc=null;
		String QRenc=null;
		Shamir sec=new Shamir();
		abecrypto m=new abecrypto();
		Qrcode q=new Qrcode();
		sec.shamirsplitplay(args);
		ffshare=sec.secret1;
		cdshare=sec.secret2;

		System.out.println(ffshare+"+"+cdshare);
		ffshareenc=m.enc(ffshare);
		cdshareenc=m.enc(cdshare);
		QRenc=ffshareenc+";;;;"+cdshareenc;
		q.QRgenerate(QRenc);
		System.out.println(QRenc);
		
		//seperating in twoo parts
		String[] parts = QRenc.split(";;;;");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // 034556
		System.out.println(part1);
		String ffsharedec=m.dec(part1);
		String cdsharedec=m.dec(part2);
		

	}

}
