package shop.common.exception;

public class DAOException extends ApplicationException
{
	/**
	 *  ����һ���� null ��Ϊ����ϸ��Ϣ���� throwable��
	 */
	public DAOException(){
		
	}
    
	/**
	 *  �����ָ����ϸ��Ϣ���� throwable�� 
	 */
	public DAOException(String message){
		super(message);
	}
	/** 
	 * ����һ����ָ����ϸ��Ϣ�� cause ���� throwable��
	 */
	public DAOException(String message, Throwable cause){
		super(message,cause);
	}

	public DAOException( Throwable cause,String message){
		super(message,cause);
	}
		
	/** 
	 * ����һ����ָ�� cause �� (cause==null ? null :cause.toString())����ͨ��������� cause ����ϸ��Ϣ������ϸ��Ϣ���� throwable��
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
