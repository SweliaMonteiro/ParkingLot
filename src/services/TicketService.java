package services;

import java.util.Date;
import java.util.Optional;

import enums.SlotAssignmentStrategyType;
import enums.VehicleType;
import exceptions.GateNumberNotFound;
import exceptions.ParkingLotNotFound;
import factories.SlotAssignmentStrategyFactory;
import models.Gate;
import models.ParkingLot;
import models.ParkingSlot;
import models.Ticket;
import models.Vehicle;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.slotAssignmentStrategies.SlotAssignmentStrategy;

public class TicketService {

	private static int ticketNumber;

	static {
		ticketNumber = 1;
	}
	
	ParkingLotRepository parkingLotRepository;
	VehicleRepository vehicleRepository;
	GateRepository gateRepository;
	TicketRepository ticketRepository;

	public TicketService(ParkingLotRepository parkingLotRepository, VehicleRepository vehicleRepository, GateRepository gateRepository, TicketRepository ticketRepository) {
		this.parkingLotRepository = parkingLotRepository;
		this.vehicleRepository = vehicleRepository;
		this.gateRepository = gateRepository;
		this.ticketRepository = ticketRepository;
	}


	public Ticket issueTicket(String parkingLotName, String vehicleNumber, String vehicleOwnerName, VehicleType vehicleType, int gateNumber) throws GateNumberNotFound, ParkingLotNotFound {
		Ticket ticket = new Ticket();

		// Set vehicle for the ticket
		Optional<Vehicle> vehicleOptional = vehicleRepository.getVehicleByVehicleNumber(vehicleNumber);
		if(vehicleOptional.isEmpty()) {
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleNumber(vehicleNumber);
			vehicle.setOwnerName(vehicleOwnerName);
			vehicle.setVehicleType(vehicleType);
			ticket.setVehicle(vehicleRepository.saveVehicle(vehicle));
		}
		else {
			ticket.setVehicle(vehicleOptional.get());
		}

		// Set parking slot for the ticket
		ParkingLot parkingLot = parkingLotRepository.getParkingLotByParkingLotName(parkingLotName);
		SlotAssignmentStrategyType slotAssignmentStratergyType = parkingLot.getSlotAssignmentStrategyType();
		SlotAssignmentStrategy slotAssignmentStrategy = SlotAssignmentStrategyFactory.getSlotAssignmentStrategy(slotAssignmentStratergyType);
		ParkingSlot parkingSlot = slotAssignmentStrategy.getSlot(parkingLot, ticket.getVehicle());
		ticket.setParkingSlot(parkingSlot);

		// Set gate for the ticket
		Gate gate = gateRepository.getGateByGateNumber(gateNumber);
		ticket.setGeneratedAt(gate);

		// Set operator for the ticket
		ticket.setGeneratedBy(gate.getCurrentOperator());

		// Set entry time of the ticket
		ticket.setEntryTime(new Date());

		// Set ticket number for the ticket
		ticket.setTicketNumber("TICKET-" + ticketNumber);
		ticketNumber++;

		// Save the ticket in the DB, will have Id after saving in the DB
		ticket = ticketRepository.saveTicket(ticket);

		return ticket;
	}

}
