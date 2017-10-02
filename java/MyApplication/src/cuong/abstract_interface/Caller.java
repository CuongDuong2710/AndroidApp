package cuong.abstract_interface;

import javax.security.auth.callback.Callback;

public class Caller {
	public void register(CallBack callback) {
		callback.methodToCallBack();
	}
	
	public static void main(String[] args) {
		Caller caller = new Caller();
		
		CallBack callback = new CallBack() {
			
			@Override
			public void methodToCallBack() {
				System.out.println("Immediately callback");
			}
		};
		
		CallBack callBack2 = new CallBackImpl();
		
		caller.register(callback); // Immediately callback
		caller.register(callBack2); // I've been called back
		
		// init CallBackImpl
		CallBackImpl callBackImpl = new CallBackImpl();
		callBackImpl.methodToCallBack(); // I've been called back
	
	}
}
