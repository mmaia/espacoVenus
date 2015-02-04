package br.com.espacovenus.view.paciente.recordatario;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;

@Named("calendarBean")
public class CalendarBean
{
	
	Logger logger = Logger.getLogger(CalendarBean.class.getName());
	
	
	private Date date1;
	private final static SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public CalendarBean()
	{
		
	}
	
	@PostConstruct
	protected void initData()
	{
		logger.info("CalendarBean.initData PostConstruct chamado");
		Faces.getFlash().put("dateText", FORMAT.format(new Date()));
	}
	
	public void handleDateSelect(SelectEvent event) {
		logger.info("Executing CalendarBean.handleDateSelect");
		Faces.getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Selecionada, Pesquisando...", FORMAT.format(event.getObject())));
		Faces.getFlash().put("dateText", FORMAT.format(event.getObject()));
		
		logger.info("Data Selecionada");
	}
	
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	
}