package repositories;

import java.util.Map;
import java.util.TreeMap;

import exceptions.TicketNumberNotFound;
import models.Ticket;

public class TicketRepository {

	// Mocking the DB
	private Map<String, Ticket> ticketsMap = new TreeMap<String, Ticket>();


	public Ticket saveTicket(Ticket ticket) {
		// Save the new ticket details in the DB
		ticketsMap.put(ticket.getTicketNumber(), ticket);
		// Set the Id from the DB for the new Ticket object created
		ticket.setId(ticketsMap.get(ticket.getTicketNumber()).getId()); 
		return ticket;
	}


	public Ticket getTicketByTicketNumber(String ticketNumber) throws TicketNumberNotFound {
		// Get ticket details for the given ticket number from the DB if present in the DB, else throw exception
		if(ticketsMap.containsKey(ticketNumber)) {
			return ticketsMap.get(ticketNumber);
		}

		throw new TicketNumberNotFound("No Ticket found in the DB with the given Ticket number.");
	}

}
