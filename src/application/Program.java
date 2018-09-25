package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:ss");
		
		System.out.println("***Enter rental data**");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.println("Pickup (dd/mm/yy hh:ss): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.println("Return (dd/mm/yyyy hh:ss)");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.println("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println();
		System.out.println("***INVOICE***");
		System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));//print basic payment 
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));//print the tax
		System.out.println("Total Payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));//Print Total to to pay
		
		
		sc.close();


	}

}
