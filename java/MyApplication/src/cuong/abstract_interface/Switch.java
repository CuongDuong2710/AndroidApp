package cuong.abstract_interface;

import java.util.ArrayList;
import java.util.List;

public class Switch {
	
	private List<Switchable> switchableItems = new ArrayList<>();
	
	// register callback. Why do not add 'Fan' or 'Light'? Because both implements 'Switchable' interface
	public void wireUp(Switchable thing) {
		switchableItems.add(thing);
	}
	
	// unregister callback
	public void unWire(Switchable thing) {
		switchableItems.remove(thing);
	}
	
	public void flipSwitchUp() {
		for(Switchable thing : switchableItems) {
			thing.turnOn(); // callbacks Interface
		}
	}
	
	public void flipSwitchDown() {
		for(Switchable thing : switchableItems) {
			thing.turnOff();
		}
	}
}
