import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import io.netty.channel.ChannelHandlerContext;

public class Core implements UserModifListeners {
	
	ArrayList<User>		lUser = new ArrayList<User>();
	private boolean 	_CountDown = false;
	private ArrayList<CoreListeners> 	_Listeners = new ArrayList<CoreListeners>();
	private Timer _Timer = new Timer();

	public Core() {

	}
	
	public void Run() throws Exception {
	
	}

	@Override
	public void addUser(ChannelHandlerContext ctx) {
		myPrint("User " + ctx.channel().remoteAddress() + " added");
		lUser.add(new User(ctx));
	}

	@Override
	public void removeUser(ChannelHandlerContext ctx) {
		for (Iterator<User> it = lUser.listIterator(); it.hasNext(); ) {
			User a = it.next();
			if (a.getConnection().channel().remoteAddress() == ctx.channel().remoteAddress()) {
				myPrint("User " + a.getConnection().channel().remoteAddress() + " removed");
		        it.remove();
		    }
		}
		if (_CountDown == true)
			stopCountDown();
	}
	
	private void checkReady() {
		int count = 0;
		for (Iterator<User> it = lUser.listIterator(); it.hasNext(); ) {
			User a = it.next();
			if (a.isReady() == true) {
		    	count = count + 1;
		    }
		}
		if (count > 1 && count == lUser.size())
			startCountDown();
	}
	
	private void StartTimer() {
		_Timer.schedule(new TimerTask() {
			int x = 0;  
			@Override
			  public void run() {
				if (x == 4) {
					_Timer.cancel();
					_Timer.purge(); 
				}
				myPrint("hey");
				x = x + 1;
			}
			}, 0, 1*1*1000);
	}
	
	private void startCountDown() {
		for (CoreListeners e : _Listeners) {
			e.startCountDown();
		}
		_Timer = new Timer();
		StartTimer();
		_CountDown = true;
	}
	
	private void stopCountDown() {
		if (_CountDown == false)
			return ;
		for (CoreListeners e : _Listeners) {
			e.stopCountDown();
		}
		_Timer.cancel();
		_Timer.purge();
		_CountDown = false;
	}

	public void addListeners(CoreListeners e) {
		_Listeners.add(e);
	}
	
	private void myPrint(String str) {
		System.out.println("[CORE] : " +  str);
	}

	@Override
	public void Ready(ChannelHandlerContext ctx) {
		for (Iterator<User> it = lUser.listIterator(); it.hasNext(); ) {
		    User a = it.next();
			if (a.getConnection().channel().remoteAddress() == ctx.channel().remoteAddress()) {
		    	a.setReady(true);
		    	myPrint(ctx.channel().remoteAddress() + " ready");
		    }
		}
		checkReady();
	}

	@Override
	public void CancelReady(ChannelHandlerContext ctx) {
		for (Iterator<User> it = lUser.listIterator(); it.hasNext(); ) {
		    User a = it.next();
			if (a.getConnection().channel().remoteAddress() == ctx.channel().remoteAddress()) {
		    	a.setReady(false);
		    	myPrint(ctx.channel().remoteAddress() + " cancel");
		    }
		}
		stopCountDown();
	}
}
