package proxy.gumball;

import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface GumballMachineRemote extends Remote {
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;
	//State가 넘어가야 함 => Serialization(직렬화)
}
