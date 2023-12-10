package chainofresponsibility;

public interface Handler {
	
	public void setHandler(Handler handler);
	public void process(File file);//handlerRequest()
	public String getHandlerName();
}
