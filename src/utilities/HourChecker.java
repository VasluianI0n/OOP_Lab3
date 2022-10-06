package utilities;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import People.Worker;
import People.Senior;
import People.WatchMan;
import WorkingPlace.WorkingPlace;

public class HourChecker {
	
	private static boolean check(Worker[] arr, String toCheckValue)
    {
        int test = 0;
        for (Worker element : arr) {
            if (element.getHuman().getNickname().getName() == toCheckValue) {
                test++;
            }
        }
        if(test > 1) {
        	return false;
        }else {
        	return true;
        }
        
    }

	public static void main(String[] args) {
		int workers;
		WorkingPlace workingPlace = new WorkingPlace();
		String day,hour;
		Senior seniors[] = new Senior[3];
		WatchMan watchmen[] = new WatchMan[7];
		Scanner in = new Scanner(System.in);
		System.out.println("The company is working "+workingPlace.getWorkingHours()+"/7 on "+workingPlace.getPlace());
		System.out.println("How many workers does the company have:");
		workers = Integer.parseInt(in.nextLine());
		Worker people[] = new Worker[workers];
		//Declaration of Workers
		for(int i = 0; i < workers; i++) {
			int position = ThreadLocalRandom.current().nextInt(1, 4);
			people[i] = new Worker();
			people[i].setShift(position);
		}
		
		for(int i = 0; i < workers; i++) {
				while(check(people,people[i].getHuman().getNickname().getName())==false) {
					people[i].getHuman().setNickname();
				}
		}
		//Declaration of Seniors
		for(int i = 0; i < 3; i++) {
			seniors[i] = new Senior();
			seniors[i].setShift(i);
		}
		//Declaration of Watchmen
		for(int i = 0; i < 7; i++) {
			watchmen[i] = new WatchMan();
		}
		watchmen[0].setDay("Sunday");
		watchmen[1].setDay("Monday");
		watchmen[2].setDay("Tuesday");
		watchmen[3].setDay("Wednesday");
		watchmen[4].setDay("Thursday");
		watchmen[5].setDay("Friday");
		watchmen[6].setDay("Saturday");
		
		//Checking each worker and their itinerary
		for(int i = 0; i < workers; i++) {
			System.out.println((i+1)+"."+people[i].getHuman().getNickname().getName());
			System.out.println("   Working days:");
			people[i].getHuman().printWorkingDays();
			System.out.println(people[i].getHuman().getShift());
		}
		//Reading the day and hour
		System.out.println("Type in the day you want it to be:");
		day = in.nextLine();
		System.out.println("Type in the hour you want to check:");
		hour = in.nextLine();
		//Printing the Watchman that is on that day
		for(int i = 0; i < 7; i++) {
			if(watchmen[i].getDay().equals(day)) {
				System.out.println(watchmen[i].getnickname()+" is "+watchmen[i].getStatus());
			}
		}
		//Printing the senior on that specific shift and the workers
		String h2 = hour.substring(0,2);
		int h = Integer.parseInt(h2);
		
		if(h >= 9 && h <=17){
			System.out.println(seniors[0].getHuman().getNickname().getName().toUpperCase()+" is "+seniors[0].getStatus());
			for(int i = 0; i < workers; i++) {
				people[i].checkWorkingHour(day,hour);
				if(!people[i].getStatus().equals("on a Free Day.")&&!people[i].getStatus().equals("Out of working hours.")) {
					System.out.println(people[i].getHuman().getNickname().getName());
				}
			}
		}else if(h >=12 && h <= 20){
			System.out.println(seniors[1].getHuman().getNickname().getName().toUpperCase()+" is "+seniors[1].getStatus());
			for(int i = 0; i < workers; i++) {
				people[i].checkWorkingHour(day,hour);
				if(!people[i].getStatus().equals("on a Free Day.")&&!people[i].getStatus().equals("Out of working hours.")) {
					System.out.println(people[i].getHuman().getNickname().getName());
				}
			}
		}else{
			System.out.println(seniors[2].getHuman().getNickname().getName().toUpperCase()+" is "+seniors[2].getStatus());
			for(int i = 0; i < workers; i++) {
				people[i].checkWorkingHour(day,hour);
				if(!people[i].getStatus().equals("on a Free Day.")&&!people[i].getStatus().equals("Out of working hours.")) {
					System.out.println(people[i].getHuman().getNickname().getName());
				}
			}
		}
		
		//Check what the workers that are working are doing
		String chk;
		System.out.println("Want to check over each of the workers who is working? Y/N");
		chk = in.nextLine();
		while(!chk.toLowerCase().equals("y") && !chk.toLowerCase().equals("n")) {
			System.out.println("Want to check over each of the workers who is working? Y/N");
			chk = in.nextLine();
		}
		if(chk.toLowerCase().equals("y")) {
			for(int i = 0; i < workers; i++) {
				people[i].checkWorkingHour(day,hour);
				if(!people[i].getStatus().equals("on a Free Day.")&&!people[i].getStatus().equals("Out of working hours.")) {
					System.out.println(people[i].getHuman().getNickname().getName()+" is "+people[i].getStatus());
				}
			}
		}else {
			
		}
		//Check over the free workers
		System.out.println("Want to check over the free workers? Y/N");
		chk = in.nextLine();
		while(!chk.toLowerCase().equals("y") && !chk.toLowerCase().equals("n")) {
			System.out.println("Want to check over the free workers? Y/N");
			chk = in.nextLine();
		}
		if(chk.toLowerCase().equals("y")) {
			for(int i = 0; i < workers; i++) {
				people[i].checkWorkingHour(day,hour);
				if(people[i].getStatus().equals("on a Free Day.")||people[i].getStatus().equals("Out of working hours.")) {
					System.out.println(people[i].getHuman().getNickname().getName()+" is "+people[i].getStatus());
				}
			}
		}else {
			
		}
//		for(int i = 0; i < workers; i++) {
//			people[i].checkWorkingHour(day,hour);
//			if(!people[i].getStatus().equals("on a Free Day.")&&!people[i].getStatus().equals("Out of working hours.")) {
//				System.out.println(people[i].getNickname()+" is "+people[i].getStatus());
//			}
//		}

	}

}
