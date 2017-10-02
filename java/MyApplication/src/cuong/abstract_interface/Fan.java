package cuong.abstract_interface;

public class Fan extends Device implements Switchable {

	public Fan() {
		setDeviceName("FAN");
	}
	
	@Override
	public void breakDevice() {
		System.out.println(getDeviceName() + " BANG, CRASH!...oops.");
	}

	@Override
	public void turnOn() {
		System.out.println(getDeviceName() + " whooooooooooopsss.");
	}

	@Override
	public void turnOff() {
		System.out.println(getDeviceName() + " darn, now it's hot.");
	}
}
