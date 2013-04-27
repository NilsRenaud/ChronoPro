package org.ChronoPro.core;

import org.ChronoPro.core.ChronoException.CodeException;


public class Chrono {

	private long startTime;
	private long save;
	private boolean isInPause;
	private boolean isStarted;
	
	public Chrono(){
		startTime = 0;
		save = 0;
		isInPause = false;
		isStarted = false;
	}
	
	public void start() throws ChronoException{
		if(isStarted)
			throw new ChronoException(CodeException.CHRONO_ALREADY_STARTED);
		
		
		startTime = System.currentTimeMillis();
		save = 0;
		isStarted = true;
		isInPause = false;
	}
	
	public void stop() throws ChronoException{
		if(!isStarted)
			throw new ChronoException(CodeException.CHRONO_NOT_STARTED);
		if(!isInPause)
			save += System.currentTimeMillis() - startTime;
		
		isStarted = false;
		isInPause = false;
	}
	
	public void pause() throws ChronoException{
		if(isInPause)
			throw new ChronoException(CodeException.CHRONO_ALREADY_PAUSED);
		if(!isStarted)
			throw new ChronoException(CodeException.CHRONO_NOT_STARTED);
		
		save += System.currentTimeMillis() - startTime;
		isInPause = true;
	}
	
	public void resume() throws ChronoException{
		if(!isInPause)
			throw new ChronoException(CodeException.CHRONO_NOT_PAUSED);
		if(!isStarted)
			throw new ChronoException(CodeException.CHRONO_NOT_STARTED);
		
		startTime = System.currentTimeMillis();
		isInPause = false;
	}
	
	
	public ChronoFormat getTime(){
		return new ChronoFormat(getTimeInt());
	}
	
	private long getTimeInt(){
		if(!isStarted || isInPause)
			return save;
		
		return save + (System.currentTimeMillis() - startTime);
	}
	
}
