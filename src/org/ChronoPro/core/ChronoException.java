package org.ChronoPro.core;


public class ChronoException extends Exception {

	public enum CodeException{
		CHRONO_ALREADY_STARTED("Chrono has been already Started"),
		CHRONO_ALREADY_PAUSED("Chrono has been already Paused"),
		CHRONO_NOT_PAUSED("Chrono is not paused"),
		CHRONO_NOT_STARTED("Chrono is not Started");
		
		final String message;
		
		private CodeException(String mess){
			this.message = mess;
		}
		
		public String toString(){
			return message;
		}
	}
	
	private static final long serialVersionUID = 3387606770592303093L;

	private final CodeException code;
	
	public ChronoException(CodeException code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CodeException getCode() {
		return code;
	}
	
	@Override
	public String getMessage(){
		return code.toString();
	}
	
	

}
