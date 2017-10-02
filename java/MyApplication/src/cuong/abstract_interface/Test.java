package cuong.abstract_interface;

public class Test {
	public static void main(String[] args) {
		Light light = new Light();
		Fan fan = new Fan();
		
		System.out.println(fan.deviceName);
		
//		fan.getDeviceName(); // FAN
//		light.getDeviceName(); // LIGHT
		
		fan.breakDevice(); // FAN BANG, CRASH!...oops.
		light.breakDevice(); // LIGHT Glass everywhere!
		
		Switch mySwitch = new Switch();
		
		// add fan and light
		mySwitch.wireUp(fan);
		mySwitch.wireUp(light);
		
		// FAN whooooooooooopsss.
		// LIGHT Now I can see my keys
		mySwitch.flipSwitchUp(); 
		
		// FAN darn, now it's hot.
		// LIGHT Oopss! It's so dark.
		mySwitch.flipSwitchDown();
		
		
		// remove fan
		mySwitch.unWire(fan);
		
//		mySwitch.flipSwitchUp();
//		mySwitch.flipSwitchDown();
		
		Switchable switchable = new Fan();
		// switchable. cannot call any function 'turnOn' or 'turnOff'
		
		// new itself
		Switchable switchable2 = new Switchable() {
			
			@Override
			public void turnOn() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void turnOff() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
