package cuong.abstract_interface;

public class Light extends Device implements Switchable{
	
	public Light() {
		setDeviceName("LIGHT");
	}

	@Override
	public void breakDevice() {
		System.out.println(getDeviceName() + " Glass everywhere!");
	}

	@Override
	public void turnOn() {
		System.out.println(getDeviceName() + " Now I can see my keys");
	}

	@Override
	public void turnOff() {
		System.out.println(getDeviceName() + " Oopss! It's so dark.");
	}
	
	

}
