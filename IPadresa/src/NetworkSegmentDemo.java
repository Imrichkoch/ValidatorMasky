import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class NetworkSegmentDemo {
	
	public static int[] calculateSegment(String [] adresa, String [] maska){
		int segment[] = {0,0,0,0}; //polu budu stacit 4 prvky
		int a;
		for(a = 0; a < 4; a++){
			segment[a] =(Integer.parseInt(adresa[a])) & (Integer.parseInt(maska[a]));
		}
		return segment;
		
	}


	public static int numberOfBitsInMask(String [] cislaMask){
		int a,count = 0;
		int segment[] = {0,0,0,0};
		for(a = 0; a < 4; a++){
			segment[a] =Integer.parseInt(cislaMask[a]);
			//System.out.println(segment[a]);
		
			count += Integer.bitCount(segment[a]);
		}
		return count;
	}
	
	public static boolean isMaskValid(String mask){
		String correctMask = "(255\\.255\\.255\\.(255|254|252|248|240|224|192))|"
				+ "(255\\.255\\.(255|254|252|248|240|224|192)\\.0)|"
				+ "(255\\.(255|254|252|248|240|224|192)\\.0\\.0)|"
				+ "((255|254|252|248|240|224|192)\\.0\\.0\\.0)";
		
		Pattern pattern = Pattern.compile(correctMask); 
		
		Matcher matcher = pattern.matcher(mask); 
		
		
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isIPAdressValid(String ipAdress){
		String correctMask = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
		
		Pattern pattern = Pattern.compile(correctMask); 
		
		Matcher matcher = pattern.matcher(ipAdress); 
		
		
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		
		String ipAddress = "164.124.141.0";
		String [] maskArray = {"255.255.255.254","255.255.255.253","255.255.255.252","255.0.0.0","255.255.0.0","255.255.20.254",};
		
		String [] ipAdressArray = {"255.255.255.255","0.0.255.253","255.255.1","255.0.0.1","255.255..0.0","123.255.20.254",};
		String mask =  "255.255.255.254";
		//najprv som si rozdeli String na polia Stringov
		String [] ipInString = ipAddress.split("\\.", -1); 
		String [] maskInString = mask.split("\\.", -1); 
		int networkSegment [] = {0,0,0,0};
		String  networkSegment2;
		int numberOfBits = 0;
		
		
		//metoda ktora my spravila bitovy sucin medzi adresou a maskou , a vratila mi vysledok ako pole Integerov
		networkSegment=calculateSegment(ipInString, maskInString);
		
		//metoda ktora mi zratala bity
		numberOfBits = numberOfBitsInMask(maskInString);
		
		//vysledne pole som znova zmenil na String
        networkSegment2 = Arrays.toString(networkSegment);
        
        //nakoniec vymenil ciarky za bodky a odstranil nepotrebne znaky
        //networkSegment2 = networkSegment2.replace(",", ".").replaceAll("[\\[\\] ]", "");
        //System.out.println("IP adresa: " + ipAddress);
        //System.out.println("Maska siete: " + mask);
        //System.out.println("CIDR: "+ networkSegment2 + "/" + numberOfBits);
       for(String mask1:maskArray)
        System.out.println("Je maska " + mask1 +" validna? " + isMaskValid(mask1));//validator masky
       
       System.out.println();
       
       for(String ipAdress:ipAdressArray)
           System.out.println("Je IPadressa " + ipAdress +" validna? " + isIPAdressValid(ipAdress));//validator masky
	}
}
