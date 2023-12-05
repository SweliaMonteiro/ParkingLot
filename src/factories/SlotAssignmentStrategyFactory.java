package factories;

import enums.SlotAssignmentStrategyType;
import strategies.slotAssignmentStrategies.AdjacentSlotAssignmentStrategy;
import strategies.slotAssignmentStrategies.NearestSlotAssignmentStrategy;
import strategies.slotAssignmentStrategies.RandomSlotAssignmentStrategy;
import strategies.slotAssignmentStrategies.SlotAssignmentStrategy;

public class SlotAssignmentStrategyFactory {
	
	public static SlotAssignmentStrategy getSlotAssignmentStrategy(SlotAssignmentStrategyType slotAssignmentStrategyType) {
		
		if(slotAssignmentStrategyType.equals(SlotAssignmentStrategyType.RANDOM)) {
			return new RandomSlotAssignmentStrategy();
		}
		
		else if(slotAssignmentStrategyType.equals(SlotAssignmentStrategyType.ADJACENT)) {
			return new AdjacentSlotAssignmentStrategy();
		}
		
		else if(slotAssignmentStrategyType.equals(SlotAssignmentStrategyType.NEAREST)) {
			return new NearestSlotAssignmentStrategy();
		}
		
		return null;
	}

}
