package com.skilldistillery.foodtruck.app;

import java.util.Scanner;

import com.skilldistillery.foodtruck.entities.FoodTruck;

public class FoodTruckApp {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in).useDelimiter("\n");

		FoodTruckApp fta = new FoodTruckApp();

		FoodTruck[] trucks = fta.buildFoodTrucksArray(scanner, fta);

		fta.menuResponseAction(scanner, trucks, fta);

		scanner.close();
	}
	
	public FoodTruck[] buildFoodTrucksArray(Scanner scanner, FoodTruckApp fta) {
		FoodTruck[] trucks = new FoodTruck[5];
		int counter = 0;
		
		while (true) {
			trucks[counter] = fta.userNewFoodTruck(scanner);
			
			if (counter == 4 || trucks[counter] == null) {
				break;
			}
			counter++;
		}
		return trucks;
	}

	public FoodTruck userNewFoodTruck(Scanner scanner) {
		System.out.println("Enter the food truck name: ");
		String name = scanner.next();
		if (name.equals("quit")) {
			return null;
		}
		System.out.println("Enter the food truck food type: ");
		String foodType = scanner.next();
		System.out.println("Enter the food truck rating: ");
		int rating = scanner.nextInt();

		FoodTruck ft = new FoodTruck(name, foodType, rating);

		return ft;
	}

	public void menuResponseAction(Scanner scanner, FoodTruck[] trucks, FoodTruckApp fta) {
		boolean keepGoing = true;
		while (keepGoing == true) {
			switch (fta.showMenu(scanner)) {
			case "1":
				fta.showAllTrucks(trucks);
				break;
			case "2":
				System.out.println("\n" + "The average rating of all food trucks is: " + fta.averageRating(trucks) + "\n");
				break;
			case "3":
				System.out.println("\n" + "The highest rated truck is: " + fta.highestRated(trucks) + "\n");
				break;
			case "4":
				keepGoing = false;
				System.out.println("Dilly Dilly!");
				break;
			default:
				break;
			}
		}
	}
	
	public String showMenu(Scanner scanner) {
		System.out.println("1.) List all existing food trucks.");
		System.out.println("2.) See the average rating of food trucks.");
		System.out.println("3.) Display the highest-rated food truck.");
		System.out.println("4.) Quit the program.");

		return scanner.next();
	}

	public String averageRating(FoodTruck[] trucks) {
		int truckTotal = 0;
		int ratingTotal = 0;
		for (FoodTruck truck : trucks) {
			if (truck != null) {
				ratingTotal += truck.getRating();
				truckTotal++;
			}
		}
		double average = (double) ratingTotal / truckTotal;
		return String.format("%.2f", average);
	}

	public FoodTruck highestRated(FoodTruck[] trucks) {
		FoodTruck highestRatedTruck = null;
		int highestRating = Integer.MIN_VALUE;

		for (FoodTruck truck : trucks) {
			if (truck != null && truck.getRating() > highestRating) {
				highestRating = truck.getRating();
				highestRatedTruck = truck;
			}
		}
		return highestRatedTruck;
	}

	public void showAllTrucks(FoodTruck[] trucks) {
		System.out.println();
		for (FoodTruck truck : trucks) {
			if (truck != null) {
				System.out.println(truck.toString());
			}
		}
		System.out.println();
	}
}