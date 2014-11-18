package shop.common.exception;

public class DAOException extends ApplicationException
{
	/**
	 *  构造一个将 null 作为其详细消息的新 throwable。
	 */
	public DAOException(){
		
	}
    
	/**
	 *  构造带指定详细消息的新 throwable。 
	 */
	public DAOException(String message){
		super(message);
	}
	/** 
	 * 构造一个带指定详细消息和 cause 的新 throwable。
	 */
	public DAOException(String message, Throwable cause){
		super(message,cause);
	}

	public DAOException( Throwable cause,String message){
		super(message,cause);
	}
		
	/** 
	 * 构造一个带指定 cause 和 (cause==null ? null :cause.toString())（它通常包含类和 cause 的详细消息）的详细消息的新 throwable。
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
