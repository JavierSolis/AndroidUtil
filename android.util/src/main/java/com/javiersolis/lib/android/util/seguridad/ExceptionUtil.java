package com.javiersolis.lib.android.util.seguridad;


public class ExceptionUtil extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Exception excepcion;
	public String mensajeUsuario;
	public String mensajeApp;

	public ExceptionUtil(Exception excepcion, String mensajeUsuario,
						 String mensajeApp) {
		super();
		
		this.excepcion = excepcion;
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeApp = mensajeApp;
	}

}
