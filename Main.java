import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Taxi> taxies = new ArrayList<>();
	static int count_Taxi=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		count_Taxi = sc.nextInt();
		int x=1;
		while(x<=count_Taxi) {
			Taxi t=new Taxi();
			t.TaxiId=x;
			taxies.add(t);
			x++;
			
		}
		while(true) {
			System.out.println("Press 1 to Book a taxi");
			System.out.println("Press 2 to Print the record of the taxis");
			System.out.println("Press 3 to exit the registeration");
			int y = sc.nextInt();
			switch(y) {
			case 1:
				booking();
				break;
			case 2:
				printRecord();
				break;
			case 3:
				return;
		}
	}
}

	private static void booking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the customer ID");
		int customerId =sc.nextInt();
		System.out.println("Enter the Pick up time");
		int pickup_time = sc.nextInt();
		System.out.println("Enter the pickup point");
		char pickup =sc.next().charAt(0);
		System.out.println("Enter the Drop Point");
		char drop =sc.next().charAt(0);
		int min_distance = 7,minimum_earning = -1,taxi_no=0,flag =0;
		int l = 0;
		while(l<count_Taxi) {
			int distance = Math.abs(pickup-(taxies.get(l).current_location));
			int time = taxies.get(l).free_time+distance;
			if(distance<min_distance && time<pickup_time) {
				min_distance = distance;
				taxi_no = l;
				flag=1;
			}
			else if(time<pickup_time && distance==min_distance && taxies.get(l).earning < taxies.get(taxi_no).earning) {
				taxi_no = l;
			}
			l++;
		}
		if(flag!=1) {
			System.out.println("We couldn't able to Book");
			return;
		}
		int dist = Math.abs(pickup-drop);	
		int earning = 200 + (dist-1)*150;
		String rec = "  "+customerId+"| "+pickup+" | "+drop+" | "+pickup_time+" | "+earning+"\n";
		taxies.get(taxi_no).current_location = drop;
		taxies.get(taxi_no).earning+=earning;
		taxies.get(taxi_no).free_time = pickup_time+dist;
		taxies.get(taxi_no).record.add(rec);
	}
	private static void printRecord() {
		int l =0 ;
		while(l<count_Taxi) {
			System.out.println("Taxi Id "+l);
			int count = 0;
			while(count<taxies.get(l).record.size()) {
				System.out.println(taxies.get(l).record.get(count));
				count++;
			}
			l++;
		}
	}
}
